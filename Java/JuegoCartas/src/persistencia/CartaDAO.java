package persistencia;

import modelo.universo.Carta;

public class CartaDAO {
    private static CartaDAO instance;
    private Conector conector;
    private Carta carta;

    private CartaDAO() {
        conector = Conector.getInstance();
    }

    public static CartaDAO getInstance() {
        if(instance == null)
            instance = new CartaDAO();
        return instance;
    }

    public boolean insertarCarta(Carta c) {
        conector.conectar("juego_cartas", "root", "root");
        boolean insertado = true;
        String sql = "INSERT INTO cartas (id, tipo, ataque, salud, habilidad, descripcion_habilidad) VALUES "
                + "(DEFAULT, '" + c.getTipo() + "', " + c.getSalud() + ", '" + c.getHabilidadEspecial() + ", '" + c.getDescripcionHabilidadEspecial() + "');";

        System.out.println(sql);

        insertado = conector.realizarActualizacion(sql);
        conector.cerrarConexion();
        return insertado;
    }
}
