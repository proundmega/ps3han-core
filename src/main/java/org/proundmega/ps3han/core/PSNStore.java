package org.proundmega.ps3han.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.proundmega.ps3han.rap.Plataform;
import org.proundmega.ps3han.rap.RapOutputFactory;
import org.proundmega.ps3han.rap.RifOutputFactory;

@AllArgsConstructor
public class PSNStore {
    private List<Entry> entries;
    private UserData userData;
    private String binDir;

    public PSNStore(List<Entry> entries) {
        this.entries = entries;
    }
    
    public PSNStore(String fileUrl, UserData userData, String binDir) throws FileNotFoundException {
        this(EntryParser.parseEntries(fileUrl), userData, binDir);
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
            return Plataform.createSigner(userData).packRiff();
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
        RifOutputFactory factory = new RifOutputFactory(userData, binDir);
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

}
