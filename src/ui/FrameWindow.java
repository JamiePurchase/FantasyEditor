package ui;

import app.Editor;
import java.awt.Graphics;

public class FrameWindow extends Frame
{
    public FrameWindow(String ref, String title, boolean close)
    {
        super(ref, title, 0, 0, Editor.getAppWidth(), Editor.getAppHeight(), close);
    }
}