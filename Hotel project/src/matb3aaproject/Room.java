/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matb3aaproject;

/**
 *
 * @author MaTb3aa
 */
public abstract class Room {

    private int roomNumber;
    private int daysOfReservation;
    private boolean isReserved;
    //private String TypeOfRoom;

    public Room(int roomNumber, int daysOfReservation, boolean isReserved,String TypeOfRoom) {
        this.roomNumber = roomNumber;
        this.daysOfReservation = daysOfReservation;
        this.isReserved = isReserved;
    }

    public Room() {

    }

    //1
    public abstract double getTheTotalPrice();
    //2
    public abstract String toString();
    
    public abstract String getTypeOfRoom() ;

    public boolean ReserveRoom(int daysOfReservation) {
        if (isIsReserved()) {
            return false;
        }
        setIsReserved(true);
        this.setDaysOfReservation(daysOfReservation);
        return true;
    }

    public boolean CancelRoom() {
        if (!isIsReserved()) {
            return false;
        }
        setIsReserved(false);
        this.setDaysOfReservation(0);
        return true;
    }
    

    public String CashOut() {
        String ret = "Thanks For You We Hope Be Pleased Her\n"
                + "Your Room Is " + getRoomNumber() + "\n"
                + "You Reserved It : " + getDaysOfReservation() + "\n"
                + "And Your CashOut Is Just ^^ : " + getTheTotalPrice();
        return ret;
    }

    public int getDaysOfReservation() {
        return daysOfReservation;
    }

    public void setDaysOfReservation(int daysOfReservation) {
        this.daysOfReservation = daysOfReservation;
    }

    public boolean isIsReserved() {
        return isReserved;
    }

    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }


    

}
