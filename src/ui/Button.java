package ui;

import app.Editor;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button extends Element
{
    private String text;
    private boolean textBold;
    private Color colorBkgStandard, colorBkgActive;
    private Action action;
    
    public Button(String ref, String text, boolean bold, int posX, int posY, int sizeX, int sizeY)
    {
        this.setRef(ref);
        this.setText(text);
        this.setTextBold(false);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(sizeX);
        this.setSizeY(sizeY);
        this.setVisible(true);
        this.setColorBkgActive(Editor.getThemeColour("BUTTON_BACKGROUND_STANDARD_ACTIVE"));
        this.setColorBkgStandard(Editor.getThemeColour("BUTTON_BACKGROUND_STANDARD"));
        this.action = null;
    }
    
    public Button(String ref, String text, boolean bold, int posX, int posY, int sizeX, int sizeY, Action action)
    {
        this.setRef(ref);
        this.setText(text);
        this.setTextBold(false);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(sizeX);
        this.setSizeY(sizeY);
        this.setVisible(true);
        this.setColorBkgActive(Editor.getThemeColour("BUTTON_BACKGROUND_STANDARD_ACTIVE"));
        this.setColorBkgStandard(Editor.getThemeColour("BUTTON_BACKGROUND_STANDARD"));
        this.action = action;
    }
    
    public void activate()
    {
        this.action.activate();
    }
    
    public Color getColorBkgActive()
    {
        return this.colorBkgActive;
    }
    
    public Color getColorBkgStandard()
    {
        return this.colorBkgStandard;
    }
    
    public String getText()
    {
        return this.text;
    }
    
    public Font getTextFont()
    {
        if(this.textBold) {return Editor.getThemeFont("BUTTON_TEXT_BOLD");}
        return Editor.getThemeFont("BUTTON_TEXT");
    }
    
    public void render(Graphics gfx)
    {
        // Background
        gfx.setColor(this.getColorBkgStandard());
        if(this.getHover()) {gfx.setColor(this.getColorBkgActive());}
        gfx.fillRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
        
        // Border
        gfx.setColor(Editor.getThemeColour("BUTTON_BORDER"));
        gfx.drawRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
        
        // Text
        gfx.setColor(Editor.getThemeColour("BUTTON_BORDER"));
        gfx.setFont(this.getTextFont());
        Drawing.write(gfx, "X", this.getPosXCenter(), 20, "CENTER");
    }
    
    public void setColorBkgActive(Color color)
    {
        this.colorBkgActive = color;
    }
    
    public void setColorBkgStandard(Color color)
    {
        this.colorBkgStandard = color;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public void setTextBold(boolean bold)
    {
        this.textBold = bold;
    }
    
}