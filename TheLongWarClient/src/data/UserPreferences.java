package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author Aaron
 */
public class UserPreferences {
    private static boolean isUserRemembered = false;
    private static boolean isAutoLogin = false;
    private static String storedUser = "";
    private static String storedPassword = "";
    
    static {
        try {
            File preferences = new File("user.pref");
            
            BufferedReader reader = new BufferedReader(new FileReader(preferences));
            String line;
            
            while ((line = reader.readLine()) != null) {
                try {
                    String command = line.substring(0, line.indexOf(" ")).toLowerCase();
                    String params = line.substring(line.indexOf(" ") + 1);
                    
                    if (command.contains("remember_user")) isUserRemembered = params.toLowerCase().contains("true");
                    if (command.contains("remember_password")) isAutoLogin = params.toLowerCase().contains("true");
                    if (command.contains("stored_user")) storedUser = params;
                    if (command.contains("stored_password")) storedPassword = params;
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isUserRemembered() { return isUserRemembered; }
    public static boolean isAutoLogin() { return isAutoLogin; }
    public static String getStoredUser() { return storedUser; }
    public static String getStoredPassword() { return storedPassword; }
    
    public static void setRememberedUser(String user) {
        isUserRemembered = true;
        storedUser = user;
        
        savePreferences();
    }
    
    public static void removeRememberedUser() {
        isUserRemembered = false;
        storedUser = "";
        
        savePreferences();
    }
    
    public static void setAutoLogin(String password) {
        isAutoLogin = true;
        storedPassword = password;
        
        savePreferences();
    }
    
    public static void removeAutoLogin() {
        isAutoLogin = false;
        storedPassword = "";
        
        savePreferences();
    }
    
    private static void savePreferences() {
        try {
            File preferences = new File("user.pref");
            
            PrintWriter writer = new PrintWriter(new FileWriter(preferences));
            
            writer.println("REMEMBER_USER: " + isUserRemembered);
            writer.println("REMEMBER_PASSWORD: " + isAutoLogin);
            if (isUserRemembered) writer.println("STORED_USER: " + storedUser);
            if (isAutoLogin) writer.println("STORED_PASSWORD: " + storedPassword);
            
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
