package org.proundmega.ps3han.rap;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.proundmega.ps3han.TestUtils;

public class WindowsSignerTest {

    // marked as ignored due to possible plataform changes
    @Test
    @Ignore
    public void resignRapManually() throws IOException {
        final String rapName = "UP0102-NPUB30033_00-AGEOFBOOTYGAME00.rap";
        final String signerName = "ps3xploit_rifgen_edatresign.exe";
        File exec = TestUtils.getTestResource(signerName);
        exec.setExecutable(true);
        ProcessBuilder builder = new ProcessBuilder(exec.getAbsolutePath(), rapName);
        builder.directory(exec.getParentFile().getAbsoluteFile());

        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);

        builder.start();
    }

}
