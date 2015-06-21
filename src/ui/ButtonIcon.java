package ui;

import app.Editor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ButtonIcon extends Button
{
    private BufferedImage imageFile;
    private String imageAlign;
    private boolean selected;
    
    public ButtonIcon(String ref, BufferedImage image, int posX, int posY, int sizeX, int sizeY, boolean selected, Action action)
    {
        super(ref, posX, posY, sizeX, sizeY, action);
        this.imageFile = image;
        this.imageAlign = "TOP_LEFT";
        this.setSelected(selected);
    }
    
    public void render(Graphics gfx)
    {
        this.renderBackground(gfx);
        this.renderImage(gfx);
        this.renderBorder(gfx);
    }
    
    private void renderImage(Graphics gfx)
    {
        int imgX = this.getPosX();
        int imgY = this.getPosY();
        if(this.imageAlign == "MID_CENTRE")
        {
            imgX = this.getPosX() + (this.getSizeX() / 2);
            imgY = this.getPosY() + (this.getSizeY() / 2);
        }
        gfx.drawImage(this.imageFile, imgX, imgY, null);
    }
    
    private void setSelected(boolean value)
    {
        this.selected = value;
        if(value)
        {
            this.setColorBkgActive(Editor.getThemeColour("BUTTON_BACKGROUND_SELECTED_ACTIVE"));
            this.setColorBkgStandard(Editor.getThemeColour("BUTTON_BACKGROUND_SELECTED"));
        }
        else
        {
            this.setColorBkgActive(Editor.getThemeColour("BUTTON_BACKGROUND_STANDARD_ACTIVE"));
            this.setColorBkgStandard(Editor.getThemeColour("BUTTON_BACKGROUND_STANDARD"));
        }
    }
    
}