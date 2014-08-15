package data;

/**
 * @author Aaron
 */
public class Codes {
    public static int makeCode(int nation, int country) {
        return nation | country;
    }
    
    public static int getCountry(int code) {
        return code & MASK_COUNTRY;
    }
    
    public static int getNation(int code) {
        return code & MASK_NATION;
    }
    
    public final static int MASK_NATION = 0xFF;
    public final static int MASK_COUNTRY = 0xFF00;
    
    public final static int NATION_RUSSIA =         0x01;
    public final static int NATION_AUSTRALASIA =    0x02;
    public final static int NATION_CHINA =          0x03;
    public final static int NATION_MIDDLE_EAST =    0x04;
    public final static int NATION_AMERICANA =      0x05;
    public final static int NATION_EUROPE =         0x06;
    public final static int NATION_AFRICA =         0x07;
    public final static int NATION_SOUTH_AMERICA =  0x08;
    
    public final static int COUNTRY_RUSSIA =        0x0100;
    public final static int COUNTRY_ARMENIA =       0x0200;
    public final static int COUNTRY_MONGOLIA =      0x0300;
    public final static int COUNTRY_KAZAKHSTAN =    0x0400;
    public final static int COUNTRY_FINLAND =       0x0500;
    public final static int COUNTRY_SWEDEN =        0x0600;
    public final static int COUNTRY_NORWAY =        0x0700;
    public final static int COUNTRY_UZBEKISTAN =    0x0800;
    public final static int COUNTRY_KYRGYZSTAN =    0x0900;
    public final static int COUNTRY_GEORGIA =       0x0A00;
    public final static int COUNTRY_TURKEY =        0x0B00;
    public final static int COUNTRY_TAJIKSTAN =     0x0C00;
    public final static int COUNTRY_ALBANIA =       0x0D00;
    public final static int COUNTRY_BELARUS =       0x0E00;
    public final static int COUNTRY_BOSNIA_AND_HERZEGOVINA = 0x0F00;
    
    public final static int COUNTRY_CROATIA =        0x1000;
    public final static int COUNTRY_DENMARK =        0x1100;
    public final static int COUNTRY_GREECE =         0x1200;
    public final static int COUNTRY_ITALY =          0x1300;
    public final static int COUNTRY_LITHUANIA =      0x1400;
    public final static int COUNTRY_MACEDONIA =      0x1500;
    public final static int COUNTRY_NETHERLANDS =    0x1600;
    public final static int COUNTRY_POLAND =         0x1700;
    public final static int COUNTRY_ROMANIA =        0x1800;
    public final static int COUNTRY_SLOVAKIA =       0x1900;
    public final static int COUNTRY_SPAIN =          0x1A00;
    public final static int COUNTRY_SWITZERLAND =    0x1B00;
    public final static int COUNTRY_UNITED_KINGDOM = 0x1C00;
    public final static int COUNTRY_BELGIUM =        0x1D00;
    public final static int COUNTRY_BULGARIA =       0x1E00;
    public final static int COUNTRY_CZECH_REPUBLIC = 0x1F00;
    
    public final static int COUNTRY_ESTONIA =        0x2000;
    public final static int COUNTRY_FRANCE =         0x2100;
    public final static int COUNTRY_GERMANY =        0x2200;
    public final static int COUNTRY_HUNGARY =        0x2300;
    public final static int COUNTRY_IRELAND =        0x2400;
    public final static int COUNTRY_LATVIA =         0x2500;
    public final static int COUNTRY_LUXEMBOURG =     0x2600;
    public final static int COUNTRY_MOLDOVIA =       0x2700;
    public final static int COUNTRY_PORTUGAL =       0x2800;
    public final static int COUNTRY_SERBIA =         0x2900;
    public final static int COUNTRY_SLOVENIA =       0x2A00;
    public final static int COUNTRY_UKRAINE =        0x2B00;
    public final static int COUNTRY_QUWAIT =         0x2C00;
    public final static int COUNTRY_QATAR =          0x2D00;
    public final static int COUNTRY_IRAN =           0x2E00;
    public final static int COUNTRY_IRAQ =           0x2F00;
    
    public final static int COUNTRY_SAUDI_ARABIA =   0x3000;
    public final static int COUNTRY_JORDAN =         0x3100;
    public final static int COUNTRY_ISRAEL =         0x3200;
    public final static int COUNTRY_SYRIA =          0x3300;
    public final static int COUNTRY_AZERBAIJAN =     0x3400;
    public final static int COUNTRY_TURKMENISTAN =   0x3500;
    public final static int COUNTRY_UNITED_ARAB_EMIRATES =        0x3600;
    public final static int COUNTRY_OMAN =           0x3700;
    public final static int COUNTRY_YEMEN =          0x3800;
    public final static int COUNTRY_AFGHANISTAN =    0x3900;
    public final static int COUNTRY_PAKISTAN =       0x3A00;
    public final static int COUNTRY_ALGERIA =        0x3B00;
    public final static int COUNTRY_BURKINA_KASO =   0x3C00;
    public final static int COUNTRY_ANGOLA =         0x3D00;
    public final static int COUNTRY_BENIN =          0x3E00;
    public final static int COUNTRY_BOTSWANA =       0x3F00;
    
    public final static int COUNTRY_BURUNDI =        0x4000;
    public final static int COUNTRY_CAMAROON =       0x4100;
    public final static int COUNTRY_CENTRAL_AFRICAN_REPUBLIC =        0x4200;
    public final static int COUNTRY_CHAD =           0x4300;
    public final static int COUNTRY_COTE_DIVOIRE =   0x4400;
    public final static int COUNTRY_DEMOCRATIC_REPUBLIC_OF_THE_CONGO =        0x4500;
    public final static int COUNTRY_EGYPT =          0x4600;
    public final static int COUNTRY_EQUATORIAL_GUINEA =        0x4700;
    public final static int COUNTRY_ETHIOPIA =       0x4800;
    public final static int COUNTRY_GABON =          0x4900;
    public final static int COUNTRY_GHANA =          0x4A00;
    public final static int COUNTRY_GINNEA_BISSAU =  0x4B00;
    public final static int COUNTRY_GUINEA =         0x4C00;
    public final static int COUNTRY_KENYA =          0x4D00;
    public final static int COUNTRY_LESOTHO =        0x4E00;
    public final static int COUNTRY_LIBERIA =        0x4F00;
    
    public final static int COUNTRY_LIBYA =          0x5000;
    public final static int COUNTRY_MADAGASCAR =     0x5100;
    public final static int COUNTRY_MALAWI =         0x5200;
    public final static int COUNTRY_MALI =           0x5300;
    public final static int COUNTRY_MAURITANIA =     0x5400;
    public final static int COUNTRY_MOROCCO =        0x5500;
    public final static int COUNTRY_MOZAMBIQUE =     0x5600;
    public final static int COUNTRY_NAMIBIA =        0x5700;
    public final static int COUNTRY_NIGER =          0x5800;
    public final static int COUNTRY_NIGERIA =        0x5900;
    public final static int COUNTRY_REPUBLIC_OF_THE_CONGO =        0x5A00;
    public final static int COUNTRY_RWANDA =         0x5B00;
    public final static int COUNTRY_SENEGAL =        0x5C00;
    public final static int COUNTRY_SIERRA_LEONE =   0x5D00;
    public final static int COUNTRY_SOMALIA =        0x5E00;
    public final static int COUNTRY_SOUTH_AFRICA =   0x5F00;
    
    public final static int COUNTRY_SUDAN =          0x6000;
    public final static int COUNTRY_TANZANIA =       0x6100;
    public final static int COUNTRY_UGANDA =         0x6200;
    public final static int COUNTRY_WESTERN_SAHARA = 0x6300;
    public final static int COUNTRY_ZAMBIA =         0x6400;
    public final static int COUNTRY_ZIMBABWE =       0x6500;
    public final static int COUNTRY_UNITED_STATES =  0x6600;
    public final static int COUNTRY_CANADA =         0x6700;
    public final static int COUNTRY_ALASKA =         0x6800;
    public final static int COUNTRY_MEXICO =         0x6900;
    public final static int COUNTRY_GREENLAND =      0x6A00;
    public final static int COUNTRY_ICELAND =        0x6B00;
    public final static int COUNTRY_GUATEMALA =      0x6C00;
    public final static int COUNTRY_HONDURAS =       0x6D00;
    public final static int COUNTRY_NICARAGUA =      0x6E00;
    public final static int COUNTRY_COSTA_RICA =     0x6F00;
    
    public final static int COUNTRY_PANAMA =         0x7000;
    public final static int COUNTRY_AUSTRALIA =      0x7100;
    public final static int COUNTRY_NEW_ZEALAND =    0x7200;
    public final static int COUNTRY_PAPUA_NEW_GUINEA =      0x7300;
    public final static int COUNTRY_INDONESIA =      0x7400;
    public final static int COUNTRY_MALAYSIA =       0x7500;
    public final static int COUNTRY_PHILIPPINES =    0x7600;
    public final static int COUNTRY_CHINA =          0x7700;
    public final static int COUNTRY_TAIWAN =         0x7800;
    public final static int COUNTRY_INDIA =          0x7900;
    public final static int COUNTRY_THAILAND =       0x7A00;
    public final static int COUNTRY_VIETNAM =        0x7B00;
    public final static int COUNTRY_BURMA =          0x7C00;
    public final static int COUNTRY_BANGLADESH =     0x7D00;
    public final static int COUNTRY_CAMBODIA =       0x7E00;
    public final static int COUNTRY_SOUTH_KOREA =    0x7F00;
    
    public final static int COUNTRY_NORTH_KOREA =    0x8000;
    public final static int COUNTRY_JAPAN =          0x8100;
    public final static int COUNTRY_ARGENTINA =      0x8200;
    public final static int COUNTRY_BOLIVIA =        0x8300;
    public final static int COUNTRY_BRAZIL =         0x8400;
    public final static int COUNTRY_CHILE =          0x8500;
    public final static int COUNTRY_COLOMBIA =       0x8600;
    public final static int COUNTRY_ECUADOR =        0x8700;
    public final static int COUNTRY_FRENCH_GUIANA =  0x8800;
    public final static int COUNTRY_GUYANA =         0x8900;
    public final static int COUNTRY_PARAGUAY =       0x8A00;
    public final static int COUNTRY_PERU =           0x8B00;
    public final static int COUNTRY_SURINAME =       0x8C00;
    public final static int COUNTRY_URUGUAY =        0x8D00;
    public final static int COUNTRY_VENEZUELA =      0x8E00;
    public final static int COUNTRY_AUSTRIA =        0x8F00;
    
    public final static int COUNTRY_TOGO =           0x9000;
    public final static int COUNTRY_TUNISIA =        0x9100;
}
