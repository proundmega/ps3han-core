package org.proundmega.ps3han.core;

import java.util.HashMap;
import java.util.Map;

public enum Region {
    NULL("null"),
    JP("JP"),
    US("US"),
    EU("EU"),
    AS("AS"),
    ALL("ALL"),
    HK("HK");
    
    private static final Map<String, Region> map = new HashMap<>();
    
    static {
        for(Region region : values()) {
            map.put(region.name, region);
        }
    }

    private Region(String name) {
        this.name = name;
    }
    
    private final String name;
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Region{" + "name=" + name + '}';
    }
    
    public static Region map(String value) {
        Region type = map.get(value);
        if(type == null) {
            throw new IllegalArgumentException("No mapped region: " + value);
        }
        
        return type;
    }
}
