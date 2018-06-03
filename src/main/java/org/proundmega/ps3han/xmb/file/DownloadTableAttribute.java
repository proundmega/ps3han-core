package org.proundmega.ps3han.xmb.file;



import lombok.Data;
import org.dom4j.Element;

@Data
public class DownloadTableAttribute {
    private String id;
    private String pkgSrc;
    private String contentId;
    private String urlImageItem;
    
    private static final String INFO_STRING = "net_package_install";
    private static final String CONTENT_NAME_STRING = "tool_pkg_install_pc";
    
    public Element toElement() {
        Element downloadElement = XmbElements.createTableElement(id);
        downloadElement.add(XmbElements.createPairElement("info", INFO_STRING));
        downloadElement.add(XmbElements.createPairElement("pkg_src", pkgSrc));
        downloadElement.add(XmbElements.createPairElement("pkg_src_qa", pkgSrc));
        downloadElement.add(XmbElements.createPairElement("content_name", CONTENT_NAME_STRING));
        downloadElement.add(XmbElements.createPairElement("content_id", contentId));
        downloadElement.add(XmbElements.createPairElement("prod_pict_path", urlImageItem));
        
        return downloadElement;
    }
}
