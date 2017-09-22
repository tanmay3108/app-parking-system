/**
 * Created by Tanmay on 22-09-2017.
 */
public class Ticket
{
    private Vehicle v;
    private Slot slot;
    public Ticket(Vehicle v, Slot slot)
    {
        this.v = v;
        this.slot = slot;
    }
    public Slot getSlot()
    {
        return slot.clone();
    }

    public Vehicle getVehicle()
    {
        return this.v;
    }
    @Override
    public String toString()
    {
        if(this.slot.getSlotNo() < 0)
        {
            return "Slot number "+ this.slot.getSlotNo()*-1 +" is free";
        }
        return "Allocated slot number: "+ (this.slot.getSlotNo()+1);
    }
}
