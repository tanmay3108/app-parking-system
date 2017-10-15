package parking_lot;
/**
 * Created by Tanmay on 22-09-2017.
 */
import java.util.ArrayList;
import java.util.List;

public interface Parking
{
     Slot getSlotByRegId(Object regId);
     List<Ticket> getSlotsByColor(Object color);
     List<Ticket> getRegNosByColor(Object color);
     void reset();
     Ticket park(String regNo, String color);
     Ticket leave(int slot);
     void status();

}