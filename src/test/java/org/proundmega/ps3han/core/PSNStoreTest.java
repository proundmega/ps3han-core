package org.proundmega.ps3han.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import static org.proundmega.ps3han.TestConstants.*;
import org.proundmega.ps3han.TestUtils;

public class PSNStoreTest {
    
    @Test
    public void shouldCreateEmptyRapsDirectory() throws FileNotFoundException {
        UserData userData = getRandomUserData();
        PSNStore store = new PSNStore(TestUtils.getTestResource(TEST_DATABASE_2).getAbsolutePath(), userData);
        store.storeRapsAsPKG();
        assertTrue(new File(userData.getRiffWorkingDirectory()).exists());
    }
    
    @Test
    @Ignore
    public void shouldCreateEmptyRapsDirectory2() throws FileNotFoundException {
        PSNStore store = new PSNStore(TestUtils.getTestResource(DEFAULT_DATABASE).getAbsolutePath(), getRandomUserData());
        store.storeRapsAsPKG();
        assertTrue(new File(RAPS_OUTPUT_DIRECTORY).exists());
    }
    
    @Test
    @Ignore
    public void createStore() throws FileNotFoundException {
        PSNStore store = new PSNStore(TestUtils.getTestResource(DEFAULT_DATABASE).getAbsolutePath(), getRandomUserData());
        store.createDatabaseToPs3();
    }
    
    @Test
    public void testParallelRapFactory() throws FileNotFoundException {
        new File(TEST_OUTPUT_DIRECTORY).mkdir();
        PSNStore store = new PSNStore(TestUtils.getTestResource(TEST_DATABASE_2).getAbsolutePath(), getRandomUserData());
        List<String> finalArtifacts = store.storeRapsAsPkgParallel();
        for(String fileName: finalArtifacts) {
            new File(fileName).exists();
        }
        assertEquals(Runtime.getRuntime().availableProcessors(), finalArtifacts.size());
    }
}
