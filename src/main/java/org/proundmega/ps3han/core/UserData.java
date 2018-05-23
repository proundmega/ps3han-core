package org.proundmega.ps3han.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import lombok.Data;
import org.codehaus.plexus.util.FileUtils;

@Data
public class UserData {

    private String workDirectory;
    private String actDatLocation;
    private String idpsHexLocation;

    public String getRapsWorkingDirectory() {
        return workDirectory + File.separator + "raps";
    }

    public String getRiffWorkingDirectory() {
        return workDirectory + File.separator + "riff";
    }

    public String getBinWorkingDirectory() {
        return workDirectory + File.separator + "bin";
    }

    public String getLogForWorkingDirectory() {
        return workDirectory + File.separator + "logs";
    }
    
    public String getReleaseWorkingDirectory() {
        return workDirectory + File.separator + "release";
    }

    public void createDirectories() {
        deleteAndRecreateDirectory(workDirectory);
        deleteAndRecreateDirectory(getRapsWorkingDirectory());
        deleteAndRecreateDirectory(getRiffWorkingDirectory());
        deleteAndRecreateDirectory(getBinWorkingDirectory());
        deleteAndRecreateDirectory(getLogForWorkingDirectory());
        deleteAndRecreateDirectory(getReleaseWorkingDirectory());
    }

    private void deleteAndRecreateDirectory(String directoryName) {
        try {
            File directory = new File(directoryName);
            FileUtils.deleteDirectory(directory);
            directory.mkdir();
        } catch (IOException ex) {
            // for now we dont care
        }
    }

    public void copyUserFilesToBin() throws IOException {
        File actDat = new File(this.getActDatLocation());
        File ipsHex = new File(this.getIdpsHexLocation());
        File riffDestinyDirectory = new File(this.getBinWorkingDirectory());

        Files.copy(actDat.toPath(), riffDestinyDirectory.toPath().resolve(actDat.getName()));
        Files.copy(ipsHex.toPath(), riffDestinyDirectory.toPath().resolve(ipsHex.getName()));
    }
    
    public void deleteTemporalFiles() {
        deleteAndRecreateDirectory(getRapsWorkingDirectory());
        deleteAndRecreateDirectory(getRiffWorkingDirectory());
        deleteAndRecreateDirectory(getBinWorkingDirectory());
    }
}
