package by.belstu.it.lyskov.main;

import by.belstu.it.lyskov.url.URLHtml;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            URLHtml urlHtml = new URLHtml();
            urlHtml.loadHtml("https://youtube.com/", "youtube.html");
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
