package ui;

import app.Editor;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Panel
{
    private String ref;
    private Rectangle rect;
    
    public Panel(String ref, int posX, int posY, int sizeX, int sizeY)
    {
        this.ref = ref;
        this.rect = new Rectangle(posX, posY, sizeX, sizeY);
    }
    
    public void render(Graphics gfx)
    {
        // Background
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BACKGROUND"));
        gfx.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
        
        // Border
        gfx.setColor(Editor.getThemeColour("TOOLBAR_BORDER"));
        gfx.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
    
}