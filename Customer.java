package model;

import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private String email;

    private final String emailRegex = "^(.+)@(.+).com$";
    private final String nameRegex = "^[a-zA-Z]*$";
    private final Pattern emailPattern = Pattern.compile(emailRegex);
    private final Pattern namePattern = Pattern.compile(nameRegex);

    public Customer(String firstName, String lastName, String email) throws IllegalArgumentException {
        super();
        if (!emailPattern.matcher(email).matches()) {
            throw new IllegalArgumentException("ERROR: invalid email. Returning to Main Menu.");
        }
        if (!namePattern.matcher(firstName).matches()) {
            throw new IllegalArgumentException("ERROR: invalid first name. Returning to Main Menu.");
        }
        if (!namePattern.matcher(lastName).matches()) {
            throw new IllegalArgumentException("ERROR: invalid last name. Returning to Main Menu.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public final String getFirstName() {
        return this.firstName;
    }
    public final String getLastName() {
        return this.lastName;
    }
    public final String getEmail() {
        return this.email;
    }
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + ", Email: " + this.email;
    }

    @Override
    public int hashCode() {
        final int num = 17;
        int result = 1;
        result = num * result + ((this.getEmail() == null) ? 0 : this.getEmail().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if  ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        Customer customer = (Customer) obj;
        return (this.getEmail().equals(customer.getEmail()));
    }



}
