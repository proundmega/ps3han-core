package org.proundmega.ps3han.xmb.file;

import org.proundmega.ps3han.xmb.file.XmbFile;
import org.proundmega.ps3han.xmb.file.InfoTableAttribute;
import org.proundmega.ps3han.xmb.file.View;
import org.proundmega.ps3han.xmb.file.DownloadTableAttribute;
import java.io.File;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ViewTest {
    private XmbFile xmb;
    private static final String TEST_FILE = "test.xml";
    
    @Before
    public void crearXMB() {
        xmb = new XmbFile(TEST_FILE);
    }
    
    @After
    public void borrarXMB() {
        new File(TEST_FILE).delete();
    }
    
    @Test
    public void createInfoView() {
        String idView = "game_1";
        View mainView = xmb.getMainView();
        View downloadView = xmb.createNewView(idView);
        
        InfoTableAttribute attribute = createInfoTableAttribute();
        mainView.addAttributeLink(attribute, downloadView);
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
    
    @Test
    public void addDownloadLink() {
        String idView = "game_1";
        View view = xmb.createNewView(idView);
        
        DownloadTableAttribute attribute = getDownloadAttribute();
        
        view.addAttributeForDownload(attribute);
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
