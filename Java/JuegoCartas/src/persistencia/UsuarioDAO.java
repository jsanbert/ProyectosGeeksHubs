package persistencia;

import modelo.jugadores.Usuario;
import modelo.universo.Carta;

public class UsuarioDAO {
    private static UsuarioDAO instance;
    private Conector conector;
    private Carta carta;

    private UsuarioDAO() {
        conector = Conector.getInstance();
    }

    public static UsuarioDAO getInstance() {
        if(instance == null)
            instance = new UsuarioDAO();
        return instance;
    }

    public boolean insertarUsuario(Usuario u) {
        conector.conectar("juego_cartas", "root", "root");
        boolean insertado = true;
        String sql = "INSERT INTO usuarios (id, nombre, t_jugado, p_jugadas, p_ganadas, p_perdidas) VALUES "
                + "(DEFAULT, '" + u.getNombre() + "', " + u.getTiempoJugado() + ", " + u.getPartidasJugadas() + ", " + u.getPartidasGanadas() + ", " + u.getPartidasPerdidas() +")";

        System.out.println(sql);

        insertado = conector.realizarActualizacion(sql);
        conector.cerrarConexion();
        return insertado;
    }
}
