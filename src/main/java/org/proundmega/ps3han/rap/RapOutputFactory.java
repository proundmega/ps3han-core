package org.proundmega.ps3han.rap;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.proundmega.ps3han.core.Entry;

public class RapOutputFactory {
    private List<Entry> entries;

    public RapOutputFactory(List<Entry> entries) {
        this.entries = entries;
    }
    
    public void storeRapsOnDirectory(String outputDirectory) throws IOException {
        for (Entry entry : entries) {
            File rapOutputFile = new File(outputDirectory + File.separator + entry.getRapName());
            byte[] actual = RapCreator.rapContentToBinary(entry.getRapValue());
            rapOutputFile.createNewFile();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(rapOutputFile));
            dataOutputStream.write(actual);
        }
    }
}
