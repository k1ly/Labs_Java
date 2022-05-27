package by.belstu.it.lyskov.url;

import java.io.*;
import java.net.URL;

public class URLHtml {

    public void loadHtml(final String urlName, final String fileName) throws IOException {
        URL url = new URL(urlName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String line;
        while ((line = reader.readLine()) != null)
            writer.write(line + "\n");
    }
}
