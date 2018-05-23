package org.proundmega.ps3han;

import org.proundmega.ps3han.core.Entry;
import org.proundmega.ps3han.core.EntryParser;
import java.io.FileNotFoundException;
import java.util.List;
import org.proundmega.ps3han.core.PSNStore;
import org.proundmega.ps3han.core.UserData;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "/home/vansi/Escritorio/ps3/database";
        
        UserData data = new UserData();
        data.setWorkDirectory("/home/vansi/Escritorio/ps3/test");
        data.setActDatLocation("/home/vansi/Escritorio/ps3/act.dat");
        data.setIdpsHexLocation("/home/vansi/Escritorio/ps3/idps.hex");
        
        PSNStore store = new PSNStore(filename, data, null);
        
        store.storeRapsAsPKG();
    }
    
}
