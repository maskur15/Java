package Basic;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ASUS on 16-Sep-23.
 */

class  App{
    private static final String createTableSQL = "CREATE TABLE AdobeBig " +
            "(ID INT PRIMARY KEY ," +
            " NAME TEXT, " +
            " EMAIL VARCHAR(50), " +
            " COUNTRY VARCHAR(50), " +
            " PASSWORD VARCHAR(50))";
    Database db = new Database();


}
public class Database {
    private final String url = "jdbc:postgresql://localhost/varsity";
    private final String user = "postgres";
    private final String password = "postgres";
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        Database db = new Database();
        db.connect();
        App app = new App();
        String selectSql = "select * from students;";

    }
}
