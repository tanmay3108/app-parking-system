package parking_lot;
/**
 * Created by Tanmay on 22-09-2017.
 */
public class Vehicle
{
    private Object regNo;
    private Color color;
    public Vehicle(Object regNo,Object color)
    {
        this.regNo =regNo;
        this.color = Color.valueOf(color.toString());
    }
    public Color getColor()
    {
        return this.color;
    }
    public Object getRegNo()
    {
        return this.regNo;
    }
    public boolean verifyRegNo(Object regNo)
    {
        return false;
    }
    @Override
    public String toString()
    {

        return "Color "+this.color+"Reg No "+this.regNo;
    }
    @Override
    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if(o == this)
            return true;
        if (!(o instanceof Vehicle)) {
            return false;
        }
        String color = this.getColor().toString();
        String color2 = ((Vehicle)o).getColor().toString();
        String regNo = this.getRegNo().toString();
        String regNo2 = ((Vehicle)o).getRegNo().toString();
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
        result = prime*result+ this.getRegNo().hashCode();
        result = prime*result+ this.getColor().hashCode();
        return result;
    }

}
