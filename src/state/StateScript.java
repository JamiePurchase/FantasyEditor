package state;

import app.Editor;
import input.InputKeyboardKey;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import ui.TextboxArea;

public class StateScript extends State
{
    // File
    
    // Interface
    private TextboxArea uiTextArea;
    
    public StateScript()
    {
        // Frame Title
        String title = "Script Editor";
        if(!Editor.getProjectNull()) {title = "Script Editor - " + Editor.getProject().getTitle();}
        Editor.getInterfaceFrame().setTitle(title);
        
        // Modal
        this.setModal();
        
        // Interface
        uiTextArea = new TextboxArea("SCRIPT_TEXTAREA", "hello", 100, 100, 400, 400, 500);
    }

    public void keyPressed(InputKeyboardKey key)
    {
        //
    }

    public void keyReleased(InputKeyboardKey key)
    {
        //
    }

    public void mousePressed(MouseEvent event)
    {
        //
    }

    public void mouseReleased(MouseEvent event)
    {
        //
    }

    public void render(Graphics gfx)
    {
        uiTextArea.render(gfx);
    }

    public void tick()
    {
        //
    }
    
}