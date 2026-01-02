import java.util.*;

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
            } else {
                Customer customer = new Customer(name, new BankAccount(accNum, balance));
                bank.addCustomer(customer);
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
