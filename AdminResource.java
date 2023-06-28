package api;

import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminResource {

    CustomerService customerService;
    ReservationService reservationService;

    private static AdminResource adminResource = new AdminResource();

    private AdminResource() {
        CustomerService customerService = CustomerService.getInstance();
        this.customerService = customerService;
        ReservationService reservationService = ReservationService.getInstance();
        this.reservationService = reservationService;
    }

    public static AdminResource getInstance() {
        return adminResource;
    }

    public Customer getCustomer(String email) {
        return this.customerService.getCustomer(email);
    }

    public void addRoom(String roomNumber, Double price, RoomType enumeration) {
        IRoom room = null;
        try {
            room = new Room(roomNumber, price, enumeration);
        } catch(IllegalArgumentException e) {
            System.out.println("ERROR: invalid room number. Returning to Admin Menu." + "\n");
            return;
        }
        this.reservationService.addRoom(room);
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservations();
    }



}
