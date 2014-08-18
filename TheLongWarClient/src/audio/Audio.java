package audio;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @author Aaron
 */
public class Audio {
    private final File file;
    private final String identifier;
    private AudioInputStream input;
    private Clip clip;
    
    public Audio(File location, String id) {
        file = location;
        identifier = id;
    }
    
    public void play() {
        try {
            input = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loop() {
        try {
            input = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stop() {
        try {
            clip.stop();
            clip.close();
            clip = null;
            
            input.close();
            input = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean hasId(String id) {
        return id.equals(identifier);
    }
}
