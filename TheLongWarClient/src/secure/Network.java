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
    
    private final static String host = "192.168.0.99";
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
                    
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                    
                    String userInput;
                    while ((userInput = stdIn.readLine()) != null) {
                        out.println(userInput);
                        System.out.println("echo: " + in.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
        connection.start();
    }
}
