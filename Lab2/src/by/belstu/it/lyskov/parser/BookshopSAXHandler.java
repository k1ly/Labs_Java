package by.belstu.it.lyskov.parser;

import by.belstu.it.lyskov.bean.Book;
import by.belstu.it.lyskov.bean.Comics;
import by.belstu.it.lyskov.bean.Postcard;
import by.belstu.it.lyskov.bookshop.Bookshop;
import by.belstu.it.lyskov.builder.*;
import by.belstu.it.lyskov.exception.ServiceException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class BookshopSAXHandler extends DefaultHandler {
    private String elementName = "";
    private Bookshop bookShop;

    private Integer amount;
    private BookBuilder bookBuilder;
    private ComicsBuilder comicsBuilder;
    private PostcardBuilder postcardBuilder;

    public Bookshop getBookShop() {
        return bookShop;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "bookshop" -> bookShop = new Bookshop();
            case "shelf" -> amount = Integer.parseInt(attributes.getValue("amount"));
            case "book" -> bookBuilder = new BookBuilder();
            case "comics" -> comicsBuilder = new ComicsBuilder();
            case "postcard" -> postcardBuilder = new PostcardBuilder();
        }
        elementName = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        try {
            switch (qName) {
                case "shelf" -> amount = null;
                case "book" -> {
                    Book book = bookBuilder.build();
                    for (int i = 0; i < amount; i++)
                        bookShop.add(book);
                    bookBuilder = null;
                }
                case "comics" -> {
                    Comics comics = comicsBuilder.build();
                    for (int i = 0; i < amount; i++)
                        bookShop.add(comics);
                    comicsBuilder = null;
                }
                case "postcard" -> {
                    Postcard postcard = postcardBuilder.build();
                    for (int i = 0; i < amount; i++)
                        bookShop.add(postcard);
                    postcardBuilder = null;
                }
            }
            elementName = "";
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        switch (elementName) {
            case "id": {
                int id = Integer.parseInt(new String(chars, start, length));
                if (bookBuilder != null)
                    bookBuilder.withId(id);
                else if (comicsBuilder != null)
                    comicsBuilder.withId(id);
                else if (postcardBuilder != null)
                    postcardBuilder.withId(id);
            }
            break;
            case "price": {
                int price = Integer.parseInt(new String(chars, start, length));
                if (bookBuilder != null)
                    bookBuilder.withPrice(price);
                else if (comicsBuilder != null)
                    comicsBuilder.withPrice(price);
                else if (postcardBuilder != null)
                    postcardBuilder.withPrice(price);
            }
            break;
            case "year": {
                int year = Integer.parseInt(new String(chars, start, length));
                if (bookBuilder != null)
                    bookBuilder.withYear(year);
                else if (comicsBuilder != null)
                    comicsBuilder.withYear(year);
                else if (postcardBuilder != null)
                    postcardBuilder.withYear(year);
            }
            break;
            case "name":
                if (bookBuilder != null)
                    bookBuilder.withName(new String(chars, start, length));
                else if (comicsBuilder != null)
                    comicsBuilder.withName(new String(chars, start, length));
            break;
            case "author":
                if (bookBuilder != null)
                    bookBuilder.withAuthor(new String(chars, start, length));
                else if (comicsBuilder != null)
                    comicsBuilder.withAuthor(new String(chars, start, length));
            break;
            case "artist":
                if (comicsBuilder != null)
                    comicsBuilder.withArtist(new String(chars, start, length));
                break;
            case "theme":
                if (postcardBuilder != null)
                    postcardBuilder.withTheme(new String(chars, start, length));
                break;
        }
    }
}
