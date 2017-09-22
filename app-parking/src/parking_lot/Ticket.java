package parking_lot;
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
        if(this == null)
        {
            return "";
        }
        if(this.slot.getSlotNo() < 0)
        {
            return "Slot number "+ this.slot.getSlotNo()*-1 +" is free";
        }
        return "Allocated slot number: "+ (this.slot.getSlotNo());
    }
    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if(o == this)
            return true;
        if (!(o instanceof Ticket)) {
            return false;
        }
        String color = this.getVehicle().getColor().toString();
        String color2 = ((Ticket) o).getVehicle().getColor().toString();
        String regNo = this.getVehicle().getRegNo().toString();
        String regNo2 = ((Ticket) o).getVehicle().getRegNo().toString();
        if(color.equalsIgnoreCase(color2))
        {
            if(regNo.equalsIgnoreCase(regNo2))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode()
    {
        final int prime =31;
        int result = 1;
        result = prime*result+ this.getVehicle().getRegNo().hashCode();
        result = prime*result+ this.getVehicle().getColor().hashCode();
        return result;
    }
}
