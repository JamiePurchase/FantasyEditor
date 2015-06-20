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
        int posX = Editor.getInputMouse().getPoint().x;
        int posY = Editor.getInputMouse().getPoint().y;
        
        // Width
        gfx.setFont(Editor.getThemeFont("CONTEXT_TEXT"));
        int width = Drawing.getTextWidth(gfx, this.text) + 20;
        
        // Shadow?
        
        // Background
        gfx.setColor(Editor.getThemeColour("CONTEXT_BACKGROUND"));
        gfx.fillRect(posX, posY, width, 30);
        
        // Border
        gfx.setColor(Editor.getThemeColour("CONTEXT_BORDER"));
        gfx.fillRect(posX, posY, width, 30);
        
        // Text
        gfx.setColor(Editor.getThemeColour("CONTEXT_TEXT"));
        Drawing.write(gfx, text, posX + 5, 30);
    }
}