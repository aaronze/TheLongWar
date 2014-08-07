package secure;

import data.Nation;
import java.util.ArrayList;

/**
 * @author Aaron
 */
public class Handle {
    public static ArrayList<Nation> getNations() {
        ArrayList<Nation> nations = new ArrayList<>();
        
        // TODO: Connect to server and acquire the latest nation data
        Nation test1 = new Nation("Australasia");
        test1.add("Australia", "New Zealad", "Papua New Guinea", "Indonesia");
        nations.add(test1);
        
        Nation test2 = new Nation("Americana");
        test2.add("United States", "Canada", "Alaska");
        nations.add(test2);
        
        return nations;
    }
}
