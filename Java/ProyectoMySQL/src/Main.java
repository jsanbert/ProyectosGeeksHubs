import java.sql.*;

public class Main {


    public static void main(String[] args) {
        Conector conector = new Conector();
        conector.conectar("sakila", "root", "root");
    }
}
