/**
 * Created by Tanmay on 22-09-2017.
 */

package parking_lot;

import java.util.*;

public class ParkingImp implements Parking
{
    private List<Vehicle> parking;
    private Map<String,ArrayList<Ticket>> colorVsTicket;
    private Map<Object,Slot> regVsSlot;
    private int park_capacity;
    private int next_park_location;
    private int slotsAvailable;

    private ParkingImp(int no_slots)
    {
        parking = new ArrayList<>(no_slots);
        parking.add(0, ParkingLotConstants.DUMMYVEHICLE);
        colorVsTicket = new HashMap<>();
        regVsSlot = new HashMap<>();
        park_capacity = no_slots-1;
        slotsAvailable = park_capacity;
        next_park_location = ParkingLotConstants.FIRSTPARKINGSPOT;


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
            System.out.println(ParkingLotConstants.NOT_FOUND);
            return null;
        }

        int c = 0;
        for (Ticket ite: vehicle_list)
        {
            if(c!=0)
            {
                System.out.print(", ");
            }
            c++;
            System.out.print((ite.getSlot().getSlotNo())+"");
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
            System.out.println(ParkingLotConstants.NOT_FOUND);
            return null;
        }
        int c = 0;
        for (Ticket ite: vehicle_list)
        {
            if(c!=0)
            {
                System.out.print(", ");
            }
            c++;
            System.out.print((ite.getVehicle().getRegNo())+"");
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
            return new Ticket(null,new Slot(ParkingLotConstants.PARKINGFULL));
        }
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

        Vehicle v =parking.get(slot);
        if(v == null)
        {
            return new Ticket(null, new Slot((slot)*-1));
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

        StringBuilder s = new StringBuilder("Slot No.\t\tRegistration No\t\tColour");
        for(int i =1;i<=park_capacity;i++)
        {
            int slotNo = i;
            Vehicle v =parking.get(i);
            if(v == null)
                continue;
            s.append("\n").append(slotNo).append("\t\t\t").append(v.getRegNo()).append("\t\t\t").append(v.getColor().getColorValue());
        }
        return s.toString();
    }

    private void updateNextParkingSlot()
    {
        parking.remove(0);
        parking.add(0, ParkingLotConstants.DUMMYVEHICLE);
        next_park_location = parking.indexOf(null);

    }

    private void updateColorMap(Ticket t)
    {
        Vehicle v = t.getVehicle();
        int slot = t.getSlot().getSlotNo();
        String color = v.getColor().getColorValue();
        color = color.toUpperCase();
        if(colorVsTicket.get(color)== null)
        {
           ArrayList<Ticket> tickets_list = new ArrayList<>();

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


}
