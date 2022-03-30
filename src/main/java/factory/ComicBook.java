package factory;

/**
 * ComicBook implements
 */
public class ComicBook implements Book {

    static final String DESCRIPTION = "this is a comic book";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}
