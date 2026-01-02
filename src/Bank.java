import java.util.*;

public class Bank {
    private String bankName;
    private List<Customer> customers = new ArrayList<>();

    public Bank(String n) { bankName = n; }

    public void addCustomer(Customer c) { customers.add(c); }

    public void showCustomers() {
        for (Customer c : customers)
            System.out.println(bankName + " - " + c);
    }

    public void sortByBalance() {
        customers.sort(Comparator.comparingDouble(c -> c.getAccount().getBalance()));
    }

    public Customer findCustomerByName(String n) {
        for (Customer c : customers)
            if (c.getName().equalsIgnoreCase(n)) return c;
        return null;
    }

    public List<VIPCustomer> getVIPCustomers() {
        List<VIPCustomer> vipList = new ArrayList<>();
        for (Customer c : customers)
            if (c instanceof VIPCustomer) vipList.add((VIPCustomer) c);
        return vipList;
    }
}
