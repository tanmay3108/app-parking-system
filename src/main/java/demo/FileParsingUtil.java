package demo;

import java.util.ArrayList;
import parking_lot.*;
/**
 * Created by Tanmay on 22-09-2017.
 */
public class FileParsingUtil
{
    public static ParkingImp obj;

    public static void executeCommand(String sCurrentLine)
    {
        ArrayList<String> commands = ConstantsTesting.getCommands();
        sCurrentLine = sCurrentLine+" ";
        if(commands.contains(sCurrentLine.substring(0,sCurrentLine.indexOf(" "))));
        {
            String commandName =sCurrentLine.substring(0,sCurrentLine.indexOf(" "));
            if(commandName.equalsIgnoreCase(ConstantsTesting.INITIALIZE))
            {
                obj = ParkingImp.initilize(Integer.parseInt(sCurrentLine.trim().substring(sCurrentLine.indexOf(" ")+1)));

            }
            else if(commandName.equalsIgnoreCase(ConstantsTesting.PARK))
            {

                String argument = sCurrentLine.trim().substring(sCurrentLine.indexOf(" ")+1).trim();
                String regNo = argument.substring(0,argument.indexOf(" "));
                String color = argument.substring(argument.indexOf(" ")+1);
                System.out.println(obj.park(regNo,color.toUpperCase()));
            }
            else if(commandName.equalsIgnoreCase(ConstantsTesting.STATUS)) {obj.status();
            }
            else if(commandName.equalsIgnoreCase(ConstantsTesting.LEAVE))
            {System.out.println(obj.leave(Integer.parseInt(sCurrentLine.trim().substring(sCurrentLine.indexOf(" ")+1))));
            }
            else if(commandName.equalsIgnoreCase(ConstantsTesting.GETSLOTSFROCOLOR))
            {obj.getSlotsByColor((sCurrentLine.trim().substring(sCurrentLine.indexOf(" ")+1)));
            }
            else if(commandName.equalsIgnoreCase(ConstantsTesting.GETREGFROCOLOR))
            {obj.getRegNosByColor((sCurrentLine.trim().substring(sCurrentLine.indexOf(" ")+1)));
            }
            else if(commandName.equalsIgnoreCase(ConstantsTesting.GETSLOTFROREG))
            {
                System.out.println(obj.getSlotByRegId(((sCurrentLine.trim().substring(sCurrentLine.indexOf(" ")+1)))));
            }
        }
    }
}
