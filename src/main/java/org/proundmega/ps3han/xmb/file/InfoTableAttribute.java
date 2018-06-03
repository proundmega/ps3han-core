package org.proundmega.ps3han.xmb.file;



import lombok.Data;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

@Data
public class InfoTableAttribute {
    private String id;
    private String urlIcon;
    private String title;
    private String info;
    private String ingame;
    
    public Element toElement() {
        Element tableElement = XmbElements.createTableElement(id);
        tableElement.add(XmbElements.createPairElement("icon", urlIcon));
        tableElement.add(XmbElements.createPairElement("title", title));
        tableElement.add(XmbElements.createPairElement("info", info));
        tableElement.add(XmbElements.createPairElement("ingame", ingame));
        
        return tableElement;
    }
    
}
