package org.proundmega.ps3han.xmb.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

@Data
@AllArgsConstructor
public class NoLoginItem {
    private String elementName;
    
    private static final String NO_LOGIN_CONSTANT = "type:x-xmb/xmlnpsignup";
    
    public Element toElement() {
        Element element = DocumentHelper.createElement("Item");
        element.addAttribute("class", NO_LOGIN_CONSTANT);
        element.addAttribute("key", elementName);
        element.addAttribute("attr", elementName);
        
        return element;
    }
}
