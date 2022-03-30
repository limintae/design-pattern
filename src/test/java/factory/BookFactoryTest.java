package factory;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookFactoryTest {

    @Test
    void createBooks() {
        Assertions.assertThat(BookFactory.getBook(BookType.COMIC)).isInstanceOf(ComicBook.class);
        Assertions.assertThat(BookFactory.getBook(BookType.FICTION)).isInstanceOf(Fictionbook.class);
    }

}
