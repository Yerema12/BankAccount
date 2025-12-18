public class Bank {
    private String bankName;

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void showCustomer(Customer customer) {
        System.out.println(bankName + " - " + customer);
    }
}
