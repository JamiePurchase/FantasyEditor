package state;

import app.Editor;
import gfx.Drawing;
import input.InputKeyboardKey;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import ui.Toolbar;

public class StateBoard extends State
{
    // File
    private boolean fileActive;
    private String fileRef;
    private boolean fileUnsaved;
    
    // Interface
    private Rectangle boardArea;
    
    public StateBoard()
    {
        // File
        this.fileActive = false;
        this.fileRef = "";
        this.fileUnsaved = false;
        
        // Interface
        this.setTitle("Board Editor", this.getFileTitle());
        this.loadInterface();
        this.boardArea = new Rectangle(66, 110, 1280, 608);
        
        // Create Nexus for each element (cascades down)
        this.mouseNexusAdd("EDITOR_QUIT", Editor.getInterfaceFrame().getCloseButton());
        
        //menu.addNexusAll(this);
        // NOTE: we need to create a nexus for every button/option/tool
    }
    
    private void boardClick(MouseEvent event)
    {
        int tileX = boardClickGetTileX(boardClickGetPosX(event.getX()));
        int tileY = boardClickGetTileY(boardClickGetPosY(event.getY()));
            
        // NOTE: the status bar should show the correct coordinates in both pixels and tiles for the
        // board, taking into consideration scrolling (also preview terrain tileset data?)
        
        //if(event.getButton() == MouseEvent.BUTTON1) {}
    }
    
    private int boardClickGetPosX(int posX)
    {
        return posX - 66;
    }
    
    private int boardClickGetPosY(int posY)
    {
        return posY - 110;
    }
    
    private int boardClickGetTileX(int posX)
    {
        // NOTE: this currently doesn't think about board scroll
        int result = 0;
        while(posX > 32)
        {
            result += 1;
            posX -= 32;
        }
        return result;
    }
    
    private int boardClickGetTileY(int posY)
    {
        int result = 0;
        while(posY > 32)
        {
            result += 1;
            posY -= 32;
        }
        return result;
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
        
        // Status Bar
        Editor.getInputMouse().setReport(this);
    }
    
    public void mouseMoved(MouseEvent event)
    {
        if(this.boardArea.contains(event.getPoint()))
        {
            int posX = boardClickGetPosX(event.getX());
            int posY = boardClickGetPosY(event.getY());
            int tileX = boardClickGetTileX(posX);
            int tileY = boardClickGetTileX(posY);
            Editor.setInterfaceStatus("", posX + "," + posY, tileX + "," + tileY, "");
        }
        else {Editor.setInterfaceStatus("", "", "", "");}
    }

    public void mousePressed(MouseEvent event)
    {
        // Board Click
        if(this.boardArea.contains(event.getPoint())) {this.boardClick(event);}
        
        // Check Nexus
        else {this.mouseNexusCheck(event);}
    }

    public void mouseReleased(MouseEvent event)
    {
        //
    }

    public void render(Graphics gfx)
    {
        // Board
        this.renderBoard(gfx);
        this.renderTools(gfx);
        this.renderPanel(gfx);
        
        // Modal
        if(this.getModalActive())
        {
            Drawing.fadeScreen(gfx, 0.5f);
            this.getModalFrame().render(gfx);
        }
    }
    
    private void renderBoard(Graphics gfx)
    {
        // Board Background
        gfx.setColor(Color.GREEN);
        gfx.fillRect(66, 110, 1280, 608);
        
        // Temp
        gfx.drawImage(Drawing.getImage("terrain/test.png"), 66, 110, null);
        
        // Scrollbar Background
        gfx.setColor(Editor.getThemeColour("SCROLLBAR_BACKGROUND"));
        gfx.fillRect(66, 718, 1280, 15);
        gfx.fillRect(1346, 110, 15, 608);
        gfx.fillRect(1346, 718, 15, 15);
        
        // Scrollbar Border
        gfx.setColor(Editor.getThemeColour("SCROLLBAR_BORDER"));
        gfx.drawRect(66, 718, 1280, 15);
        gfx.drawRect(1346, 110, 15, 608);
        gfx.drawRect(1346, 718, 15, 15);
    }
    
    private void renderPanel(Graphics gfx)
    {
        // Background
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BACKGROUND"));
        gfx.fillRect(5, 110, 61, 623);
        
        // Border
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BORDER"));
        gfx.drawRect(5, 110, 61, 623);
    }
    
    private void renderTools(Graphics gfx)
    {
        // Background
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BACKGROUND"));
        gfx.fillRect(5, 60, 1356, 50);
        
        // Border
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BORDER"));
        gfx.drawRect(5, 60, 1356, 50);
    }

    public void tick()
    {
        //
    }
    
}