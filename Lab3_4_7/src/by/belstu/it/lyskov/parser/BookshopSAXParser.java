package by.belstu.it.lyskov.parser;

import by.belstu.it.lyskov.bookshop.Bookshop;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BookshopSAXParser {

    public Bookshop parseXML(final String xmlFilePath) {
        Bookshop bookShop;
        try (InputStream xmlFile = new FileInputStream(xmlFilePath)) {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();

            BookshopSAXHandler handler = new BookshopSAXHandler();
            saxParser.parse(xmlFile, handler);
            bookShop = handler.getBookShop();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException("Ошибка разбора XML файла SAX парсером", e);
        }
        return bookShop;
    }
}
