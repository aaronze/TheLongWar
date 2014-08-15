package main;

import army.Army;
import army.unit.Unit;
import battle.Simulator;
import data.DataManager;
import java.net.ServerSocket;

/**
 * @author Aarib
 */
public class TheLongWarServer {
    private static final int port = 2574;
    
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(port);
            
            while (true) {
                Session session = new Session(ss.accept());
                session.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
