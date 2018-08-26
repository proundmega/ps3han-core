package org.proundmega.ps3han.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.codehaus.plexus.util.FileUtils;

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
    
    public String getGameCreatorWorkingDirectory() {
        return workDirectory + File.separator + "gameCreator";
    }

    public void createDirectories() {
        deleteAndRecreateDirectory(workDirectory);
        deleteAndRecreateDirectory(getRapsWorkingDirectory());
        deleteAndRecreateDirectory(getRiffWorkingDirectory());
        deleteAndRecreateDirectory(getBinWorkingDirectory());
        deleteAndRecreateDirectory(getLogForWorkingDirectory());
        deleteAndRecreateDirectory(getReleaseWorkingDirectory());
        deleteAndRecreateDirectory(getGameCreatorWorkingDirectory());
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

    public String getWorkDirectory() {
        return workDirectory;
    }

    public void setWorkDirectory(String workDirectory) {
        this.workDirectory = workDirectory;
    }

    public String getActDatLocation() {
        return actDatLocation;
    }

    public void setActDatLocation(String actDatLocation) {
        this.actDatLocation = actDatLocation;
    }

    public String getIdpsHexLocation() {
        return idpsHexLocation;
    }

    public void setIdpsHexLocation(String idpsHexLocation) {
        this.idpsHexLocation = idpsHexLocation;
    }

    @Override
    public String toString() {
        return "UserData{" + "workDirectory=" + workDirectory + ", actDatLocation=" + actDatLocation + ", idpsHexLocation=" + idpsHexLocation + '}';
    }

    public UserData createSubWorkDir(String subWorkDir) {
        UserData subUserData = new UserData();
        subUserData.setWorkDirectory(workDirectory + File.separator + subWorkDir);
        subUserData.setActDatLocation(actDatLocation);
        subUserData.setIdpsHexLocation(idpsHexLocation);
        
        return subUserData;
    }
}
