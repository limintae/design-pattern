package factory;

import java.util.function.Supplier;

public enum BookType {
    COMIC(ComicBook::new),
    FICTION(Fictionbook::new);

    private final Supplier<Book> constructor;

    BookType(Supplier<Book> constructor) {
        this.constructor = constructor;
    }

    public Supplier<Book> getConstructor() {
        return constructor;
    }

}
