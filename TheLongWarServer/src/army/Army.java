package army;

import army.unit.Unit;
import java.util.ArrayList;

/**
 * @author Aaron
 */
public class Army {
    public ArrayList<Unit> units = new ArrayList<>();
    
    public void add(Unit unit) {
        units.add(unit);
    }
    
}
