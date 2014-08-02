package army.unit;

import army.Army;
import data.DataManager;
import database.Table;
import util.Util;

/**
 * @author Aaron
 */
public class Unit {
    public String name;
    private int spellID = 0;
    private int meleeDamage = 4;
    private int rangedDamage = 0;
    private int spellDamage = 0;
    private double meleeAccuracy = 0.3;
    private double rangedAccuracy = 0.3;
    private double spellAccuracy = 0.3;
    private double maxHP = 100;
    private double hp = 100;
    private double maxMana = 0;
    private double mana = 0;
    private double speed = 1;
    
    public Unit(String template) {
        name = template;
        
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
    
    public boolean isCommander() {
        return this instanceof Commander;
    }
    
    public boolean isRanged() {
        return rangedDamage > 0 && rangedAccuracy > 0;
    }
    
    public void rangedAttack(Unit unit) {
        double damage = 0;
        
        if (Util.random(rangedAccuracy)) 
            damage += rangedDamage;
        
        unit.takeDamage(damage);
    }
    
    public boolean isSpellCaster() {
        return spellDamage > 0 && spellAccuracy > 0;
    }
    
    public void spellAttack(Army allies, Army enemy) {
        
    }
    
    public boolean isMelee() {
        return meleeDamage > 0 && meleeAccuracy > 0;
    }
    
    public void meleeAttack(Unit unit) {
        double damage = 0;
        
        if (Util.random(meleeAccuracy)) 
            damage += meleeDamage;
        
        unit.takeDamage(damage);
    }
    
    public boolean isHealthy() {
        return hp >= maxHP/2;
    }
    
    public boolean isInjured() {
        return hp <= maxHP/2;
    }
    
    public boolean isDead() {
        return hp <= 0;
    }
    
    public void takeDamage(double amount) {
        hp -= amount;
    }
    
    public int getType() {
        if (isRanged()) return Army.RANGED;
        if (isSpellCaster()) return Army.CASTER;
        if (isCommander()) return Army.COMMANDER;
        if (isMelee()) return Army.MELEE;
        
        return Army.NONE;
    }
}
