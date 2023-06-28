package model;

import java.util.regex.Pattern;

public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType enumeration;
    private final String emailRegex = "^\\d+$";
    private final Pattern pattern = Pattern.compile(emailRegex);

    public Room(String roomNumber, Double price, RoomType enumeration) {
        super();
        if (!pattern.matcher(roomNumber).matches()) {
            throw new IllegalArgumentException("Error, invalid room number");
        }
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
      }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void setRoomPrice(Double price) {
        this.price = price;
    }

    @Override
    public void setRoomType(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean isFree() {
         if (this.price == 0.0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Room number: " + this.roomNumber + ", Price: " + String.format("%.2f", this.price) + ", Room type: " +
                this.enumeration;
    }

    @Override
    public int hashCode() {
        final int num = 11;
        int result = 1;
        result = num * result + ((this.getRoomNumber() == null) ? 0 : this.getRoomNumber().hashCode());
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
        IRoom room = (IRoom) obj;
        return (this.getRoomNumber().equals(room.getRoomNumber()));
    }

}
