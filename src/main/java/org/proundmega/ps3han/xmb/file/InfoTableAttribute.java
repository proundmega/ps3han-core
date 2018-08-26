package org.proundmega.ps3han.xmb.file;
import org.dom4j.Element;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIngame() {
        return ingame;
    }

    public void setIngame(String ingame) {
        this.ingame = ingame;
    }
    
}
