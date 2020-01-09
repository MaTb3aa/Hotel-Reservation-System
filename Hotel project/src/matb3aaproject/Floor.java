/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matb3aaproject;

import java.util.ArrayList;

public class Floor {

    private int floorNumber;
    private int NumberOfRooms;
    private ArrayList<Room> ListOfRooms;

    public Floor() {

    }

    public Floor(int floorNumber, int NumberOfRooms) {
        this.floorNumber = floorNumber;
        this.NumberOfRooms = NumberOfRooms;
        ListOfRooms = new ArrayList<Room>();
    }

    public void addRoom(Room r) {
        ListOfRooms.add(r);
    }

    public String availableRooms() {
        String ret = "";
        for (int i = 0; i < ListOfRooms.size(); i++) {
            if (!ListOfRooms.get(i).isIsReserved()) {
                ret += ListOfRooms.get(i).toString();
                ret += "\n";
            }
        }
        return ret;
    }

    public String allRooms() {
        String ret = "";
        for (int i = 0; i < ListOfRooms.size(); i++) {
            
            ret += (i+1)+")"+ListOfRooms.get(i).toString();
            ret += "\n";
        }
        return ret;
    }

    public double calculateTheProfitsInFloor() {
        double sum = 0;
        for (int i = 0; i < NumberOfRooms; i++) {
            if (ListOfRooms.get(i).isIsReserved()) {
                sum += ListOfRooms.get(i).getTheTotalPrice();
            }
        }
        return sum;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getNumberOfRooms() {
        return NumberOfRooms;
    }

    public ArrayList<Room> getListOfRooms() {
        return ListOfRooms;
    }

}
