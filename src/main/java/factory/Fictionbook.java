package factory;

/**
 * Fictionbook implements
 */
public class Fictionbook implements Book {

    static final String DESCRIPTION = "this is a fiction book";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}
