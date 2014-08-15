package data;

import static data.Codes.*;
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
        nationTable.addEntry(""+Codes.NATION_AUSTRALASIA, ""+Codes.COUNTRY_AUSTRALIA, "australasia.png");
        nationTable.addEntry(""+Codes.NATION_CHINA, ""+Codes.COUNTRY_CHINA, "china.png");
        nationTable.addEntry(""+Codes.NATION_AMERICANA, ""+Codes.COUNTRY_UNITED_STATES, "usa.png");
        nationTable.addEntry(""+Codes.NATION_SOUTH_AMERICA, ""+Codes.COUNTRY_PERU, "southamerica.png");
        nationTable.addEntry(""+Codes.NATION_AFRICA, ""+Codes.COUNTRY_SOUTH_AFRICA, "africa.png");
        nationTable.addEntry(""+Codes.NATION_EUROPE, ""+Codes.COUNTRY_UNITED_KINGDOM, "europe.png");
        nationTable.addEntry(""+Codes.NATION_MIDDLE_EAST, ""+Codes.COUNTRY_IRAQ, "middleeast.png");
        nationTable.addEntry(""+Codes.NATION_RUSSIA, ""+Codes.COUNTRY_RUSSIA, "russia.png");
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
        countryTable.addEntry(""+Codes.COUNTRY_RUSSIA, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_ARMENIA, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_MONGOLIA, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_KAZAKHSTAN, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_FINLAND, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_SWEDEN, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_NORWAY, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_UZBEKISTAN, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_KYRGYZSTAN, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_GEORGIA, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_TURKEY, ""+Codes.NATION_RUSSIA);
        countryTable.addEntry(""+Codes.COUNTRY_TAJIKSTAN, ""+Codes.NATION_RUSSIA);
    }
    
    private static void addDefaultEurope() {
        countryTable.addEntry(""+Codes.COUNTRY_ALBANIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_AUSTRIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_BELARUS, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_BOSNIA_AND_HERZEGOVINA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_CROATIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_DENMARK, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_GREECE, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_ITALY, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_LITHUANIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_MACEDONIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_NETHERLANDS, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_POLAND, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_ROMANIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_SLOVAKIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_SPAIN, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_SWITZERLAND, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_UNITED_KINGDOM, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_BELGIUM, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_BULGARIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_CZECH_REPUBLIC, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_ESTONIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_FRANCE, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_GERMANY, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_HUNGARY, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_IRELAND, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_LATVIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_LUXEMBOURG, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_MOLDOVIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_PORTUGAL, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_SERBIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_SLOVENIA, ""+Codes.NATION_EUROPE);
        countryTable.addEntry(""+Codes.COUNTRY_UKRAINE, ""+Codes.NATION_EUROPE);
    }
    
    private static void addDefaultMiddleEast() {
        countryTable.addEntry(""+Codes.COUNTRY_QUWAIT, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_QATAR, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_IRAN, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_IRAQ, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_SAUDI_ARABIA, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_JORDAN, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_ISRAEL, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_SYRIA, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_AZERBAIJAN, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_TURKMENISTAN, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_UNITED_ARAB_EMIRATES, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_OMAN, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_YEMEN, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_AFGHANISTAN, ""+Codes.NATION_MIDDLE_EAST);
        countryTable.addEntry(""+Codes.COUNTRY_PAKISTAN, ""+Codes.NATION_MIDDLE_EAST);
    }
    
    private static void addDefaultAfrica() {
        countryTable.addEntry(""+Codes.COUNTRY_ALGERIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_BURKINA_KASO, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_ANGOLA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_BENIN, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_BOTSWANA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_BURUNDI, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_CAMAROON, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_CENTRAL_AFRICAN_REPUBLIC, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_CHAD, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_COTE_DIVOIRE, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_DEMOCRATIC_REPUBLIC_OF_THE_CONGO, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_EGYPT, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_EQUATORIAL_GUINEA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_ETHIOPIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_GABON, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_GHANA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_GINNEA_BISSAU, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_GUINEA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_KENYA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_LESOTHO, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_LIBERIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_LIBYA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_MADAGASCAR, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_MALAWI, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_MALI, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_MAURITANIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_MOROCCO, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_MOZAMBIQUE, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_NAMIBIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_NIGER, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_NIGERIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_REPUBLIC_OF_THE_CONGO, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_RWANDA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_SENEGAL, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_SIERRA_LEONE, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_SOMALIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_SOUTH_AFRICA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_SUDAN, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_TANZANIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_TOGO, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_TUNISIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_UGANDA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_WESTERN_SAHARA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_ZAMBIA, ""+Codes.NATION_AFRICA);
        countryTable.addEntry(""+Codes.COUNTRY_ZIMBABWE, ""+Codes.NATION_AFRICA);
    }
    
    private static void addDefaultAmericana() {
        countryTable.addEntry(""+Codes.COUNTRY_UNITED_STATES, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_CANADA, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_ALASKA, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_MEXICO, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_GREENLAND, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_ICELAND, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_GUATEMALA, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_HONDURAS, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_NICARAGUA, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_COSTA_RICA, ""+Codes.NATION_AMERICANA);
        countryTable.addEntry(""+Codes.COUNTRY_PANAMA, ""+Codes.NATION_AMERICANA);
    }
    
    private static void addDefaultAustralasia() {
        countryTable.addEntry(""+Codes.COUNTRY_AUSTRALIA, ""+Codes.NATION_AUSTRALASIA);
        countryTable.addEntry(""+Codes.COUNTRY_NEW_ZEALAND, ""+Codes.NATION_AUSTRALASIA);
        countryTable.addEntry(""+Codes.COUNTRY_PAPUA_NEW_GUINEA, ""+Codes.NATION_AUSTRALASIA);
        countryTable.addEntry(""+Codes.COUNTRY_INDONESIA, ""+Codes.NATION_AUSTRALASIA);
        countryTable.addEntry(""+Codes.COUNTRY_MALAYSIA, ""+Codes.NATION_AUSTRALASIA);
        countryTable.addEntry(""+Codes.COUNTRY_PHILIPPINES, ""+Codes.NATION_AUSTRALASIA);
    }
    
    private static void addDefaultChina() {
        countryTable.addEntry(""+Codes.COUNTRY_CHINA, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_TAIWAN, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_INDIA, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_THAILAND, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_VIETNAM, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_BURMA, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_BANGLADESH, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_CAMBODIA, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_SOUTH_KOREA, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_NORTH_KOREA, ""+Codes.NATION_CHINA);
        countryTable.addEntry(""+Codes.COUNTRY_JAPAN, ""+Codes.NATION_CHINA);
    }
    
    private static void addDefaultSouthAmerica() {
        countryTable.addEntry(""+Codes.COUNTRY_ARGENTINA, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_BOLIVIA, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_BRAZIL, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_CHILE, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_COLOMBIA, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_ECUADOR, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_FRENCH_GUIANA, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_GUYANA, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_PARAGUAY, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_PERU, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_SURINAME, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_URUGUAY, ""+Codes.NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+Codes.COUNTRY_VENEZUELA, ""+Codes.NATION_SOUTH_AMERICA);
    }
}
