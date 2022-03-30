package singleton;

public class ThreadSafeLazyLoadedCar {

    public static volatile ThreadSafeLazyLoadedCar instance;

    private ThreadSafeLazyLoadedCar() {
        if (instance != null) {
            throw new IllegalStateException("Already initalized.");
        }
    }

    public static synchronized ThreadSafeLazyLoadedCar getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeLazyLoadedCar.class) {
                if (instance == null) {
                    instance = new ThreadSafeLazyLoadedCar();
                }
            }
        }
        return instance;
    }

}
