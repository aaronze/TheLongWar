package secure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * @author Aaron
 */
public class Network {
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;
    
    private final static String host = "127.0.0.1";//192.168.0.99";
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
                    System.out.println("Connected!");
                    
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
        connection.start();
    }
    
    public static String request(String packet) {
        try {
            out.println(packet);
            return in.readLine();
        } catch (Exception e) {
            System.err.println("Network Error");
            e.printStackTrace();
            return "";
        }
    }
}
