public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accNum, double bal) {
        accountNumber = accNum;
        balance = bal;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    public void deposit(double amount) { balance += amount; }

    public void withdraw(double amount) {
        if (amount <= balance) balance -= amount;
        else System.out.println("Insufficient funds");
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Balance: " + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BankAccount)) return false;
        BankAccount other = (BankAccount) obj;
        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}
