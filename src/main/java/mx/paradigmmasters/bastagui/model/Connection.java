package mx.paradigmmasters.bastagui.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private final ObjectOutputStream output;
    private final ObjectInputStream input;

    public Connection() throws RuntimeException {
        try {
            Socket socket = new Socket("localhost", 5555);
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public ObjectInputStream getInput() {
        return input;
    }
}
