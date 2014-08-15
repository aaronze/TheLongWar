package ui;

import data.Codes;
import java.util.Date;
import javax.swing.JFrame;
import secure.Network;

/**
 * @author Aaron
 */
public class Home extends JFrame {
    public static void main(String[] args) {
        new Home();
    }
    
    public Home() {
        Network.connect();
        
        setSize(1024, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        World world = new World();
        add(world);
        
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
