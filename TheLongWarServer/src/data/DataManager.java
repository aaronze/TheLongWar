package data;

import database.Database;
import database.MemTable;
import database.Table;

/**
 * @author Aaron
 */
public class DataManager {
    public static Table unitTable;
    public static Table spellTable;
    
    static {
        unitTable = new MemTable("unitTable");
        Database.addTable(unitTable);
        unitTable.addRows("NAME", "DMG", "RNG", "SPL", "MACC", "RACC", "SACC", "HP", "MANA", "SPD", "SPELL");
        unitTable.addEntry("Warrior", "4", "0", "0", "30", "0", "0", "100", "0", "1", "");
        unitTable.addEntry("Archer", "0", "14", "0", "0", "30", "0", "80", "0", "1", "");
        unitTable.addEntry("Commander1", "12", "0", "0", "100", "0", "0", "200", "0", "1", "");
        
        spellTable = new MemTable("spellTable");
        Database.addTable(spellTable);
        spellTable.addRows("DD", "PBAOE", "TAOE", "ATKDBF", "SHEAL", "THEAL", "PBHEAL", "ATK", "CHARGES", "NAME", "DSC");
        spellTable.addEntry("20", "0", "0", "0", "0", "0", "0", "0", "1", "Bolt of Fire", "Throws a bolt of fire causing 20 damage to a single target.");
    }
}
