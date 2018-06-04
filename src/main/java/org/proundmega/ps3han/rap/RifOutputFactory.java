package org.proundmega.ps3han.rap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.SystemUtils;
import org.proundmega.ps3han.core.UserData;

@AllArgsConstructor
public class RifOutputFactory {
    private UserData userData;
    private String binDir;

    public void createRiffs() throws IOException {
        copyRafsToRifDirectory();
        Signer signer = Plataform.createSigner(userData);
        signer.copySigner(binDir);
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
