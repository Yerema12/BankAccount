import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bankdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Ermek150208";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static List<Customer> loadAllCustomers() {
        List<Customer> list = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT c.name, c.account_number, c.is_vip, c.bonus_rate, b.balance " +
                    "FROM customer c JOIN bank_account b ON c.account_number = b.account_number";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String accNum = rs.getString("account_number");
                boolean isVip = rs.getBoolean("is_vip");
                double bonus = rs.getDouble("bonus_rate");
                double balance = rs.getDouble("balance");

                BankAccount account = new BankAccount(accNum, balance);
                if (isVip) {
                    VIPCustomer vip = new VIPCustomer(name, account, bonus);
                    list.add(vip);
                } else {
                    Customer customer = new Customer(name, account);
                    list.add(customer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void insertAccount(String accNum, double balance) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO bank_account VALUES (?, ?)");
            ps.setString(1, accNum);
            ps.setDouble(2, balance);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertCustomer(String name, String accNum, boolean isVip, double bonusRate) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO customer (name, account_number, is_vip, bonus_rate) VALUES (?, ?, ?, ?)"
            );
            ps.setString(1, name);
            ps.setString(2, accNum);
            ps.setBoolean(3, isVip);
            ps.setDouble(4, bonusRate);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(String accNum) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM customer WHERE account_number = ?");
            ps.setString(1, accNum);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAccount(String accNum) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM bank_account WHERE account_number = ?");
            ps.setString(1, accNum);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
