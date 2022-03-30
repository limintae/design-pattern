package factory;

public class BookFactory {

    public static Book getBook(BookType type) {
        return type.getConstructor().get();
    }

}
