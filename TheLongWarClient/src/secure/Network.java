package secure;

import java.io.IOException;
import java.net.*;

/**
 * @author Aaron
 */
public class Network {
    private static Socket socket;
    
    private final static String host = "localhost";
    private final static int port = 2574;
    
    /**
     * Attempts to establish a connection in a non-blocking method
     */
    public static void connect() {
        // Open a new thread for connection
        Thread connection = new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket(host, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
        connection.start();
    }
}
