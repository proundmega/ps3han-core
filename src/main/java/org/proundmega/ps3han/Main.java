package org.proundmega.ps3han;

import java.io.FileNotFoundException;
import java.util.List;
import org.proundmega.ps3han.core.PSNStore;
import org.proundmega.ps3han.core.UserData;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String base = "/home/vansi/NetBeansProjects/ps3han-core/src/test/resources";
        String filename = base + "/database";
        
        UserData data = new UserData();
        data.setWorkDirectory("/home/vansi/Escritorio/ps3/");
        data.setActDatLocation(base + "/act.dat");
        data.setIdpsHexLocation(base + "/idps.hex");
        
        PSNStore store = new PSNStore(filename, data);
        
        List<String> finalArtifacts = store.storeRapsAsPkgParallel();
        for(String file: finalArtifacts) {
            System.out.println("Filename: " + file);
        }
        
        //        System.out.println(store.storeRapsAsPKG());

    }
    
}
