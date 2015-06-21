package state;

import app.Editor;
import input.InputKeyboardKey;
import ui.ElementNexus;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import ui.Element;
import ui.FrameModal;

public abstract class State
{
    private boolean modalActive;
    private FrameModal modalFrame;
    private ArrayList<ElementNexus> mouseNexus;
    
    public boolean getModalActive()
    {
        return this.modalActive;
    }
    
    public FrameModal getModalFrame()
    {
        return this.modalFrame;
    }
    
    public abstract void keyPressed(InputKeyboardKey key);
    public abstract void keyReleased(InputKeyboardKey key);
    
    public void mouseNexusAdd(Element element)
    {
        mouseNexusAdd(element.getRef(), element);
    }
    
    public void mouseNexusAdd(String ref, Element element)
    {
        // Create the array if not already defined
        if(mouseNexus == null) {mouseNexus = new ArrayList<ElementNexus>();}
        
        // Append a new nexus object to the array
        mouseNexus.add(new ElementNexus(ref, element));
    }
    
    public boolean mouseNexusCheck(MouseEvent event)
    {
        // Debug
        System.out.println("There are currently " + mouseNexus.size() + " active nexus objects");
        
        boolean found = false;
        for(int e = 0; e < mouseNexus.size(); e++)
        {
            if(mouseNexus.get(e).contains(event.getPoint()))
            {
                // Debug
                System.out.println("Clicked on " + mouseNexus.get(e).getRef() + "!");
        
                // Left Click
                if(event.getButton() == MouseEvent.BUTTON1)
                {
                    // Element has been clicked
                    boolean activate = true;

                    // Does a modal have focus? Is this element part of the modal?
                    if(Editor.getState().getModalActive() && !mouseNexus.get(e).getElement().getModalElement()) {activate = false;}
                    
                    // Is this element visible?
                    if(!mouseNexus.get(e).getElement().getValidAction()) {activate = false;}
                    
                    // Activate this element
                    if(activate)
                    {
                        // Debug
                        System.out.println("Action on element " + mouseNexus.get(e).getElement().getRef());
                        
                        mouseNexus.get(e).getElement().activate();
                        found = true;
                    }
                }
                
                // Right Click
                if(event.getButton() == MouseEvent.BUTTON3)
                {
                    //
                }
            }
        }
        if(!found) {Editor.getInterfaceMenu().collapse();}
        return found;
    }
    
    public Element mouseNexusCheck(Point point)
    {
        for(int e = 0; e < mouseNexus.size(); e++)
        {
            if(mouseNexus.get(e).contains(point))
            {
                if(mouseNexus.get(e).getElement().getTooltipEnabled())
                {
                    return mouseNexus.get(e).getElement();
                }
            }
        }
        return null;
    }
    
    public void mouseNexusClear()
    {
        this.mouseNexus = new ArrayList<ElementNexus>();
    }
    
    public abstract void mousePressed(MouseEvent event);
    public abstract void mouseReleased(MouseEvent event);
    public abstract void render(Graphics gfx);
    
    public void setModal()
    {
        this.modalActive = false;
        this.modalFrame = null;
    }
    
    public void setModal(FrameModal modal)
    {
        this.modalActive = true;
        this.modalFrame = modal;
    }
    
    public void setTitle()
    {
        this.setTitle("", "");
    }
    
    public void setTitle(String editor, String file)
    {
        String title = "jFantasy [" + Editor.getProject().getTitle() + "]";
        if(editor.length() > 0) {title = title + "  ~  " + editor;}
        if(file.length() > 0) {title = title + " " + file;}
        Editor.getInterfaceFrame().setTitle(title);
    }
    
    public abstract void tick();
}