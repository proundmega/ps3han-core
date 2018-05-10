package org.proundmega.ps3han.core;

import java.util.Objects;
import java.util.regex.Pattern;

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
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.gameId);
        hash = 71 * hash + Objects.hashCode(this.gameName);
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.region);
        hash = 71 * hash + Objects.hashCode(this.pkgUrl);
        hash = 71 * hash + Objects.hashCode(this.rapName);
        hash = 71 * hash + Objects.hashCode(this.rapValue);
        hash = 71 * hash + Objects.hashCode(this.comments);
        hash = 71 * hash + Objects.hashCode(this.contributor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
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
        if (this.type != other.type) {
            return false;
        }
        if (this.region != other.region) {
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
        return true;
    }

    @Override
    public String toString() {
        return "Entry{" + "gameId=" + gameId + ", gameName=" + gameName + ", type=" + type + ", region=" + region + ", pkgUrl=" + pkgUrl + ", rapName=" + rapName + ", rapValue=" + rapValue + ", comments=" + comments + ", contributor=" + contributor + '}';
    }
    
    public boolean hasRap() {
        return !getRapName().equals("");
    }
}
