/**
 * Created by Tanmay on 22-09-2017.
 */
public class Vehicle
{
    private Object regNo;
    private Object color;
    public Vehicle(Object regNo,Object color)
    {
        this.regNo =regNo;
        this.color = color;
    }
    public Object getColor()
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
}
