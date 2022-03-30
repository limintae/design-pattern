package singleton;

public class EagerCar {

    public static final EagerCar INSTANCE = new EagerCar();

    public static EagerCar getInstance() {
        return INSTANCE;
    }

}
