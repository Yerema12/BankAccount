public class Main {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("KZ001", 1000);
        BankAccount account2 = new BankAccount("KZ002", 500);

        Customer customer1 = new Customer("Ayan", account1);
        Customer customer2 = new Customer("Dias", account2);

        Bank bank = new Bank("Kaspi bank");

        bank.showCustomer(customer1);
        bank.showCustomer(customer2);

        System.out.println("Comparing balances:");
        if (account1.getBalance() > account2.getBalance()) {
            System.out.println(customer1.getName() + " has more money");
        } else {
            System.out.println(customer2.getName() + " has more money");
        }
    }
}
