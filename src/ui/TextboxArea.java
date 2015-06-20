package ui;

public class TextboxArea extends Textbox
{
    private int rows;
    
    public TextboxArea(String ref, String value, int posX, int posY, int sizeX, int sizeY, int length)
    {
        super(ref, value, posX, posY, sizeX, length);
        this.setSizeY(sizeY);
        this.rows = rows;
    }
}