package mx.paradigmmasters.bastagui.model;

public class Estado {
    private int estado = Constants.CARGANDO;
    private int puntos = 0;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
