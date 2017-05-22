/**
 * This is a class that creates objects of clients and their coordinates in the map
 */

public class Client
{
    private double coordinate_x ,coordinate_y;

    /*
     * Constructor for objects of class Client
     * This constructor creates an object of class Client
     * and defines its coordinates
     */
    
    //constructor for class Client
    public Client(double x , double y ) 
    {
        coordinate_x = x;
        coordinate_y = y;
    }
    
    public double get_coordinate_x()    /*a method which rerurns the coordinate x of the client*/
    {
        return coordinate_x;
    }
    
    public double get_coordinate_y()    /*a method which rerurns the coordinate y of the client*/
    {
        return coordinate_y;
    }
    
    public void set_coordinate_x( double x) /*a method which changes the coordinate x of the client with a given x*/
    {
        coordinate_x = x;
    }
    
    public void set_coordinate_y( double y) /*a method which changes the coordinate y of the client with a given y*/
    {
        coordinate_y = y;
    }
    
}
