package org.proundmega.ps3han.xmb.file;

import org.proundmega.ps3han.xmb.file.XmbFile;
import org.proundmega.ps3han.xmb.file.View;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class XmbFileTest {
    
    @Test
    public void createNewFileSuccessfully() {
        String filename = "test.xml";
        
        // erase the file if exist
        new File(filename).delete();
        
        XmbFile xmb = new XmbFile(filename);
        assertTrue(new File(filename).exists());
    }
    
    @Test
    public void createNewView() {
        String filename = "test.xml";
        XmbFile xmb = new XmbFile(filename);
        View view = xmb.createNewView("test1");
        assertTrue(true);
    }
    
}
