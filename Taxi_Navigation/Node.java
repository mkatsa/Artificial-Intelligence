import java.util.ArrayList;

public class Node
{
    private String coordinate_x;    /*Each node has a x coordinate*/
    private String coordinate_y;    /*Each node has a y coordinate*/
    private String name;            /*Each node has a name*/
    private String id;              /*Each node has an id*/
    public ArrayList< String > neighbors = new ArrayList< String >();   /*Each node has an ArrayList, in which we store all the nodes that are connected with the specific node*/
    private int visited;            /*for every node we need to know if we visited him or not, so we have a flag visited, according to its value(0 or 1) we knoe if the node
                                      has been visited in the past or not*/
    private double heuristic;

    public String get_coordinate_x()    /*returns the coordinate x of a node*/
    {
        return coordinate_x;
    }
    
    public String get_coordinate_y()    /*returns the coordinate y of a node*/
    {
        return coordinate_y;
    }
    
    public String get_name()            /*returns the name of a node*/
    {
        return name;
    }
    
    public String get_id()              /*returns the id of a node*/
    {
        return id;
    }
    
    public ArrayList< String > get_neighbors()  /*returns all the neighbors of a node*/
    {
        return neighbors;
    }
    
    public int get_visited()            /*returns if a node is visited or not*/
    {
        return visited;
    }
    
    public double get_heuristic()    /*returns the coordinate x of a node*/
    {
        return heuristic;
    }
    
    public void set_coordinate_x( String x) /*sets the coordinate x of a node*/
    {
        coordinate_x = x;
    }
    
    public void set_coordinate_y( String y) /*sets the coordinate y of a node*/
    {
        coordinate_y = y;
    }
    
    public void set_name( String n)         /*returns the name of a node*/
    {
        name = n;
    }
    
    public void set_id( String i)           /*returns the id of a node*/
    {
        id = i;
    }
    
    public void set_visited( int i)         /*returns the flag visited of a node*/
    {
        visited = i;
    }
    
    public void set_heuristic( double h )    /*returns the coordinate x of a node*/
    {
        heuristic = h;
    }

}
