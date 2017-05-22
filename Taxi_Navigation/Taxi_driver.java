/**
 * This is a class of the Taxi drivers
 */
public class Taxi_driver
{
    private String id;          /*Each driver has his id*/
    private String coordinate_x , coordinate_y; /*and his x and y coordinates*/
    
    /*
     * Objects of class Taxi_drivers
     * Each taxi driver has his coordinates x and y and
     * hit id.
     */
    
    public void set_coordinate_x(String x)  /*sets coordinate x of the taxi driver*/
    {
        coordinate_x = x;
    }
    
    public void set_coordinate_y(String y)  /*sets coordinate y of the taxi driver*/
    {
        coordinate_y = y;
    }
    
    public void set_id(String tid) /*sets the id of the taxi driver*/
    {
        id = tid;
    }
    
    public String get_coordinate_x()    /*returns coordinate x of the taxi driver*/
    {
        return coordinate_x;
    }
    
    public String get_coordinate_y()    /*returns coordinate y of the taxi driver*/
    {
        return coordinate_y;
    }
    
    public String get_id()                 /*returns the id of the taxi driver*/
    {
        return id;
    }
}
