package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Aaron
 */
public class Session extends Thread {
    private final Socket socket;
    
    public Session(Socket s) {
        this.socket = s;
    }
    
    @Override
    public void run() {
        System.out.println("Session created.");
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Session terminated.");
        }
    }
    
}
