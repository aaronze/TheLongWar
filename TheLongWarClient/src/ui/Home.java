package ui;

import audio.Audio;
import audio.Mixer;
import data.Codes;
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
    public static void main(String[] args) {
        new Home();
    }
    
    JPanel login = new Login();
    
    public Home() {
        for (int i = 60; i < 110; i++) {
            System.out.print((char)i);
        }
        
        Mixer.register(new Audio(Resources.getResource("bgmusicloop.wav"), "background"));
        Mixer.loop("background");
        
        Network.connect();
        Network.request(""+Codes.REQUEST_REGISTER + " Storms " + Network.md5("Everquest1") + " aaron.kison@gmail.com");
        
        setSize(1024, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        World world = new World();
        add(world);
        add(login);
        
        long timer = 0;
        while (true) {
            long previous = new Date().getTime();
            if (timer > 1000) {
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
    
    
}
