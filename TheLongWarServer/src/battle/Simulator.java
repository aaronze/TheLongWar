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
        
        for (int round = 0; round < MAX_ROUNDS; round++) {
            // Ranged Damage
            for (int turn = 0; turn < 2; turn++) {
                // Ranged
                Army a = turn == 0 ? aggressor : defender;
                Army b = turn == 0 ? defender : aggressor;
                
                // Calculate total damage stats
                int rangedDamageTokens = 0;
                int rangedTotalDamage = 0;
                
                for (Unit unit : a.units) {
                    
                }
            }
        }
        
        return aggressor;
    }
}
