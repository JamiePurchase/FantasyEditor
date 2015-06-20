package ui;

import java.awt.Color;
import java.awt.Graphics;

public class Textbox extends Element
{
    private String value;
    private int length;
    private int cursor;
    private boolean focus;
    
    public Textbox(String ref, String value, int posX, int posY, int sizeX, int length)
    {
        this.setRef(ref);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(sizeX);
        this.setSizeY(30);
        this.value = value;
        this.length = length;
        this.focus = false;
    }

    public void render(Graphics gfx)
    {
        gfx.setColor(Color.WHITE);
        gfx.fillRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
        gfx.setColor(Color.BLACK);
        gfx.drawRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
    }
}