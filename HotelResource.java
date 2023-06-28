package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    CustomerService customerService;
    ReservationService reservationService;

    private static HotelResource hotelResource = new HotelResource();

    private HotelResource() {
        CustomerService customerService = CustomerService.getInstance();
        this.customerService = customerService;
        ReservationService reservationService = ReservationService.getInstance();
        this.reservationService = reservationService;
    }

    public static HotelResource getInstance() {
        return hotelResource;
    }

    public Customer getCustomer(String email) {
        return this.customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Collection<Reservation> reservations = null;
        Customer customer = this.getCustomer(customerEmail);
        if (customer == null) {
            System.out.println("\n" + "No account exists for this email!");
            System.out.println("Choose option 3 from the Main Menu to create an account" + "\n");
        } else {
            reservations = reservationService.getCustomersReservation(customer);
        }
        return reservations;
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

}
