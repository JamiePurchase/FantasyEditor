package board;

import audio.Soundtrack;
import file.FileRead;
import gfx.Drawing;
import java.io.IOException;
import project.Project;

public class BoardService
{
    
    public static Board getBoard(String ref) throws IOException
    {
        // Load board data from file
        FileRead fr = new FileRead("workspace/Test/boards/" + ref + ".jf1brd");
        String[] data = fr.FileReadData();
        String[] title = data[1].split("\\|");
        String[] size = data[2].split("\\|");
        Board board = new Board(ref, title[0], title[1], Integer.parseInt(size[0]), Integer.parseInt(size[1]), new Soundtrack(data[4]));
        String[] bkg = data[3].split("\\|");
        board.setBackground(Drawing.getColorRGB(Integer.parseInt(bkg[0]), Integer.parseInt(bkg[1]), Integer.parseInt(bkg[2])));
        
        // Set terrain
        int pos = 6;
        for(int x = 0; x < Integer.parseInt(size[0]); x++)
        {
            for(int y = 0; y < Integer.parseInt(size[1]); y++)
            {
                board.setTerrain(data[pos], x, y);
                pos += 1;
            }
        }
        
        // Set zones
        return board;
    }
    
    public static Board getBoardTest()
    {
        Board board = new Board("test", "Test Board", "World", 100, 100, new Soundtrack("test"));
        board.setTerrainAll("test|0|0");
        return board;
    }
    
}