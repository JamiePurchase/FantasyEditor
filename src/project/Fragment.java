package project;

import app.Editor;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Fragment
{
    private String name;
    private String type;
    private String path;
    
    public Fragment(String name, String type, String path)
    {
        this.name = name;
        this.type = type;
        this.path = path;
    }
    
    public BufferedImage getIcon()
    {
        return Drawing.getImage("icons/fragment_" + type + ".png", "EDITOR");
    }
    
    public void render(Graphics gfx, int posX, int posY)
    {
        gfx.drawImage(this.getIcon(), posX, posY, null);
        gfx.setFont(Editor.getThemeFont("FRAGMENT_LIST"));
        gfx.drawString(this.name, posX + 20, posY);
    }
    
}