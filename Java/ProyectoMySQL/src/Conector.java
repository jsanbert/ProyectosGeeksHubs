import java.sql.*;

public class Conector {
    private Connection connect;
    private Statement statement;
    private ResultSet resultSet;

    public Conector() {
        connect = null;
        statement = null;
        resultSet = null;
    }

    public void conectar(String bd, String user, String pass) {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
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

    public void realizarConsulta(String consulta, String ... columnas) {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM actor");

            while(resultSet.next()) {
                for(String col : columnas) {
                    System.out.println(resultSet.getString(col));
                }
            }

            this.cerrarConexion();

        } catch (SQLException e) {
            System.out.println("ERROR: excepción SQL");
        }
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
        } catch (Exception e) {
        }

    }
}