package org.proundmega.ps3han.xmb.file;
import org.dom4j.Element;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPkgSrc() {
        return pkgSrc;
    }

    public void setPkgSrc(String pkgSrc) {
        this.pkgSrc = pkgSrc;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getUrlImageItem() {
        return urlImageItem;
    }

    public void setUrlImageItem(String urlImageItem) {
        this.urlImageItem = urlImageItem;
    }

    @Override
    public String toString() {
        return "DownloadTableAttribute{" + "id=" + id + ", pkgSrc=" + pkgSrc + ", contentId=" + contentId + ", urlImageItem=" + urlImageItem + '}';
    }
    
}
