import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank("Kaspi Bank");

    public static void main(String[] args) {
        List<Customer> dbCustomers = DBConnection.loadAllCustomers();
        for (Customer c : dbCustomers) bank.addCustomer(c);

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addAccount();
                case 2 -> removeAccount();
                case 3 -> checkBalance();
                case 4 -> bank.showCustomers();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice, try again.");
            }

        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("\n=== Bank Menu ===");
        System.out.println("1. Add Account");
        System.out.println("2. Remove Account");
        System.out.println("3. Check Balance");
        System.out.println("4. Show All Customers");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addAccount() {
        try {
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
                System.out.println("Enter VIP bonus rate (0-100%):");
                double bonus = scanner.nextDouble();
                scanner.nextLine();

                VIPCustomer vip = new VIPCustomer(name, new BankAccount(accNum, balance), bonus);
                bank.addCustomer(vip);
                DBConnection.insertAccount(accNum, balance);
                DBConnection.insertCustomer(name, accNum, true, bonus);

                System.out.println("VIP account added successfully!");
            } else {
                Customer customer = new Customer(name, new BankAccount(accNum, balance));
                bank.addCustomer(customer);
                DBConnection.insertAccount(accNum, balance);
                DBConnection.insertCustomer(name, accNum, false, 0);

                System.out.println("Account added successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error adding account: " + e.getMessage());
        }
    }

    private static void removeAccount() {
        System.out.println("Enter account number to remove:");
        String accNum = scanner.nextLine().trim(); // убираем пробелы

        Customer toRemove = bank.findCustomerByAccount(accNum); // ищем среди всех клиентов

        if (toRemove != null) {
            bank.removeCustomer(toRemove); // удаляем из памяти
            DBConnection.deleteCustomer(accNum); // удаляем из БД
            DBConnection.deleteAccount(accNum);
            System.out.println("Account removed successfully!");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance() {
        System.out.println("Enter account number to check balance:");
        String accNum = scanner.nextLine();

        Customer c = bank.findCustomerByAccount(accNum);
        if (c != null) {
            System.out.println("Balance for account " + accNum + ": " + c.getAccount().getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}