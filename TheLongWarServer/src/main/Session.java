package main;

import data.Account;
import data.Codes;
import data.DataManager;
import data.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Aaron
 */
public class Session extends Thread {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public Session(Socket s) {
        this.socket = s;
    }
    
    @Override
    public void run() {
        System.out.println("Session created.");
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                Log.log(socket.toString() + " v: " + line);
                
                // Process request
                processCommand(line);

                
            }
        } catch (Exception e) {
            System.out.println("Session terminated.");
        }
    }
    
    public void processCommand(String line) {
        try {
            int command = Integer.parseInt(""+line.charAt(0));
            
            if (command == Codes.REQUEST_GET_CURRENT_STATE) {
                int[] stream = DataManager.toDataStream();
                String packet = "";
                for (int i : stream) packet += i + " ";
                out.println(packet);
            }
            
            if (command == Codes.REQUEST_GET_CHANGES_SINCE) {
                long lastSeen = Long.parseLong(line.substring(2));
                
                ArrayList<String[]> entries = DataManager.captureTable.getEntries();
                
                ArrayList<String> modifiedCountries = new ArrayList<>();
                for (int i = entries.size()-1; i >= 0; i--) {
                    String[] entry = entries.get(i);
                    long time = Long.parseLong(entry[1]);
                    
                    if (time < lastSeen - 5000) break;
                    
                    String country = entry[3];
                    
                    if (!modifiedCountries.contains(country))
                        modifiedCountries.add(country);
                }
                
                String packet = "";
                for (String s : modifiedCountries)
                    packet += s + " ";
                out.println(packet);
            }
            
            if (command == Codes.REQUEST_GET_NATION_INFO) {
                String packet = "";
                for (String[] s : DataManager.nationTable.getEntries()) {
                    packet += s[0] + ":" + s[1] + ":" + s[2] + ";";
                }
                out.println(packet);
            }
            
            if (command == Codes.REQUEST_ATTACK) {
                // Inputs: Username, Session, Attacker, Defender
                // Outputs: Success or Fail
                
                String[] data = line.substring(2).split(" ");
                
                String username = data[0];
                String session = data[1];
                
                // Check if user has authentication to do this action
                if (Account.reauthorize(username, session) == null) {
                    // User was not authorized
                    out.println(""+Codes.RESPONSE_NOT_AUTHORIZED);
                } else {
                    int attacker = Integer.parseInt(data[2]);
                    int defender = Integer.parseInt(data[3]);

                    DataManager.captureTable.addEntry(DataManager.captureTable.nextID(), ""+new Date().getTime(), ""+attacker, ""+defender);

                    int capturingNation = Codes.getNation(attacker);
                    int capturedCountry = Codes.getCountry(defender);

                    DataManager.countryTable.updateEntry("CountryName", ""+capturedCountry, "CountryOwner", ""+capturingNation);

                    out.println(""+Codes.RESPONSE_SUCCESS);
                }
            }
            
            if (command == Codes.REQUEST_AUTHORIZATION) {
                // Inputs: Username, Password
                // Outputs: Session Token
                
                String[] inputs = line.substring(2).split(" ");
                
                String username = inputs[0];
                String password = inputs[1];
                
                String session = Account.authorize(username, password);
                
                // Session is null if authorization failed
                if (session == null) {
                    String output = ""+Codes.RESPONSE_FAIL;
                    out.println(output);
                } else {
                    String output = ""+Codes.RESPONSE_SUCCESS + " " + session;
                    out.println(output);
                }
            }
            
            if (command == Codes.REQUEST_REGISTER) {
                // Inputs: Username, Password, Email
                // Outputs: Success or Fail
                
                String[] inputs = line.substring(2).split(" ");
                
                String username = inputs[0];
                String password = inputs[1];
                String email = inputs[2];
                
                boolean success = Account.register(username, password, email);
                
                if (success) {
                    out.println(""+Codes.RESPONSE_SUCCESS);
                } else {
                    out.println(""+Codes.RESPONSE_FAIL);
                }
            }
            
        } catch (Exception e) {
            Log.log("Error in request: [" + line + "]");
            e.printStackTrace();
        }
    }
}
