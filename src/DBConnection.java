import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bankdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Ermek150208";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Соединение успешно!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}