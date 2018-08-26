package org.proundmega.ps3han.rap;

import org.apache.commons.lang3.SystemUtils;
import org.proundmega.ps3han.core.UserData;

public class Platform {
    
    public static Signer createSigner(UserData userData) {
        if(SystemUtils.IS_OS_WINDOWS) {
            return new WindowsSigner(userData);
        }
        else if(SystemUtils.IS_OS_LINUX) {
            return new LinuxSigner(userData);
        }
        else {
            throw new UnsupportedClassVersionError("OS not supported yet");
        }
    }
}
