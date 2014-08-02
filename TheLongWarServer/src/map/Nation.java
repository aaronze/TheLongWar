package map;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Aaron
 */
public class Nation {
    public String name;
    public String motd;
    public Color color;
    
    public ArrayList<Nation> allies = new ArrayList<>();
    public ArrayList<Nation> enemies = new ArrayList<>();
    public ArrayList<Nation> ceasefire = new ArrayList<>();
    
}
