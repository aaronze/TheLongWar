package mapstitcher;

import java.awt.Color;
import java.awt.Frame;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * @author Aaron
 */
public class MapStitcher {
    static int width = 4050;
    static int height = 2025;
    
    static File mapLocation = new File("C:\\Users\\Aarib\\Pictures\\maps\\territories");
    
    static Color background = Color.BLACK;
    
    static BufferedImage composite;
    
    public static void main(String[] args) {
        composite = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        // Blend constituents
        blendImages(mapLocation);
        
        // Output the result
        try {
            ImageIO.write(composite, "jpg", new File("output.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void blendImages(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles())
                blendImages(f);
        } else {
            try {
                BufferedImage image = ImageIO.read(file);
                MediaTracker mt = new MediaTracker(new Frame());
                mt.addImage(image, 0);
                mt.waitForAll();
                if (!file.getName().contains("jpg")) return;
                blend(file.getName(), image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void blend(String name, BufferedImage image) {
        // Generate a new color for this image
        Color color = nextColor();
        System.out.println(name + ": " + color.getRed() + "," + color.getGreen() + "," + color.getBlue());
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pix = image.getRGB(x, y);
                
                int a = (pix >> 24) & 0xFF;
                int r = (pix >> 16) & 0xFF;
                int g = (pix >> 8) & 0xFF;
                int b = pix & 0xFF;
                
                if (a > 128 && r < 100 && g < 100 && b < 100) {
                    // Add to composite
                    composite.setRGB(x, y, color.getRGB());
                }
            }
        }
    }
    
    static double colorRedFrequency = 1.666;
    static double colorGreenFrequency = 2.666;
    static double colorBlueFrequency = 3.666;
    static double colorAmplitude = 127;
    static double colorCentre = 128;
    static int index = 0;
    
    public static Color nextColor() {
        double red = Math.sin(colorRedFrequency*index + 0) * colorAmplitude + colorCentre;
        double green = Math.sin(colorGreenFrequency*index + 2) * colorAmplitude + colorCentre;
        double blue = Math.sin(colorBlueFrequency*index + 4) * colorAmplitude + colorCentre;
        
        index++;
        
        return new Color((int)red, (int)green, (int)blue);
    }
}
