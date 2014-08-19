package secure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;

/**
 * @author Aaron
 */
public class Network {
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;
    
    public static String username;
    public static String session;
    
    private final static String host = "203.122.219.233";
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
        long start = System.nanoTime();
        String output = "";
        try {
            out.println(packet);
            output = in.readLine();
        } catch (Exception e) {
            System.err.println("Network Error");
            e.printStackTrace();
        }
        long end = System.nanoTime();
        
        long dur = end - start;
        System.out.println("Request [" + packet + "]" + " took " + dur / 1000000000.0 + " seconds");
        
        return output;
    }
    
    public static String md5(String input) {
        String md5 = "";
         
        if(null == input) 
            return "";
         
        try {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return md5;
    }
}
