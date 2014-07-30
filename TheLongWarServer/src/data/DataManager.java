package data;

import database.Database;
import database.HardTable;
import database.Table;

/**
 * @author Aaron
 */
public class DataManager {
    public static Table unitTable;
    
    static {
        unitTable = Database.getTable("unitTable");
        if (unitTable == null) {
            unitTable = new HardTable("unitTable");
            Database.addTable(unitTable);
            unitTable.addRows("NAME", "DMG", "RNG", "SPL", "MACC", "RACC", "SACC", "HP", "MANA", "SPD");
            unitTable.addEntry("Warrior", "4", "0", "0", "30", "0", "0", "100", "0", "1");
        }
    }
}
