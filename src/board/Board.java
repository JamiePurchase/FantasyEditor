package board;

import app.Editor;
import gfx.Drawing;
import java.awt.Graphics;

public class Board
{
    private String ref;
    private int sizeX, sizeY;
    private String terrain[][];
    private int scrollX, scrollY;
    private int renderPosX, renderPosY, renderSizeX, renderSizeY;
    
    public Board(String ref, int sizeX, int sizeY)
    {
        this.ref = ref;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.terrain = new String[sizeX][sizeY];
        
        // Render Options
        this.scrollX = 0;
        this.scrollY = 0;
        this.renderPosX = 0;
        this.renderPosY = 0;
        this.renderSizeX = 0;
        this.renderSizeY = 0;
    }
    
    public String getTerrain(int posX, int posY)
    {
        return this.terrain[posX][posY];
    }
    
    public void render(Graphics gfx)
    {
        for(int x = 0; x < this.renderSizeX; x++)
        {
            for(int y = 0; y < this.renderSizeY; y++)
            {
                int tileX = x + scrollX;
                int tileY = y + scrollY;
                gfx.drawImage(Editor.structTilesetGetTile(this.terrain[tileX][tileY]), this.renderPosX * x, this.renderPosY * y, null);
            }
        }
    }
    
    public void setRender(int posX, int posY, int sizeX, int sizeY)
    {
        this.renderPosX = posX;
        this.renderPosY = posY;
        this.renderSizeX = sizeX;
        this.renderSizeY = sizeY;
    }
    
    public void setTerrain(String terrain, int posX, int posY)
    {
        this.terrain[posX][posY] = terrain;
    }
    
    public void setTerrainAll(String terrain)
    {
        for(int x = 0; x < this.sizeX; x++)
        {
            for(int y = 0; y < this.sizeY; y++)
            {
                this.terrain[x][y] = terrain;
            }
        }
    }
    
}