package tileset;

import file.FileRead;
import gfx.Drawing;
import java.awt.image.BufferedImage;
import java.io.IOException;
import project.Project;

public class TilesetService
{
    public static Tileset getTileset(String ref)
    {
        try
        {
            FileRead fr = new FileRead("workspace/Test/tilesets/" + ref + ".jf1tst");
            String[] data = fr.FileReadData();
            BufferedImage tileSheet = Drawing.getImage(data[1]);
            int tileSizeX = Integer.parseInt(data[2]);
            int tileSizeY = Integer.parseInt(data[3]);
            int tileCountX = Integer.parseInt(data[4]);
            int tileCountY = Integer.parseInt(data[5]);
            return new Tileset(ref, tileSheet, tileSizeX, tileSizeY, tileCountX, tileCountY);
        }
        catch (IOException ex) {System.out.println(ex);}
        return null;
    }
}