
package matb3aaproject;

public class ConferenceRoom extends Room {

    private int numberOfSeats;
    private double seatPrice;

    public ConferenceRoom(int numberOfSeats, int roomNumber, int daysOfReservation, boolean isReserved) {
        super(roomNumber, daysOfReservation, isReserved,"conference");
        this.numberOfSeats = numberOfSeats;
    }
     public ConferenceRoom (int roomNumber,int numberOfSeats,double seatPrice){
        super(roomNumber,0,false,"conference");
        this.numberOfSeats=numberOfSeats;
        this.seatPrice=seatPrice;
        
    }

    public ConferenceRoom() {
    }

    //1
    @Override
    public double getTheTotalPrice() {
        double sum = (numberOfSeats * seatPrice) * getDaysOfReservation();
        return sum;
    }

    //2
    @Override
    public String toString() {
        String ret = "ConferenceRoom #" + getRoomNumber() + " is "
                + (isIsReserved() ? "NOT available" : "available") + " now.\n"
                + "room include " + numberOfSeats + " seat(s), Each with price = $"
                + seatPrice + ".\nTotal price = $" + (numberOfSeats * seatPrice) + ".\n";
        return ret;
    }
    /*
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }
    */

    @Override
    public String getTypeOfRoom() {
         return "conference";
    }

}
