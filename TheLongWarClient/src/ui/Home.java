package ui;

import audio.Audio;
import audio.Mixer;
import data.Codes;
import java.awt.Frame;
import java.awt.Graphics;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import secure.Network;
import secure.Resources;

/**
 * @author Aaron
 */
public class Home extends JFrame {
    public static Home self;
    
    public static void main(String[] args) {
        new Home();
    }
    
    JPanel login = new Login();
    World world;
    
    public Home() {
        self = this;
        
        Mixer.register(new Audio(Resources.getResource("bgmusicloop.wav"), "background"));
        Mixer.loop("background");
        
        Network.connect();
        
        try {
            Thread.sleep(1000);
            
            String packet = "";
            packet += Codes.REQUEST_FILE + " ";
            packet += "codes.txt";
            String response = Network.request(packet);
            
            if (response.equals(""+Codes.RESPONSE_SUCCESS)) {
                System.out.println("File transfer initiated");

                Network.downloadFile();
            } else {
                System.out.println("File transfer failed with error code: " + response);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
        setSize(1024, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        add(new Login());
        
        long timer = 0;
        while (true) {
            long previous = new Date().getTime();
            if (timer > 1000) {
                if (world != null) 
                    world.buildAllianceOverlay();
                timer = 0;
            }
            
            try {
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long time = new Date().getTime();
            timer += time - previous;
        }
    }
    
    public void loadWorld() {
        world = new World();
        world.setSize(1024, 768);
        world.setVisible(true);
        add(world);
    }
}
