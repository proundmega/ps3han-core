package org.proundmega.ps3han.core;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.proundmega.ps3han.TestConstants.*;

public class UserDataTest {
    
    @Test
    public void subDataFolderFor() {
        final String subWorkDir = "1";
        UserData userData = getUserData();
        UserData subUserData = userData.createSubWorkDir(subWorkDir);
        
        assertEquals(userData.getWorkDirectory() + File.separator + subWorkDir, subUserData.getWorkDirectory());
        assertEquals(userData.getActDatLocation(), subUserData.getActDatLocation());
        assertEquals(userData.getIdpsHexLocation(), subUserData.getIdpsHexLocation());
    }
}
