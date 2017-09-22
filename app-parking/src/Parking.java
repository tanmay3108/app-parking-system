/**
 * Created by Tanmay on 22-09-2017.
 */
import java.util.ArrayList;

public interface Parking
{
     Slot getSlotByRegId(Object regId);
     ArrayList<Ticket> getSlotsByColor(Object color);
     ArrayList<Ticket> getRegNosByColor(Object color);
     void reset();
     Ticket park(String regNo, String color);
     Ticket leave(int slot);
     void status();

}