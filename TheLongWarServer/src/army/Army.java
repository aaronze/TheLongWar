package army;

import army.unit.Unit;
import java.util.ArrayList;
import util.Util;

/**
 * @author Aaron
 */
public class Army {
    public ArrayList<Unit> units = new ArrayList<>();
    
    public void add(Unit unit) {
        units.add(unit);
    }
    
    public Unit getRandomUnit(boolean includeInjured, boolean includeDead) {
        // If include everything, use normal array
        if (includeInjured && includeDead) {
            return units.get(Util.random(units.size()));
        }
        
        // Construct a new list
        ArrayList<Unit> list = new ArrayList<>(units.size());
        for (Unit unit : units) {
            if (unit.isDead() && includeDead)
                list.add(unit);
            else if (unit.isInjured() && includeInjured)
                list.add(unit);
            else
                list.add(unit);
        }
        return list.get(Util.random(list.size()));
    }
}
