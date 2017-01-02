package ua.com.codefire.cms.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by kris on 12.12.16
 */

public class Utils {

    public static boolean isValid(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static String readFileAsString(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
