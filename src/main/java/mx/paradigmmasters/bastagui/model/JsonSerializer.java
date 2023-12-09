package mx.paradigmmasters.bastagui.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class JsonSerializer {
    public JsonSerializer() {
    }

    public int receiveStatus(JsonObject json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        System.out.println(gson.fromJson(json, Estado.class).getEstado());

        return gson.fromJson(json, Estado.class).getEstado();
    }

    public int receiveTotalPoints(JsonObject json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        System.out.println(gson.fromJson(json, Estado.class).getPuntos());

        return gson.fromJson(json, Estado.class).getPuntos();
    }

    public String sendAnswers(Respuesta answers) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(answers);
    }

    public Calificacion receivePoints(JsonObject json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.fromJson(json, Calificacion.class);
    }

    public String receiveLetter(JsonObject json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.fromJson(json, Letra.class).getLetra();
    }
}
