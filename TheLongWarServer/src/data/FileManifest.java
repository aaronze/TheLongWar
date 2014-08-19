package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * @author Aaron
 */
public class FileManifest {
    public static String manifestHash = "";
    
    public static File manifest = new File("manifest.txt");
    public static File syncFolder = new File("sync");
    public static ArrayList<File> acceptedUploads = new ArrayList<>();
    
    public static void main(String[] args) {
        
    }
    
    static PrintWriter manifestWriter;
    static {
        // Build manifest for sync folder
        try {
            manifestWriter = new PrintWriter(new FileWriter(manifest));
            
            buildManifest(syncFolder);
            
            manifestWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Build manifest for manifest
        manifestHash = hash(manifest);
        System.out.println(manifestHash);
    }
    
    public static void buildManifest(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            
            for (File f : files) {
                buildManifest(f);
            }
        } else {
            String entry = "";
            
            entry += hash(folder) + " ";
            entry += folder.getPath().substring(folder.getPath().indexOf("sync") + 5);

            manifestWriter.println(entry);
            acceptedUploads.add(folder);
        }
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
    
    public static boolean isInManifest(File file) {
        return acceptedUploads.contains(file);
    }
}
