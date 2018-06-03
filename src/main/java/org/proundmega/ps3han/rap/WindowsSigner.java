package org.proundmega.ps3han.rap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.proundmega.ps3han.core.UserData;

public class WindowsSigner extends AbstractSigner {
    private static final String BINARY_NAME = "ps3xploit_rifgen_edatresign.exe";
    private static final String LIBRARY_NAME = "cygwin1.dll";
    private static final String PACKER_NAME = "pkg_exdata.exe";
    
    public WindowsSigner(UserData userData) {
        super(userData);
    }
    
    @Override
    protected List<String> getFilenamesToCopyToBin() {
        List<String> files = new ArrayList<>();
        files.add(BINARY_NAME);
        files.add(LIBRARY_NAME);
        files.add(PACKER_NAME);
        
        return files;
    }

    @Override
    protected String getSignerCommand() {
        return BINARY_NAME;
    }

    @Override
    // TODO falta arreglar el packer de raps en Windows, NO FUNCIONA
    protected List<String> getPackerCommand() {
        return Arrays.asList(PACKER_NAME);
    }

}
