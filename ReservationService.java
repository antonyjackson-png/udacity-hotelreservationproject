package service;

import model.Customer;
import model.IRoom;
import model.Reservation;


import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = new ReservationService();
    private HashSet<IRoom> rooms;
    private ArrayList<Reservation> reservations;
    private Map<String, IRoom> mapOfRooms;
    private Map<String, ArrayList<Reservation>> mapOfReservations;

    private ReservationService() {
        this.rooms = new HashSet<IRoom>();
        this.reservations = new ArrayList<Reservation>();
        this.mapOfRooms = new HashMap<String, IRoom>();
        this.mapOfReservations = new HashMap<String, ArrayList<Reservation>>();
    }

    public static ReservationService getInstance() {
        return reservationService;
    }

    public void addRoom(IRoom room) {
        if (rooms.contains(room)) {
            System.out.println("A room with this room number already exists!" + "\n");
        } else {
            this.rooms.add(room);
            ReservationService.addToRoomsMap(mapOfRooms, room);
        }
    }

    public IRoom getARoom(String roomId) {
        return this.mapOfRooms.get(roomId);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        this.reservations.add(reservation);
        ReservationService.addToReservationsMap(mapOfReservations, customer, reservation);
        return reservation;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return this.mapOfReservations.get(customer.getEmail());
    }

    public void printAllReservations() {
        for (Reservation reservation: this.reservations) {
            System.out.println(reservation + "\n");
        }
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        HashSet<IRoom> available =  (HashSet) rooms.clone();
        for (Reservation reservation: this.reservations) {
            Date checkInReservation = reservation.getCheckInDate();
            Date checkOutReservation = reservation.getCheckOutDate();
            if (
                    ( (checkInDate.equals(checkInReservation) || checkInDate.after(checkInReservation)) &&
                    (checkInDate.equals(checkOutReservation) || checkInDate.before(checkOutReservation)) ) ||
                    ( (checkOutDate.equals(checkInReservation) || checkOutDate.after(checkInReservation)) &&
                    (checkOutDate.equals(checkOutReservation) || checkOutDate.before(checkOutReservation)) )
            ) {
                System.out.println("Clash!");
                System.out.println(reservation);
                System.out.println("\n");
                available.remove(reservation.getRoom());
            }
        }
        return available;
    }

    private static void addToRoomsMap(Map<String, IRoom> map, IRoom room) {
        map.put(room.getRoomNumber(), room);
    }

    private static void addToReservationsMap(Map<String, ArrayList<Reservation>> map, Customer customer,
                                             Reservation reservation) {
        if (map.containsKey(customer.getEmail())) {
            map.get(customer.getEmail()).add(reservation);
        } else {
            map.put(customer.getEmail(), new ArrayList<Reservation>());
            map.get(customer.getEmail()).add(reservation);
        }
    }


}
