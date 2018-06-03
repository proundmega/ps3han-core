package org.proundmega.ps3han.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class EntryParserTest {
    
    @Test
    public void testCorrectParse1() {
        String test = "BLUS30755;007 GoldenEye: Reloaded - Emilio Largo;DLC;US;http://zeus.dl.playstation.net/cdn/UP0002/BLUS30755_00/nDLrlSwjNszHkxDeCGeNmjGaVCKbHvRpzuhvxluHSOPWGUKBBlbFlYnKtxjncHuwpkzJYrIfuEkVDBDTisebFuFRkVdpZNuxNLHJc.pkg;UP0002-BLUS30755_00-BOND2011LARGDLC2.rap;2BA5438138C96761B77E21A16F71E1B9;;";
        
        Entry expected = createExpectedEntryCase1();
        Entry actual = EntryParser.getEntry(test);
        
        assertEquals(expected, actual);
    }
    
    private Entry createExpectedEntryCase1() {
        Entry entry = new Entry();
        entry.setGameId("BLUS30755");
        entry.setGameName("007 GoldenEye: Reloaded - Emilio Largo");
        entry.setType(Type.DLC);
        entry.setRegion(Region.US);
        entry.setPkgUrl("http://zeus.dl.playstation.net/cdn/UP0002/BLUS30755_00/nDLrlSwjNszHkxDeCGeNmjGaVCKbHvRpzuhvxluHSOPWGUKBBlbFlYnKtxjncHuwpkzJYrIfuEkVDBDTisebFuFRkVdpZNuxNLHJc.pkg");
        entry.setRapName("UP0002-BLUS30755_00-BOND2011LARGDLC2.rap");
        entry.setRapValue("2BA5438138C96761B77E21A16F71E1B9");
        
        return entry;
    }
    
    @Test
    public void testCorrectParse2WithoutRap() {
        String test = "NPHG00061;1000000t no barabara;PSP;HK;http://zeus.dl.playstation.net/cdn/HP9000/NPHG00061_00/JGDvMWbyAXIcKwSXp0tv2sd0yDMPWoxO8vXjiTn7BFMtmIuogV3XMjX09tLygvFSa1fqjaCnJxtpaT7tGybS9hiJaI9vTaGqbA0QX.pkg;;;*Warning, missing RAP*;JinKazama";
        
        Entry expected = createExpectedEntryCase2();
        Entry actual = EntryParser.getEntry(test);
        
        assertEquals(expected, actual);
    }
    
    private Entry createExpectedEntryCase2() {
        Entry entry = new Entry();
        entry.setGameId("NPHG00061");
        entry.setGameName("1000000t no barabara");
        entry.setType(Type.PSP);
        entry.setRegion(Region.HK);
        entry.setPkgUrl("http://zeus.dl.playstation.net/cdn/HP9000/NPHG00061_00/JGDvMWbyAXIcKwSXp0tv2sd0yDMPWoxO8vXjiTn7BFMtmIuogV3XMjX09tLygvFSa1fqjaCnJxtpaT7tGybS9hiJaI9vTaGqbA0QX.pkg");
        entry.setComments("*Warning, missing RAP*");
        entry.setContributor("JinKazama");
        return entry;
    }
}
