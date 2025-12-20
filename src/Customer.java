public class Customer {
    private String name;
    private BankAccount account;

    public Customer(String aty, BankAccount akk) {
        name = aty;
        account = akk;
    }

    public String getName() {
        return name;
    }

    public BankAccount getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", " + account;
    }
}

