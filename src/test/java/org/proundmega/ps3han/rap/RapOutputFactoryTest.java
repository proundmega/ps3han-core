package org.proundmega.ps3han.rap;

import org.proundmega.ps3han.rap.RapOutputFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.proundmega.ps3han.SelfCleaningDirectorie;
import org.proundmega.ps3han.TestUtils;
import org.proundmega.ps3han.core.Entry;
import org.proundmega.ps3han.core.EntryParser;
import static org.proundmega.ps3han.TestConstants.*;

public class RapOutputFactoryTest extends SelfCleaningDirectorie {
    
    @Test
    public void writeTestRaps() throws FileNotFoundException, IOException {
        File testResource = TestUtils.getTestResource(TEST_DATABASE_1);
        List<Entry> parseEntriesWithRap = EntryParser.parseEntries(testResource.getAbsolutePath());
        
        RapOutputFactory factory = new RapOutputFactory(parseEntriesWithRap);
        factory.storeRapsOnDirectory(RAPS_OUTPUT_DIRECTORY);
        
        List<String> expectedWrittenRaps = getTestRapNames();
        
        for(String rap : expectedWrittenRaps) {
            assertTrue(new File(RAPS_OUTPUT_DIRECTORY + File.separator + rap).exists());
        }
    }
    
    private List<String> getTestRapNames() {
        List<String> rapNameList = new ArrayList<>();
        rapNameList.add("UP0002-BLUS30755_00-BOND2011LARGDLC2.rap");
        rapNameList.add("UP0002-BLUS30755_00-BOND2011GALODLC3.rap");
        
        return rapNameList;
    }
    
}
