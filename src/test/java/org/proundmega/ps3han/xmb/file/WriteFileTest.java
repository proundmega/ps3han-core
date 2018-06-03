package org.proundmega.ps3han.xmb.file;

import org.junit.Test;
import static org.junit.Assert.*;

public class WriteFileTest {

    @Test
    public void saveFile() {
        String filename = "test.xml";
        XmbFile xmb = new XmbFile(filename);

        String idView = "game_1";
        View mainView = xmb.getMainView();
        View downloadView = xmb.createNewView(idView);
        downloadView.addAttributeForDownload(getDownloadAttribute());

        InfoTableAttribute attribute = createInfoTableAttribute();
        mainView.addAttributeLink(attribute, downloadView);
        
        xmb.writeToFile();
        
        assertTrue(true);
    }

    private InfoTableAttribute createInfoTableAttribute() {
        InfoTableAttribute attributes = new InfoTableAttribute();
        attributes.setId("psnstuff_PSN_EU_item_62");
        attributes.setUrlIcon("/dev_hdd0/game/HANTOOLBX/USRDIR/download.png");
        attributes.setTitle("Beyond: Two Souls Making Of Featurettes Installer HUN");
        attributes.setInfo("NPEA00496 - 836.32 Mb");
        attributes.setIngame("disable");

        return attributes;
    }

    private DownloadTableAttribute getDownloadAttribute() {
        DownloadTableAttribute attribute = new DownloadTableAttribute();
        attribute.setId("psnstuff_PSN_EU_item_62_link");
        attribute.setContentId("EP0002-NPEB02087_00-CODAW00000000000");
        attribute.setPkgSrc("http://zeus.dl.playstation.net/cdn/EP0002/NPEB02087_00/GHAFdICMkAtoPJkPmywLjhkpewgjESXuJdQnZzOOfKccVSALJUGijrErIwtHMvuNrOypwaxgcjfSkDwtyqNsUMdKmhQexHEAejAAE.pkg");
        attribute.setUrlImageItem("/dev_hdd0/game/HANTOOLBX/USRDIR/PSN_EU_images/download.png");
        return attribute;
    }
}
