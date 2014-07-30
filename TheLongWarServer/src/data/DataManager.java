package data;

import database.Database;
import database.Table;

/**
 * @author Aaron
 */
public class DataManager {
    public static Table unitTable;
    
    static {
        unitTable = Database.getTable("unitTable");
        if (unitTable == null) {
            unitTable = Database.addTable("unitTable");
            unitTable.addRows("NAME", "DMG", "RNG", "SPL", "MACC", "RACC", "SACC", "HP", "MANA", "SPD");
        }
    }
}
