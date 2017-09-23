package parking_lot;
/**
 * Created by Tanmay on 22-09-2017.
 */

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ParkingImp implements Parking
{
    private ArrayList<Vehicle> parking;
    private HashMap<String,ArrayList<Ticket>> colorVsTicket;
    private HashMap<Object,Slot> regVsSlot;
    private int park_capacity;
    private int next_park_location;
    private int slotsAvailable;

    private ParkingImp(int no_slots)
    {
        parking = new ArrayList<Vehicle>(no_slots);
        parking.add(0, new Vehicle("Dummy","Dummy"));
        colorVsTicket = new HashMap<>();
        regVsSlot = new HashMap<>();
        park_capacity = no_slots-1;
        slotsAvailable = park_capacity;
        next_park_location = 1;


    }
    public static ParkingImp initilize(int slots)
    {
        ParkingImp obj =  new ParkingImp(slots+1);
        for(int i = 0;i<=slots;i++)
        {
            obj.parking.add(i,null);
        }
        System.out.println("Created a parking lot with "+obj.park_capacity+" slots");
        return obj;
    }

    @Override
    public Slot getSlotByRegId(Object regId)
    {
        Slot slotId = regVsSlot.get(regId);
        if(slotId == null)
        {
           return new Slot(-1);
        }
        return slotId.clone();
    }

    private ArrayList<Ticket> getTicketByColor(Object color)
    {
        return  colorVsTicket.get(color.toString().toUpperCase());

    }
    @Override
    public ArrayList<Ticket> getSlotsByColor(Object color)
    {
        ArrayList<Ticket> vehicle_list = getTicketByColor(color.toString().toUpperCase());
        if(vehicle_list == null)
        {
            System.out.println("Not Found");
            return null;
        }
        Iterator<Ticket> ite = vehicle_list.iterator();

        while (ite.hasNext())
        {
            System.out.print((ite.next().getSlot().getSlotNo())+" ");
        }
        System.out.println();
        return vehicle_list;
    }
    @Override
    public ArrayList<Ticket> getRegNosByColor(Object color)
    {
        ArrayList<Ticket> vehicle_list = getTicketByColor(color.toString().toUpperCase());
        if(vehicle_list == null)
        {
            System.out.println("Not Found");
            return null;
        }
        Iterator<Ticket> ite = vehicle_list.iterator();

        while (ite.hasNext())
        {
            System.out.print((ite.next().getVehicle().getRegNo())+" ");
        }
        System.out.println();
        return vehicle_list;
    }
    @Override
    public void reset()
    {

    }
    @Override
    public Ticket park(String reg, String color)
    {
        Vehicle v = new Vehicle(reg,color.toUpperCase());
        return park(v);
    }
    private Ticket park(Vehicle v)
    {

        if(parking.contains(v))
        {
            return new Ticket(v,getSlotByRegId(v.getRegNo()));
        }
        if(getAvailability() == 0)
        {

            System.out.println("Sorry, parking lot is full");
            return null;
        }
        Object color = v.getColor();
        Object regNo = v.getRegNo();
        parking.remove(next_park_location);
        parking.add(next_park_location,v);
        slotsAvailable--;
        Ticket ticket = new Ticket(v,new Slot(next_park_location));
        updateNextParkingSlot();
        updateColorMap(ticket);
        updateRegMap(ticket);

        return ticket;

    }
    @Override
    public Ticket leave(int slot)
    {
        if(slot <=0)
        {
            System.out.println("Invalid Slot Id");
            return null;
        }
        if(getAvailability() == park_capacity )
        {
            System.out.println("Not Found");
        }
        Vehicle v =parking.get(slot);
        if(v == null)
        {
            Ticket ticket = new Ticket(v, new Slot((slot)*-1));
            return ticket;
        }
        parking.remove(slot);
        parking.add(slot,null);
        slotsAvailable++;
        Ticket ticket = new Ticket(v, new Slot((slot)*-1));
        updateNextParkingSlot();
        updateColorMap(ticket);
        updateRegMap(ticket);
        return ticket;
    }
    @Override
    public void  status()
    {
        System.out.println(this);
    }
    @Override
    public String toString()
    {

        StringBuilder s = new StringBuilder("Slot No\t\tRegistration No.\t\tColor");
        for(int i =1;i<=park_capacity;i++)
        {
            int slotNo = i;
            Vehicle v =parking.get(i);
            if(v == null)
                continue;
            s.append("\n").append(slotNo).append("\t\t\t").append(v.getRegNo()).append("\t\t\t").append(v.getColor());
        }
        return s.toString();
    }

    private void updateNextParkingSlot()
    {
        parking.remove(0);
        parking.add(0, new Vehicle("Dummy","Dummy"));
        next_park_location = parking.indexOf(null);

    }

    private void updateColorMap(Ticket t)
    {
        Vehicle v = t.getVehicle();
        int slot = t.getSlot().getSlotNo();
        String color = (String)v.getColor();
        color.toUpperCase();
        if(colorVsTicket.get(color)== null)
        {
           ArrayList<Ticket> tickets_list = new ArrayList<Ticket>();

           tickets_list.add(t);
           colorVsTicket.put(color,tickets_list);
        }
        else
        {
            if(slot>0)
            {
                colorVsTicket.get(color).add(t);

            }

            else
            {
                //System.out.println("Slot number:::: "+slot);
                ArrayList<Ticket> a = colorVsTicket.get(color);
                a.remove((t));
                if(colorVsTicket.get(color).size() == 0)
                    colorVsTicket.put(color,null);
            }

        }

    }
    private void updateRegMap(Ticket t)
    {
        Vehicle v = t.getVehicle();
        String regNo = (String)v.getRegNo();
        Slot slot = t.getSlot();
        if(slot.getSlotNo()>0)
        {
            regVsSlot.put(regNo, slot);
        }
        else
            {
                regVsSlot.remove(regNo);
            }

    }


    public int getAvailability()
    {
        return slotsAvailable;
    }

    public int getParkCapacity()
    {
        return park_capacity;
    }
    public int getNextParkLocation()
    {
        return next_park_location;
    }
    public static void main(String args[])
    {

        ParkingImp obj = ParkingImp.initilize(6);
        System.out.println(obj.park("KA-01-HH-1234", "white"));
        System.out.println(obj.park("KA-01-HH-9999", "white"));
        System.out.println(obj.park("KA-01-BB-0001", "black"));
        System.out.println(obj.park("KA-01-HH-7777", "Red"));
        System.out.println(obj.park("KA-01-HH-2701", "Blue"));
        System.out.println(obj.park("KA-01-HH-3141", "Black"));
        System.out.println(obj.leave(4));
        obj.status();
        //System.out.println(obj.getAvailability());

        System.out.println(obj.park("KA-01-P-333", "White"));
        System.out.println(obj.park("DL-12-AA-9999", "White"));
        obj.getRegNosByColor("White");
        obj.getSlotsByColor("White");
        System.out.println(obj.getSlotByRegId("KA-01-HH-3141"));
        System.out.println(obj.getSlotByRegId("MH-04-AY-1111"));






    }

}
