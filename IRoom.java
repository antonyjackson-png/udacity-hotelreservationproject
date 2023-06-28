package model;

public interface IRoom {

    public String getRoomNumber();

    public Double getRoomPrice();

    public RoomType getRoomType();

    public void setRoomNumber(String roomNumber);

    public void setRoomPrice(Double price);

    public void setRoomType(RoomType enumeration);

    public boolean isFree();
}
