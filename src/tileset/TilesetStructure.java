package tileset;

import java.util.HashMap;
import java.util.Map;

public class TilesetStructure
{
    private Map<String, Tileset> tileset;
    
    public TilesetStructure()
    {
        this.tileset = new HashMap<String, Tileset>();
    }
    
    public void addTileset(String ref, Tileset tileset)
    {
        this.tileset.put(ref, tileset);
    }
    
    public Tileset getTileset(String ref)
    {
        return this.tileset.get(ref);
    }
}