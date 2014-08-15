package ui;

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
        
        add(new World());
        
        while (true) {
            try {
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
