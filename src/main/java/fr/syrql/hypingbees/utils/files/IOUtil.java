package fr.syrql.hypingbees.utils.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.syrql.hypingbees.beehives.data.Beehive;

public class IOUtil {

    private Gson gson;

    public IOUtil() {
        this.gson = createGsonInstance();
    }

    public Gson createGsonInstance() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
    }

    public String serialize(Beehive kit) {
        return this.gson.toJson(kit);
    }

    public Beehive deserialize(String json) {
        return this.gson.fromJson(json, Beehive.class);
    }

}
