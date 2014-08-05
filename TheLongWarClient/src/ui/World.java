package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
    public static String[][] lookup;
    public static ArrayList<Legend> legends;
    
    /**
     * Returns the name of the country (empty string for no country) at the given
     * normalized screen co-ordinates.
     * 
     * @param mX Value between 0.0 and 1.0 representing the position of the mouse on the screen
     * @param mY Value between 0.0 and 1.0 representing the position of the mouse on the screen
     * @return Name of country or empty string for no country
     */
    public static String getCountryAt(double mX, double mY) {
        int x = (int)(mX * lookup.length);
        int y = (int)(mY * lookup[0].length);
        
        return lookup[x][y];
    }
    
    static {
        // Load legends and lookup image
        try {
            BufferedImage lookupImage = ImageIO.read(Resources.getResource("countries.png"));
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
            
            lookup = new String[lookupImage.getWidth()][lookupImage.getHeight()];
            for (int x = 0; x < lookup.length; x++) {
                for (int y = 0; y < lookup[0].length; y++) {
                    int code = lookupImage.getRGB(x, y);
                    
                    lookup[x][y] = "";
                    for (Legend legend : legends) {
                        if (legend.code == code) {
                            lookup[x][y] = legend.name;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static class Legend {
        public String name;
        public int code;
        
        public Legend(String name, int code) {
            this.name = name;
            this.code = code;
        }
    }
    
    private BufferedImage worldImage;
    private BufferedImage mapOverlay;
    private Image mapScaled;
    private String selectedCountry = "";
    
    public World() {
        try {
            worldImage = ImageIO.read(Resources.getResource("world.jpg"));
            mapOverlay = new BufferedImage(worldImage.getWidth(), worldImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            mapScaled = worldImage.getScaledInstance(this.getWidth(), this.getHeight(), BufferedImage.SCALE_DEFAULT);
            buildOverlay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                double mx = e.getX() * 1.0 / getWidth();
                double my = e.getY() * 1.0 / getHeight();
                
                String previous = selectedCountry;
                selectedCountry = getCountryAt(mx, my);
                
                if (!previous.equals(selectedCountry)) {
                    buildOverlay();
                }
            }
        });
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mapScaled = worldImage.getScaledInstance(getWidth(), getHeight(), BufferedImage.SCALE_DEFAULT);
                buildOverlay();
            }
        });
    }
    
    public void buildOverlay() {
        if (this.getWidth() != 0) {
            mapOverlay.getGraphics().drawImage(mapScaled, 0, 0, null);

            double scaleX = this.getWidth() * 1.0 / worldImage.getWidth();
            double scaleY = this.getHeight() * 1.0 / worldImage.getHeight();

            if (!selectedCountry.isEmpty()) {
                double stepX = 1.0 / mapOverlay.getWidth();
                double stepY = 1.0 / mapOverlay.getHeight();

                for (int x = 0; x < mapOverlay.getWidth(); x++) {
                    for (int y = 0; y < mapOverlay.getHeight(); y++) {
                        String country = getCountryAt(x * stepX, y * stepY);

                        int xP = (int)(x * scaleX);
                        int yP = (int)(y * scaleY);

                        if (country.equals(selectedCountry)) {
                            mapOverlay.setRGB(xP, yP, increaseBrightness(mapOverlay.getRGB(xP, yP), 40));
                        }
                    }
                }
            }
        }
    }
    
    public int increaseBrightness(int color, int amount) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        
        r += amount;
        g += amount;
        b += amount;
        
        r = r > 255 ? 255 : r;
        g = g > 255 ? 255 : g;
        b = b > 255 ? 255 : b;
        
        return (255 << 24) | (r << 16) | (g << 8) | b;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(mapOverlay, 0, 0, null);
        
        if (!selectedCountry.isEmpty()) {
            Point mouse = MouseInfo.getPointerInfo().getLocation();

            int stringWidth = g.getFontMetrics().stringWidth(selectedCountry);
            
            g.setColor(Color.DARK_GRAY);
            g.fillRoundRect(mouse.x, mouse.y - 40, stringWidth + 20, 30, 10, 10);

            g.setColor(Color.WHITE);
            g.drawString(selectedCountry, mouse.x + 10, mouse.y - 20);
        }
    }
    
    
}
