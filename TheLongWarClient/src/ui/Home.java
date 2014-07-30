package ui;

import javax.swing.JPanel;
import secure.Network;

/**
 * @author Aaron
 */
public class Home extends JPanel {
    public static void main(String[] args) {
        Network.connect();
    }
}
