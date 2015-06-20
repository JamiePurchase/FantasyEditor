package ui;

import state.StateBoard;

public class ActionBoard extends Action
{
    private StateBoard board;
    
    public ActionBoard(StateBoard board)
    {
        this.board = board;
    }
    
    public void activate()
    {
        //
    }
    
    public StateBoard getBoard()
    {
        return this.board;
    }
}