package data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Aaron
 */
public class Nation {
    public String name = "";
    public Texture texture;
    public ArrayList<String> ownedCountries = new ArrayList<>();
    
    public Nation(String name) {
        this.name = name;
        
        BufferedImage mat = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int color = Color.red.getRGB();
                
                if (x >= 4)
                    color = Color.white.getRGB();
                
                mat.setRGB(x, y, color);
            }
        }
        
        texture = new Texture(mat);
    }
    
    public void add(String... countries) {
        ownedCountries.addAll(Arrays.asList(countries));
    }
    
    public Texture getTexture() {
        return texture;
    }
    
    public boolean contains(String country) {
        return ownedCountries.contains(country);
    }
}
