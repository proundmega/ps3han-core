package org.proundmega.ps3han.xmb.store;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.proundmega.ps3han.core.Entry;
import org.proundmega.ps3han.core.UserData;
import org.proundmega.ps3han.xmb.file.DownloadTableAttribute;
import org.proundmega.ps3han.xmb.file.InfoTableAttribute;
import org.proundmega.ps3han.xmb.file.View;
import org.proundmega.ps3han.xmb.file.XmbFile;

public class GameStoreCreator {
    private List<Entry> store;
    private UserData userData;
    private int idCount = 1;

    public GameStoreCreator(List<Entry> store, UserData userData) {
        this.store = store;
        this.userData = userData;
    }
    
    private static final String DOWNLOAD_ICON = "/dev_hdd0/game/HANTOOLBX/USRDIR/download.png";
    
    public void convertToXmb() {
        XmbFile file = new XmbFile(userData.getGameCreatorWorkingDirectory() + File.separator + "store_1.xml");
        View mainView = file.getMainView();
        
        for(Entry entry : store) {
            try {
                String idNewView = entry.getGameId() + "_" + idCount;
                View createNewView = file.createNewView(idNewView);
                
                InfoTableAttribute attribute = createInfoAttributeForEntry(entry);
                
                DownloadTableAttribute downloadAttribute = createDownloadAttribute(entry);
                createNewView.addAttributeForDownload(downloadAttribute);
                
                mainView.addAttributeLink(attribute, createNewView);
                
                idCount++;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        
        file.writeToFile();
    }

    private InfoTableAttribute createInfoAttributeForEntry(Entry entry) throws IOException {
        String idEntryData = entry.getGameId() + "_" + idCount + "_data";
        InfoTableAttribute attribute = new InfoTableAttribute();
        attribute.setId(idEntryData);
        attribute.setUrlIcon(DOWNLOAD_ICON);
        attribute.setTitle(entry.getGameName());
        attribute.setInfo(entry.getGameId());
        // too slow to go to the network for just get the size
        // attribute.setInfo(entry.getGameId() + " - " + entry.getFileSize());
        attribute.setIngame("disable");
        
        return attribute;
    }
    
    private DownloadTableAttribute createDownloadAttribute(Entry entry) {
        String idLink = entry.getGameId() + "_" + idCount + "_link";
        DownloadTableAttribute attribute = new DownloadTableAttribute();
        attribute.setId(idLink);
        attribute.setContentId(entry.getContentId());
        attribute.setPkgSrc(entry.getPkgUrl());
        attribute.setUrlImageItem(DOWNLOAD_ICON);
        
        return attribute;
    }
}
