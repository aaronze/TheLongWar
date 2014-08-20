package data;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author Aaron
 */
public class Log {
    private final static File log = new File("log.txt");
    private static PrintWriter writer;
    
    static {
        try {
            writer = new PrintWriter(new FileWriter(log, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void log(String s) {
        try {
            
            System.out.println(s);
            writer.println(System.nanoTime() + ": " + s);
            writer.flush();
            
        } catch (Exception e) {
            System.err.println("Log File crashed. Attempting to reopen");
            
            try {
                writer = new PrintWriter(new FileWriter(log, true));
            } catch (Exception f) {
                System.err.println("Cannot reopen log file. Fix asap.");
                e.printStackTrace();
            }
        }
    }
}
