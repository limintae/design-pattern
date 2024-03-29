package singleton;

public class InitializingOnDemandHolderIdiom {

    // Private constructor.
    private InitializingOnDemandHolderIdiom() {}

    public InitializingOnDemandHolderIdiom getInstance() {
        return HelperHolder.INSTANCE;
    }

    // Provides the lazy-loaded Singleton instance.
    private static class HelperHolder {
        private static final InitializingOnDemandHolderIdiom INSTANCE = new InitializingOnDemandHolderIdiom();
    }

}
