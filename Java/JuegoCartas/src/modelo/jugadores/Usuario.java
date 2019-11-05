package modelo.jugadores;

import persistencia.UsuarioDAO;

public class Usuario {
    private String nombre;
    private int tiempoJugado;
    private int partidasJugadas;
    private int partidasGanadas;
    private int partidasPerdidas;

    public Usuario(String nombre, int tiempoJugado, int partidasJugadas, int partidasGanadas, int partidasPerdidas) {
        this.nombre = nombre;
        this.tiempoJugado = tiempoJugado;
        this.partidasJugadas = partidasJugadas;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(int tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    public static void insertarUsuario(Usuario u) {
        UsuarioDAO.getInstance().insertarUsuario(u);
    }
}
