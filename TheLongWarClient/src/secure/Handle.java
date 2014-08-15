package secure;

import data.Codes;
import data.Nation;
import data.Texture;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    
    public static long lastUpdated = new Date().getTime();

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
        
        String packet = Network.request(""+Codes.REQUEST_GET_NATION_INFO);
        try {
            String[] natty = packet.split(";");
            for (String na : natty) {
                String[] info = na.split(":");

                Nation nation = new Nation(info[0]);
                nation.texture = new Texture(ImageIO.read(Resources.getResource(info[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        packet = Network.request(""+Codes.REQUEST_GET_CURRENT_STATE);
        String[] s = packet.split(" ");

        for (String item : s) {
            int token = Integer.parseInt(item);
            String country = Codes.getCountryName(token);
            String nation = Codes.getNationName(token);
            Nation nat = null;
            for (Nation n : nations) {
                if (n.name.equals(nation)) {
                    nat = n;
                    break;
                }
            }
            if (nat == null) {
                nat = new Nation(nation);
            }
            nat.add(country);
        }
        
        return nations;
    }
}
