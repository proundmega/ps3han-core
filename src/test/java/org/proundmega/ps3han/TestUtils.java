package org.proundmega.ps3han;

import java.io.File;
import java.nio.file.Paths;

public class TestUtils {
    private static final String PATH_TEST = "src" + File.separator + "test" + File.separator + "resources";
    
    public static File getTestResource(String... path) {
        return Paths.get(PATH_TEST, path).toFile();
    }
}
