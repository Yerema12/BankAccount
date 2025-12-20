public class Bank {
    private String bankName;

    public Bank(String bankAty) {
        bankName = bankAty;
    }

    public String getBankName() {
        return bankName;
    }

    public void showCustomer(Customer customer) {
        System.out.println(bankName + " - " + customer);
    }
}

