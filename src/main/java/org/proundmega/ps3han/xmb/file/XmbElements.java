package org.proundmega.ps3han.xmb.file;



import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmbElements {
    
    public static Element createTableElement(String key) {
        Element element = DocumentHelper.createElement("Table");
        element.addAttribute("key", key);
        
        return element;
    }
    
    public static Element createPairElement(String key, String value) {
        Element pair = DocumentHelper.createElement("Pair");
        Element stringElement = DocumentHelper.createElement("String");
        pair.addAttribute("key", key);
        stringElement.setText(value);
        pair.add(stringElement);
        
        return pair;
    }
    
    public static Element createStringElement(String content) {
        return null;
    }
}
