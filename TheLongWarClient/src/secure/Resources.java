package secure;

import java.io.File;

/**
 * @author Aaron
 */
public class Resources {
    public static File getResource(String name) {
        return new File(name);
    }
}
