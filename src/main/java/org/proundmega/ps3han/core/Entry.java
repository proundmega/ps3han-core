package org.proundmega.ps3han.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Objects;

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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getPkgUrl() {
        return pkgUrl;
    }

    public void setPkgUrl(String pkgUrl) {
        this.pkgUrl = pkgUrl;
    }

    public String getRapName() {
        return rapName;
    }

    public void setRapName(String rapName) {
        this.rapName = rapName;
    }

    public String getRapValue() {
        return rapValue;
    }

    public void setRapValue(String rapValue) {
        this.rapValue = rapValue;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    @Override
    public String toString() {
        return "Entry{" + "gameId=" + gameId + ", gameName=" + gameName + ", type=" + type + ", region=" + region + ", pkgUrl=" + pkgUrl + ", rapName=" + rapName + ", rapValue=" + rapValue + ", comments=" + comments + ", contributor=" + contributor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.gameId);
        hash = 89 * hash + Objects.hashCode(this.gameName);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.region);
        hash = 89 * hash + Objects.hashCode(this.pkgUrl);
        hash = 89 * hash + Objects.hashCode(this.rapName);
        hash = 89 * hash + Objects.hashCode(this.rapValue);
        hash = 89 * hash + Objects.hashCode(this.comments);
        hash = 89 * hash + Objects.hashCode(this.contributor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entry other = (Entry) obj;
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        if (!Objects.equals(this.gameName, other.gameName)) {
            return false;
        }
        if (!Objects.equals(this.pkgUrl, other.pkgUrl)) {
            return false;
        }
        if (!Objects.equals(this.rapName, other.rapName)) {
            return false;
        }
        if (!Objects.equals(this.rapValue, other.rapValue)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.contributor, other.contributor)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.region != other.region) {
            return false;
        }
        return true;
    }
    
}
