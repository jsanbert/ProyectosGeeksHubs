package persistencia;


import java.sql.*;

public class Conector {
    private static Conector instance;

    private Connection connect;
    private Statement statement;
    private ResultSet resultSet;

    private Conector() {
        connect = null;
        statement = null;
        resultSet = null;
    }

    public static Conector getInstance() {
        if (instance == null)
            instance = new Conector();
        return instance;
    }

    public void conectar(String bd, String user, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/" + bd +"?"
                    + "user=" + user + "&password=" + pass);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: No se encontró la clase");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("ERROR: excepción SQL");
            e.printStackTrace();
        }
    }

    public void realizarConsulta(String sql) {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println("ERROR: excepción SQL");
            e.printStackTrace();
        }
    }

    public boolean realizarActualizacion(String sql) {
        boolean insertado = false;
        try {
            statement = connect.createStatement();
            insertado = (statement.executeUpdate(sql) > 0);

        } catch (SQLException e) {
            System.out.println("ERROR: excepción SQL");
            e.printStackTrace();
        }
        return insertado;
    }

    public void cerrarConexion() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) { }

    }
}