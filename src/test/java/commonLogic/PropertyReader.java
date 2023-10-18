package commonLogic;

import java.io.IOException;

public class PropertyReader {
    public static String getProperties(String propertyFile, String value) throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream(propertyFile));
        return System.getProperty(value);
    }
}
