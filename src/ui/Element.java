package ui;

import app.Editor;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Element
{
    private String ref;
    private int posX, posY, sizeX, sizeY;
    private boolean visible;
    private ElementNexus nexus;
    private boolean isModal;
    private String tooltipString = null;
    private boolean tooltipEnable = false;
    
    public void activate()
    {
        //
    }
    
    public boolean getHover()
    {
        if(this.getRect().contains(Editor.getInputMouse().getPoint())) {return true;}
        return false;
    }
    
    public boolean getModalElement()
    {
        return this.isModal;
    }
    
    public ElementNexus getNexus()
    {
        return this.nexus;
    }
    
    public int getPosX()
    {
        return this.posX;
    }
    
    public int getPosXCenter()
    {
        return this.posX + (this.sizeX / 2);
    }
    
    public int getPosY()
    {
        return this.posY;
    }
    
    public int getPosYCenter()
    {
        return this.posY + (this.sizeY / 2);
    }
    
    public Rectangle getRect()
    {
        return new Rectangle(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public int getSizeX()
    {
        return this.sizeX;
    }
    
    public int getSizeY()
    {
        return this.sizeY;
    }
    
    public boolean getTooltipEnabled()
    {
        return this.tooltipEnable;
    }
    
    public String getTooltipString()
    {
        return this.tooltipString;
    }
    
    public boolean getValidAction()
    {
        if(!this.getVisible()) {return false;}
        return true;
    }
    
    public boolean getVisible()
    {
        return this.visible;
    }
    
    public abstract void render(Graphics gfx);
    
    public void setModalElement(boolean isModal)
    {
        this.isModal = isModal;
    }
    
    public void setNexus(ElementNexus nexus)
    {
        this.nexus = nexus;
    }
    
    public void setPosX(int posX)
    {
        this.posX = posX;
    }
    
    public void setPosY(int posY)
    {
        this.posY = posY;
    }
    
    public void setRef(String ref)
    {
        this.ref = ref;
    }
    
    public void setSizeX(int sizeX)
    {
        this.sizeX = sizeX;
    }
    
    public void setSizeY(int sizeY)
    {
        this.sizeY = sizeY;
    }
    
    public void setTooltip()
    {
        this.tooltipString = null;
        this.tooltipEnable = false;
    }
    
    public void setTooltip(String text)
    {
        this.tooltipString = text;
        this.tooltipEnable = true;
    }
    
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
    
}