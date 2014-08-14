package secure;

import data.Nation;
import data.Texture;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * @author Aaron
 */
public class Handle {
    public static ArrayList<Nation> getNations() {
        ArrayList<Nation> nations = new ArrayList<>();
        
        // TODO: Connect to server and acquire the latest nation data
        Nation test1 = new Nation("Australasia");
        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            image = ImageIO.read(Resources.getResource("australasia.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test1.texture = new Texture(image);
        test1.add("Australia", "New Zealand", "Papua New Guinea", "Indonesia", "Malaysia", "Philippines");
        nations.add(test1);
        
        Nation test2 = new Nation("Americana");
        BufferedImage image2 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            image2 = ImageIO.read(Resources.getResource("usa.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test2.texture = new Texture(image2);
        test2.add("United States", "Canada", "Alaska", "Mexico", "Greenland", "Iceland", "Guatemala", "Honduras", "Nicaragua", "Costa Rica", "Panama");
        nations.add(test2);
        
        Nation test3 = new Nation("China");
        BufferedImage image3 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            image3 = ImageIO.read(Resources.getResource("china.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test3.texture = new Texture(image3);
        test3.add("China", "India", "Thailand", "Vietnam", "Burma", "Bangladesh", "Cambodia", "South Korea", "North Korea", "Japan");
        nations.add(test3);
        
        Nation test4 = new Nation("Russia");
        BufferedImage image4 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            image4 = ImageIO.read(Resources.getResource("russia.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test4.texture = new Texture(image4);
        test4.add("Russia", "Armenia", "Mongolia", "Kazakhstan", "Finland", "Sweden", "Norway", "Uzbekistan", "Kyrgyzstan", "Georgia", "Turkey", "Tajikstan");
        nations.add(test4);
        
        Nation test5 = new Nation("Middle East");
        BufferedImage image5 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            image5 = ImageIO.read(Resources.getResource("middleeast.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test5.texture = new Texture(image5);
        test5.add("Quwait", "Qatar", "Iran", "Iraq", "Saudi Arabia", "Jordan", "Israel", "Syria", "Azerbaijan", "Turkmenistan", "United Arab Emirates", "Oman", "Yemen", "Afghanistan", "Pakistan");
        nations.add(test5);
        
        return nations;
    }
}
