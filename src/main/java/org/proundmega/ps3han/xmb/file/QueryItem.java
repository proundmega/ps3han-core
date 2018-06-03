package org.proundmega.ps3han.xmb.file;



import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class QueryItem {
    private String classString;
    private String key;
    private String attr;
    private String viewLink;
    
    private static final String LINK_CONSTANT = "type:x-xmb/folder-pixmap";
    
    public QueryItem(InfoTableAttribute attribute, View linkView) {
        classString = LINK_CONSTANT;
        key = attribute.getId();
        attr = attribute.getId();
        viewLink = "#" + linkView.getId();
    }
    
    public Element toElement() {
        Element query = DocumentHelper.createElement("Query");
        query.addAttribute("class", classString);
        query.addAttribute("key", key);
        query.addAttribute("attr", attr);
        query.addAttribute("src", viewLink);
        
        return query;
    }
}
