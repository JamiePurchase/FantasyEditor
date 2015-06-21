package input;

import app.Editor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import state.StateBoard;

public class InputMouse extends MouseAdapter implements MouseMotionListener
{
    // Location
    public int mouseCoordsX;
    public int mouseCoordsY;
    
    // Report
    public boolean reportMove;
    public StateBoard reportState;
    
    public InputMouse()
    {
        mouseCoordsX = 0;
        mouseCoordsY = 0;
        reportMove = false;
        reportState = null;
    }
    
    public Point getPoint()
    {
        return new Point(this.mouseCoordsX, this.mouseCoordsY);
    }
    
    public void mouseMoved(MouseEvent e)
    {
        // Update coordinates
        mouseCoordsX = e.getX();
        mouseCoordsY = e.getY();
        
        // Context (clear existing element due to moving)
        if(Editor.uiContextGetActive()) {Editor.uiContextClear();}
        
        // Context (check new location for potential requirement)
        if(Editor.getState().mouseNexusCheck(e.getPoint()) != null)
        {
            Editor.uiContextCount(Editor.getState().mouseNexusCheck(e.getPoint()));
        }
        
        // Movement event
        if(this.reportMove) {this.reportState.mouseMoved(e);}
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
    
    public void setReport()
    {
        reportMove = false;
        reportState = null;
    }
    
    public void setReport(StateBoard state)
    {
        reportMove = true;
        reportState = state;
    }

}