package board;

import audio.Soundtrack;

public class BoardService
{
    
    /*public static Board getBoard(String ref)
    {
        // NOTE: we need to load a file and create a board with the data
    }*/
    
    public static Board getBoardTest()
    {
        Board board = new Board("test", "Test Board", "World", 100, 100, new Soundtrack("test"));
        board.setTerrainAll("test|0|0");
        board.setRender(5, 110, 10, 10);
        return board;
    }
    
}