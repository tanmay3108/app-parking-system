package demo;
import java.util.ArrayList;

/**
 * Created by Tanmay on 22-09-2017.
 */
public final class ConstantsTesting
{
    private ConstantsTesting() {
        // Not Required
    }

    public static final String INITIALIZE = "create_parking_lot";
    public static final String PARK = "park";
    public static final String LEAVE = "leave";
    public static final String STATUS = "status";
    public static final String GETREGFROCOLOR = "registration_numbers_for_cars_with_colour";
    public static final String GETSLOTSFROCOLOR = "slot_numbers_for_cars_with_colour";
    public static final String GETSLOTFROREG = "slot_number_for_registration_number";
    public static ArrayList<String> getCommands()
    {
        ArrayList<String> commands = new ArrayList<>();
        commands.add(INITIALIZE);
        commands.add(PARK);
        commands.add(LEAVE);
        commands.add(STATUS);
        commands.add(GETREGFROCOLOR);
        commands.add(GETSLOTFROREG);
        commands.add(GETSLOTSFROCOLOR);
    return commands;
    }
}

