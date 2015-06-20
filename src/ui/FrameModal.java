package ui;

import app.Editor;
import java.awt.Graphics;

public class FrameModal extends Frame
{
    private Label tempLabel;
    
    public FrameModal(String ref, String title, int sizeX, int sizeY)
    {
        super(ref, title, (Editor.getAppWidth() / 2) - (sizeX / 2), (Editor.getAppHeight() / 2) - (sizeY / 2), sizeX, sizeY, false);
    }
    
    public void addLabel(String ref, String text, int posX, int posY, int sizeX, String align)
    {
        // NOTE: we need to offer better support for adding different elements to the correct part of the modal
        // the section of the modal under the titlebar should be used for these things
        this.tempLabel = new Label(ref, text, this.getPosX() + posX, this.getPosY() + posY, sizeX, align);
    }
    
    public void render(Graphics gfx)
    {
        gfx.setColor(Editor.getThemeColour("APP_BACKGROUND"));
        gfx.fillRect(this.getPosX(), this.getPosY(), this.getSizeX(), this.getSizeY());
        super.render(gfx);
        
        this.tempLabel.render(gfx);
        // NOTE: to keep all of the modals uniform and to provide better access to good layouts,
        // it would be wise to have a table-like grid (several rows going down) that can hold elements
        // eg: label on line one and two, gap on line three, text box on line four, etc...
    }
}