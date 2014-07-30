package army.unit;

import data.DataManager;
import database.Table;

/**
 * @author Aaron
 */
public class Unit {
    private int meleeDamage = 4;
    private int rangedDamage = 0;
    private int spellDamage = 0;
    private double meleeAccuracy = 30;
    private double rangedAccuracy = 30;
    private double spellAccuracy = 30;
    private double maxHP = 100;
    private double hp = 100;
    private double maxMana = 0;
    private double mana = 0;
    private double speed = 1;
    
    public Unit(String template) {
        Table units = DataManager.unitTable.where("NAME", template);
        
        meleeDamage = Integer.parseInt(units.select("DMG"));
        rangedDamage = Integer.parseInt(units.select("RNG"));
        spellDamage = Integer.parseInt(units.select("SPL"));
        
        meleeAccuracy = Double.parseDouble(units.select("MACC"));
        rangedAccuracy = Double.parseDouble(units.select("RACC"));
        spellAccuracy = Double.parseDouble(units.select("SACC"));
        
        maxHP = hp = Double.parseDouble(units.select("HP"));
        maxMana = mana = Double.parseDouble(units.select("MANA"));
        speed = Double.parseDouble(units.select("SPD"));
    }
}
