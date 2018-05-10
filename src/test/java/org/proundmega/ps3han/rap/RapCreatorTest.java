package org.proundmega.ps3han.rap;

import org.proundmega.ps3han.rap.RapCreator;
import org.proundmega.ps3han.core.EntryParser;
import org.proundmega.ps3han.core.Entry;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import org.junit.Test;
import static org.junit.Assert.*;

public class RapCreatorTest {
    
    private void writeTestRap(String baseEntry, String test) throws IOException, FileNotFoundException {
        Entry entry = EntryParser.getEntry(baseEntry);
        byte[] actual = RapCreator.rapContentToBinary(entry.getRapValue());
        File create = new File(test);
        create.createNewFile();
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(create));
        dataOutputStream.write(actual);
    }

    @Test
    public void signRapCorrectly() throws IOException {
        String url = "NPUB30033;Age of Booty;PSN;US;http://zeus.dl.playstation.net/cdn/UP0102/NPUB30033_00/BPVqlh37oDuBNXOyXTs1ffpgEqHWAglJg43Sst3a95DufJ6rikpDdjO4Rj0T6MlDsb3mRu39WSJ2S7KiHYcKo7iY179aF6bhUuPj3.pkg;UP0102-NPUB30033_00-AGEOFBOOTYGAME00.rap;15D37CAA09B66ADA4785954B3F3F9310;;HeihachiMishima";
        Entry entry = EntryParser.getEntry(url);
        
        writeTestRap(url, "target" + File.separator + entry.getRapName());
    }
    
}
