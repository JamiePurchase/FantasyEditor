package ui;

import java.awt.Graphics;
import java.util.ArrayList;
import project.Fragment;

public class FileSelection
{
    private String ref;
    private ArrayList<Fragment> files;
    private int cursorPosX, cursorPosY;
    private int scrollPage;
    
    public FileSelection(String ref, ArrayList<Fragment> files)
    {
        this.ref = ref;
        this.files = files;
        this.cursorPosX = 0;
        this.cursorPosY = 0;
        this.scrollPage = 0;
    }
    
    public void render(Graphics gfx)
    {
        for(int f = 0; f < files.size(); f++)
        {
            files.get(f).render(gfx, 100, 100);
            // NOTE: adjust these coordinates
        }
    }
    
}