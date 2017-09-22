package demo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Tanmay on 22-09-2017.
 */
public class ParkingDemo
{
    public static void main(String args[])
    {
        if(args.length<=0)
        {
          while(true)
          {
              Scanner in = new Scanner(System.in);
              String command = in.nextLine();
              FileParsingUtil.executeCommand(command);
          }
        }
        else
        {
            String FILENAME =  args[0];
            BufferedReader br = null;
            FileReader fr = null;
            try {
                fr = new FileReader(FILENAME);
                br = new BufferedReader(fr);
                String sCurrentLine;
                //ParkingImp obj = null;
                while ((sCurrentLine = br.readLine()) != null)
                {
                    FileParsingUtil.executeCommand(sCurrentLine);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                    if (fr != null)
                        fr.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }
}
