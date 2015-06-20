package ui;

import app.Editor;
import java.awt.Graphics;

public class FrameModal extends Frame
{
    public FrameModal(String ref, String title, int sizeX, int sizeY)
    {
        super(ref, title, (Editor.getAppWidth() / 2) - (sizeX / 2), (Editor.getAppHeight() / 2) - (sizeY / 2), sizeX, sizeY, false);
    }
    
    public void render(Graphics gfx)
    {
        gfx.setColor(Editor.getThemeColour("APP_BACKGROUND"));
        gfx.fillRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
        super.render(gfx);
    }
}