package secure;

import data.Codes;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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
    
    public static String downloadFile() {
        try {
            System.out.println("Waiting for file info ...");
            String info = in.readLine();
            while (info.equals(""+Codes.RESPONSE_SUCCESS)) info = in.readLine();
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
            
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ""+Codes.RESPONSE_FAIL;
    }
    
    public static void runPatcher() {
        request(""+Codes.REQUEST_MANIFEST);
        
        // Download manifest
        String successCode = downloadFile();
        
        if (successCode.equals(""+Codes.RESPONSE_SUCCESS)) {
            // Run through the manifest looking for any files that need syncing
            try (BufferedReader reader = new BufferedReader(new FileReader(new File("manifest.txt")))) {
                String line;
                
                while ((line = reader.readLine()) != null) {
                    String expectedHash = line.substring(0, line.indexOf(" "));
                    String filename = line.substring(line.indexOf(" ")+1);
                    
                    File file = new File(filename);
                    
                    if (file.exists()) {
                        String calculatedHash = hash(file);

                        if (!expectedHash.equals(calculatedHash)) {
                            patch(file);
                        } else {
                            System.out.println("Checked " + file.toString());
                        }
                    } else {
                        patch(file);
                    }
                }
                
                System.out.println("Patching Completed Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Patching Completed");
    }
    
    private static void patch(File file) {
        System.out.println("Patching " + file.toString());
        String response = Network.request(Codes.REQUEST_FILE + " " + file.getName());
        
        if (response.equals(""+Codes.RESPONSE_SUCCESS)) {
            downloadFile();
        } else {
            System.err.println("Failed to patch: " + file.toString());
            System.err.println("Error code: " + response);
        }
        
        try {Thread.sleep(100);} catch (Exception e) {}
    }
    
    public static String hash(File file) {
        String hash = "";
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);

            byte[] dataBytes = new byte[1024];

            int nread = 0; 
            while ((nread = fis.read(dataBytes)) != -1) {
              md.update(dataBytes, 0, nread);
            };
            byte[] mdbytes = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            hash = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hash;
    }
}
