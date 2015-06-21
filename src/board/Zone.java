package board;

import app.Editor;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Zone
{
    private Board board;
    private String ref;
    private Rectangle rect;
    private boolean solid;
    
    public Zone(Board board, String ref, int posX, int posY, int sizeX, int sizeY, boolean solid)
    {
        this.ref = ref;
        this.rect = new Rectangle(posX, posY, sizeX, sizeY);
        this.solid = solid;
    }
    
    /**
     * Collect all object data as a string for saving
     * 
     * @return string delimited by pipe characters
     */
    public String getData()
    {
        return this.ref + "|" + this.rect.x + "|" + this.rect.y + "|" + this.rect.width + "|" + this.rect.height + "|" + this.solid;
        // NOTE: when zones are devloped (eg: scripts attached with conditions) this will need rethinking
        // subclasses could override this method and write things differently
    }
    
    public Rectangle getRect()
    {
        return this.rect;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public boolean getSolid()
    {
        return this.solid;
    }
    
    public void render(Graphics gfx)
    {
        gfx.setColor(Editor.getThemeColour("ZONE_BORDER"));
        gfx.drawRect(this.board.getRenderPosX(this.rect.x), this.board.getRenderPosY(this.rect.y), this.rect.width, this.rect.height);
    }
    
}