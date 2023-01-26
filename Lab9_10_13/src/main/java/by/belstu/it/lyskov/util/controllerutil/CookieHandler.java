package by.belstu.it.lyskov.util.controllerutil;

import by.belstu.it.lyskov.util.serialization.LocalTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.Cookie;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

public class CookieHandler {
    private static final int COOKIE_ZERO_AGE = 0;
    private static final CookieHandler instance = new CookieHandler();

    private CookieHandler() {
    }

    public static CookieHandler getInstance() {
        return instance;
    }

    public Cookie findCookie(final Cookie[] cookies, final String name) {
        Cookie ret = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    ret = cookie;
                    break;
                }
            }
        }
        return ret;
    }

    public Cookie createCookie(final String name, final Object value) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalTime.class, LocalTimeSerializer.getInstance());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        String json = gson.toJson(value);
        return new Cookie(name, URLEncoder.encode(json, StandardCharsets.UTF_8));
    }

    public <T> T parseCookie(Cookie cookie, TypeToken<T> typeToken) {
        T ret = null;
        if (cookie != null) {
            String json = cookie.getValue();
            if (json != null) {
                ret = new Gson().fromJson(URLDecoder.decode(json, StandardCharsets.UTF_8), typeToken.getType());
            }
        }
        return ret;
    }

    public void clearCookie(Cookie cookie) {
        if (cookie != null)
            cookie.setMaxAge(COOKIE_ZERO_AGE);
    }
}
