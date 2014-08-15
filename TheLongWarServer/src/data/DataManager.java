package data;

import database.Database;
import database.HardTable;
import database.MemTable;
import database.Table;

/**
 * @author Aaron
 */
public class DataManager {
    public static Table countryTable;
    public static Table nationTable;
    
    public static Table captureTable;
    
    public static Table unitTable;
    public static Table spellTable;
    
    static {
        countryTable = Database.getTable("countryTable");
        if (countryTable == null) {
            countryTable = new HardTable("countryTable");
            Database.addTable(countryTable);
            countryTable.addRows("CountryName", "CountryOwner");
            addDefaultCountries();
        }
        
        nationTable = Database.getTable("nationTable");
        if (nationTable == null) {
            nationTable = new HardTable("nationTable");
            Database.addTable(nationTable);
            nationTable.addRows("NationName", "NationCapital", "NationBanner");
            addDefaultNations();
        }
        
        captureTable = Database.getTable("captureTable");
        if (captureTable == null) {
            captureTable = new HardTable("captureTable");
            Database.addTable(captureTable);
            captureTable.addRows("captureID", "time", "attackingCountry", "attackingNation", "defendingCountry", "defendingNation");
        }
        
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
    
    public static void reset() {
        captureTable.removeAllEntries();
        
        nationTable.removeAllEntries();
        addDefaultNations();
        
        countryTable.removeAllEntries();
        addDefaultCountries();
    }
    
    private static void addDefaultNations() {
        nationTable.addEntry("Australasia", "Australia", "australasia.png");
        nationTable.addEntry("China", "China", "china.png");
        nationTable.addEntry("Americana", "United States", "usa.png");
        nationTable.addEntry("South America", "Peru", "southamerica.png");
        nationTable.addEntry("Africa", "South Africa", "africa.png");
        nationTable.addEntry("Europe", "Europe", "europe.png");
        nationTable.addEntry("Middle East", "Iraq", "middleeast.png");
        nationTable.addEntry("Russia", "Russia", "russia.png");
    }
    
    private static void addDefaultCountries() {
        addDefaultAustralasia();
        addDefaultChina();
        addDefaultAmericana();
        addDefaultSouthAmerica();
        addDefaultAfrica();
        addDefaultMiddleEast();
        addDefaultEurope();
        addDefaultRussia();
    }
    
    private static void addDefaultRussia() {
        countryTable.addEntry("Russia", "Russia");
        countryTable.addEntry("Armenia", "Russia");
        countryTable.addEntry("Mongolia", "Russia");
        countryTable.addEntry("Kazakhstan", "Russia");
        countryTable.addEntry("Finland", "Russia");
        countryTable.addEntry("Sweden", "Russia");
        countryTable.addEntry("Norway", "Russia");
        countryTable.addEntry("Uzbekistan", "Russia");
        countryTable.addEntry("Kyrgyzstan", "Russia");
        countryTable.addEntry("Georgia", "Russia");
        countryTable.addEntry("Turkey", "Russia");
        countryTable.addEntry("Tajikstan", "Russia");
    }
    
    private static void addDefaultEurope() {
        countryTable.addEntry("Albania", "Europe");
        countryTable.addEntry("Austria", "Europe");
        countryTable.addEntry("Belarus", "Europe");
        countryTable.addEntry("Bosnia and Herzegovina", "Europe");
        countryTable.addEntry("Croatia", "Europe");
        countryTable.addEntry("Denmark", "Europe");
        countryTable.addEntry("Greece", "Europe");
        countryTable.addEntry("Italy", "Europe");
        countryTable.addEntry("Lithuania", "Europe");
        countryTable.addEntry("Macedonia", "Europe");
        countryTable.addEntry("Netherlands", "Europe");
        countryTable.addEntry("Poland", "Europe");
        countryTable.addEntry("Romania", "Europe");
        countryTable.addEntry("Slovakia", "Europe");
        countryTable.addEntry("Spain", "Europe");
        countryTable.addEntry("Switzerland", "Europe");
        countryTable.addEntry("United Kingdom", "Europe");
        countryTable.addEntry("Belgium", "Europe");
        countryTable.addEntry("Bulgaria", "Europe");
        countryTable.addEntry("Czech Republic", "Europe");
        countryTable.addEntry("Estonia", "Europe");
        countryTable.addEntry("France", "Europe");
        countryTable.addEntry("Germany", "Europe");
        countryTable.addEntry("Hungary", "Europe");
        countryTable.addEntry("Ireland", "Europe");
        countryTable.addEntry("Latvia", "Europe");
        countryTable.addEntry("Luxembourg", "Europe");
        countryTable.addEntry("Moldovia", "Europe");
        countryTable.addEntry("Portugal", "Europe");
        countryTable.addEntry("Serbia", "Europe");
        countryTable.addEntry("Slovenia", "Europe");
        countryTable.addEntry("Ukraine", "Europe");
    }
    
    private static void addDefaultMiddleEast() {
        countryTable.addEntry("Quwait", "Middle East");
        countryTable.addEntry("Qatar", "Middle East");
        countryTable.addEntry("Iran", "Middle East");
        countryTable.addEntry("Iraq", "Middle East");
        countryTable.addEntry("Saudi Arabia", "Middle East");
        countryTable.addEntry("Jordan", "Middle East");
        countryTable.addEntry("Israel", "Middle East");
        countryTable.addEntry("Syria", "Middle East");
        countryTable.addEntry("Azerbaijan", "Middle East");
        countryTable.addEntry("Turkmenistan", "Middle East");
        countryTable.addEntry("United Arab Emirates", "Middle East");
        countryTable.addEntry("Oman", "Middle East");
        countryTable.addEntry("Yemen", "Middle East");
        countryTable.addEntry("Afghanistan", "Middle East");
        countryTable.addEntry("Pakistan", "Middle East");
    }
    
    private static void addDefaultAfrica() {
        countryTable.addEntry("Algeria", "Africa");
        countryTable.addEntry("Burkina Kaso", "Africa");
        countryTable.addEntry("Angola", "Africa");
        countryTable.addEntry("Benin", "Africa");
        countryTable.addEntry("Botswana", "Africa");
        countryTable.addEntry("Burundi", "Africa");
        countryTable.addEntry("Camaroon", "Africa");
        countryTable.addEntry("Central African Republic", "Africa");
        countryTable.addEntry("Chad", "Africa");
        countryTable.addEntry("Cote d'Ivoire", "Africa");
        countryTable.addEntry("Democratic Republic of the Congo", "Africa");
        countryTable.addEntry("Egypt", "Africa");
        countryTable.addEntry("Equatorial Guinea", "Africa");
        countryTable.addEntry("Ethiopia", "Africa");
        countryTable.addEntry("Gabon", "Africa");
        countryTable.addEntry("Ghana", "Africa");
        countryTable.addEntry("Ginnea-Bissau", "Africa");
        countryTable.addEntry("Guinea", "Africa");
        countryTable.addEntry("Kenya", "Africa");
        countryTable.addEntry("Lesotho", "Africa");
        countryTable.addEntry("Liberia", "Africa");
        countryTable.addEntry("Libya", "Africa");
        countryTable.addEntry("Madagascar", "Africa");
        countryTable.addEntry("Malawi", "Africa");
        countryTable.addEntry("Mali", "Africa");
        countryTable.addEntry("Mauritania", "Africa");
        countryTable.addEntry("Morocco", "Africa");
        countryTable.addEntry("Mozambique", "Africa");
        countryTable.addEntry("Namibia", "Africa");
        countryTable.addEntry("Niger", "Africa");
        countryTable.addEntry("Nigeria", "Africa");
        countryTable.addEntry("Republic of the Congo", "Africa");
        countryTable.addEntry("Rwanda", "Africa");
        countryTable.addEntry("Senegal", "Africa");
        countryTable.addEntry("Sierra Leone", "Africa");
        countryTable.addEntry("Somalia", "Africa");
        countryTable.addEntry("South Africa", "Africa");
        countryTable.addEntry("Sudan", "Africa");
        countryTable.addEntry("Tanzania", "Africa");
        countryTable.addEntry("Togo", "Africa");
        countryTable.addEntry("Tunisia", "Africa");
        countryTable.addEntry("Uganda", "Africa");
        countryTable.addEntry("Western Sahara", "Africa");
        countryTable.addEntry("Zambia", "Africa");
        countryTable.addEntry("Zimbabwe", "Africa");
    }
    
    private static void addDefaultAmericana() {
        countryTable.addEntry("United States", "Americana");
        countryTable.addEntry("Canada", "Americana");
        countryTable.addEntry("Alaska", "Americana");
        countryTable.addEntry("Mexico", "Americana");
        countryTable.addEntry("Greenland", "Americana");
        countryTable.addEntry("Iceland", "Americana");
        countryTable.addEntry("Guatemala", "Americana");
        countryTable.addEntry("Honduras", "Americana");
        countryTable.addEntry("Nicaragua", "Americana");
        countryTable.addEntry("Costa Rica", "Americana");
        countryTable.addEntry("Panama", "Americana");
    }
    
    private static void addDefaultAustralasia() {
        countryTable.addEntry("Australia", "Australasia");
        countryTable.addEntry("New Zealand", "Australasia");
        countryTable.addEntry("Papua New Guinea", "Australasia");
        countryTable.addEntry("Indonesia", "Australasia");
        countryTable.addEntry("Malaysia", "Australasia");
        countryTable.addEntry("Philippines", "Australasia");
    }
    
    private static void addDefaultChina() {
        countryTable.addEntry("China", "China");
        countryTable.addEntry("Taiwan", "China");
        countryTable.addEntry("India", "China");
        countryTable.addEntry("Thailand", "China");
        countryTable.addEntry("Vietnam", "China");
        countryTable.addEntry("Burma", "China");
        countryTable.addEntry("Bangladesh", "China");
        countryTable.addEntry("Cambodia", "China");
        countryTable.addEntry("South Korea", "China");
        countryTable.addEntry("North Korea", "China");
        countryTable.addEntry("Japan", "China");
    }

    private static void addDefaultSouthAmerica() {
        countryTable.addEntry("Argentina", "South America");
        countryTable.addEntry("Bolivia", "South America");
        countryTable.addEntry("Brazil", "South America");
        countryTable.addEntry("Chile", "South America");
        countryTable.addEntry("Colombia", "South America");
        countryTable.addEntry("Ecuador", "South America");
        countryTable.addEntry("French Guiana", "South America");
        countryTable.addEntry("Guyana", "South America");
        countryTable.addEntry("Paraguay", "South America");
        countryTable.addEntry("Peru", "South America");
        countryTable.addEntry("Suriname", "South America");
        countryTable.addEntry("Uruguay", "South America");
        countryTable.addEntry("Venezuela", "South America");
    }
}
