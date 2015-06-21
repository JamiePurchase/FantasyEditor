package ui;

import app.Editor;
import gfx.Drawing;
import java.awt.Graphics;

public class Context
{
    private String text;
    
    public Context(String text)
    {
        this.text = text;
    }
    
    public void render(Graphics gfx)
    {
        // Coordinates
        int posX = Editor.getInputMouse().getPoint().x + 17;
        int posY = Editor.getInputMouse().getPoint().y;
        
        // Width
        gfx.setFont(Editor.getThemeFont("CONTEXT_TEXT"));
        int width = Drawing.getTextWidth(gfx, this.text) + 20;
        
        // Shadow
        gfx.setColor(Editor.getThemeColour("CONTEXT_SHADOW"));
        gfx.fillRect(posX + 2, posY + 2, width, 30);
        
        // Background
        gfx.setColor(Editor.getThemeColour("CONTEXT_BACKGROUND"));
        gfx.fillRect(posX, posY, width, 30);
        
        // Border
        gfx.setColor(Editor.getThemeColour("CONTEXT_BORDER"));
        gfx.drawRect(posX, posY, width, 30);
        
        // Text
        gfx.setColor(Editor.getThemeColour("CONTEXT_TEXT"));
        Drawing.write(gfx, text, posX + 5, posY + 20);
    }
}