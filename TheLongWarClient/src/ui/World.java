package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import secure.Resources;

/**
 * @author Aaron
 */
public class World extends JPanel {
    static BufferedImage lookupImage;
    static ArrayList<Legend> legends;
    
    /**
     * Returns the name of the country (empty string for no country) at the given
     * normalized screen co-ordinates.
     * 
     * @param mX Value between 0.0 and 1.0 representing the position of the mouse on the screen
     * @param mY Value between 0.0 and 1.0 representing the position of the mouse on the screen
     * @return Name of country or empty string for no country
     */
    public static String getCountryAt(double mX, double mY) {
        int x = (int)(mX * lookupImage.getWidth());
        int y = (int)(mY * lookupImage.getHeight());
        
        int code = lookupImage.getRGB(x, y);
        
        for (Legend legend : legends) {
            if (legend.code == code) {
                return legend.name;
            }
        }
        
        return "";
    }
    
    static {
        // Load legends and lookup image
        try {
            lookupImage = ImageIO.read(Resources.getResource("countries.jpg"));
            legends = new ArrayList<>();
            
            BufferedReader reader = new BufferedReader(new FileReader(Resources.getResource("codes.txt")));
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(":");
                String name = str[0];
                String[] color = str[1].split(",");
                
                int r = Integer.parseInt(color[0]);
                int g = Integer.parseInt(color[1]);
                int b = Integer.parseInt(color[2]);
                
                int code = new Color(r, g, b).getRGB();
                
                legends.add(new Legend(name, code));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private static class Legend {
        public String name;
        public int code;
        
        public Legend(String name, int code) {
            this.name = name;
            this.code = code;
        }
    }
    
    private BufferedImage worldImage;
    
    public World() {
        try {
            worldImage = ImageIO.read(Resources.getResource("world.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                double mx = e.getX() * 1.0 / getWidth();
                double my = e.getY() * 1.0 / getHeight();
                
                System.out.println(getCountryAt(mx, my));
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(worldImage.getScaledInstance(this.getWidth(), this.getHeight(), BufferedImage.SCALE_DEFAULT), 0, 0, null);
    }
    
    
}
