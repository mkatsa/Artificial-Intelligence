import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Scanner;


public class PathFinder
{
    //Here we use Astar algorithm to find the best route for a driver to the goal
    public static Way find_route(Way w , HashMap< String,Node > map , Node goal , int number)       
    {
        String key_goal = goal.get_coordinate_x() + goal.get_coordinate_y();        //the key of the goal
        ArrayList< String > temp_list = new ArrayList< String >();                  //temporary ArrayList
        Node temp_node = new Node();                                                //temporary Node
        ArrayList< Way > queue = new ArrayList< Way >();                            //here we have an ArrayList of objects Way
        queue.add(w);       //add the 1st way on the queue, which now has only in the list the 1st node from which we begin
        String key;
        String temp_key;
        int size;
        Way temp = new Way();
        Way t = new Way();
        temp = w;
        double distance;    //used for counting the distance between two nodes
        Node kid = new Node();
        int flag = 0;
        int i;
        Node temp_last = new Node();
        while( !key_goal.equals( temp.route.get( temp.route.size() - 1) ) )     //while we haven't reached the goal...
        {
            temp_list = temp.route;
            key = temp_list.get( temp_list.size() - 1);
            temp_node = map.get(key);
            size = temp_node.neighbors.size();

            for(  i = 0; i <= size - 1; i++ )                                   //for all the neighbors of the current node...
            {
               Way new_way = new Way();
               ArrayList< String > copy = new ArrayList< String >( temp_list );
               new_way.route = copy;
                
                kid = map.get( temp_node.neighbors.get(i) );
                if( ( kid.get_coordinate_x() + kid.get_coordinate_y() ).equals(temp_last.get_coordinate_x() + temp_last.get_coordinate_y()) )   //if the current kid is the node we came from before then continue to next kid
                {
                    continue;
                }
                
                if( kid.get_visited() != number )       //if the current kid hasn't been visited before....
                {
                    kid.set_visited(number);
                 
                    new_way.route.add( temp_node.neighbors.get(i) );
                    //count the distance between current node and kid
                    distance = ( Double.parseDouble( temp_node.get_coordinate_x() ) - Double.parseDouble( kid.get_coordinate_x()) ) * ( Double.parseDouble( temp_node.get_coordinate_x() ) - Double.parseDouble( kid.get_coordinate_x()) ) + ( Double.parseDouble( temp_node.get_coordinate_y() ) - Double.parseDouble( kid.get_coordinate_y()) ) * ( Double.parseDouble( temp_node.get_coordinate_y() ) - Double.parseDouble( kid.get_coordinate_y()));
                    distance = Math.sqrt(distance);
                    
                    //set new g and f
                    new_way.set_g( temp.get_g() +  distance );
                    new_way.set_f( new_way.get_g() + kid.get_heuristic() );
                 
                    int j;
                    int f = 0;
                    
                    //sort the ArrayList of Ways, so in the 1st place we have the ArrayList that has the minimum cuurent f.
                    for(  j = 0; j <= queue.size() - 1; j++ )
                    {
                        t = queue.get(j);
                        if( Double.compare(t.get_f(),new_way.get_f())>=1 )
                        {   
                            queue.add( j , new_way );
                            f = 1;
                            break;
                        }
                    }
                    if( f == 0 ){
                        queue.add(j,new_way);
                    }
                }
            }
            for(  i = 0; i <= queue.size() - 1; i++ )
            {
                t = queue.get(i);
                flag = 0;   
                
                //we need to remove from the ArrayList of Ways the Way that has the List which has the path from the driver to the ''father'' of the nodes we inserted before
                for( int l = 0; l <= t.route.size() - 1; l++ )
                {
                    if( t.route.size() == temp.route.size() )
                    {
                        if( !(  t.route.get(l).equals(temp.route.get(l))  ) )
                        {
                            flag = 1;
                            break;
                        }
                    }
                    else
                    {
                        flag = 1;
                        break;
                    }
                }
                if( flag == 0 )
                {
                    queue.remove(i);                        
                    break;
                }
            }
            temp_last=temp_node;
            temp = queue.get(0);
        }
        //Display the real distance needed for each driver to reach the goal
        System.out.printf( "Total distance needed to reach the client is : %f\n" , temp.get_f() );
        //return the sequence of coordinates needed to reach the goal
        return temp;
    }
    
}
