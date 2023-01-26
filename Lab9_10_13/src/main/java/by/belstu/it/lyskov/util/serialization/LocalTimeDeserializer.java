package by.belstu.it.lyskov.util.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeDeserializer implements JsonDeserializer<LocalTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final LocalTimeDeserializer instance = new LocalTimeDeserializer();

    private LocalTimeDeserializer() {
    }

    public static LocalTimeDeserializer getInstance() {
        return instance;
    }

    public LocalTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalTime.from(formatter.parse(json.getAsJsonPrimitive().getAsString()));
    }
}
