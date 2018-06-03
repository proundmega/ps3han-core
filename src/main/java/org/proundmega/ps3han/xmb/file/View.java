package org.proundmega.ps3han.xmb.file;

import java.util.List;
import org.dom4j.Element;

public class View {
    private Element viewElement;
    private Element attributes;
    private Element items;

    public View(Element mainView) {
        this.viewElement = mainView;
        List<Element> elements = mainView.elements();
        attributes = elements.get(0);
        items = elements.get(1);
    }
    
    public void addAttributeLink(InfoTableAttribute attribute, View linkView) {
        Element tableElement = attribute.toElement();
        attributes.add(tableElement);
        
        QueryItem query = new QueryItem(attribute, linkView);
        items.add(query.toElement());
    }
    
    public void addAttributeForDownload(DownloadTableAttribute attribute) {
        Element downloadElement = attribute.toElement();
        attributes.add(downloadElement);
        
        NoLoginItem item = new NoLoginItem(attribute.getId());
        items.add(item.toElement());
    }
    
    public String getId() {
        return viewElement.attributeValue("id");
    }
    
    public String toXML() {
        return viewElement.asXML();
    }
    
}
