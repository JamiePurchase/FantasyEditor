package ui;

import app.Editor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Picture extends Element
{
    private BufferedImage imageFile;
    private boolean imageBorder;
    
    public Picture(String ref, BufferedImage image, boolean border, int posX, int posY, int sizeX, int sizeY)
    {
        this.setRef(ref);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setSizeX(100);
        this.setSizeY(30);
        this.setVisible(true);
        this.imageFile = image;
        this.imageBorder = border;
    }
    
    public void render(Graphics gfx)
    {
        gfx.drawImage(this.imageFile, this.getRect().x, this.getRect().y, null);
        if(this.imageBorder)
        {
            gfx.setColor(Editor.getThemeColour("TOOLBAR_BORDER"));
            gfx.drawRect(this.getRect().x, this.getRect().y, this.getRect().width, this.getRect().height);
        }
    }
    
}