package ui;

import app.Editor;
import java.awt.Graphics;
import java.util.ArrayList;
import state.State;

public class Toolbar extends Element
{
    private ArrayList<ToolbarMenu> elements;
    private ArrayList<Label> labels;
    
    public Toolbar(String ref, int posX, int posY, int sizeX)
    {
        this.setRef(ref);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(sizeX);
        this.setSizeY(30);
        this.setVisible(true);
        this.elements = new ArrayList<ToolbarMenu>();
        
        // NOTE: we should use a parent class to encorporate menu options and labels in the element array
        this.labels = new ArrayList<Label>();
    }
    
    public void addLabel(String ref, String text, int posX, int posY, int sizeX, String align)
    {
        this.labels.add(new Label(ref, text, this.getPosX() + posX, this.getPosY() + posY, sizeX, align));
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
    
    public Label getLabel(int pos)
    {
        return this.labels.get(pos);
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
        gfx.drawRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
        
        // Elements
        for(int e = 0; e < this.elements.size(); e++)
        {
            this.elements.get(e).render(gfx);
        }
        
        // Labels
        for(int l = 0; l < this.labels.size(); l++)
        {
            this.labels.get(l).render(gfx);
        }
    }
    
}