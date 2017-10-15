
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import parking_lot.ParkingImp;
import parking_lot.Ticket;

import static org.junit.Assert.*;

/**
 * Created by Tanmay on 22-09-2017.
 */
public class TestTest
{
    private static ParkingImp parking_lot;

    @BeforeClass
    public static void setUp()
    {
        parking_lot = ParkingImp.initilize(6);
        assertEquals(6,parking_lot.getAvailability());
    }

    @AfterClass
    public static void destroy()
    {
        parking_lot.reset();
    }

    @Test
    public void parkVehicle()
    {
        parking_lot.leave(1);
        Ticket t = parking_lot.park("KA-111-HH-3421","White");
        assertEquals("Allocated slot number: 1", t.toString() );
    }
    @Test
    public void parkVehicleWhenParkingFull()
    {
        int capacity = parking_lot.getAvailability();
        String regNo = "KL-AB-11-0";
        String color = "Black";
        for(int i =1;i<=capacity;i++)
        {
            regNo = regNo+"1";
            parking_lot.park(regNo,color);
        }
        Ticket t = parking_lot.park(regNo+"1",color);
        //assertEquals("Sorry, parking lot is full",t.toString());
    }
    @Test
    public void tryingToParkVehicleAlreadyParked()
    {
        if(parking_lot.getAvailability() < 2)
        {
            parking_lot.leave(1);
            parking_lot.leave(2);
        }
        Ticket t = parking_lot.park("KA-111-HH-3421","White");
        int a = parking_lot.getAvailability();
        t = parking_lot.park("KA-111-HH-3421","White");
        int b = parking_lot.getAvailability();
        assertEquals(a,b);
    }
    @Test
    public void leaveParking()
    {
        if(parking_lot.getAvailability() == parking_lot.getParkCapacity())
        {
            parking_lot.park("KA-111-HH-3421","White");
        }
        assertEquals("Slot number 1 is free",parking_lot.leave(1).toString());
    }
    @Test
    public void leaveParkingForVehicleNeverParked()
    {
        if(parking_lot.getAvailability() == parking_lot.getParkCapacity())
        {
            parking_lot.park("KA-111-HH-3421","White");
        }

        parking_lot.leave(1);
        System.out.println(parking_lot.leave(1));
        assertEquals(1,parking_lot.getNextParkLocation());
    }
    @Test
    public void testNextParkingSlot()
    {
        if(parking_lot.getAvailability() == parking_lot.getParkCapacity())
        {
            parking_lot.park("KA-111-HH-3421","White");
        }
        else if(parking_lot.getAvailability() == 0)
        {
            parking_lot.leave(4);
            parking_lot.leave(2);
        }
        int nextParkingLot = parking_lot.getNextParkLocation();
        Ticket t = parking_lot.park("KA-111-HH-3422","White");
       assertEquals(t.getSlot().getSlotNo(),nextParkingLot);

    }
    @Test
    public void testGetTicketsByColor()
    {
        int c =1;
        while(parking_lot.getAvailability() != parking_lot.getParkCapacity())
        {
            parking_lot.leave(c++);
        }
        parking_lot.park("KA-01-HH-1234", "White");
        parking_lot.park("KA-01-HH-9999", "white");
        parking_lot.park("KA-01-BB-0001", "black");
        parking_lot.park("KA-01-HH-7777", "Red");
        parking_lot.park("KA-01-HH-2701", "Blue");
        parking_lot.park("KA-01-HH-3141", "Black");

        assertEquals(2,parking_lot.getRegNosByColor("white").size());
        assertEquals(2,parking_lot.getSlotsByColor("white").size());
        parking_lot.leave(3);
        assertEquals(1,parking_lot.getSlotsByColor("black").size());
        parking_lot.leave(6);
        assertEquals(null,parking_lot.getSlotsByColor("black"));
    }
}