package org.proundmega.ps3han.rap;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.xml.bind.DatatypeConverter;

public class RapCreator {

    public static byte[] rapContentToBinary(String rapContent) {
        return DatatypeConverter.parseHexBinary(rapContent);
    }
}
