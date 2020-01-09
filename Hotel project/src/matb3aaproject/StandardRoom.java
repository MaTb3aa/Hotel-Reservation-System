
package matb3aaproject;

public class StandardRoom extends Room {

    private int numberOfBeds;
    private double bedPrice;

    public StandardRoom(int numberOfBeds, double bedPrice, int roomNumber, int daysOfReservation, boolean isReserved) {
        super(roomNumber, daysOfReservation, isReserved,"standard");
        this.numberOfBeds = numberOfBeds;
        this.bedPrice = bedPrice;
    }
    public StandardRoom(int roomNumber,int numberOfBeds,double bedPrice) {
        super(roomNumber, 0, false,"standard");
        this.numberOfBeds=numberOfBeds;
        this.bedPrice=bedPrice;
    }
    
    public StandardRoom() {
        
    }


  

    

    //1
    @Override
    public double getTheTotalPrice() {
        double sum = (numberOfBeds * bedPrice) * getDaysOfReservation();
        return sum;
    }

    //2
    @Override
    public String toString() {
        String ret = "StandardRoom #" + getRoomNumber() + " is "
                + (isIsReserved() ? "NOT available" : "available") + " now.\n"
                + "room include " + numberOfBeds + " bed(s), Each with price = $"
                + bedPrice + ".\nTotal price per night = $" + (numberOfBeds * bedPrice) + ".\n";
        return ret;
    }

    /*public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public double getBedPrice() {
        return bedPrice;
    }

    public void setBedPrice(double bedPrice) {
        this.bedPrice = bedPrice;
    }*/

    @Override
    public String getTypeOfRoom() {
           return "standard";
    }

   
}
