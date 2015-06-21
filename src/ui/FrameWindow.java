package ui;

import app.Editor;
import gfx.Drawing;
import java.awt.Graphics;

public class FrameWindow extends Frame
{
    public FrameWindow(String ref, String title, boolean close)
    {
        super(ref, title, 0, 0, Editor.getAppWidth(), Editor.getAppHeight(), close);
        this.setTitleIcon(Drawing.getImage("icons/icon2.png", "EDITOR"));
    }
}