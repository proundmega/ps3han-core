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
    @Ignore
    public void shouldCreateEmptyRapsDirectory() throws FileNotFoundException {
        PSNStore store = PSNStore.of(TestUtils.getTestResource(TEST_DATABASE_2).getAbsolutePath(), getUserData());
        store.storeRapsAsPKG();
        assertTrue(new File(RAPS_OUTPUT_DIRECTORY).exists());
    }
    
    @Test
    public void shouldCreateEmptyRapsDirectory2() throws FileNotFoundException {
        PSNStore store = PSNStore.of(TestUtils.getTestResource(DEFAULT_DATABASE).getAbsolutePath(), getUserData());
        store.storeRapsAsPKG();
        assertTrue(new File(RAPS_OUTPUT_DIRECTORY).exists());
    }
}
