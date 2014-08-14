package secure;

import data.Nation;
import data.Texture;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 * @author Aaron
 */
public class Handle {
    public static ArrayList<String> australiaCountries = new ArrayList<>();
    public static ArrayList<String> americanaCountries = new ArrayList<>();
    public static ArrayList<String> chinaCountries = new ArrayList<>();
    public static ArrayList<String> russiaCountries = new ArrayList<>();
    public static ArrayList<String> africaCountries = new ArrayList<>();
    public static ArrayList<String> europeCountries = new ArrayList<>();
    public static ArrayList<String> southAmericaCountries = new ArrayList<>();
    
    static {
        australiaCountries.addAll(Arrays.asList("Australia", "New Zealand", "Papua New Guinea", "Indonesia", "Malaysia", "Philippines"));
        americanaCountries.addAll(Arrays.asList("United States", "Canada", "Alaska", "Mexico", "Greenland", "Iceland", "Guatemala", "Honduras", "Nicaragua", "Costa Rica", "Panama"));
        chinaCountries.addAll(Arrays.asList("China", "Taiwan", "India", "Thailand", "Vietnam", "Burma", "Bangladesh", "Cambodia", "South Korea", "North Korea", "Japan"));
        russiaCountries.addAll(Arrays.asList("Russia", "Armenia", "Mongolia", "Kazakhstan", "Finland", "Sweden", "Norway", "Uzbekistan", "Kyrgyzstan", "Georgia", "Turkey", "Tajikstan"));
        africaCountries.addAll(Arrays.asList("Quwait", "Qatar", "Iran", "Iraq", "Saudi Arabia", "Jordan", "Israel", "Syria", "Azerbaijan", "Turkmenistan", "United Arab Emirates", "Oman", "Yemen", "Afghanistan", "Pakistan"));
        europeCountries.addAll(Arrays.asList("Albania", "Austria", "Belarus", "Bosnia and Herzegovina", "Croatia", "Denmark", "Greece", "Italy", "Lithuania", "Macedonia", "Netherlands", "Poland", "Romania", "Slovakia", "Spain", "Switzerland", "United Kingdom",
                "Belgium", "Bulgaria", "Czech Republic", "Estonia", "France", "Germany", "Hungary", "Ireland", "Latvia", "Luxembourg", "Moldovia", "Portugal", "Serbia", "Slovenia", "Ukraine"));
        southAmericaCountries.addAll(Arrays.asList("Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "French Guiana", "Guyana", "Panama", "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela"));
    }
    
    public static void capture(String fromCountry, String toCountry) {
        ArrayList<String> from = getOwner(fromCountry);
        ArrayList<String> to = getOwner(toCountry);
        
        to.remove(toCountry);
        from.add(toCountry);
    }
    
    public static ArrayList<String> getOwner(String country) {
        if (australiaCountries.contains(country)) return australiaCountries;
        if (americanaCountries.contains(country)) return americanaCountries;
        if (chinaCountries.contains(country)) return chinaCountries;
        if (russiaCountries.contains(country)) return russiaCountries;
        if (africaCountries.contains(country)) return africaCountries;
        if (europeCountries.contains(country)) return europeCountries;
        if (southAmericaCountries.contains(country)) return southAmericaCountries;
        
        ArrayList<String> fakeOwner = new ArrayList<>();
        fakeOwner.add(country);
        return fakeOwner;
    }
    
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
        test1.add(australiaCountries.toArray(new String[0]));
        nations.add(test1);
        
        Nation test2 = new Nation("Americana");
        BufferedImage image2 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            image2 = ImageIO.read(Resources.getResource("usa.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test2.texture = new Texture(image2);
        test2.add(americanaCountries.toArray(new String[0]));
        nations.add(test2);
        
        Nation test3 = new Nation("China");
        BufferedImage image3 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            image3 = ImageIO.read(Resources.getResource("china.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test3.texture = new Texture(image3);
        test3.add(chinaCountries.toArray(new String[0]));
        nations.add(test3);
        
        Nation test4 = new Nation("Russia");
        BufferedImage image4 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            image4 = ImageIO.read(Resources.getResource("russia.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test4.texture = new Texture(image4);
        test4.add(russiaCountries.toArray(new String[0]));
        nations.add(test4);
        
        Nation test5 = new Nation("Middle East");
        BufferedImage image5 = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            image5 = ImageIO.read(Resources.getResource("middleeast.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        test5.texture = new Texture(image5);
        test5.add(africaCountries.toArray(new String[0]));
        nations.add(test5);
        
        Nation europe = new Nation("Europe");
        BufferedImage europeImage = new BufferedImage (16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            europeImage = ImageIO.read(Resources.getResource("europe.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        europe.texture = new Texture(europeImage);
        europe.add(europeCountries.toArray(new String[0]));
        nations.add(europe);
        
        Nation africa = new Nation("Africa");
        BufferedImage africaImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            africaImage = ImageIO.read(Resources.getResource("africa.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        africa.texture = new Texture(africaImage);
        africa.add(africaCountries.toArray(new String[0]));
        
        nations.add(africa);
        
        Nation southAmerica = new Nation("South America");
        BufferedImage southAmericaImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        try {
            southAmericaImage = ImageIO.read(Resources.getResource("southamerica.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        southAmerica.texture = new Texture(southAmericaImage);
        southAmerica.add(southAmericaCountries.toArray(new String[0]));
        nations.add(southAmerica);
        
        return nations;
    }
}
