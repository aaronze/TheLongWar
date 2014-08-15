package main;

import data.Codes;
import data.DataManager;
import data.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
                String packet = ""+Codes.REQUEST_GET_CURRENT_STATE+" ";
                for (int i : stream) packet += i + " ";
                out.println(packet);
            }
            
        } catch (Exception e) {
            Log.log("Error in request: [" + line + "]");
            e.printStackTrace();
        }
    }
}
