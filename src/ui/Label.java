package ui;

import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;

public class Label extends Element
{
    private String text;
    private String align;
    // default font? custom?
    
    public Label(String ref, String text, int posX, int posY, int sizeX, String align)
    {
        this.setRef(ref);
        this.text = text;
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(sizeX);
        this.setSizeY(30);
        this.align = align;
        this.setVisible(true);
    }
    
    public void render(Graphics gfx)
    {
        // NOTE: we need to use the theme colourschemes and fonts
        gfx.setColor(Color.BLACK);
        Drawing.write(gfx, this.text, this.getPosX(), this.getPosY(), this.align);
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
}