package org.proundmega.ps3han.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import lombok.Data;

@Data
public class Entry {
    private String gameId = "";
    private String gameName = "";
    private Type type = Type.NULL;
    private Region region = Region.NULL;
    private String pkgUrl = "";
    private String rapName = "";
    private String rapValue = "";
    private String comments = "";
    private String contributor = "";
    
    public boolean hasRap() {
        return !getRapName().equals("");
    }
    
    public boolean exist() {
        try {
            HttpURLConnection.setFollowRedirects(false);
      // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con
                    = (HttpURLConnection) new URL(pkgUrl).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getFileSize() throws MalformedURLException, IOException {
        URL url = new URL(pkgUrl);
        URLConnection openConnection = url.openConnection();
        return readableFileSize(openConnection.getContentLengthLong());
    }
    
    private static String readableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
    
    // I assume that the rap is generated properly
    public String getContentId() {
        return getRapName().replace(".rap", "");
    }
}
