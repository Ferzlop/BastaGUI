package mx.paradigmmasters.bastagui.threads;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mx.paradigmmasters.bastagui.control.MainControl;
import mx.paradigmmasters.bastagui.model.Constants;
import mx.paradigmmasters.bastagui.model.JsonSerializer;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ListenerThread extends Thread {
    private final ObjectInputStream input;
    private final MainControl mainControl;

    public ListenerThread(ObjectInputStream input, MainControl mainControl) {
        this.input = input;
        this.mainControl = mainControl;
    }

    @Override
    public void run() {
        super.run();
        do {
            try {
                String json = (String) this.input.readObject();

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

                for (String key : jsonObject.keySet()) {
                    switch (key) {
                        case "Estado":
                            this.mainControl.setEstadoVista(new JsonSerializer().receiveStatus(jsonObject.getAsJsonObject(key)), new JsonSerializer().receiveTotalPoints(jsonObject.getAsJsonObject(key)));
                            break;
                        case "Calificacion":
                            this.mainControl.setCalificacion(new JsonSerializer().receivePoints(jsonObject.getAsJsonObject(key)));
                            break;
                        case "Letra":
                            this.mainControl.setLetra(new JsonSerializer().receiveLetter(jsonObject.getAsJsonObject(key)));
                            break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } while (this.mainControl.getEstado() != Constants.FINALIZADO);
    }
}
