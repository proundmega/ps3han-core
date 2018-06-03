package org.proundmega.ps3han.rap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.proundmega.ps3han.core.UserData;

@AllArgsConstructor
public abstract class AbstractSigner implements Signer {
    protected UserData userData;

    private static final String PKG_ID = "RIF000-INSTALLER_00-0000000000000000";
    private static final String PKG_NAME = PKG_ID + ".pkg";
    private static final String PKG_NAME_SIGNED = PKG_ID + ".pkg_signed.pkg";

    protected abstract List<String> getFilenamesToCopyToBin();

    protected abstract String getSignerCommand();

    protected abstract List<String> getPackerCommand();

    @Override
    public void copySigner(String origin) {
        try {
            List<String> filesToCopyToBin = getFilenamesToCopyToBin();
            File binDirectory = new File(userData.getBinWorkingDirectory());
            
            if(origin == null) {
                origin = "bin";
            }
            
            for (String fileNameToCopy : filesToCopyToBin) {
                File binFile = new File(origin + File.separator + fileNameToCopy);
                Files.copy(binFile.toPath(), binDirectory.toPath().resolve(fileNameToCopy));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void signAllRaps() {
        try {
            String signerCommand = new File(userData.getBinWorkingDirectory() + File.separator + getSignerCommand()).getAbsolutePath();
            File fileWorkingDirectory = new File(userData.getRiffWorkingDirectory());
            File binDirectory = new File(userData.getBinWorkingDirectory());

            Process process = createSignerProcess(signerCommand, fileWorkingDirectory, binDirectory);
            int exitCode = process.exitValue();
            if (exitCode != 0) {
                throw new InstantiationException("Could not sign data, please check logs");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Process createSignerProcess(String command, File riffWorkingDirectory, File binDirectory) throws InterruptedException, IOException {
        File outputSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "outputSigning.log");
        File errorSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "errorSigning.log");

        ProcessBuilder builder = new ProcessBuilder(command, "batchrap", riffWorkingDirectory.getAbsolutePath());
        builder.directory(binDirectory.getAbsoluteFile());
        Process process = builder.start();
        FileUtils.copyInputStreamToFile(process.getInputStream(), outputSigning);
        FileUtils.copyInputStreamToFile(process.getErrorStream(), errorSigning);
        process.waitFor();
        return process;
    }

    @Override
    public void deleteSigner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String packRiff() {
        try {
            createPkgFromRiff();
            signPkg();
            copySignedPKGToRelease();
            return userData.getReleaseWorkingDirectory() + File.separator + PKG_NAME_SIGNED;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void createPkgFromRiff() throws InterruptedException, IOException, InstantiationException {
        List<String> command = getPackerCommand();
        File riffDir = new File(userData.getRiffWorkingDirectory()).getAbsoluteFile();
        File binDir = new File(userData.getBinWorkingDirectory());
        Process process = createProcessForPacking(command, riffDir, binDir);
        if (process.exitValue() != 0) {
            throw new InstantiationException("Couldn't create pkg for riff");
        }
    }

    // it is assumed that for both platforms a python script will be executed
    private Process createProcessForPacking(List<String> packerCommand, File riffDir, File binDirectory) throws InterruptedException, IOException {
        File outputSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "packer.log");
        File errorSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "errorPacker.log");
        
        packerCommand.add("--contentid");
        packerCommand.add(PKG_ID);
        packerCommand.add(riffDir.getPath());
        ProcessBuilder builder = new ProcessBuilder(packerCommand.toArray(new String[10]));
        
        builder.redirectOutput(ProcessBuilder.Redirect.appendTo(outputSigning));
        builder.redirectError(ProcessBuilder.Redirect.appendTo(errorSigning));
        builder.directory(binDirectory.getAbsoluteFile());
        Process process = builder.start();
        process.waitFor();
        return process;

    }

    private void signPkg() throws IOException, InstantiationException, InterruptedException {
        File binDir = new File(userData.getBinWorkingDirectory());
        String signerCommand = new File(userData.getBinWorkingDirectory() + File.separator + getSignerCommand()).getAbsolutePath();
        final File pkgUnsigned = new File(userData.getBinWorkingDirectory() + File.separator + PKG_NAME);
        Process signer = createSignerProcessForPkg(signerCommand, pkgUnsigned, binDir);

        if (signer.exitValue() != 0) {
            throw new InstantiationException("Couldn't sign pkg");
        }
    }

    private Process createSignerProcessForPkg(String command, File fileToSign, File binDirectory) throws InterruptedException, IOException {
        File outputSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "outputSigning.log");
        File errorSigning = new File(userData.getLogForWorkingDirectory() + File.separator + "errorSigning.log");

        ProcessBuilder builder = new ProcessBuilder(command,
                 fileToSign.getAbsolutePath(),
                 "ps3");
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);
        builder.directory(binDirectory.getAbsoluteFile());
        Process process = builder.start();
        process.waitFor();
        return process;
    }

    private void copySignedPKGToRelease() throws IOException {
        File releaseDir = new File(userData.getReleaseWorkingDirectory());
        File pkgSigned = new File(userData.getBinWorkingDirectory() + File.separator + PKG_NAME_SIGNED);
        Files.copy(pkgSigned.toPath(), releaseDir.toPath().resolve(PKG_NAME_SIGNED), StandardCopyOption.REPLACE_EXISTING);
    }

}
