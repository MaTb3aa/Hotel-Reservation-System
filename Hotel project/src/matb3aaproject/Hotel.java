
package matb3aaproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hotel {

    Scanner in = new Scanner(System.in);

    public Hotel() throws Exception, InvalidInput {
        File f = new File("file.txt");
        ArrayList<Floor> hotel = new ArrayList<Floor>();
        Input(f, hotel);
        GO(hotel);
    }

    public void Input(File f, ArrayList<Floor> hotel) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        String s = sc.next();
        int numFloors = sc.nextInt();

        for (int i = 0; i < numFloors; i++) {

            String BlaBla1 = sc.next();
            String BlaBla2 = sc.next();
            int NumOfRooms = sc.nextInt();

            hotel.add(new Floor(i + 1, NumOfRooms));
            sc.nextLine();

            for (int j = 0; j < NumOfRooms; j++) {
                //Third Line (Details Of Room) 
                String RoomDetails = sc.nextLine();
                if(RoomDetails.charAt(RoomDetails.length()-1)=='.')
                    RoomDetails = RoomDetails.substring(0, RoomDetails.length() - 1);
                String details[] = RoomDetails.split(",");
                for (int k = 0; k < details.length; k++) {
                    details[k] = details[k].trim();
                }

                int RNum = Integer.parseInt(details[1]);

                if (details[0].equals("Standard")) {
                    int NOfBed = Integer.parseInt(details[2]);
                    double BedPrice = Double.parseDouble(details[3]);
                    hotel.get(i).addRoom(new StandardRoom(RNum, NOfBed, BedPrice));
                } else if (details[0].equals("Sweet")) {
                    double Price = Double.parseDouble(details[2]);
                    hotel.get(i).addRoom(new SweetRoom(RNum, Price));
                } else if (details[0].equals("Conference")) {
                    int NOfSeats = Integer.parseInt(details[2]);
                    double SeatPrice = Double.parseDouble(details[3]);
                    hotel.get(i).addRoom(new ConferenceRoom(RNum, NOfSeats, SeatPrice));
                }

            }
        }

    }

    public void ShowMenu() {
        System.out.println("1) View Status Of The Hotel");
        System.out.println("2) Reserve a Room");
        System.out.println("3) Cancel Reservation");
        System.out.println("4) Check Out");
        System.out.println("5) Display Available Rooms");
        System.out.println("6) Display Total Profit");
        System.out.println("7) Exit.");
        System.out.print("What Is Your Option : ");

    }

    public void GO(ArrayList<Floor> hotel) throws Exception , InvalidInput ,RoomValidation,RoomNotFound {
        int option = -1;
    
        do {
            try {
                ShowMenu();

                option = in.nextInt();
                switch (option) {
                    case 1:
                        hotelStatus(hotel);
                        break;
                    case 2:
                        Case2(hotel);
                        break;
                    case 3:
                        Case3(hotel);
                        break;
                    case 4:
                        Case4(hotel);
                        break;
                    case 5:
                        Case5(hotel);
                        break;
                    case 6:
                        Case6(hotel);
                        break;
                    case 7:
                        break;
                    default:
                        throw new InvalidInput("\n\n\t\t\t\tPlease Choose Number From (1,7)\n");

                }

            }catch(InputMismatchException ex){
                System.out.println("\n\n\t\t\t\tPlease Enter A Valid Input^^\n");
                in.nextLine();
            }
            catch(InvalidInput ex){
                System.out.println(ex.getMessage());
            }
            catch(RoomValidation ex){
                 System.out.println(ex.getMessage());
            }catch(RoomNotFound ex){
                System.out.println(ex.getMessage());
            }
          
        } while (option != 7);

    }
    public void Case2(ArrayList<Floor> hotel) throws InvalidInput , RoomValidation{
        System.out.println("\n\n1)StandardRoom\n"+"2)ConferenceRoom\n"+"3)SweetRoom");
        System.out.print("\nChoose Room Type : ");
        int x = in.nextInt();
        if (x>=1&&x<=3) {
            String s;
            if(x==1)
                s="standard";
            else if(x==2)
                s="conference";
            else 
                s="sweet";
            if (chkIfRoomTypeXisAv(s, hotel)) {
                System.out.print("Enter Days Of Reservation : ");
                int days = in.nextInt();
                if(days<1)
                    throw new InvalidInput("\n\n\t\t\t\tPlease Enter A Valid Number\n");
                int idxf = getIdxOfFloor_Type(hotel, s);
                int idxr = getIdxOfRoom_Type(hotel, s);
                hotel.get(idxf).getListOfRooms().get(idxr).ReserveRoom(days);
                System.out.println("\n\n\t\tSuccessfully Reserve Room Of Type "
                        + s + " Room It's Number Is :"
                        + hotel.get(idxf).getListOfRooms().get(idxr).getRoomNumber() + "\n");
            } else {
                throw new RoomValidation("\n\n\t\t\tThis Type Of Room Is Not Available Right Now \n\n");
            }
        } else {
           throw new InvalidInput("\n\n\t\t\t\tPlease Choose Number From (1,3)\n");
            
        }
    }

    public static void hotelStatus(ArrayList<Floor> l) {
        System.out.println("\n\n\t\t\t\t\t The Status Of The Hotel Is \n");
        for (int i = 0; i < l.size(); i++) {
            System.out.println("*****Floor Number Is : ("+(i+1)+")******");
            System.out.println(l.get(i).allRooms());
        }
    }

    public void Case3(ArrayList<Floor> hotel) throws RoomNotFound ,RoomValidation {
        System.out.print("Please Enter Room Number : ");
        int RoomN = in.nextInt();
        if (chkIfRoomIsExist(RoomN, hotel)) {
            if (chkIfRoomIsReserved(RoomN, hotel)) {
                int idxf = getIdxOfFloor_RoomN(hotel, RoomN);
                int idxr = getIdxOfRoom_RoomN(hotel, RoomN);
                hotel.get(idxf).getListOfRooms().get(idxr).CancelRoom();
                System.out.println("\n\n\t\tSuccessfully Cancel Room Of Type "
                        + hotel.get(idxf).getListOfRooms().get(idxr).getTypeOfRoom() + " Room It's Number Is : "
                        + hotel.get(idxf).getListOfRooms().get(idxr).getRoomNumber() + "\n");
            } else {
                throw new RoomValidation("\n\n\t\t\t\tThis Room Is Already Available\n");
            }

        } else {
            throw new RoomNotFound("\n\n\t\t\tThis Room Number Is Not Exist In The Hotel \n");
        }

    }

    public void Case4(ArrayList<Floor> hotel) throws RoomNotFound ,RoomValidation {
        System.out.print("Please Enter Room Number : ");
        int RoomN = in.nextInt();
        if (chkIfRoomIsExist(RoomN, hotel)) {
            if (chkIfRoomIsReserved(RoomN, hotel)) {
                int idxf = getIdxOfFloor_RoomN(hotel, RoomN);
                int idxr = getIdxOfRoom_RoomN(hotel, RoomN);
                double CashOut = hotel.get(idxf).getListOfRooms().get(idxr).getTheTotalPrice();
                System.out.println(
                        "\n\nThanks For You We Hope Be Pleased In Our Hotel\n"
                        + "The Room Type Is " + hotel.get(idxf).getListOfRooms().get(idxr).getTypeOfRoom() + "\n"
                        + "The Number Of Room Is : " + hotel.get(idxf).getListOfRooms().get(idxr).getRoomNumber() + "\n"
                        + "You Reserved It For : " + hotel.get(idxf).getListOfRooms().get(idxr).getDaysOfReservation() + " Days\n"
                        + "The Total CashOut Is Just : " + CashOut + "\n\n");
                hotel.get(idxf).getListOfRooms().get(idxr).CancelRoom();

            } else {
               throw new RoomValidation("\n\n\t\t\t\tThis Room Is Already Available\n");
            }

        } else {
            throw new RoomNotFound("\n\n\t\t\tThis Room Number Is Not Exist In The Hotel \n");
        }
    }

    public void Case5(ArrayList<Floor> hotel) {
        System.out.println("\n\n\t\t\tThe Avalable Rooms In Our Hotel Is \n\n");
        for (int i = 0; i < hotel.size(); i++) {
            System.out.println("\n\n" + hotel.get(i).availableRooms());
        }
    }

    public void Case6(ArrayList<Floor> hotel) {
        System.out.println("\n\n\t\t\tThe Total Profits In Our Hotel Is As Following : \n\n");
        double Total = 0;
        for (int i = 0; i < hotel.size(); i++) {
            System.out.println("The Total Price In The Floor Number : " + (i + 1)
                    + ") Is :" + hotel.get(i).calculateTheProfitsInFloor() + "\n");
            Total += hotel.get(i).calculateTheProfitsInFloor();
        }
        System.out.println("The Total Price Of The Hotel Is : " + Total + "\n\n");
    }

    public static int getIdxOfFloor_Type(ArrayList<Floor> l, String s) {
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.get(i).getListOfRooms().size(); j++) {
                if (l.get(i).getListOfRooms().get(j).isIsReserved() == false
                        && l.get(i).getListOfRooms().get(j).getTypeOfRoom().equals(s)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int getIdxOfRoom_Type(ArrayList<Floor> l, String s) {
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.get(i).getListOfRooms().size(); j++) {
                if (l.get(i).getListOfRooms().get(j).isIsReserved() == false
                        && l.get(i).getListOfRooms().get(j).getTypeOfRoom().equals(s)) {
                    return j;
                }
            }
        }
        return -1;
    }

    public static int getIdxOfFloor_RoomN(ArrayList<Floor> l, int RoomN) {
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.get(i).getListOfRooms().size(); j++) {
                if (l.get(i).getListOfRooms().get(j).getRoomNumber() == RoomN) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int getIdxOfRoom_RoomN(ArrayList<Floor> l, int RoomN) {
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.get(i).getListOfRooms().size(); j++) {
                if (l.get(i).getListOfRooms().get(j).getRoomNumber() == RoomN) {
                    return j;
                }
            }
        }
        return -1;
    }

    public boolean chkIfRoomTypeXisAv(String s, ArrayList<Floor> l) {
        for (int i = 0; i < l.size(); i++) {
            ArrayList<Room> cur = l.get(i).getListOfRooms();
            for (int j = 0; j < cur.size(); j++) {
                if (cur.get(j).isIsReserved() == false
                        && cur.get(j).getTypeOfRoom().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean chkIfRoomIsExist(int RoomN, ArrayList<Floor> l) {
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.get(i).getListOfRooms().size(); j++) {
                if (l.get(i).getListOfRooms().get(j).getRoomNumber() == RoomN) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean chkIfRoomIsReserved(int RoomN, ArrayList<Floor> l) {
        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.get(i).getListOfRooms().size(); j++) {
                if (l.get(i).getListOfRooms().get(j).getRoomNumber() == RoomN
                        && l.get(i).getListOfRooms().get(j).isIsReserved() == true) {
                    return true;
                }
            }
        }
        return false;
    }

  
}
