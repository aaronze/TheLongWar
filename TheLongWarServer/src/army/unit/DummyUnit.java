package army.unit;

/**
 * @author Aaron
 */
public class DummyUnit extends Unit {
    public DummyUnit() {
        super("Warrior");
    }

    @Override
    public void takeDamage(double amount) {
        // do nothing
    }
}
