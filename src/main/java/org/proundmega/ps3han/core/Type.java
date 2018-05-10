package org.proundmega.ps3han.core;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    NULL("null"),
    PSN("PSN"),
    PSVita("PSVita"),
    DLC("DLC"),
    WALLPAPER("Wallpaper"),
    MINI("Mini"),
    PS1("PS1"),
    DEMO("Demo"),
    C00("C00"),
    EDAT("EDAT"),
    THEME("Theme"),
    PS4("PS4"),
    VIDEO("Video"),
    PS2("PS2"),
    PS3("PS3"),
    AVATAR("Avatar"),
    WEB_TV("WebTV"),
    SOUNDTRACT("Soundtrack"),
    PSP("PSP");
    
    private static final Map<String, Type> map = new HashMap<>();
    
    static {
        for(Type entry : values()) {
            map.put(entry.name, entry);
        }
    }
    
    
    private Type(String name) {
        this.name = name;
    }
    
    private final String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "EntryType{" + "name=" + name + '}';
    }
    
    public static Type map(String value) {
        Type type = map.get(value);
        if(type == null) {
            throw new IllegalArgumentException("No mapped entry type: " + value);
        }
        
        return type;
    }
}
