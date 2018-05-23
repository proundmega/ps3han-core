package org.proundmega.ps3han.rap;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import org.proundmega.ps3han.core.UserData;

@AllArgsConstructor
public class WindowsSigner extends AbstractSigner {
    private UserData userData;

    private static final String BINARY_NAME = "ps3xploit_rifgen_edatresign.exe";
    private static final String LIBRARY_NAME = "cygwin1.dll";
    private static final String PACKER_NAME = "pkg_exdata.exe";
    
    @Override
    protected List<String> getFilenamesToCopyToBin() {
        List<String> files = new ArrayList<String>();
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
    protected String getPackerCommand() {
        return PACKER_NAME;
    }

}
