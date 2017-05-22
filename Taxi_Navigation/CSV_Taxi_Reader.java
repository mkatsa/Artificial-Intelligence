/**
 * This is a class that will have objects that will read and save
 * the coordinates x and y for an object of type Client
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class CSV_Taxi_Reader {
    
    public static void read(ArrayList< Taxi_driver > list)
    {
        
        String csvFile = "taxis.csv";      /*the file to read from*/
        String line = "";
        String cvsSplitBy = ",";            /*The csv file has comma separated values*/
        int counter = 0;                    /*just a counter*/
        String temp_x,temp_y;               /* temporary cordinates x and y*/
        String temp_id;                     /*temporary id*/
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {        /*while data haven't finished...*/
                // use comma as separator
                counter++;              /*for each data(string) that we read increase counter*/
                String[] data = line.split(cvsSplitBy);
                Taxi_driver d = new Taxi_driver();

                if ( counter >= 2)
                {
                    temp_x = data[0];       /*Store the coordinate x we read from input file to temp_x*/
                    temp_y = data[1];       /*Store the coordinate y we read from input file to temp_y*/
                    temp_id = data[2];      /*Store the id we read from input file to temp_id*/
                    d.set_coordinate_x(temp_x);         /*set coordinate x for Taxi_driver c*/
                    d.set_coordinate_y(temp_y);         /*set coordinate y for Taxi_driver c*/
                    d.set_id(temp_id);                  /*set id fot Taxi_driver d*/
                    list.add(d);                        /*add the new driver to the list of drivers*/
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}