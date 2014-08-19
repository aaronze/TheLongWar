package ui;

import data.Nation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import math.Vec;
import secure.Handle;
import secure.Resources;

/**
 * @author Aaron
 */
public class World extends JPanel {
    public static String[][] lookup;
    public static ArrayList<Legend> legends;
    
    public final static int ALLIANCE_VIEW = 1;
    public final static int THREAT_VIEW = 2;
    
    public static int allianceViewType = ALLIANCE_VIEW;
    
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
        
        if (mX < 0 || mX >= 1.0 || mY < 0 || mY >= 1.0) 
            return "";
        
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
    private BufferedImage allianceOverlay;
    private Image mapScaled;
    private String selectedCountry = "";
    private String attackFromCountry = "";
    private Point attackFromLocation;
    
    private double zoom = 1;
    private int panX = 0;
    private int panY = 0;
    
    
    public World() {
        try {
            worldImage = ImageIO.read(Resources.getResource("world.jpg"));
            MediaTracker mt = new MediaTracker(this);
            mt.addImage(worldImage, 0);
            mt.waitForAll();
            
            mapOverlay = new BufferedImage(worldImage.getWidth(), worldImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            allianceOverlay = new BufferedImage(worldImage.getWidth(), worldImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            mapScaled = worldImage.getSubimage(panX, panY, (int)(mapOverlay.getWidth()/zoom), (int)(mapOverlay.getHeight()/zoom))
                        .getScaledInstance((int)(Home.self.getWidth()), (int)(Home.self.getHeight()), BufferedImage.SCALE_DEFAULT);
            buildAllianceOverlay();
            buildOverlay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                double mx = (e.getX() * 1.0 / getWidth()) / zoom - panX;
                double my = (e.getY() * 1.0 / getHeight()) / zoom - panY;

                attackFromCountry = getCountryAt(mx, my);
                attackFromLocation = e.getPoint();
            }
            
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!attackFromCountry.isEmpty()) {
                    double mx = (e.getX() * 1.0 / getWidth()) / zoom - panX;
                    double my = (e.getY() * 1.0 / getHeight()) / zoom - panY;
                    
                    String attackToCountry = getCountryAt(mx, my);
                    
                    if (!attackToCountry.isEmpty()) {
                        String attackFromNation = "";
                        String attackToNation = "";
                        for (Nation n : nations) {
                            if (n.contains(attackFromCountry)) attackFromNation = n.name;
                            if (n.contains(attackToCountry)) attackToNation = n.name;
                        }
                        
                        Handle.capture(attackFromCountry, attackFromNation, attackToCountry, attackToNation);
                        buildAllianceOverlay();
                    }
                }
                
                attackFromLocation = null;
                
                /*
                Misplaced Zoom function
                
                double midX = e.getX() / zoom;
                double midY = e.getY() / zoom;
                
                zoom *= 2;
                
                panX += midX - ((getWidth() / zoom) / 2);
                panY += midY - ((getHeight() / zoom) / 2);
                
                if (panX < 0) panX = 0;
                if (panY < 0) panY = 0;
                
                mapScaled = worldImage.getSubimage(panX, panY, (int)(mapOverlay.getWidth()/zoom), (int)(mapOverlay.getHeight()/zoom))
                        .getScaledInstance((int)(getWidth()), (int)(getHeight()), BufferedImage.SCALE_DEFAULT);
                buildOverlay();*/
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                double mx = (e.getX() * 1.0 / getWidth()) / zoom - panX;
                double my = (e.getY() * 1.0 / getHeight()) / zoom - panY;
                
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
                mapScaled = worldImage.getSubimage(0, 0, (int)(mapOverlay.getWidth()/zoom), (int)(mapOverlay.getHeight()/zoom))
                        .getScaledInstance((int)(getWidth()), (int)(getHeight()), BufferedImage.SCALE_DEFAULT);
                buildOverlay();
                buildAllianceOverlay();
            }
        });
    }
    
    private ArrayList<Nation> nations;
    public void buildAllianceOverlay() {
        // Only rebuild layer if there are changes
        if (Handle.updateNations()) {
            nations = Handle.getNations();

            int backgroundColor = new Color(0, 0, 0, 0).getRGB();

            double stepX = 1.0 / getWidth();
            double stepY = 1.0 / getHeight();

            if (allianceViewType == ALLIANCE_VIEW) {
                // For each pixel, if it represents a country look up it's nation. Color code appropriately.
                for (int x = 0; x < allianceOverlay.getWidth(); x++) {
                    for (int y = 0; y < allianceOverlay.getHeight(); y++) {
                        String country = getCountryAt(x * stepX, y * stepY);

                        int color = backgroundColor;

                        if (!country.isEmpty()) {
                            for (Nation nation : nations) {
                                if (nation.contains(country)) {
                                    color = (nation.getTexture().getColorAt(x, y) & 0xFFFFFF) | (160 << 24);
                                }
                            }
                        }

                        allianceOverlay.setRGB(x, y, color);
                    }
                }
            }
        }
    }
    
    public void buildOverlay() {
        if (this.getWidth() != 0) {
            mapOverlay.getGraphics().drawImage(mapScaled, 0, 0, null);
            
            if (!selectedCountry.isEmpty()) {
                double stepX = 1.0 / getWidth();
                double stepY = 1.0 / getHeight();
                
                for (int x = 0; x < getWidth(); x++) {
                    for (int y = 0; y < getHeight(); y++) {
                        String country = getCountryAt(x * stepX, y * stepY);
                        
                        int xP = (int)((x * zoom) - panX);
                        int yP = (int)((y * zoom) - panY);
                        
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
        g.drawImage(allianceOverlay, 0, 0, null);
        
        if (!selectedCountry.isEmpty()) {
            Point mouse = MouseInfo.getPointerInfo().getLocation();

            int stringWidth = g.getFontMetrics().stringWidth(selectedCountry);
            
            g.setColor(Color.DARK_GRAY);
            g.fillRoundRect(mouse.x, mouse.y - 40, stringWidth + 20, 30, 10, 10);

            g.setColor(Color.WHITE);
            g.drawString(selectedCountry, mouse.x + 10, mouse.y - 20);
        }
        
        if (attackFromLocation != null) {
            // Draw arrow represinting attack
            Point mouse = MouseInfo.getPointerInfo().getLocation();
            
            Vec source = new Vec(attackFromLocation.x, attackFromLocation.y);
            Vec dest = new Vec(mouse.x + 4, mouse.y - 15);
            
            double arrowSize = 20;
            
            Vec dir = dest.subtract(source).normalize();
            
            Vec DL = source.add(dir.rotate(Math.PI / 2).scale(0.5).scale(arrowSize));
            Vec DR = source.add(dir.rotate(-(Math.PI / 2)).scale(0.5).scale(arrowSize));
            
            Vec ML = dest.add(dir.rotate(-(9 * Math.PI / 10)).scale(0.5).scale(arrowSize));
            Vec UL = dest.add(dir.rotate(-(3 * Math.PI / 4)).scale(0.8).scale(arrowSize));
            
            Vec MR = dest.add(dir.rotate(9 * Math.PI / 10).scale(0.5).scale(arrowSize));
            Vec UR = dest.add(dir.rotate(3 * Math.PI / 4).scale(0.8).scale(arrowSize));
            
            Vec U = dest;
            
            int[] xPoints = new int[] {
                (int)DL.x,(int)MR.x,(int)UR.x,(int)U.x,(int)UL.x,(int)ML.x,(int)DR.x
            };
            
            int[] yPoints = new int[] {
                (int)DL.y,(int)MR.y,(int)UR.y,(int)U.y,(int)UL.y,(int)ML.y,(int)DR.y
            };
            
            int len = 7;
            
            g.setColor(Color.RED);
            g.fillPolygon(xPoints, yPoints, len);
        }
    }
    
    
}
