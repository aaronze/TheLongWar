package battle;

import army.Army;
import army.unit.Unit;

/**
 * @author Aaron
 */
public class Simulator {
    public final static int AGGRESSOR_WON = 1;
    public final static int DEFENDER_WON = 2;
    public final static int BOTH_LOST_TIE = 3;
    public final static int BOTH_WON_TIE = 4;
    
    public final static int MAX_ROUNDS = 5;
    
    /**
     * Returns the winning army
     * 
     * @param aggressor
     * @param defender
     * @return Winner flag
     */
    public static Report simulateBattle(Army aggressor, Army defender) {
        // Build report from initial battle conditions
        Report report = new Report();
        
        report.attackerName = aggressor.getName();
        report.attackerMeleeTotal = aggressor.countUnits(Army.MELEE, false, false);
        report.attackerRangedTotal = aggressor.countUnits(Army.RANGED, false, false);
        report.attackerCasterTotal = aggressor.countUnits(Army.CASTER, false, false);
        report.attackerCommanderTotal = aggressor.countUnits(Army.COMMANDER, false, false);
        
        int attackerMeleeInjured = aggressor.countUnits(Army.MELEE, true, false) - report.attackerMeleeTotal;
        int attackerRangedInjured = aggressor.countUnits(Army.RANGED, true, false) - report.attackerRangedTotal;
        int attackerCasterInjured = aggressor.countUnits(Army.CASTER, true, false) - report.attackerCasterTotal;
        int attackerCommanderInjured = aggressor.countUnits(Army.COMMANDER, true, false) - report.attackerCommanderTotal;
        
        int attackerMeleeDead = aggressor.countUnits(Army.MELEE, false, true) - report.attackerMeleeTotal;
        int attackerRangedDead = aggressor.countUnits(Army.RANGED, false, true) - report.attackerRangedTotal;
        int attackerCasterDead = aggressor.countUnits(Army.CASTER, false, true) - report.attackerCasterTotal;
        int attackerCommanderDead = aggressor.countUnits(Army.COMMANDER, false, true) - report.attackerCommanderTotal;
        
        
        report.defenderName = defender.getName();
        report.defenderMeleeTotal = defender.countUnits(Army.MELEE, false, false);
        report.defenderRangedTotal = defender.countUnits(Army.RANGED, false, false);
        report.defenderCasterTotal = defender.countUnits(Army.CASTER, false, false);
        report.defenderCommanderTotal = defender.countUnits(Army.COMMANDER, false, false);
        
        int defenderMeleeInjured = defender.countUnits(Army.MELEE, true, false) - report.defenderMeleeTotal;
        int defenderRangedInjured = defender.countUnits(Army.RANGED, true, false) - report.defenderRangedTotal;
        int defenderCasterInjured = defender.countUnits(Army.CASTER, true, false) - report.defenderCasterTotal;
        int defenderCommanderInjured = defender.countUnits(Army.COMMANDER, true, false) - report.defenderCommanderTotal;
        
        int defenderMeleeDead = defender.countUnits(Army.MELEE, false, true) - report.defenderMeleeTotal;
        int defenderRangedDead = defender.countUnits(Army.RANGED, false, true) - report.defenderRangedTotal;
        int defenderCasterDead = defender.countUnits(Army.CASTER, false, true) - report.defenderCasterTotal;
        int defenderCommanderDead = defender.countUnits(Army.COMMANDER, false, true) - report.defenderCommanderTotal;
        
        
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
            // Terminate early if either side is too injured to continue
            if (aggressor.isInjured() || defender.isInjured()) break;
            
            // Melee Damage
            for (int turn = 0; turn < 2; turn++) {
                // Ranged
                Army a = turn == 0 ? aggressor : defender;
                Army b = turn == 0 ? defender : aggressor;
                
                // For every ranged unit, range attack a random enemy unit
                for (Unit unit : a.units) {
                    if (unit.isMelee()) {
                        unit.meleeAttack(b.getRandomUnit(true, false));
                    }
                }
            }
        }
        
        // Determine victor. Army wins if opposing army is Injured (>50% injuries). 
        // If both armies injured or neither then it's a tie.
        boolean aIsInjured = aggressor.isInjured();
        boolean bIsInjured = defender.isInjured();
        
        if (aIsInjured) {
            if (bIsInjured) 
                report.victoryStatus = BOTH_LOST_TIE;
            else
                report.victoryStatus = DEFENDER_WON;
        } else if (bIsInjured) {
            report.victoryStatus = AGGRESSOR_WON;
        } else {
            report.victoryStatus = BOTH_WON_TIE;
        }
        
        report.attackerMeleeInjuries = aggressor.countUnits(Army.MELEE, true, false) - aggressor.countUnits(Army.MELEE, false, false) - attackerMeleeInjured;
        report.attackerRangedInjuries = aggressor.countUnits(Army.RANGED, true, false) - aggressor.countUnits(Army.RANGED, false, false) - attackerRangedInjured;
        report.attackerCasterInjuries = aggressor.countUnits(Army.CASTER, true, false) - aggressor.countUnits(Army.CASTER, false, false) - attackerCasterInjured;
        report.attackerCommanderInjuries = aggressor.countUnits(Army.COMMANDER, true, false) - aggressor.countUnits(Army.COMMANDER, false, false) - attackerCommanderInjured;
        
        report.defenderMeleeInjuries = defender.countUnits(Army.MELEE, true, false) - defender.countUnits(Army.MELEE, false, false) - defenderMeleeInjured;
        report.defenderRangedInjuries = defender.countUnits(Army.RANGED, true, false) - defender.countUnits(Army.RANGED, false, false) - defenderRangedInjured;
        report.defenderCasterInjuries = defender.countUnits(Army.CASTER, true, false) - defender.countUnits(Army.CASTER, false, false) - defenderCasterInjured;
        report.defenderCommanderInjuries = defender.countUnits(Army.COMMANDER, true, false) - defender.countUnits(Army.COMMANDER, false, false) - defenderCommanderInjured;
        
        report.attackerMeleeDead = aggressor.countUnits(Army.MELEE, false, true) - aggressor.countUnits(Army.MELEE, false, false) - attackerMeleeDead;
        report.attackerRangedDead = aggressor.countUnits(Army.RANGED, false, true) - aggressor.countUnits(Army.RANGED, false, false) - attackerRangedDead;
        report.attackerCasterDead = aggressor.countUnits(Army.CASTER, false, true) - aggressor.countUnits(Army.CASTER, false, false) - attackerCasterDead;
        report.attackerCommanderDead = aggressor.countUnits(Army.COMMANDER, false, true) - aggressor.countUnits(Army.COMMANDER, false, false) - attackerCommanderDead;
        
        report.defenderMeleeDead = defender.countUnits(Army.MELEE, false, true) - defender.countUnits(Army.MELEE, false, false) - defenderMeleeDead;
        report.defenderRangedDead = defender.countUnits(Army.RANGED, false, true) - defender.countUnits(Army.RANGED, false, false) - defenderRangedDead;
        report.defenderCasterDead = defender.countUnits(Army.CASTER, false, true) - defender.countUnits(Army.CASTER, false, false) - defenderCasterDead;
        report.defenderCommanderDead = defender.countUnits(Army.COMMANDER, false, true) - defender.countUnits(Army.COMMANDER, false, false) - defenderCommanderDead;
        
        return report;
    }
}
