package data;

import java.awt.image.BufferedImage;

/**
 * @author Aaron
 */
public class Texture {
    BufferedImage material;
    
    public Texture(BufferedImage mat) {
        material = mat;
    }
    
    public int getColorAt(int x, int y) {
        return material.getRGB(x % material.getWidth(), y % material.getHeight());
    }
}
