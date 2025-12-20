public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accnum, double balik) {
        accountNumber = accnum;
        balance = balik;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accnum) {
        accountNumber = accnum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balik) {
        balance = balik;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Balance: " + balance;
    }
}



