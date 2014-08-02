package army.unit;

/**
 * @author Aaron
 */
public class Commander extends Unit {
    public Commander(String name, int level) {
        super("Commander"+level);
        super.name = name;
    }

}
