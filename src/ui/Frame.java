package ui;

import app.Editor;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Frame extends Element
{
    private String title;
    private Button closeButton;
    private boolean closeEnable;
    
    // Icon
    private BufferedImage iconImage;
    private boolean iconEnable;
    
    public Frame(String ref, String title, int posX, int posY, int sizeX, int sizeY, boolean close)
    {
        this.setRef(ref);
        this.setTitle(title);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(sizeX);
        this.setSizeY(sizeY);
        this.closeButton = new ButtonClose(this.getCloseRef(), this.getSizeX() - 26, 5, new Action()
        {
            @Override
            public void activate() {System.exit(0);}
        });
        this.closeEnable = close;
        this.iconImage = null;
        this.iconEnable = false;
    }
    
    public Button getCloseButton()
    {
        return this.closeButton;
    }
    
    public boolean getCloseEnabled()
    {
        return this.closeEnable;
    }
    
    public String getCloseRef()
    {
        return this.getRef() + "_CLOSE";
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    public int getTitlePosX()
    {
        if(this.iconEnable) {return this.getPosX() + 50;}
        return this.getPosX() + 15;
    }
    
    public void render(Graphics gfx)
    {
        // Background
        gfx.setColor(Editor.getThemeColour("TITLEBAR_BACKGROUND"));
        gfx.fillRect(this.getPosX(), this.getPosY(), this.getSizeX(), 30);
        gfx.fillRect(this.getPosX(), this.getPosY() + (this.getSizeY() - 5), this.getSizeX(), 5);
        gfx.fillRect(this.getPosX(), this.getPosY(), 5, this.getSizeY());
        gfx.fillRect(this.getPosX() + (this.getSizeX() - 5), this.getPosY(), 5, this.getSizeY());
        
        // Border
        gfx.setColor(Editor.getThemeColour("TITLEBAR_BORDER"));
        gfx.drawRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
        gfx.drawRect(this.getPosX() + 5, this.getPosY() + 30, this.getSizeX() - 10, this.getSizeY() - 35);
        
        // Titlebar Icon
        if(this.iconEnable) {gfx.drawImage(this.iconImage, this.getPosX() + 10, this.getPosY(), null);}
        
        // Titlebar Text
        gfx.setColor(Editor.getThemeColour("TITLEBAR_TEXT"));
        gfx.setFont(Editor.getThemeFont("TITLEBAR_TEXT"));
        Drawing.write(gfx, this.getTitle(), this.getTitlePosX(), this.getPosY() + 24);
        
        // Close Button
        if(this.getCloseEnabled()) {this.getCloseButton().render(gfx);}
    }
    
    public void setCloseEnabled(boolean enable)
    {
        this.closeEnable = enable;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void setTitleIcon()
    {
        this.iconImage = null;
        this.iconEnable = false;
    }
    
    public void setTitleIcon(BufferedImage icon)
    {
        this.iconImage = icon;
        this.iconEnable = true;
    }
    
}