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
    private boolean fileUnsaved;
    
    public StateBoard()
    {
        // File
        this.fileActive = false;
        this.fileRef = "";
        this.fileUnsaved = false;
        
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
        // Board
        this.renderBoard(gfx);
        
        // Modal
        if(this.getModalActive())
        {
            Drawing.fadeScreen(gfx, 0.5f);
            this.getModalFrame().render(gfx);
        }
    }
    
    private void renderBoard(Graphics gfx)
    {
        // Temp
        gfx.setColor(Color.WHITE);
        gfx.fillRect(5, 60, 1356, 703);
        gfx.setColor(Color.BLACK);
        gfx.drawRect(5, 60, 1356, 703);
        
        // Board Background
        gfx.setColor(Color.GREEN);
        gfx.fillRect(66, 108, 1280, 640);
        
        // Scrollbar Background
        gfx.setColor(Color.DARK_GRAY);
        gfx.fillRect(66, 748, 1280, 15);
        gfx.fillRect(1346, 108, 15, 640);
        gfx.fillRect(1346, 748, 15, 15);
        
        // Scrollbar Border
        gfx.setColor(Color.BLACK);
        gfx.drawRect(66, 748, 1280, 15);
        gfx.drawRect(1346, 108, 15, 640);
        gfx.drawRect(1346, 748, 15, 15);
    }

    public void tick()
    {
        //
    }
    
}