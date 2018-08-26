package org.proundmega.ps3han.xmb.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class XmbFile {
    private Document document;
    private Element mainView;
    private String filename;
    
    public XmbFile(String filename)  {
        try {
            this.filename = filename;
            checkIfFileExistIFNotCreateOne(filename);
            readXmd(filename);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void checkIfFileExistIFNotCreateOne(String filename) throws DocumentException, IOException {
        File file = new File(filename);
        if(!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(createBasicFileStructure(file.getName()));
            }
        }
    }

    private void readXmd(String filename) throws DocumentException {
        SAXReader reader = new SAXReader();
        document = reader.read(filename);
        
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        mainView = elements.get(0);
    }
    
    private String createBasicFileStructure(String filename) {
        return "<XMBML version=\"1.0\">\n" +
               "   <View id=\"" + filename.replace(".", "_") + "_items\">\n" +
               "       <Attributes>"
            +  "       </Attributes>"
            +  "       <Items>"
            +  "       </Items>"
            +  "   </View>"
            +  "</XMBML>";
    }
    
    public View getMainView() {
        return new View(mainView);
    }
    
    public View createNewView(String viewId) {
        Element newView = createViewElement(viewId);
        document.getRootElement().add(newView);
        
        return new View(newView);
    }

    private Element createViewElement(String viewId) {
        Element newView = DocumentHelper.createElement("View");
        newView.addAttribute("id", viewId);
        Element attributes = DocumentHelper.createElement("Attributes");
        newView.add(attributes);
        Element items = DocumentHelper.createElement("Items");
        newView.add(items);
        return newView;
    }
    
    public String toXML() {
        return document.asXML();
    }
    
    public void writeToFile() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(fos, format);
            writer.write(this.document);
            writer.flush();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } 
    }
}
