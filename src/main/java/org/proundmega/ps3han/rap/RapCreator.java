package org.proundmega.ps3han.rap;

import java.util.stream.IntStream;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class RapCreator {

    public static byte[] rapContentToBinary(String rapContent) {
        try {
            return Hex.decodeHex(rapContent.toCharArray());
        } catch (DecoderException ex) {
            throw new RuntimeException(ex);
        }
    }
}
