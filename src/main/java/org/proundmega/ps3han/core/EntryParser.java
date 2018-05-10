package org.proundmega.ps3han.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EntryParser {
    private static final Pattern SEPARATOR_ENTRY = Pattern.compile(";");
    
    public static List<Entry> parseEntries(String filename) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        return reader.lines()
                .map(EntryParser::getEntry)
                .collect(Collectors.toList());
    }
    
    public static Entry getEntry(String entryText) {
        List<String> values = convertEntryTextToList(entryText);
        
        Entry entry = new Entry();
        entry.setGameId(values.get(0));
        entry.setGameName(values.get(1));
        entry.setType(Type.map(values.get(2)));
        entry.setRegion(Region.map(values.get(3)));
        entry.setPkgUrl(values.get(4));
        entry.setRapName(values.get(5));
        entry.setRapValue(values.get(6));
        entry.setComments(values.get(7));
        entry.setContributor(values.get(8));
        return entry;
    }

    private static List<String> convertEntryTextToList(String entryText) {
        Matcher matcher = SEPARATOR_ENTRY.matcher(entryText);
        List<String> values = new ArrayList<>();
        int currentPosicion = 0;
        while(matcher.find()) {
            String value = entryText.substring(currentPosicion, matcher.start());
            values.add(value);
            currentPosicion += value.length() + 1;
        }
        values.add(entryText.substring(currentPosicion));
        return values;
    }
            
}
