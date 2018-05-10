package org.proundmega.ps3han.rap;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import org.proundmega.ps3han.core.UserData;

@AllArgsConstructor
public class WindowsSigner implements Signer {
    private UserData userData;

    private static final String BINARY_NAME = "ps3xploit_rifgen_edatresign.exe";
    private static final String LIBRARY_NAME = "cygwin1.dll";
    private static final String PACKER_NAME = "pkg_exdata.exe";
    private static final String BINARY_LOCATION = "bin" + File.separator + BINARY_NAME;
    private static final String LIBRARY_LOCATION = "bin" + File.separator + LIBRARY_NAME;
    private static final String PACKER_LOCATION = "bin" + File.separator + PACKER_NAME;
    
    private static final String PKG_NAME = "RIF000-INSTALLER_00-0000000000000000";
    
    @Override
    public void copySigner() {
        try {
            File signer = new File(BINARY_LOCATION);
            File cygwinLib = new File(LIBRARY_LOCATION);
            File pkgPacker = new File(PACKER_LOCATION);
            File riffDestinyDirectory = new File(userData.getBinWorkingDirectory());
            
            Files.copy(signer.toPath(), riffDestinyDirectory.toPath().resolve(BINARY_NAME));
            Files.copy(cygwinLib.toPath(), riffDestinyDirectory.toPath().resolve(LIBRARY_NAME));
            Files.copy(pkgPacker.toPath(), riffDestinyDirectory.toPath().resolve(PACKER_NAME));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void signAllRaps() {
        try {
            File signer = new File(BINARY_LOCATION);
            File fileWorkingDirectory = new File(userData.getRiffWorkingDirectory());
            File binDirectory = new File(userData.getBinWorkingDirectory());
            
            for (File rap : fileWorkingDirectory.listFiles()) {
                Process process = createRiffSignerProcess(signer, rap, binDirectory);
                int exitCode = process.exitValue();
                if (exitCode != 0) {
                    throw new InstantiationException("Could not sign rap file: " + rap.getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Process createRiffSignerProcess(File signer, File rap, File binDirectory) throws InterruptedException, IOException {
        File outputSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "outputSigning.log");
        File errorSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "errorSigning.log");
        
        ProcessBuilder builder = new ProcessBuilder(signer.getAbsolutePath(), rap.getAbsolutePath());
        builder.redirectOutput(Redirect.appendTo(outputSigning));
        builder.redirectError(Redirect.appendTo(errorSigning));
        builder.directory(binDirectory.getAbsoluteFile());
        Process process = builder.start();
        process.waitFor();
        return process;
    }

    @Override
    public void deleteSigner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void packRiff() {
        try {
            File packer = new File(PACKER_LOCATION);
            File riffDir = new File(userData.getRiffWorkingDirectory());
            File binDir = new File(userData.getBinWorkingDirectory());
            Process process = createProcessForPacking(packer, riffDir, binDir);
            
            if(process.exitValue() != 0) {
                throw new InstantiationException("Couldn't create pkg for riff");
            }
            
        } catch (InterruptedException | IOException | InstantiationException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private Process createProcessForPacking(File packer, File riffDir, File binDirectory) throws InterruptedException, IOException {
        File outputSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "packer.log");
        File errorSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "errorPacker.log");
        File pkgName = new File(userData.getRiffWorkingDirectory() + File.separator + PKG_NAME);
        
        ProcessBuilder builder = new ProcessBuilder(packer.getAbsolutePath()
                , "--contentid"
                , pkgName.getAbsolutePath()
                , riffDir.getAbsolutePath());
        builder.redirectOutput(ProcessBuilder.Redirect.appendTo(outputSigning));
        builder.redirectError(ProcessBuilder.Redirect.appendTo(errorSigning));
        builder.directory(binDirectory.getAbsoluteFile());
        Process process = builder.start();
        process.waitFor();
        return process;
    }

}
