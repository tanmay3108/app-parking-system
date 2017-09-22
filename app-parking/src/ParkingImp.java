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
    private HashMap<Long,Slot> regVsSlot;
    private int park_capacity;
    private int next_park_location;
    private int slotsAvailable;

    private ParkingImp(int no_slots)
    {
        parking = new ArrayList<Vehicle>(no_slots+1);
        parking.add(0, new Vehicle(null,null));
        colorVsTicket = new HashMap<>();
        regVsSlot = new HashMap<>();
        park_capacity = no_slots;
        slotsAvailable = park_capacity;
        next_park_location = 1;


    }
    public static ParkingImp initilize(int slots)
    {
        ParkingImp obj =  new ParkingImp(slots);
        for(int i = 0;i<slots;i++)
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
        return slotId.clone();
    }

    private ArrayList<Ticket> getTicketByColor(Object color)
    {
        ArrayList<Ticket> vehicle_list = colorVsTicket.get(color);
        return vehicle_list;
    }
    @Override
    public ArrayList<Ticket> getSlotsByColor(Object color)
    {
        ArrayList<Ticket> vehicle_list = colorVsTicket.get(color);
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
        ArrayList<Ticket> vehicle_list = colorVsTicket.get(color);
        return vehicle_list;
    }
    @Override
    public void reset()
    {

    }
    @Override
    public Ticket park(String reg, String color)
    {
        Vehicle v = new Vehicle(reg,color);
        return park(v);
    }
    private Ticket park(Vehicle v)
    {
        if(getAvailability() == 0)
        {
            //throw Exception
        }
        Object color = v.getColor();
        Object regNo = v.getRegNo();
        parking.remove(next_park_location);
        parking.add(next_park_location,v);
        slotsAvailable--;
        Ticket ticket = new Ticket(v,new Slot(next_park_location));
        updateNextParkingSlot();
        updateColorMap(ticket);
        updateRegMap(v);

        return ticket;

    }
    @Override
    public Ticket leave(int slot)
    {
        if(slot <=0)
        {
            //Error
        }
        if(getAvailability() == park_capacity ) {//Throw Exception No car found exception
        }
        Vehicle v =parking.get(slot);
        if(v == null) {//throw car not found exception
        }
        parking.remove(slot);
        parking.add(slot,null);
        Ticket ticket = new Ticket(v, new Slot((slot)*-1));
        updateNextParkingSlot();
        updateColorMap(ticket);
        updateRegMap(v);
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

        StringBuilder s = new StringBuilder("Slot No.\tRegistration No.\tColor");
        for(int i =1;i<park_capacity;i++)
        {
            int slotNo = i;
            Vehicle v =parking.get(i);
            if(v == null)
                continue;
            s.append("\n").append(slotNo).append("\t").append(v.getRegNo()).append("\t").append(v.getColor());
        }
        return s.toString();
    }

    private void updateNextParkingSlot()
    {
        parking.remove(0);
        parking.add(0, new Vehicle(null,null));
        next_park_location = parking.indexOf(null);

    }

    private void updateColorMap(Ticket t)
    {
        Vehicle v = t.getVehicle();
        int slot = t.getSlot().getSlotNo();
        String color = (String)v.getColor();
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
    private void updateRegMap(Vehicle v)
    {

    }

    public int getAvailability()
    {
        return slotsAvailable;
    }
    public static void main(String args[])
    {

        ParkingImp obj = ParkingImp.initilize(7);
        System.out.println(obj.park("MM-123-pp-123", "white"));
        System.out.println(obj.park("UP-32-EZ-420","black"));
        System.out.println(obj.park("Uk-32-EZ-420","black"));
        System.out.println(obj.park("Uk-32-EZ-420","black"));
        System.out.println(obj.park("Uk-32-EZ-420","black"));
        System.out.println(obj.park("Uk-32-EZ-420","black"));
        System.out.println(obj.park("Uk-32-EZ-420","black"));
        obj.status();
        obj.getSlotsByColor("black");
        obj.getSlotsByColor("white");
        System.out.println(obj.leave(2));
        obj.status();
        System.out.println(obj.leave(3));
        obj.getSlotsByColor("black");
        System.out.println(obj.park("KL-123-pp-123", "black"));
        obj.getSlotsByColor("black");
        obj.status();
        //System.out.println("Availability "+obj.getAvailability());
        //obj.getSlotsByColor("black");

    }

}
