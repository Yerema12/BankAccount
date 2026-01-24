import java.util.*;

public class Bank {
    private String bankName;
    private List<Customer> customers = new ArrayList<>();

    public Bank(String n) {
        bankName = n;
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    // Новый метод: удаление по объекту
    public void removeCustomer(Customer c) {
        customers.remove(c);
    }

    // Новый метод: удаление по номеру счета
    public boolean removeCustomerByAccount(String accNum) {
        Customer c = findCustomerByAccount(accNum);
        if (c != null) {
            customers.remove(c);
            return true;
        }
        return false;
    }

    public void showCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
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

    public Customer findCustomerByAccount(String accNum) {
        for (Customer c : customers)
            if (c.getAccount().getAccountNumber().equals(accNum)) return c;
        return null;
    }

    public List<VIPCustomer> getVIPCustomers() {
        List<VIPCustomer> vipList = new ArrayList<>();
        for (Customer c : customers)
            if (c instanceof VIPCustomer) vipList.add((VIPCustomer) c);
        return vipList;
    }
}
