package data;

import static data.Codes.*;
import database.Database;
import database.HardTable;
import database.MemTable;
import database.Table;
import java.util.ArrayList;

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
            captureTable.addRows("captureID", "time", "attacker", "defender");
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
    
    public static int[] toDataStream() {
        int[] stream = new int[countryTable.size()];
        
        ArrayList<String[]> countries = countryTable.getEntries();
        
        for (int i = 0; i < countries.size(); i++) {
            String[] countryEntry = countries.get(i);
            
            int country = Integer.parseInt(countryEntry[0]);
            int nation = Integer.parseInt(countryEntry[1]);
            
            stream[i] = Codes.toCode(nation, country);
        }
        
        return stream;
    }
    
    private static void addDefaultNations() {
        nationTable.addEntry(""+NATION_AUSTRALASIA, ""+COUNTRY_AUSTRALIA, "australasia.png");
        nationTable.addEntry(""+NATION_CHINA, ""+COUNTRY_CHINA, "china.png");
        nationTable.addEntry(""+NATION_AMERICANA, ""+COUNTRY_UNITED_STATES, "usa.png");
        nationTable.addEntry(""+NATION_SOUTH_AMERICA, ""+COUNTRY_PERU, "southamerica.png");
        nationTable.addEntry(""+NATION_AFRICA, ""+COUNTRY_SOUTH_AFRICA, "africa.png");
        nationTable.addEntry(""+NATION_EUROPE, ""+COUNTRY_UNITED_KINGDOM, "europe.png");
        nationTable.addEntry(""+NATION_MIDDLE_EAST, ""+COUNTRY_IRAQ, "middleeast.png");
        nationTable.addEntry(""+NATION_RUSSIA, ""+COUNTRY_RUSSIA, "russia.png");
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
        countryTable.addEntry(""+COUNTRY_RUSSIA, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_ARMENIA, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_MONGOLIA, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_KAZAKHSTAN, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_FINLAND, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_SWEDEN, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_NORWAY, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_UZBEKISTAN, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_KYRGYZSTAN, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_GEORGIA, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_TURKEY, ""+NATION_RUSSIA);
        countryTable.addEntry(""+COUNTRY_TAJIKSTAN, ""+NATION_RUSSIA);
    }
    
    private static void addDefaultEurope() {
        countryTable.addEntry(""+COUNTRY_ALBANIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_AUSTRIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_BELARUS, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_BOSNIA_AND_HERZEGOVINA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_CROATIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_DENMARK, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_GREECE, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_ITALY, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_LITHUANIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_MACEDONIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_NETHERLANDS, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_POLAND, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_ROMANIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_SLOVAKIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_SPAIN, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_SWITZERLAND, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_UNITED_KINGDOM, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_BELGIUM, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_BULGARIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_CZECH_REPUBLIC, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_ESTONIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_FRANCE, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_GERMANY, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_HUNGARY, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_IRELAND, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_LATVIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_LUXEMBOURG, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_MOLDOVIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_PORTUGAL, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_SERBIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_SLOVENIA, ""+NATION_EUROPE);
        countryTable.addEntry(""+COUNTRY_UKRAINE, ""+NATION_EUROPE);
    }
    
    private static void addDefaultMiddleEast() {
        countryTable.addEntry(""+COUNTRY_QUWAIT, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_QATAR, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_IRAN, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_IRAQ, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_SAUDI_ARABIA, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_JORDAN, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_ISRAEL, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_SYRIA, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_AZERBAIJAN, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_TURKMENISTAN, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_UNITED_ARAB_EMIRATES, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_OMAN, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_YEMEN, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_AFGHANISTAN, ""+NATION_MIDDLE_EAST);
        countryTable.addEntry(""+COUNTRY_PAKISTAN, ""+NATION_MIDDLE_EAST);
    }
    
    private static void addDefaultAfrica() {
        countryTable.addEntry(""+COUNTRY_ALGERIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_BURKINA_KASO, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_ANGOLA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_BENIN, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_BOTSWANA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_BURUNDI, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_CAMAROON, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_CENTRAL_AFRICAN_REPUBLIC, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_CHAD, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_COTE_DIVOIRE, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_DEMOCRATIC_REPUBLIC_OF_THE_CONGO, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_EGYPT, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_EQUATORIAL_GUINEA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_ETHIOPIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_GABON, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_GHANA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_GINNEA_BISSAU, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_GUINEA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_KENYA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_LESOTHO, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_LIBERIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_LIBYA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_MADAGASCAR, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_MALAWI, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_MALI, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_MAURITANIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_MOROCCO, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_MOZAMBIQUE, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_NAMIBIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_NIGER, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_NIGERIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_REPUBLIC_OF_THE_CONGO, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_RWANDA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_SENEGAL, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_SIERRA_LEONE, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_SOMALIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_SOUTH_AFRICA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_SUDAN, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_TANZANIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_TOGO, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_TUNISIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_UGANDA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_WESTERN_SAHARA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_ZAMBIA, ""+NATION_AFRICA);
        countryTable.addEntry(""+COUNTRY_ZIMBABWE, ""+NATION_AFRICA);
    }
    
    private static void addDefaultAmericana() {
        countryTable.addEntry(""+COUNTRY_UNITED_STATES, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_CANADA, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_ALASKA, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_MEXICO, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_GREENLAND, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_ICELAND, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_GUATEMALA, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_HONDURAS, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_NICARAGUA, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_COSTA_RICA, ""+NATION_AMERICANA);
        countryTable.addEntry(""+COUNTRY_PANAMA, ""+NATION_AMERICANA);
    }
    
    private static void addDefaultAustralasia() {
        countryTable.addEntry(""+COUNTRY_AUSTRALIA, ""+NATION_AUSTRALASIA);
        countryTable.addEntry(""+COUNTRY_NEW_ZEALAND, ""+NATION_AUSTRALASIA);
        countryTable.addEntry(""+COUNTRY_PAPUA_NEW_GUINEA, ""+NATION_AUSTRALASIA);
        countryTable.addEntry(""+COUNTRY_INDONESIA, ""+NATION_AUSTRALASIA);
        countryTable.addEntry(""+COUNTRY_MALAYSIA, ""+NATION_AUSTRALASIA);
        countryTable.addEntry(""+COUNTRY_PHILIPPINES, ""+NATION_AUSTRALASIA);
    }
    
    private static void addDefaultChina() {
        countryTable.addEntry(""+COUNTRY_CHINA, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_TAIWAN, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_INDIA, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_THAILAND, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_VIETNAM, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_BURMA, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_BANGLADESH, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_CAMBODIA, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_SOUTH_KOREA, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_NORTH_KOREA, ""+NATION_CHINA);
        countryTable.addEntry(""+COUNTRY_JAPAN, ""+NATION_CHINA);
    }
    
    private static void addDefaultSouthAmerica() {
        countryTable.addEntry(""+COUNTRY_ARGENTINA, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_BOLIVIA, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_BRAZIL, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_CHILE, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_COLOMBIA, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_ECUADOR, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_FRENCH_GUIANA, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_GUYANA, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_PARAGUAY, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_PERU, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_SURINAME, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_URUGUAY, ""+NATION_SOUTH_AMERICA);
        countryTable.addEntry(""+COUNTRY_VENEZUELA, ""+NATION_SOUTH_AMERICA);
    }
}
