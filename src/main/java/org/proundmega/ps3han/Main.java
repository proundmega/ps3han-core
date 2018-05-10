package org.proundmega.ps3han;

import org.proundmega.ps3han.core.Entry;
import org.proundmega.ps3han.core.EntryParser;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "C:\\Users\\sakshi\\Desktop\\ps3 tools\\psnstuff_ver_03.07.08-Cyb3r\\database";
        List<Entry> entries = EntryParser.parseEntries(filename);
    }
    
}
