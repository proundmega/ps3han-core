package org.proundmega.ps3han;

import java.io.File;
import org.proundmega.ps3han.core.UserData;

public class TestConstants {
    public static final String TEST_OUTPUT_DIRECTORY = "target" + File.separator + "output";
    public static final String RAPS_OUTPUT_DIRECTORY = TEST_OUTPUT_DIRECTORY + File.separator + "raps";
    
    // databases
    public static final String DEFAULT_DATABASE = "database"; 
    public static final String TEST_DATABASE_1 = "testDatabase1";
    public static final String TEST_DATABASE_2 = "testDatabase2";
    
    public static UserData getUserData() {
        UserData userData = new UserData();
        userData.setWorkDirectory(TEST_OUTPUT_DIRECTORY);
        userData.setActDatLocation(TestUtils.getTestResource("act.dat").toString());
        userData.setIdpsHexLocation(TestUtils.getTestResource("idps.hex").toString());
        
        return userData;
    }
}
