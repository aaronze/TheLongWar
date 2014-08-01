package data;

import database.Database;
import database.HardTable;
import database.Table;

/**
 * @author Aaron
 */
public class DataManager {
    public static Table unitTable;
    public static Table spellTable;
    
    static {
        unitTable = Database.getTable("unitTable");
        if (unitTable == null) {
            unitTable = new HardTable("unitTable");
            Database.addTable(unitTable);
            unitTable.addRows("NAME", "DMG", "RNG", "SPL", "MACC", "RACC", "SACC", "HP", "MANA", "SPD", "SPELL");
            unitTable.addEntry("Warrior", "4", "0", "0", "30", "0", "0", "100", "0", "1", "");
        }
        
        spellTable = Database.getTable("spellTable");
        if (spellTable == null) {
            spellTable = new HardTable("spellTable");
            Database.addTable(spellTable);
            spellTable.addRows("DD", "PBAOE", "TAOE", "ATKDBF", "SHEAL", "THEAL", "PBHEAL", "ATK", "CHARGES", "NAME", "DSC");
            spellTable.addEntry("20", "0", "0", "0", "0", "0", "0", "0", "1", "Bolt of Fire", "Throws a bolt of fire causing 20 damage to a single target.");
        }
    }
}
