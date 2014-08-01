package battle;

import army.Army;
import army.unit.Unit;

/**
 * @author Aaron
 */
public class Simulator {
    public final static int MAX_ROUNDS = 5;
    
    /**
     * Returns the winning army
     * 
     * @param aggressor
     * @param defender
     * @return 
     */
    public static Army simulateBattle(Army aggressor, Army defender) {
        // Aggressor gets battle advantage
        
        // Ranged Damage only happens once per battle
        for (int turn = 0; turn < 2; turn++) {
            // Ranged
            Army a = turn == 0 ? aggressor : defender;
            Army b = turn == 0 ? defender : aggressor;

            // For every ranged unit, range attack a random enemy unit
            for (Unit unit : a.units) {
                if (unit.isRanged()) {
                    unit.rangedAttack(b.getRandomUnit(true, false));
                }
            }
        }
        
        // Spell Damage only happens once per battle
        for (int turn = 0; turn < 2; turn++) {
            // Ranged
            Army a = turn == 0 ? aggressor : defender;
            Army b = turn == 0 ? defender : aggressor;

            // For every caster unit, spell attack the opposing army
            for (Unit unit : a.units) {
                if (unit.isSpellCaster()) {
                    unit.spellAttack(a, b);
                }
            }
        }
        
        for (int round = 0; round < MAX_ROUNDS; round++) {
            // Ranged Damage
            for (int turn = 0; turn < 2; turn++) {
                // Ranged
                Army a = turn == 0 ? aggressor : defender;
                Army b = turn == 0 ? defender : aggressor;
                
                // For every ranged unit, range attack a random enemy unit
                for (Unit unit : a.units) {
                    if (unit.isRanged()) {
                        unit.rangedAttack(b.getRandomUnit(true, false));
                    }
                }
            }
            
            // Melee Damage
            
        }
        
        return aggressor;
    }
}
