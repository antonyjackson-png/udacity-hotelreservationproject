package model;

import java.util.Date;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        super();
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public final Customer getCustomer() {
        return this.customer;
    }
    public final IRoom getRoom() {
        return this.room;
    }
    public final Date getCheckInDate() {
        return this.checkInDate;
    }

    public final Date getCheckOutDate() {
        return this.checkOutDate;
    }
    public final void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public final void setRoom(IRoom room) {
        this.room = room;
    }

    public final void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    public final void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return this.customer.toString() + "\n" + this.room.toString() + "\n" + "Check-in Date: " + this.checkInDate +
                ", Check-out Date: " + this.checkOutDate;
    }

}
