package model;

public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, (Double) 0.0, enumeration);
    }

    @Override
    public String toString() {
        String string = super.toString();
        return string;
    }
}
