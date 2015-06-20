package state;

import app.Editor;
import gfx.Drawing;
import input.InputKeyboardKey;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import ui.Toolbar;

public class StateBoard extends State
{
    // File
    private boolean fileActive;
    private String fileRef;
    
    public StateBoard()
    {
        // File
        this.fileActive = false;
        this.fileRef = "";
        
        // Interface
        this.setTitle("Board Editor", this.getFileTitle());
        this.loadInterface();
    }
    
    private String getFileTitle()
    {
        if(this.fileActive = true) {return this.fileRef;}
        return "";
    }

    public void keyPressed(InputKeyboardKey key)
    {
        //
    }

    public void keyReleased(InputKeyboardKey key)
    {
        //
    }
    
    private void loadInterface()
    {
        Toolbar menu = new Toolbar("EDITOR_MENU", 5, 30, Editor.getAppWidth() - 10);
        menu.addMenu("EDITOR_MENU_FILE", "FILE");
        menu.getMenu(0).addMenu("EDITOR_MENU_FILE_NEW", "NEW");
        menu.getMenu(0).addMenu("EDITOR_MENU_FILE_OPEN", "OPEN");
        menu.getMenu(0).addMenu("EDITOR_MENU_FILE_SAVE", "SAVE");
        menu.getMenu(0).addMenu("EDITOR_MENU_FILE_CLOSE", "CLOSE");
        menu.getMenu(0).addMenu("EDITOR_MENU_FILE_DONE", "DONE");
        menu.addMenu("EDITOR_MENU_BOARD", "BOARD");
        menu.getMenu(0).addMenu("EDITOR_MENU_BOARD_SETTINGS", "SETTINGS");
        Editor.setInterfaceMenu(menu);
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
        if(this.getModalActive())
        {
            Drawing.fadeScreen(gfx, 0.5f);
            this.getModalFrame().render(gfx);
        }
    }

    public void tick()
    {
        //
    }
    
}