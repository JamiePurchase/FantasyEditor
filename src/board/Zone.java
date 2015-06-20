package board;

import app.Editor;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Zone
{
    private Board board;
    private String ref;
    private Rectangle rect;
    
    public Zone(Board board, String ref, int posX, int posY, int sizeX, int sizeY)
    {
        this.ref = ref;
        this.rect = new Rectangle(posX, posY, sizeX, sizeY);
    }
    
    public Rectangle getRect()
    {
        return this.rect;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public void render(Graphics gfx)
    {
        gfx.setColor(Editor.getThemeColour("ZONE_BORDER"));
        gfx.drawRect(this.board.getRenderPosX(this.rect.x), this.board.getRenderPosY(this.rect.y), this.rect.width, this.rect.height);
    }
    
}