public class Customer {
    private String name;
    private BankAccount account;

    public Customer(String n, BankAccount a) {
        name = n;
        account = a;
    }

    public String getName() { return name; }
    public BankAccount getAccount() { return account; }

    @Override
    public String toString() {
        return "Customer: " + name + ", " + account;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer)) return false;
        Customer other = (Customer) obj;
        return name.equals(other.name) && account.equals(other.account);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + account.hashCode();
    }
}
