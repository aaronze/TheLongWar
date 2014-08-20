package secure;

import data.Codes;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
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
    
    public static int total = 0;
    public static long downloadStart = 0;
    
    public static void downloadFile() {
        try {
            String info = in.readLine();
            long length = Long.parseLong(info.substring(0, info.indexOf(" ")));
            String filename = info.substring(info.indexOf(" ")+1);
            System.out.println(info);

            // Send a message declaring ready
            out.println(""+Codes.RESPONSE_SUCCESS);
            
            BufferedInputStream bis = new BufferedInputStream(inStream);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename));
            
            byte[] data = new byte[(int)length];
            
            int read = 1;
            total = 0;
            
            downloadStart = System.nanoTime();
            while (total < data.length) {
                read = bis.read(data);
                bos.write(data, 0, read);
                if (read > 0) total += read;
            }
            bos.flush();
            bos.close();
            
            long downloadEnd = System.nanoTime();
            long timeTaken = downloadEnd - downloadStart;
            
            double downloadRateKBS = (total / 1000.0) / (timeTaken / 1000000000.0);
            System.out.println("Downloaded " + filename + " at " + downloadRateKBS + " kb/s");
            
            String success = in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
