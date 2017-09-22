/**
 * Created by Tanmay on 22-09-2017.
 */
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ParkingImp implements Parking
{
    private ArrayList<Vehicle> parking;
    private HashMap<String,ArrayList<Vehicle>> colorVsVehicle;
    private HashMap<Long,Slot> regVsSlot;
    private int park_capacity;
    private int next_park_location;
    private int slotsAvailable;

    private ParkingImp(int no_slots)
    {
        parking = new ArrayList<Vehicle>(no_slots);
        colorVsVehicle = new HashMap<>();
        regVsSlot = new HashMap<>();
        park_capacity = no_slots;
        slotsAvailable = park_capacity;
        next_park_location = 0;


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

    private ArrayList<Vehicle> getVehicleByColor(Object color)
    {
        ArrayList<Vehicle> vehicle_list = colorVsVehicle.get(color);
        return vehicle_list;
    }
    @Override
    public ArrayList<Vehicle>  getSlotsByColor(Object color)
    {
        ArrayList<Vehicle> vehicle_list = colorVsVehicle.get(color);
        return vehicle_list;
    }
    @Override
    public ArrayList<Vehicle> getRegNosByColor(Object color)
    {
        ArrayList<Vehicle> vehicle_list = colorVsVehicle.get(color);
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
        updateColorMap(v);
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
        Vehicle v =parking.get(slot-1);
        if(v == null) {//throw car not found exception
        }
        parking.remove(slot-1);
        parking.add(slot-1,null);
        updateNextParkingSlot();
        updateColorMap(v);
        updateRegMap(v);
        return new Ticket(v, new Slot(slot*-1));
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
        for(int i =0;i<park_capacity;i++)
        {
            int slotNo = i+1;
            Vehicle v =parking.get(i);
            if(v == null)
                continue;
            s.append("\n").append(slotNo).append("\t").append(v.getRegNo()).append("\t").append(v.getColor());
        }
        return s.toString();
    }

    private void updateNextParkingSlot()
    {
        next_park_location = parking.indexOf(null);

    }

    private void updateColorMap(Vehicle v)
    {

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

        ParkingImp obj = ParkingImp.initilize(6);
        System.out.println(obj.park("MM-123-pp-123", "white"));
        System.out.println(obj.park("UP-32-EZ-420","black"));
        System.out.println("Availability "+obj.getAvailability());
        System.out.println(obj.park("Uk-32-EZ-420","black"));
        obj.status();
        System.out.println(obj.leave(2));
        System.out.println("Availability "+obj.getAvailability());
        System.out.println("Next Parking Slot "+(obj.next_park_location+1));
        obj.status();
        System.out.println(obj.park("TN-32-EZ-420","Black"));
        obj.status();






    }

}
