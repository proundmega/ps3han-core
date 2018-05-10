package org.proundmega.ps3han;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import static org.proundmega.ps3han.TestConstants.*;

public class SelfCleaningDirectorie {
    
    @Before
    public void createCleanDirectory() {
        try {
            getUserData().createDirectories();
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(SelfCleaningDirectorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
