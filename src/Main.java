import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank("Kaspi Bank");

        System.out.println("Enter number of customers:");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter customer name:");
            String name = scanner.nextLine();

            System.out.println("Enter account number:");
            String accNum = scanner.nextLine();

            System.out.println("Enter balance:");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Is this customer VIP? (yes/no):");
            String isVip = scanner.nextLine();

            if (isVip.equalsIgnoreCase("yes")) {
                System.out.println("Enter VIP bonus rate (in %):");
                double bonus = scanner.nextDouble();
                scanner.nextLine();
                VIPCustomer vip = new VIPCustomer(name, new BankAccount(accNum, balance), bonus);
                bank.addCustomer(vip);
                try (Connection conn = DBConnection.getConnection()) {
                    PreparedStatement ps1 = conn.prepareStatement(
                            "INSERT INTO bank_account VALUES (?, ?)"
                    );
                    ps1.setString(1, accNum);
                    ps1.setDouble(2, balance);
                    ps1.executeUpdate();

                    PreparedStatement ps2 = conn.prepareStatement(
                            "INSERT INTO customer (name, account_number, is_vip, bonus_rate) VALUES (?, ?, ?, ?)"
                    );
                    ps2.setString(1, name);
                    ps2.setString(2, accNum);
                    ps2.setBoolean(3, true);
                    ps2.setDouble(4, bonus);
                    ps2.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Customer customer = new Customer(name, new BankAccount(accNum, balance));
                bank.addCustomer(customer);
                try (Connection conn = DBConnection.getConnection()) {
                    PreparedStatement ps1 = conn.prepareStatement(
                            "INSERT INTO bank_account VALUES (?, ?)"
                    );
                    ps1.setString(1, accNum);
                    ps1.setDouble(2, balance);
                    ps1.executeUpdate();

                    PreparedStatement ps2 = conn.prepareStatement(
                            "INSERT INTO customer (name, account_number, is_vip, bonus_rate) VALUES (?, ?, ?, ?)"
                    );
                    ps2.setString(1, name);
                    ps2.setString(2, accNum);
                    ps2.setBoolean(3, false);
                    ps2.setDouble(4, 0);
                    ps2.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\nAll customers:");
        bank.showCustomers();

        System.out.println("\nSorting customers by balance:");
        bank.sortByBalance();
        bank.showCustomers();

        System.out.println("\nEnter a name to search:");
        String searchName = scanner.nextLine();
        Customer found = bank.findCustomerByName(searchName);
        if (found != null) System.out.println("Found: " + found);
        else System.out.println("Customer not found");

        System.out.println("\nVIP Customers:");
        for (VIPCustomer vip : bank.getVIPCustomers()) System.out.println(vip);
    }
}

