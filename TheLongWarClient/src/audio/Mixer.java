package audio;

import java.util.ArrayList;

/**
 * @author Aaron
 */
public class Mixer {
    private static ArrayList<Audio> sounds = new ArrayList<>();
    
    public static void play(String id) {
        for (Audio audio : sounds) {
            if (audio.hasId(id)) {
                audio.play();
            }
        }
    }
    
    public static void loop(String id) {
        for (Audio audio : sounds) {
            if (audio.hasId(id)) {
                audio.loop();
            }
        }
    }
    
    public static void register(Audio audio) {
        sounds.add(audio);
    }
}
