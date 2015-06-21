package ui;

import app.Editor;
import gfx.Drawing;
import java.awt.Graphics;

public class PanelFloat extends Panel
{
    String title;
    
    public PanelFloat(String ref, String title, int posX, int posY, int sizeX, int sizeY)
    {
        super(ref, posX, posY, sizeX, sizeY);
        this.title = title;
    }
    
    @Override
    public void render(Graphics gfx)
    {
        // Background
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BACKGROUND"));
        gfx.fillRect(this.getRect().x, this.getRect().y, this.getRect().width, this.getRect().height);
        
        // Titlebar
        gfx.setColor(Editor.getThemeColour("TITLEBAR_BACKGROUND"));
        gfx.fillRect(this.getRect().x, this.getRect().y, this.getRect().width, 28);
        gfx.setColor(Editor.getThemeColour("TITLEBAR_TEXT"));
        gfx.setFont(Editor.getThemeFont("TITLEBAR_TEXT_MINI"));
        Drawing.write(gfx, this.title.toUpperCase(), this.getRect().x + (this.getRect().width / 2), this.getRect().y + 20, "CENTER");
        
        // Border
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BORDER"));
        gfx.drawRect(this.getRect().x, this.getRect().y, this.getRect().width, this.getRect().height);
        gfx.drawLine(this.getRect().x, this.getRect().y + 28, this.getRect().x + this.getRect().width, this.getRect().y + 28);
    }
    
}