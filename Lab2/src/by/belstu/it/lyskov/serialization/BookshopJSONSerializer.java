package by.belstu.it.lyskov.serialization;

import by.belstu.it.lyskov.bookshop.Bookshop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class BookshopJSONSerializer {

    public void serializeBookshop(Bookshop bookshop, final String jsonFilePath) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
            gson.toJson(bookshop, fileWriter);
        } catch (IOException exception) {
            throw new RuntimeException("Ошибка сериализации JSON", exception);
        }
    }
}
