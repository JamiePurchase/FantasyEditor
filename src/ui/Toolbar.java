package ui;

import app.Editor;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import state.State;

public class Toolbar extends Element
{
    private ArrayList<ToolbarMenu> elements;
    
    public Toolbar(String ref, int posX, int posY, int sizeX)
    {
        this.setRef(ref);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(sizeX);
        this.setSizeY(30);
        this.setVisible(true);
        this.elements = new ArrayList<ToolbarMenu>();
    }
    
    public void addMenu(String ref, String text)
    {
        this.elements.add(new ToolbarMenu(ref, this, text, this.getPosX() + (this.elements.size() * 100), this.getPosY()));
    }
    
    public void addNexusAll(State state)
    {
        for(int e = 0; e < this.elements.size(); e++)
        {
            state.mouseNexusAdd(this.elements.get(e));
            this.elements.get(e).addNexusAll(state);
        }
    }
    
    public void collapse()
    {
        for(int e = 0; e < this.elements.size(); e++)
        {
            this.elements.get(e).setExpand(false);
        }
    }
    
    public ToolbarMenu getMenu(int pos)
    {
        return this.elements.get(pos);
    }
    
    public void render(Graphics gfx)
    {
        // Background
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BACKGROUND"));
        gfx.fillRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
        
        // Border
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BORDER"));
        gfx.drawRect(5, 30, 1356, 30);
        
        // Elements
        for(int e = 0; e < this.elements.size(); e++)
        {
            this.elements.get(e).render(gfx);
        }
    }
    
}