package parking_lot;
/**
 * Created by Tanmay on 22-09-2017.
 */
public class Slot implements Cloneable
{
    private int slotNo;
    public Slot(int slotNo)
    {
        this.slotNo = slotNo;
    }

    public int getSlotNo()
    {
        return  this.slotNo;
    }
    public Slot clone()
    {

        return new Slot(this.slotNo);
    }
    @Override
    public String toString()
    {
        if(this.slotNo<=0)
        {
            return ParkingLotConstants.NOT_FOUND;
        }
        return this.slotNo+"";
    }
}
