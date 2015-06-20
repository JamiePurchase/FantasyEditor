package ui;

import app.Editor;

public class ButtonClose extends Button
{
    public ButtonClose(String ref, int posX, int posY, Action action)
    {
        super(ref, "X", true, posX, posY, 20, 20, action);
        this.setColorBkgActive(Editor.getThemeColour("BUTTON_BACKGROUND_CLOSE_ACTIVE"));
        this.setColorBkgStandard(Editor.getThemeColour("BUTTON_BACKGROUND_CLOSE"));
    }
}