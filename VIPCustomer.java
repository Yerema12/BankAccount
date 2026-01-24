public class VIPCustomer extends Customer {
    private double bonusRate;

    public VIPCustomer(String n, BankAccount a, double b) {
        super(n, a);
        bonusRate = b;
    }

    public void depositWithBonus(double amount) {
        double bonus = amount * bonusRate / 100;
        getAccount().deposit(amount + bonus);
    }

    @Override
    public String toString() {
        return super.toString() + ", VIP Bonus Rate: " + bonusRate + "%";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VIPCustomer)) return false;
        VIPCustomer other = (VIPCustomer) obj;
        return getName().equals(other.getName()) && getAccount().equals(other.getAccount());
    }

    @Override
    public int hashCode() {
        return getName().hashCode() + getAccount().hashCode();
    }
}
