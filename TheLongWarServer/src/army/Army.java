package army;

import army.unit.DummyUnit;
import army.unit.Unit;
import java.util.ArrayList;
import util.Util;

/**
 * @author Aaron
 */
public class Army {
    public final static int NONE =      0x0000;
    public final static int MELEE =     0x000F;
    public final static int RANGED =    0x00F0;
    public final static int CASTER =    0x0F00;
    public final static int COMMANDER = 0xF000;
    public final static int ALL =       0xFFFF;
    
    public final static Unit DUMMY = new DummyUnit();
    public String name;
    public ArrayList<Unit> units = new ArrayList<>();
    
    public Army(String leader) {
        name = leader;
    }
    
    public void add(Unit unit) {
        units.add(unit);
    }
    
    /**
     * Injured is if more then 50% of units are injured or dead.
     * 
     * @return 
     */
    public boolean isInjured() {
        int totalUnits = units.size();
        int injuredOrDead = 0;
        
        for (Unit unit : units) {
            if (unit.isInjured() || unit.isDead())
                injuredOrDead++;
        }
        
        return (injuredOrDead > totalUnits / 2);
    }
    
    public Unit getRandomUnit(boolean includeInjured, boolean includeDead) {
        if (units.isEmpty()) return DUMMY;
        
        // If include everything, use normal array
        if (includeInjured && includeDead) {
            return units.get(Util.random(units.size()));
        }
        
        // Construct a new list
        ArrayList<Unit> list = new ArrayList<>(units.size());
        for (Unit unit : units) {
            if (unit.isDead()) {
                if (includeDead) list.add(unit);
            } else if (unit.isInjured()) {
                if (includeInjured) list.add(unit);
            } else {
                list.add(unit);
            }
        }
        
        if (list.isEmpty()) return DUMMY;
        
        return list.get(Util.random(list.size()));
    }
    
    public int countUnits(int includeTypes, boolean includeInjured, boolean includeDead) {
        if (units.isEmpty()) return 0;
        
        int count = 0;
        
        for (Unit unit : units) {
            // Check if including this type of unit
            int unitType = unit.getType();
            if ((includeTypes & unitType) == unitType) {
                // Check if meets injured and dead requirements
                if (unit.isDead()) {
                    if (includeDead) count++;
                } else if (unit.isInjured()) {
                    if (includeInjured) count++;
                } else { 
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public String getName() {
        return name;
    }
}
