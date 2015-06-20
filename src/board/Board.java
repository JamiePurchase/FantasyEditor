package board;

import app.Editor;
import file.FileWrite;
import gfx.Drawing;
import java.awt.Graphics;
import java.util.ArrayList;

public class Board
{
    private String ref;
    private int sizeX, sizeY;
    
    // Terrain
    private String terrain[][];
    
    // Zones
    private ArrayList<Zone> zones;
    
    // Render Options
    private int scrollX, scrollY;
    private int renderPosX, renderPosY, renderSizeX, renderSizeY;
    
    public Board(String ref, int sizeX, int sizeY)
    {
        this.ref = ref;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        
        // Terrain
        this.terrain = new String[sizeX][sizeY];
        
        // Zones
        this.zones = new ArrayList<Zone>();
        
        // Render Options
        this.scrollX = 0;
        this.scrollY = 0;
        this.renderPosX = 0;
        this.renderPosY = 0;
        this.renderSizeX = 0;
        this.renderSizeY = 0;
    }
    
    public int getBoardPosX(int screenX)
    {
        return screenX - this.renderPosX + this.scrollX;
    }
    
    public int getBoardPosY(int screenY)
    {
        return screenY - this.renderPosY + this.scrollY;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    /**
     * Gets the location on the screen that a part of the board needs to be drawn at
     * 
     * @param boardX a location on the board (in pixels)
     * @return the relevant location on the screen (in pixels)
     */
    public int getRenderPosX(int boardX)
    {
        return this.renderPosX + boardX - this.scrollX;
    }
    
    /**
     * Gets the location on the screen that a part of the board needs to be drawn at
     * 
     * @param boardY a location on the board (in pixels)
     * @return the relevant location on the screen (in pixels)
     */
    public int getRenderPosY(int boardY)
    {
        return this.renderPosY + boardY - this.scrollY;
    }
    
    private String[] getSaveData()
    {
        // Build an array list
        ArrayList<String> data = new ArrayList<String>();
        data.add("! BOARD FILE - " + this.getRef() + " !");
        data.add("");
        
        // Convert to array
        String[] result = new String[data.size()];
        result = data.toArray(result);
        return result;
    }
        
    /**
     * Gets the terrain code of a particular tile
     * 
     * @param posX
     * @param posY
     * @return the terrain code string (sheet|x|y)
     */
    public String getTerrain(int posX, int posY)
    {
        return this.terrain[posX][posY];
    }
    
    /**
     * Works out what tile a coordinate is on (x)
     * 
     * @param posX a location (in pixels) on the board
     * @return the tileX location of that point
     */
    public int getTileX(int posX)
    {
        // NOTE: this currently doesn't think about board scroll
        int result = 0;
        while(posX > 32)
        {
            result += 1;
            posX -= 32;
        }
        return result;
    }
    
    /**
     * Works out what tile a coordinate is on (y)
     * 
     * @param posY a location (in pixels) on the board
     * @return the tileY location of that point
     */
    public int getTileY(int posY)
    {
        int result = 0;
        while(posY > 32)
        {
            result += 1;
            posY -= 32;
        }
        return result;
    }
    
    public void render(Graphics gfx)
    {
        for(int x = 0; x < this.renderSizeX; x++)
        {
            for(int y = 0; y < this.renderSizeY; y++)
            {
                int tileX = x + scrollX;
                int tileY = y + scrollY;
                gfx.drawImage(Editor.structTilesetGetTile(this.terrain[tileX][tileY]), this.renderPosX + (32 * x), this.renderPosY + (32 * y), null);
            }
        }
    }
    
    public void save(String ref)
    {
        FileWrite fw = new FileWrite("workspace/Test/boards/" + this.getRef() + ".jf1brd", false);
        fw.FileWriteArray(this.getSaveData());
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