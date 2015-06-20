package input;

import app.Editor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class InputMouse extends MouseAdapter implements MouseMotionListener
{
    // Location
    public int mouseCoordsX;
    public int mouseCoordsY;
    
    public InputMouse()
    {
        mouseCoordsX = 0;
        mouseCoordsY = 0;
    }
    
    public Point getPoint()
    {
        return new Point(this.mouseCoordsX, this.mouseCoordsY);
    }
    
    public void mouseMoved(MouseEvent e)
    {
        mouseCoordsX = e.getX();
        mouseCoordsY = e.getY();
    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        Editor.mousePressed(e);
    }

    @Override
    public void mouseReleased (MouseEvent e)
    {
        Editor.mouseReleased(e);
    }

}