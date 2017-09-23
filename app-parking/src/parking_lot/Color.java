package parking_lot;

/**
 * Created by Tanmay on 24-09-2017.
 */
public enum Color
{
    RED("Red"),BLACK("Black"),BLUE("Blue"),GREEN("Green")
    ,WHITE("White"),
    DUMMY("Dummy");
    private String color;
    private Color(String color)
    {
        this.color = color;
    }
    public String getColorValue()
    {
        return this.color;
    }
}
