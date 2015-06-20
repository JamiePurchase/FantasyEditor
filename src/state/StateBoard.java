package state;

import app.Editor;
import board.Board;
import gfx.Drawing;
import gfx.Theme;
import input.InputKeyboardKey;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import ui.Panel;
import ui.Toolbar;

public class StateBoard extends State
{
    // File
    private boolean fileActive;
    private String fileRef;
    private boolean fileUnsaved;
    
    // Interface
    private Panel uiTools, uiPanel;
    
    // Board
    private Board boardObject;
    private Rectangle boardArea;
    
    // Tools
    private String toolActive, toolGroup, toolPaint;
    
    // Options
    private boolean optionGridlines;
    
    public StateBoard()
    {
        // File
        this.fileActive = false;
        this.fileRef = "";
        this.fileUnsaved = false;
        
        // Interface
        this.setTitle("Board Editor", this.getFileTitle());
        this.loadInterface();
        this.uiTools = new Panel("EDITOR_BOARD_TOOLS", 5, 60, 1356, 50);
        this.uiPanel = new Panel("EDITOR_BOARD_PANEL", 1204, 110, 157, 623);
        
        // Board
        this.boardObject = new Board("test", 100, 100);
        this.boardObject.setTerrainAll("test|0|0");
        this.boardObject.setRender(5, 110, 10, 10);
        this.boardArea = new Rectangle(5, 110, 1184, 608);
        
        // Tools
        this.toolActive = "PAINT_SINGLE";
        this.toolGroup = "TERRAIN";
        this.toolPaint = "test|3|1";
        
        // Options
        this.optionGridlines = false;
        
        // Create Nexus for each element (cascades down)
        this.mouseNexusAdd("EDITOR_QUIT", Editor.getInterfaceFrame().getCloseButton());
        
        //menu.addNexusAll(this);
        // NOTE: we need to create a nexus for every button/option/tool
    }
    
    private void boardClick(MouseEvent event)
    {
        int tileX = this.boardObject.getTileX(this.boardObject.getBoardPosX(event.getX()));
        int tileY = this.boardObject.getTileY(this.boardObject.getBoardPosY(event.getY()));
            
        // NOTE: the status bar should show the correct coordinates in both pixels and tiles for the
        // board, taking into consideration scrolling (also preview terrain tileset data?)
        
        if(event.getButton() == MouseEvent.BUTTON1)
        {
            if(this.toolActive == "PAINT_SINGLE")
            {
                // NOTE: set the terrain of (tileX,tileY) to the current toolPaint image
                this.boardObject.setTerrain(this.toolPaint, tileX, tileY);
                this.setFileUnsaved();
            }
        }
    }
    
    private String getFileTitle()
    {
        if(this.fileActive = true) {return this.fileRef;}
        return "";
    }
    
    private String getToolName()
    {
        if(this.toolActive == "PAINT_SINGLE") {return "Terrain Brush";}
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
            int posX = this.boardObject.getBoardPosX(event.getX());
            int posY = this.boardObject.getBoardPosY(event.getY());
            int tileX = this.boardObject.getTileX(posX);
            int tileY = this.boardObject.getTileY(posY);
            Editor.setInterfaceStatus(this.getToolName(), posX + "," + posY, tileX + "," + tileY, "");
        }
        else {Editor.setInterfaceStatus(this.getToolName(), "", "", "");}
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
        gfx.fillRect(this.boardArea.x, this.boardArea.y, this.boardArea.width, this.boardArea.height);
        
        // Board Terrain
        boardObject.render(gfx);
        
        // Scrollbar Background
        gfx.setColor(Editor.getThemeColour("SCROLLBAR_BACKGROUND"));
        gfx.fillRect(this.boardArea.x, this.boardArea.y + this.boardArea.height, this.boardArea.width, 15);
        gfx.fillRect(this.boardArea.x + this.boardArea.width, this.boardArea.y, 15, this.boardArea.height);
        gfx.fillRect(this.boardArea.x + this.boardArea.width, this.boardArea.y + this.boardArea.height, 15, 15);
        
        // Scrollbar Border
        gfx.setColor(Editor.getThemeColour("SCROLLBAR_BORDER"));
        gfx.drawRect(this.boardArea.x, this.boardArea.y + this.boardArea.height, this.boardArea.width, 15);
        gfx.drawRect(this.boardArea.x + this.boardArea.width, this.boardArea.y, 15, this.boardArea.height);
        gfx.drawRect(this.boardArea.x + this.boardArea.width, this.boardArea.y + this.boardArea.height, 15, 15);
    }
    
    private void renderPanel(Graphics gfx)
    {
        this.uiPanel.render(gfx);
        if(this.toolGroup == "TERRAIN") {renderPanelTerrain(gfx);}
        if(this.toolGroup == "ZONE") {renderPanelZone(gfx);}
        if(this.toolGroup == "ENTITY") {renderPanelEntity(gfx);}
    }
    
    private void renderPanelEntity(Graphics gfx)
    {
        // Panel Header
        gfx.setColor(Editor.getThemeColour("TOOLBAR_TEXT"));
        gfx.setFont(Editor.getThemeFont("TOOLBAR_HEADER_MINI"));
        Drawing.write(gfx, "ENTITIES", 1282, 135, "CENTER");
    }
    
    private void renderPanelTerrain(Graphics gfx)
    {
        // Panel Header
        gfx.setColor(Editor.getThemeColour("TOOLBAR_TEXT"));
        gfx.setFont(Editor.getThemeFont("TOOLBAR_HEADER_MINI"));
        Drawing.write(gfx, "TERRAIN", 1282, 135, "CENTER");
        
        // Tile Image
        BufferedImage tile = Drawing.resize(Editor.structTilesetGetTile(this.toolPaint), 64, 64);
        gfx.drawImage(tile, 1251, 150, null);
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BORDER"));
        gfx.drawRect(1251, 150, 64, 64);
        
        // Tile Info
        gfx.setColor(Editor.getThemeColour("TOOLBAR_TEXT"));
        gfx.setFont(Editor.getThemeFont("TOOLBAR_MINI"));
        Drawing.write(gfx, "hello", 1282, 250, "CENTER");
    }
    
    private void renderPanelZone(Graphics gfx)
    {
        // Panel Header
        gfx.setColor(Editor.getThemeColour("TOOLBAR_TEXT"));
        gfx.setFont(Editor.getThemeFont("TOOLBAR_HEADER_MINI"));
        Drawing.write(gfx, "ZONES", 1282, 135, "CENTER");
    }
    
    private void renderTools(Graphics gfx)
    {
        this.uiTools.render(gfx);
    }
    
    private void setFileUnsaved()
    {
        this.fileUnsaved = true;
    }

    public void tick()
    {
        //
    }
    
}