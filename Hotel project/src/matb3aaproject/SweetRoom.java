package matb3aaproject;

public class SweetRoom extends Room {

    private double pricePerNight;
    public SweetRoom(double pricePerNight, int roomNumber, int daysOfReservation, boolean isReserved) {
        super(roomNumber, daysOfReservation, isReserved,"sweet");
        this.pricePerNight = pricePerNight;
        
    }
     public SweetRoom (int roomNumber,double pricePerNight){
        super(roomNumber,0,false,"sweet");
        this.pricePerNight=pricePerNight; 
   }

    public SweetRoom() {
    }

   
  
    //1
    @Override
    public double getTheTotalPrice() {
        double sum = (pricePerNight) * getDaysOfReservation();
        return sum;
    }
    //2
    @Override
    public String toString() {
        String ret = "SweetRoom #" + getRoomNumber() + " is "
                + (isIsReserved() ? "NOT available" : "available") + " now.\n"
                + "Each night with price = $" + pricePerNight + "\n";
        return ret;
    }

   /* public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }*/

    @Override
    public String getTypeOfRoom() {
        return "sweet";
    }

}
