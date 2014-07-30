package secure;

/**
 * This class handles the amount of authorization the user currently possesses.
 * 
 * Each new handle starts with Silenced authorization (Cannot send/retrieve data)
 * and this increases based on session timers and logged in status
 * 
 * @author Aaron
 */
public class Authorization {
    private final static int SILENCED = 0;
    private final static int SESSION_EXPIRED = 1;
    private final static int SESSION_PENDING = 2;
    private final static int SESSION_CURRENT = 3;
    
    private static int LAST_STATUS = SILENCED;
    private static long LAST_CONNECTED = 0;
    
    
}
