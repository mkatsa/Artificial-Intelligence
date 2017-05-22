/**
 * Write a description of class Navigator here.
 * 
 * @author Manolis Katsaragakis-03113059,Kwstantinos Kouvatzis-03113112
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Scanner;

public class Navigator
{
    static HashMap<String,Node> map = new HashMap<String,Node>();
    
    public static void main( String[] args )
    {
        Client client = new Client(0,0);                /*new Client,initialize his coordinates to 0,0*/
        ArrayList< Taxi_driver > drivers = new ArrayList< Taxi_driver >();  /*Create an ArrayList of Taxi_drivers, in which we store all the drivers and their data*/
        CSV_Client_Reader client_reader  = new CSV_Client_Reader(); /*CSV_Client_Reader is a class which will be used to create objects that read from csv files data for a Client,using a specific method*/
        CSV_Taxi_Reader taxi_reader = new CSV_Taxi_Reader(); /*CSV_Taxi_Reader is a class which will be used to create objects that read from csv files data for a taci driver,using a specific method*/
        CSV_Node_Reader node_reader = new CSV_Node_Reader(); /*CSV_Node_Reader is a class which will be used to create objects that read from csv files data for a Node,using a specific method*/
        
        ArrayList< String > driver_id= new ArrayList< String >();
        ArrayList< Double > driver_distance = new ArrayList< Double >();
        System.out.printf( "READING DATA FROM FILES....\n");
        client_reader.read(client);        /*is a method in class CSV_Client_reader that reads data for Client client from csv file*/
        taxi_reader.read(drivers);         /*is a method in class CSV_Taxi_reader that reads data for taxi drivers client from csv file and stores them to an ArrayList*/
        node_reader.read(map);             /*is a method in class CSV_Nodes_reader that reads data for nodes and saves them to a HashMap*/
        System.out.printf( "READING COMPLETE\n");
        System.out.printf( "-----------------------------------------------------------------------------------\n\n" );        
       
        ArrayList< String > temp_list = new ArrayList< String >();
        Taxi_driver temp_driver = new Taxi_driver();
        Node temp_node = new Node();
        Node goal = new Node();
        double min = 100000;
        double dis_client = 0;
        double dis = 0;
        String closest_x = "0" , closest_y = "0";
        String closest_client_x = "0" , closest_client_y  = "0";
        int c = 0;
        
        //Now we want to find the closest node to the client
        for(String key: map.keySet())   //for all the nodes in the HashMap...
        {
            temp_node = map.get(key);  
            dis = distance_node_client(client , temp_node);
            if( dis < min )             
            {
                min = dis;
                closest_client_x = temp_node.get_coordinate_x();
                closest_client_y = temp_node.get_coordinate_y();
            }
        }
        System.out.printf( "The client is at node %s %s\n" , client.get_coordinate_x() , client.get_coordinate_y() );
        System.out.printf( "The closest node to the client is %s , %s\n" , closest_client_x , closest_client_y );
        System.out.printf( "-----------------------------------------------------------------------------------\n\n" );
        goal.set_coordinate_x(closest_client_x);
        goal.set_coordinate_y(closest_client_y);
        goal.set_heuristic(0);
        
        for(String key: map.keySet())   //for all the nodes in the HashMap...
        {
            temp_node = map.get(key);  
            temp_node.set_heuristic(find_heuristic(goal,temp_node));
            map.put(temp_node.get_coordinate_x() + temp_node.get_coordinate_y(),temp_node);
        }
        
        
        Way w = new Way();
        PathFinder finder = new PathFinder();
        Way result1 = new Way();
        ArrayList< String > result = new ArrayList< String >();
        Node r = new Node();
        Double minimum = 10.0;
        int d = -1;
        String id = "0";
        //Now we want to find the closest node to all the taxi drivers
        for( int i = 0; i < drivers.size(); i++ )
        {
            min = 100000;
            c++;
            temp_driver = drivers.get(i);
            System.out.printf( "%d:The current driver is in position x = %s , y = %s and has id = %s\n" , c , temp_driver.get_coordinate_x() , temp_driver.get_coordinate_y() , temp_driver.get_id() );
            for(String key: map.keySet())
            {
                temp_node = map.get(key);
                dis = distance_node_driver(temp_driver , temp_node);
                if( dis < min )
                {
                    min = dis;
                    closest_x = temp_node.get_coordinate_x();
                    closest_y = temp_node.get_coordinate_y();
                }
            }
            System.out.printf( "For this driver the closest node to begin is %s , %s\n\n" , closest_x , closest_y);
            w.route.add(closest_x+closest_y);
            w.set_g(0.0);
            temp_node = map.get(closest_x + closest_y);
            w.set_f(temp_node.get_heuristic());
            result1 = finder.find_route(w,map,goal,i);
            result = result1.route;
            driver_distance.add( result1.get_g() );
            driver_id.add( temp_driver.get_id() );
            if( i == 0 )
            {
                minimum = result1.get_g();
                d = 0;
                id = "0";
            }
            else
            {
                if( result1.get_g() < minimum )
                {
                    minimum = result1.get_g();
                    d = i;
                    id = drivers.get(i).get_id();
                }
            }
            System.out.printf( "The driver has to follow this path in order to get to the client:\n\n" );
            
            for( int j = 0; j <= result.size() - 1; j++ )
            {
                r = map.get( result.get( j ) );
                if( j >= i )
                    System.out.printf( "%s,%s,%s\n" , r.get_coordinate_x() , r.get_coordinate_y() , r.get_id());
            }
            System.out.printf( "-----------------------------------------------------------------------------------\n\n" );
        }
        
        
        System.out.printf("\n\nThe closest Driver to Client is the driver with id %s, who has to make total distance %f, in order to reach the client!!!\n" , id , minimum );
        
        System.out.printf( "The program finished successfully\n");
    }
    
    private static double distance_node_driver(Taxi_driver driver , Node node)        //give a Node and a Taxi_driver and find their euclidian distance
    {
        double x_node,y_node,x_driver,y_driver,euclidian;
        x_node = Double.parseDouble(node.get_coordinate_x());
        y_node = Double.parseDouble(node.get_coordinate_y());
        x_driver = Double.parseDouble(driver.get_coordinate_x());
        y_driver = Double.parseDouble(driver.get_coordinate_y());
        euclidian = (x_node - x_driver)*(x_node - x_driver) + (y_node - y_driver)*(y_node - y_driver);
        euclidian = Math.sqrt(euclidian);
        return euclidian;
    }
    
    private static double distance_node_client(Client client , Node node)          //gie a Node and a Client and find their euclidian distance
    {
        double x_client,y_client,x_node,y_node,euclidian;
        x_client = client.get_coordinate_x();
        y_client = client.get_coordinate_y();
        x_node = Double.parseDouble(node.get_coordinate_x());
        y_node = Double.parseDouble(node.get_coordinate_y());
        euclidian = (x_node - x_client)*(x_node - x_client) + (y_node - y_client)*(y_node - y_client);
        euclidian = Math.sqrt(euclidian);
        return euclidian;
    }
    
    private static double find_heuristic(Node goal , Node node)
    {
        double x_node,y_node,x_goal,y_goal,euclidian;
        x_node = Double.parseDouble(node.get_coordinate_x());
        y_node = Double.parseDouble(node.get_coordinate_y());
        x_goal = Double.parseDouble(goal.get_coordinate_x());
        y_goal = Double.parseDouble(goal.get_coordinate_y());
        euclidian = (x_node - x_goal)*(x_node - x_goal) + (y_node - y_goal)*(y_node - y_goal);
        euclidian = Math.sqrt(euclidian);
        return euclidian;
    }
}
