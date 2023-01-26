package test.by.belstu.it.lyskov.bookshop;

import by.belstu.it.lyskov.bean.Book;
import by.belstu.it.lyskov.bean.Printing;
import by.belstu.it.lyskov.bookshop.Bookshop;
import by.belstu.it.lyskov.exception.ServiceException;
import by.belstu.it.lyskov.parser.BookshopSAXParser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BookshopTest {
    private static Bookshop bookshop;

    @BeforeAll
    static void testBookshopXmlParsingInit() {
        final String XML_PATH = "files/bookshop.xml";
        BookshopSAXParser saxParser = new BookshopSAXParser();
        bookshop = saxParser.parseXML(XML_PATH);
        assertNotNull(bookshop);
    }

    @BeforeEach
    public void testBookshopSizeShouldBePositive() {
        int size = bookshop.size();
        assertTrue(size > 0);
    }

    @Test
    @DisplayName("Searching product by negative id test")
    public void testSearchingProductByNegativeIdShouldReturnNull() {
        Printing product = bookshop.find(-1);
        assertNull(product);
    }

    @Test
    @Disabled
    @DisplayName("Disabled test..")
    public void testDisabledFail() {
        fail();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    @DisplayName("Parametrized selling not existing id test")
    public void testSellingNotExistingIdShouldThrowException(int id) {
        Assertions.assertThrows(ServiceException.class, () -> bookshop.sell(id));
    }

    @RepeatedTest(4)
    @DisplayName("Repeated product adding checking test")
    public void testStorageSizeAfterAddingShouldBeOneGreater() throws ServiceException {
        int size = bookshop.size();
        Book book = new Book();
        book.setPrice(4);
        book.setYear(2020);
        book.setName("Book");
        book.setAuthor("Author");
        bookshop.add(book);
        Assertions.assertEquals(size + 1, bookshop.size());
    }

    @AfterAll
    public static void testBookshopSizeShouldBeZeroAfterClearingStorage() {
        bookshop.getStorage().clear();
        boolean isBookshopNotEmpty = bookshop.size() > 0;
        assertFalse(isBookshopNotEmpty);
    }
}
