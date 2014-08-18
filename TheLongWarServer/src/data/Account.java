package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * @author Aaron
 * 
 * Contains everything to do with a particular registered user.
 */
public class Account {
    public static ArrayList<Account> authorizedPlayers = new ArrayList<>();
    
    public static String applicationSalt = "abcdefghijklmnopqrstuvwxyz";
    
    static {
        // Load application salt
        File fileSalt = new File("server_salt.txt");
        
        // Generate a new salt if one does not exist
        if (!fileSalt.exists()) {
            try {
                fileSalt.createNewFile();
                
                PrintWriter writer = new PrintWriter(new FileWriter(fileSalt));
                
                applicationSalt = randomSalt(32);
                
                writer.println(applicationSalt);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileSalt));
                applicationSalt = reader.readLine();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private String username;
    private String sessionToken;
    private Account(String username, String sessionToken) {
        this.username = username;
        this.sessionToken = sessionToken;
    }
    
    private boolean isAccountOfUser(String user) {
        return (username.equals(user));
    }
    
    public boolean isSessionToken(String token) {
        return sessionToken.equals(token);
    }
    
    /**
     * Attempts to reauthorize an account by using a valid session token.
     * Tokens are generated randomly each time a new session is created.
     * 
     * @param username
     * @param sessionToken
     * @return 
     */
    public static Account reauthorize(String username, String sessionToken) {
        // Check list of authorized users for this combination
        for (Account account : authorizedPlayers) {
            if (account.isAccountOfUser(username) && account.isSessionToken(sessionToken)) {
                return account;
            }
        }
        
        // No combination found
        return null;
    }
    
    /**
     * Attempts to authorize an account with the given username.
     * 
     * Returns null if either username does not exist or password is invalid.
     * Separation of fail cases is not possible, nor should it be for password security.
     * 
     * @param username
     * @param password
     * @return 
     */
    public static String authorize(String username, String password) {
        // Ensure username is a valid user
        if (DataManager.userTable.where("UserName", username).isEmpty()) {
            return null;
        }
        
        // Retrieve user salt
        String salt = DataManager.userTable.where("UserName", username).select("UserSalt");
        
        String saltedPassword = md5(password + applicationSalt + salt);
        String storedPassword = DataManager.userTable.where("UserName", username).select("UserPassword");
        
        // If passwords don't match return null
        if (!saltedPassword.equals(storedPassword)) {
            return null;
        }
        
        // Search if this account was already authorized
        for (Account account : authorizedPlayers.toArray(new Account[0])) {
            if (account.isAccountOfUser(username)) {
                // Found the pre-authorized account, we need to remove it and create another session
                authorizedPlayers.remove(account);
            }
        }
        
        // Create a new account with a new session id
        String sessionToken = randomSalt(32);
        
        Account account = new Account(username, sessionToken);
        authorizedPlayers.add(account);
        
        return sessionToken;
    }
    
    public static boolean register(String username, String password, String email) {
        // Ensure username is not already taken
        if (!DataManager.userTable.where("UserName", username).isEmpty()) {
            return false;
        }
        
        // Convert password to salted-md5
        // Generate user salt
        String userSalt = randomSalt(32);
        
        // Get md5-salted password
        String saltedPassword = md5(password + applicationSalt + userSalt);
        
        DataManager.userTable.addEntry(username, userSalt, saltedPassword, email);
        
        return true;
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
    
    public static String randomSalt(int length) {
        String salt = "";
        
        for (int i = 0; i < length; i++) {
            int random = (int)(Math.random()*26 + 66);
            salt += (char)random;
        }
        
        return salt;
    }
}
