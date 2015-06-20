package state;

import app.Editor;
import input.InputKeyboardKey;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import ui.Toolbar;

public class StateBoard extends State
{
    
    public StateBoard()
    {
        // Frame Title
        Editor.getInterfaceFrame().setTitle("Fantasy Editor - " + Editor.getProject().getTitle() + " - Board Editor");
        
        // Menu Toolbar
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

    @Override
    public void render(Graphics gfx)
    {
        gfx.setColor(Editor.getThemeColour("APP_BACKGROUND"));
        gfx.fillRect(0, 0, Editor.getAppWidth(), Editor.getAppHeight());
    }

    public void tick()
    {
        //
    }
    
}