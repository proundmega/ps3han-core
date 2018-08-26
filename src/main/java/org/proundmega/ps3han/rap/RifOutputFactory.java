package org.proundmega.ps3han.rap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.proundmega.ps3han.core.UserData;

public class RifOutputFactory {
    private UserData userData;

    public RifOutputFactory(UserData userData) {
        this.userData = userData;
    }
    
    public void createRiffs() throws IOException {
        copyRafsToRifDirectory();
        Signer signer = Platform.createSigner(userData);
        signer.copySigner();
        signer.signAllRaps();
        deleteRaps();
    }

    private void copyRafsToRifDirectory() throws IOException {
        File rapsDirectory = new File(userData.getRapsWorkingDirectory());
        
        for (File rapFile : rapsDirectory.listFiles()) {
            String destinationRapFile = userData.getRiffWorkingDirectory() + File.separator + rapFile.getName();
            File rapCopyFile = new File(destinationRapFile);
            Files.copy(rapFile.toPath(), rapCopyFile.toPath());
        }
    }

    private void deleteRaps() {
        File riffDirectory = new File(userData.getRiffWorkingDirectory());
        File[] raps = riffDirectory.listFiles((dir, filename) -> filename.endsWith(".rap"));
        for(File rap : raps) {
            rap.delete();
        }
    }
}
