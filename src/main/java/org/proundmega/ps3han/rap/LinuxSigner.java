package org.proundmega.ps3han.rap;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.proundmega.ps3han.core.UserData;

public class LinuxSigner extends AbstractSigner {

    public LinuxSigner(UserData userData) {
        super(userData);
    }

    @Override
    protected List<String> getFilenamesToCopyToBin() {
        List<String> binFiles = new ArrayList<>();
        binFiles.add("ps3xploit_rifgen_edatresign");
        binFiles.add("pkg.py");
        binFiles.add("pkgcrypt.so");
        
        return binFiles;
    }

    @Override
    protected String getSignerCommand() {
        return "ps3xploit_rifgen_edatresign";
    }

    @Override
    protected List<String> getPackerCommand() {
        return Arrays.asList("python", "pkg.py");
    }
    
}
