package org.proundmega.ps3han.core;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import static org.proundmega.ps3han.TestConstants.*;
import org.proundmega.ps3han.TestUtils;

public class PSNStoreTest {
    
    @Test
    public void shouldCreateEmptyRapsDirectory() throws FileNotFoundException {
        PSNStore store = new PSNStore(TestUtils.getTestResource(TEST_DATABASE_2).getAbsolutePath(), getUserData(), null);
        store.storeRapsAsPKG();
        assertTrue(new File(RAPS_OUTPUT_DIRECTORY).exists());
    }
    
    @Test
    @Ignore
    public void shouldCreateEmptyRapsDirectory2() throws FileNotFoundException {
        PSNStore store = new PSNStore(TestUtils.getTestResource(DEFAULT_DATABASE).getAbsolutePath(), getUserData(), null);
        store.storeRapsAsPKG();
        assertTrue(new File(RAPS_OUTPUT_DIRECTORY).exists());
    }
    
    @Test
    @Ignore
    public void createStore() throws FileNotFoundException {
        PSNStore store = new PSNStore(TestUtils.getTestResource(DEFAULT_DATABASE).getAbsolutePath(), getUserData(), null);
        store.createDatabaseToPs3();
    }
}
