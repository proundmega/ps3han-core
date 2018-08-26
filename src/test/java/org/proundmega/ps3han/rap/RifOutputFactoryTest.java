package org.proundmega.ps3han.rap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.proundmega.ps3han.SelfCleaningDirectorie;
import static org.proundmega.ps3han.TestConstants.*;
import org.proundmega.ps3han.TestUtils;
import org.proundmega.ps3han.core.Entry;
import org.proundmega.ps3han.core.EntryParser;
import org.proundmega.ps3han.core.UserData;

public class RifOutputFactoryTest extends SelfCleaningDirectorie {

    @Test
    @Ignore
    public void copyRapsCorrectly() throws FileNotFoundException, IOException {
        List<Entry> entries = EntryParser.parseEntries(TestUtils.getTestResource(TEST_DATABASE_1).getAbsolutePath());
        RapOutputFactory rapsFactory = new RapOutputFactory(entries);
        rapsFactory.storeRapsOnDirectory(getUserData().getRapsWorkingDirectory());
        RifOutputFactory factory = new RifOutputFactory(getUserData());
        factory.createRiffs();
        
        UserData userData = getUserData();
        File riffDirectory = new File(userData.getRiffWorkingDirectory());
        File[] riffs = riffDirectory.listFiles();
        assertEquals(2, riffs.length);
    }
    
    @Test
    @Ignore
    public void logFilesCreatedProperly() throws FileNotFoundException, IOException {
        List<Entry> entries = EntryParser.parseEntries(TestUtils.getTestResource(TEST_DATABASE_1).getAbsolutePath());
        RapOutputFactory rapsFactory = new RapOutputFactory(entries);
        rapsFactory.storeRapsOnDirectory(getUserData().getRapsWorkingDirectory());
        RifOutputFactory factory = new RifOutputFactory(getUserData());
        factory.createRiffs();
        
        UserData userData = getUserData();
        File logs = new File(userData.getLogForWorkingDirectory());
        assertEquals(2, logs.list().length);
    }

}
