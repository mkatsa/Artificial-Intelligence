import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV_Node_Reader
{ 
    public static void read( HashMap<String,Node> m )
    {
        
        String csvFile = "nodes.csv";      /*the file to read from*/
        String line = "";
        String cvsSplitBy = ",";            /*The csv file has comma separated values*/
        int counter = 0;                    /*just a counter*/
        String temp_x=null, temp_y=null , prev_x=null , prev_y=null , curr_x=null , curr_y=null;               /* temporary cordinates x and y*/
        String temp_id=null , prev_id=null , curr_id=null;                                                     /*temporary id*/
        String temp_name = null , prev_name = null , curr_name = null;                                         /*temporary names*/
        String key_prev=null , key_curr=null;                                                                  /*temporary keys*/
        int count = 0;                                                                                         /*just a counter*/
        Node prev_n = new Node();                                                                              /* Used to know the previous node*/
        Node temp = new Node();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {        /*while data haven't finished...*/
                // use comma as separator
                Node n = new Node();
                counter++;              /*for each data(string) that we read increase counter*/
                String[] data = line.split(cvsSplitBy,-1);
                count++;
                if ( counter >= 2 && data[0] != "X")
                {
                    temp_x = data[0];    
                    temp_y = data[1];    
                    temp_id = data[2];    
                    temp_name = data[3];
                }
               
                if( count == 2 )    //insert the 1st Node on the hashmap
                {
                    prev_x = data[0];
                    prev_y = data[1];
                    prev_id = data[2];
                    prev_name = data[3];
                    
                    key_prev = prev_x + prev_y;     //the key of each node is the concatination of coordinates x and y
                    
                    prev_n.set_coordinate_x( prev_x );   //set x
                    prev_n.set_coordinate_y( prev_y );   //set y                 
                    prev_n.set_name( prev_name );        //set name
                    prev_n.set_id( prev_id );            //set id
                    prev_n.set_visited( -1 );             //set visited to 0 ==> not visited
                    
                    //System.out.printf( "1 %s\n" , key_prev );
                    m.put(key_prev,prev_n);              //put the first node on the hashmap
                }
                else if( count == 3 )   //now we are at the second node on the .csv file
                {
                    curr_x = data[0];
                    curr_y = data[1];
                    curr_id = data[2];
                    curr_name = data[3];
                    
                    key_curr = curr_x + curr_y;     //set the key of the node
                    
                    if( !key_curr.equals(key_prev) )    //if these nodes have the same key it actually means that there are the same node 
                    {
                        n.set_coordinate_x( curr_x );
                        n.set_coordinate_y( curr_y );                    
                        n.set_name( curr_name );
                        n.set_id( curr_id );
                        n.set_visited( -1 );
                        
                        if( curr_id.equals(prev_id) )
                        {
                            prev_n.neighbors.add(key_curr);  //if two continuous nodes have the same id it means that there are neighbors
                            n.neighbors.add(key_prev);
                        }
                        m.put(key_curr,n);
                    
                        /*now we need to set the current node to previous, so that the next current comes*/
                        prev_x = curr_x;
                        prev_y = curr_y;
                        prev_id = curr_id;
                        prev_name = curr_name;
                        key_prev = key_curr;
                        prev_n = n;
                    }
                }
                else if( count > 3 )
                {
                    curr_x = data[0];
                    curr_y = data[1];
                    curr_id = data[2];
                    curr_name = data[3];
                    
                    key_curr = curr_x + curr_y;     //set the key of the current node
                    
                    if( m.get(key_curr) == null )   //if we haven't found a node with the same key==>if we haven't found a node with the same coordinates before...
                    {
                         
                        n.set_coordinate_x( curr_x );
                        n.set_coordinate_y( curr_y );
                        n.set_name( curr_name );
                        n.set_id( curr_id );
                        n.set_visited( -1 );
                        
                        if( curr_id.equals(prev_id) )   //check if the current node has the same id with the provious node.If yes, they are neighbors
                        {
                            n.neighbors.add(key_prev);                 
                            prev_n.neighbors.add(key_curr);
                            m.put(key_prev,prev_n);
                        }

                        m.put(key_curr,n);              //Add the Node to the HashMap
                        
                        //and set previous node as current
                        prev_x = curr_x;            
                        prev_y = curr_y;
                        prev_id = curr_id;
                        prev_name = curr_name;
                        key_prev = key_curr;
                        prev_n = n;
                    }
                    else            //else, if we have found a node with the same coordinates...
                    {
                        if( curr_id.equals(prev_id) )       //if the node we just read has the same id with the previous node, we must add the previous node to the
                        {                                   //list of the neighbors of the already existing node with coordinates curr_x and curr_y and key_curr = curr_x + curr_y
                            temp = m.get(key_curr);
                            temp.neighbors.add(key_prev);
                            temp.set_coordinate_x(data[0]);
                            temp.set_coordinate_y(data[1]);
                            temp.set_name(data[3]);
                            temp.set_id(data[2]);
                            n = temp;

                            m.put(key_curr,n);
                            prev_n.neighbors.add(key_curr);
                            m.put(key_prev,prev_n);   
                            
                            //set previous as current
                            prev_x = curr_x;
                            prev_y = curr_y;
                            prev_id = curr_id;
                            prev_name = curr_name;
                            key_prev = key_curr;
                            prev_n = n;
                        }
                        else
                        {
                            //set previous as current
                            key_prev = key_curr;
                            temp = m.get(key_curr);
                            prev_x = curr_x;
                            prev_y = curr_y;
                            prev_id = curr_id;
                            prev_name = curr_name;
                            prev_n = temp;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
