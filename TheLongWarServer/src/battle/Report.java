package battle;

/**
 * @author Aaron
 */
public class Report {
    public String attackerName, defenderName;
    
    public int attackerCommanderTotal, defenderCommanderTotal;
    public int attackerRangedTotal, defenderRangedTotal;
    public int attackerCasterTotal, defenderCasterTotal;
    public int attackerMeleeTotal, defenderMeleeTotal;
    
    public int attackerCommanderInjuries, defenderCommanderInjuries;
    public int attackerRangedInjuries, defenderRangedInjuries;
    public int attackerCasterInjuries, defenderCasterInjuries;
    public int attackerMeleeInjuries, defenderMeleeInjuries;
    
    public int attackerCommanderDead, defenderCommanderDead;
    public int attackerRangedDead, defenderRangedDead;
    public int attackerCasterDead, defenderCasterDead;
    public int attackerMeleeDead, defenderMeleeDead;
    
    public int victoryStatus;
    
    public int attackerXPEarned, defenderXPEarned;
    public int attackerArmyXP, defenderArmyXP;
    public int attackerGoldEarned, defenderGoldEarned;
    
    public String toString() {
        String s = "";
        
        s += attackerName + " versus " + defenderName + "\n";
        
        if (victoryStatus == Simulator.AGGRESSOR_WON) s += attackerName + " won\n";
        else if (victoryStatus == Simulator.DEFENDER_WON) s += defenderName + " won\n";
        else s += "Battle was a Tie\n";
        
        s += "\n";
        s += attackerName + "\n";
        s += "------------------\n";
        s += "Type\tTotal\tInj'd\tDead\n";
        s += "Cmmdr:\t" + attackerCommanderTotal + "\t" + attackerCommanderInjuries + "\t" + attackerCommanderDead + "\n";
        s += "Melee:\t" + attackerMeleeTotal + "\t" + attackerMeleeInjuries + "\t" + attackerMeleeDead + "\n";
        s += "Ranged:\t" + attackerRangedTotal + "\t" + attackerRangedInjuries + "\t" + attackerRangedDead + "\n";
        s += "Caster:\t" + attackerCasterTotal + "\t" + attackerCasterInjuries + "\t" + attackerCasterDead + "\n";
        s += "------------------\n";
        s += "\n";
        s += defenderName + "\n";
        s += "------------------\n";
        s += "Type\tTotal\tInj'd\tDead\n";
        s += "Cmmdr:\t" + defenderCommanderTotal + "\t" + defenderCommanderInjuries + "\t" + defenderCommanderDead + "\n";
        s += "Melee:\t" + defenderMeleeTotal + "\t" + defenderMeleeInjuries + "\t" + defenderMeleeDead + "\n";
        s += "Ranged:\t" + defenderRangedTotal + "\t" + defenderRangedInjuries + "\t" + defenderRangedDead + "\n";
        s += "Caster:\t" + defenderCasterTotal + "\t" + defenderCasterInjuries + "\t" + defenderCasterDead + "\n";
        s += "------------------\n";
        s += "\n";
        
        return s;
    }
}
