package input;

import java.awt.Point;
import java.awt.Rectangle;

public class InputMouseNexus
{
    public String ref;
    public Rectangle rect;

    public InputMouseNexus(String ref, Rectangle rect)
    {
        this.ref = ref;
        this.rect = rect;
    }
    
    public boolean contains(Point point)
    {
        if(this.rect.contains(point)) {return true;}
        return false;
    }
    
    public String getRef()
    {
        return this.ref;
    }

}