package org.proundmega.ps3han.core;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.proundmega.ps3han.rap.Platform;
import org.proundmega.ps3han.rap.RapOutputFactory;
import org.proundmega.ps3han.rap.RifOutputFactory;
import org.proundmega.ps3han.xmb.store.GameStoreCreator;

public class PSNStore {
    private List<Entry> entries;
    private UserData userData;

    public PSNStore(List<Entry> entries) {
        this.entries = entries;
    }

    public PSNStore(List<Entry> entries, UserData userData) {
        this.entries = entries;
        this.userData = userData;
    }
    
    public PSNStore(String fileUrl, UserData userData) throws FileNotFoundException {
        this(EntryParser.parseEntries(fileUrl), userData);
    } 
    
    public PSNStore filterByRegion(Region... region) {
        if(region.length == 0) {
            throw new IllegalArgumentException("You need to include at least one region");
        }
        
        List<Region> filteredRegions = Arrays.asList(region);
        List<Entry> filtered = entries.stream()
                .filter(entry -> filteredRegions.contains(entry.getRegion()))
                .collect(Collectors.toList());
        
        return new PSNStore(filtered);
    }
    
    public List<Entry> getEntries() {
        return entries;
    }
    
    public List<Entry> getEntriesWithRap() {
        return entries.stream()
                .filter(Entry::hasRap)
                .collect(Collectors.toList());
    }
    
    public String storeRapsAsPKG() {
        try {
            userData.createDirectories();
            userData.copyUserFilesToBin();
            createRaps();
            createRiffs();
            return Platform.createSigner(userData).packRiff();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void createRaps() throws IOException {
        String rapsDirectory = userData.getRapsWorkingDirectory();
        List<Entry> entriesWithRap = getEntriesWithRap();
        RapOutputFactory factory = new RapOutputFactory(entriesWithRap);
        factory.storeRapsOnDirectory(rapsDirectory);
    }
    
    private void createRiffs() throws IOException {
        RifOutputFactory factory = new RifOutputFactory(userData);
        factory.createRiffs();
        copySignedActDatToRiffDir();
    }

    private void copySignedActDatToRiffDir() throws IOException {
        File actDatSigned = new File(userData.getBinWorkingDirectory() + File.separator + "signed_act.dat");
        File riffDirectory = new File(userData.getRiffWorkingDirectory());
        Files.copy(actDatSigned.toPath(), riffDirectory.toPath().resolve("act.dat"));
    }
    
    public String storeRapsAsPkgAndDeleteIntermediateFiles() {
        String result = storeRapsAsPKG();
        userData.deleteTemporalFiles();
        
        return result;
    }
    
    public void createDatabaseToPs3() {
        userData.createDirectories();
        List<Entry> entriesNoPs4 = getEntriesWithRap().stream()
                .filter(entry -> !entry.getType().equals(Type.PS4))
                .collect(Collectors.toList());
        GameStoreCreator store = new GameStoreCreator(entriesNoPs4, userData);
        store.convertToXmb();
    }
    
    public List<String> storeRapsAsPkgParallel() {
        try {
            userData.createDirectories();
            userData.copyUserFilesToBin();
            int nThreads = Runtime.getRuntime().availableProcessors();
            List<PSNStore> stores = partitionCurrentStoreIntoMultipleStores(nThreads);
            List<String> results = proceedToCreateRaps(stores);
            
            return results;
        } catch (InterruptedException | ExecutionException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<String> proceedToCreateRaps(List<PSNStore> stores) throws InterruptedException, ExecutionException {
        List<Callable<String>> works = stores.stream()
                .map(this::execSignRap)
                .collect(Collectors.toList());
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<String>> futures = service.invokeAll(works);
        
        List<String> results = new ArrayList<>();
        for(Future<String> future: futures) {
            try {
                results.add(future.get());
            } finally {
                service.shutdown();
            }
        }
        
        return results;
    }
    
    private Callable<String> execSignRap(PSNStore store) {
        return () -> store.storeRapsAsPKG();
    }

    private List<PSNStore> partitionCurrentStoreIntoMultipleStores(int nThreads) {
        List<List<Entry>> entryPartitions = Lists.partition(entries, entries.size()/nThreads + 1).stream()
                .filter(entries -> entries != null && !entries.isEmpty())
                .collect(Collectors.toList());
        List<PSNStore> stores = new ArrayList<>();
        for(int nCore = 0; nCore < entryPartitions.size(); nCore++) {
            List<Entry> partition = entryPartitions.get(nCore);
            PSNStore store = new PSNStore(partition, userData.createSubWorkDir(String.valueOf(nCore)));
            stores.add(store);
        }
        return stores;
    }

}
