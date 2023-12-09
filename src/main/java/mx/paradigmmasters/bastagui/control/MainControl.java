package mx.paradigmmasters.bastagui.control;

import mx.paradigmmasters.bastagui.model.*;
import mx.paradigmmasters.bastagui.threads.ListenerThread;
import mx.paradigmmasters.bastagui.view.GameView;
import mx.paradigmmasters.bastagui.view.JoinView;

import java.io.IOException;

public class MainControl {
    private JoinView joinView;
    private GameView gameView;
    private Connection connection;
    private int connectionAttempts;
    private int estado = Constants.CARGANDO;

    public MainControl() {
        this.joinView = new JoinView(this);
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void startGame(String userName) {
        boolean isConnected = false;
        this.connectionAttempts = 3;
        do {
            isConnected = tryConnection();
            this.connectionAttempts--;
        } while (!isConnected);

        try {
            this.connection.getOutput().writeObject(userName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.gameView = new GameView(this);
        this.joinView.setVisible(false);

        ListenerThread listenerThread = new ListenerThread(this.connection.getInput(), this);
        listenerThread.start();

    }

    public boolean tryConnection() {
        try {
            this.connection = new Connection();
            this.connectionAttempts = 3;
            return true;
        } catch (RuntimeException e) {
            if (this.connectionAttempts == 0) {
                throw new RuntimeException(e);
            } else {
                this.joinView.connectionError(this.connectionAttempts);
            }
            return false;
        }
    }

    public void setEstadoVista(int estado, int puntos) {
        this.estado = estado;
        this.gameView.setStatus(estado);
        this.gameView.setTotalPoints(puntos);
    }

    public void setLetra(String letter) {
        this.gameView.setLetter(letter);
    }

    public void setCalificacion(Calificacion calificacion) {
        this.gameView.setCalificacion(
                calificacion.getNombre(),
                calificacion.getFlorFruto(),
                calificacion.getPais(),
                calificacion.getAnimal(),
                calificacion.getColor()
        );
    }

    public void sendAnswers(
            String nombre,
            String florFruto,
            String pais,
            String animal,
            String color
    ) throws IOException {
        this.connection.getOutput().writeObject(new JsonSerializer().sendAnswers(new Respuesta(nombre, florFruto, pais, animal, color)));
    }
}
