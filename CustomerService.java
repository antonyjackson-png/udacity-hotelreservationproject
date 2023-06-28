package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private static CustomerService customerService = new CustomerService();

    private ArrayList<Customer> customers;
    private Map<String, Customer> mapOfCustomers;

    private CustomerService() {
        this.customers = new ArrayList<Customer>();
        this.mapOfCustomers = new HashMap<String, Customer>();
    }

    public static CustomerService getInstance() {
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        try {
            Customer customer = new Customer(firstName, lastName, email);

            if (customers.contains(customer)) {
                System.out.println("An account already exists for this email!" + "\n");
            } else {
                this.customers.add(customer);
                CustomerService.addToMap(mapOfCustomers, customer);
            }
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }


    public Customer getCustomer(String customerEmail) {
        Customer customer = null;
        try {
            customer = this.mapOfCustomers.get(customerEmail);
        } catch (Exception e) {
            System.out.println("An account does not exist for this email");
        }
        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        return customers;
    }


    private static void addToMap(Map<String, Customer> map, Customer customer) {
        map.put(customer.getEmail(), customer);
    }

}


