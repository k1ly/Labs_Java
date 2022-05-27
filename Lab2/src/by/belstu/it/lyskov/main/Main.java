package by.belstu.it.lyskov.main;

import by.belstu.it.lyskov.bookshop.Bookshop;
import by.belstu.it.lyskov.parser.BookshopSAXParser;
import by.belstu.it.lyskov.serialization.BookshopJSONSerializer;
import by.belstu.it.lyskov.streamapi.BookshopStreamAPI;
import by.belstu.it.lyskov.validation.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String XML_PATH = "files/bookshop.xml";
    private static final String XSD_PATH = "files/bookshop.xsd";
    private static final String JSON_PATH = "files/bookshop.json";

    public static void main(String[] args) {
            XMLValidator validator = new XMLValidator();
            validator.validateXML(XML_PATH, XSD_PATH);
            BookshopSAXParser bookshopSaxParser = new BookshopSAXParser();
            Bookshop bookShop = bookshopSaxParser.parseXML(XML_PATH);
            bookShop.sort();
            bookShop.show();

            BookshopJSONSerializer jsonSerializer = new BookshopJSONSerializer();
            jsonSerializer.serializeBookshop(bookShop, JSON_PATH);

            BookshopStreamAPI streamAPI = new BookshopStreamAPI();
            int cost = streamAPI.calculateFullCost(bookShop);
            logger.info("Общая стоимость книжного хранилища: " + cost + "$");

//        try {
//            Book book1 = new Book(1);
//            book1.setPrice(926);
//            book1.setYear(1940);
//            book1.setName("Adventures");
//            book1.setAuthor("Johnson");
//            bookShop.add(book1);
//            Comics comics2 = new Comics(2);
//            comics2.setPrice(473);
//            comics2.setYear(2009);
//            comics2.setName("Love dramatic!");
//            comics2.setAuthor("Hiragana");
//            comics2.setArtist("Shiitake");
//            bookShop.add(comics2);
//            Postcard postcard3 = new Postcard(3);
//            postcard3.setPrice(58);
//            postcard3.setTheme("Wedding");
//            bookShop.add(postcard3);
//            Book book4 = new Book(4);
//            bookShop.add(book4);
//            bookShop.sort();
//            bookShop.show();
//            Printing sold = null;
//            if (bookShop.find(1) != null)
//                sold = bookShop.sell(1);
//            if (sold != null)
//                System.out.println("Printing " + sold + "\n was sold for " + sold.getPrice() + "$");
//        } catch (ServiceException e) {
//            logger.error(e);
//        }
    }
}