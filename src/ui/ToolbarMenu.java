package ui;

import app.Editor;
import gfx.Drawing;
import java.awt.Graphics;
import java.util.ArrayList;
import state.State;

public class ToolbarMenu extends Element
{
    private Toolbar parentToolbar;
    private ToolbarMenu parentMenu;
    private boolean parentTop;
    private String text;
    private ArrayList<ToolbarMenu> elements;
    private boolean expand;
    private boolean actionDefault;
    private Action actionObject;
    
    public ToolbarMenu(String ref, Toolbar toolbar, String text, int posX, int posY)
    {
        this.setRef(ref);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(100);
        this.setSizeY(30);
        this.setVisible(true);
        this.setParent(toolbar);
        this.setText(text);
        this.elements = new ArrayList<ToolbarMenu>();
        this.setExpand(false);
        this.actionDefault = true;
        this.actionObject = null;
    }
    
    public ToolbarMenu(String ref, ToolbarMenu menu, String text, int posX, int posY)
    {
        this.setRef(ref);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(100);
        this.setSizeY(30);
        this.setVisible(true);
        this.setParent(menu);
        this.setText(text);
        this.elements = new ArrayList<ToolbarMenu>();
        this.setExpand(false);
        this.actionDefault = true;
        this.actionObject = null;
    }
    
    public void activate()
    {
        if(this.getVisible())
        {
            // Debug
            System.out.println("Activating " + this.getRef());
            System.out.println("actionDefault: " + this.actionDefault);
            
            // Custom Action
            if(!this.actionDefault) {this.actionObject.activate();}
            
            // Default Action
            else
            {
                if(!this.getExpand())
                {
                    // Collapse other elements
                    if(this.getParentTop()) {this.getParentToolbar().collapse();}
                    else {this.getParentMenu().collapse();}

                    // Expand this element
                    this.setExpand(true);
                }
                else {this.setExpand(false);}
            }
        }
    }
    
    public void addMenu(String ref, String text)
    {
        this.elements.add(new ToolbarMenu(ref, this, text, this.getPosX(), this.getPosY() + ((this.elements.size() + 1) * 30)));
    }
    
    public void addMenu(String ref, String text, Action action)
    {
        ToolbarMenu menu = new ToolbarMenu(ref, this, text, this.getPosX(), this.getPosY() + ((this.elements.size() + 1) * 30));
        menu.setAction(action);
        this.elements.add(menu);
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
    
    public boolean getExpand()
    {
        return this.expand;
    }
    
    public ToolbarMenu getMenu(int pos)
    {
        return this.elements.get(pos);
    }
    
    public String getText()
    {
        return this.text;
    }
    
    public ToolbarMenu getParentMenu()
    {
        return this.parentMenu;
    }
    
    public Toolbar getParentToolbar()
    {
        return this.parentToolbar;
    }
    
    public boolean getParentTop()
    {
        return this.parentTop;
    }
    
    @Override
    public boolean getValidAction()
    {
        if(!this.getVisible()) {return false;}
        if(!this.getParentTop())
        {
            if(!this.getParentMenu().getExpand()) {return false;}
        }
        return true;
    }
    
    public void render(Graphics gfx)
    {
        // Highlight
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BACKGROUND"));
        if(this.getHover()) {gfx.setColor(Editor.getThemeColour("TOOLBAR_BACKGROUND_ACTIVE"));}
        gfx.fillRect(this.getPosX() + 1, this.getPosY() + 1, this.getSizeX() - 1, this.getSizeY() - 1);
        
        // Text
        gfx.setColor(Editor.getThemeColour("TOOLBAR_TEXT"));
        gfx.setFont(Editor.getThemeFont("TOOLBAR_TEXT"));
        Drawing.write(gfx, this.getText(), this.getPosXCenter(), this.getPosY() + 20, "CENTER");
        
        // Expand
        if(this.getExpand()) {renderElements(gfx);}
    }
    
    private void renderElements(Graphics gfx)
    {
        // Background
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BACKGROUND"));
        gfx.fillRect(this.getPosX(), this.getPosY() + 30, 100, this.elements.size() * 30);
        
        // Elements
        for(int e = 0; e < this.elements.size(); e++)
        {
            this.elements.get(e).render(gfx);
        }
        
        // Border
        gfx.setFont(Editor.getThemeFont("TOOLBAR_TEXT"));
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BORDER"));
        gfx.drawRect(this.getPosX(), this.getPosY() + 30, 100, this.elements.size() * 30);
    }
    
    public void setAction(Action action)
    {
        // Debug
        System.out.println("Setting custom action for " + this.getRef());
        
        this.actionDefault = false;
        this.actionObject = action;
    }
    
    public void setExpand(boolean expand)
    {
        this.expand = expand;
        
        // Collapse child elements
        if(!expand) {this.collapse();}
    }
    
    public void setParent(Toolbar toolbar)
    {
        this.parentToolbar = toolbar;
        this.parentTop = true;
    }
    
    public void setParent(ToolbarMenu menu)
    {
        this.parentMenu = menu;
        this.parentTop = false;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
}