import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Scanner;


public class Way
{
    private double g;       //f = g + h(h is the heuristic and g is the current distance made)
    private double f;
    ArrayList< String > route = new ArrayList< String >();
    
    public void set_g(double p)     //give g a new value
    {
        g = p;
    }
    
    public void set_f(double p)     //give f a new value
    {
        f = p;
    }
    
    public double get_g()           //return g
    {
        return g;
    }
    
    public double get_f()           //return f
    {
        return f;
    }
}
