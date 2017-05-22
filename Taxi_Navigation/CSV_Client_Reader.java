/**
 * This is a class that will have objects that will read and save
 * the coordinates x and y for an object of type Client
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSV_Client_Reader {

    public static void read( Client c)      /*reads from a srecific file the data for he client and store them to an object of class Client*/
    {
        
        String csvFile = "client.csv";      /*the file to read from*/
        String line = "";
        String cvsSplitBy = ",";            /*The csv file has comma separated values*/
        int counter = 0;                    /*just a counter*/
        double temp_x,temp_y;               /* temporary cordinates x and y*/
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {        /*while data haven't finished...*/
                // use comma as separator
                counter++;              /*for each data(string) that we read increase counter*/
                String[] data = line.split(cvsSplitBy);
                if ( counter >= 2)
                {
                    temp_x = Double.parseDouble(data[0]);       /*Convert String to Double*/
                    temp_y = Double.parseDouble(data[1]);        /*Convert String to Double*/
                    c.set_coordinate_x(temp_x);                 /*set coordinate x for Client c*/
                    c.set_coordinate_y(temp_y);                 /*set coordinate y for Client c*/
                }
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}