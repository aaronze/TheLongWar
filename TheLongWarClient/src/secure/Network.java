package secure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.*;
import java.security.MessageDigest;

/**
 * @author Aaron
 */
public class Network {
    private static Socket socket;
    private static InputStream inStream;
    private static BufferedReader in;
    private static OutputStream outStream;
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
                    
                    outStream = socket.getOutputStream();
                    out = new PrintWriter(outStream, true);
                    inStream = socket.getInputStream();
                    in = new BufferedReader(new InputStreamReader(inStream));
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
    
    public static void downloadFile() {
        long length = 0;
        File file = null;
        
        try {
            String info = in.readLine();

            length = Long.parseLong(info.substring(0, info.indexOf(" ")));
            file = new File(info.substring(info.indexOf(" ")+1));
            
            System.out.println("Downloading " + file.getName() + ": " + length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try (FileOutputStream writer = new FileOutputStream(file)) {
            int read;
            int pos = 0;
            byte[] buffer = new byte[1024];
            
            while (pos < length - buffer.length) {
                read = inStream.read(buffer);
                writer.write(buffer, 0, read);
                pos += read;
            }
            
            read = inStream.read(buffer, 0, (int)(length - buffer.length));
            writer.write(buffer, 0, read);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            // Read final success message
            String success = in.readLine();
            System.out.println("Success message: " + success);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
