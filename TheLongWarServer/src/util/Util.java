package util;

/**
 * @author Aaron
 */
public class Util {
    public static int random(int max) {
        return (int)(Math.random()*max);
    }
    
    public static boolean random(double chance) {
        return Math.random() <= chance;
    }
}
