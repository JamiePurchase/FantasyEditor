package gfx;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

public class Theme
{
    private static Map<String, Color> pallete;
    private static Map<String, Font> typeface;

    public Theme()
    {
        // Colour Pallette
        this.pallete = new HashMap<String, Color>();
        pallete.put("APP_BACKGROUND", Drawing.getColorRGB(140, 170, 110));
        pallete.put("APP_BORDER", Drawing.getColorRGB(70, 80, 60));
        pallete.put("BUTTON_BACKGROUND_CLOSE", Drawing.getColorRGB(185, 85, 85));
        pallete.put("BUTTON_BACKGROUND_CLOSE_ACTIVE", Drawing.getColorRGB(205, 85, 85));
        pallete.put("BUTTON_BACKGROUND_STANDARD", Drawing.getColorRGB(185, 85, 85));
        pallete.put("BUTTON_BACKGROUND_STANDARD_ACTIVE", Drawing.getColorRGB(205, 85, 85));
        pallete.put("BUTTON_BORDER", Drawing.getColorRGB(0, 0, 0));
        pallete.put("BUTTON_TEXT", Drawing.getColorRGB(0, 0, 0));
        pallete.put("TITLEBAR_BACKGROUND", Drawing.getColorRGB(110, 140, 170));
        pallete.put("TITLEBAR_BORDER", Drawing.getColorRGB(0, 0, 0));
        pallete.put("TITLEBAR_TEXT", Drawing.getColorRGB(0, 0, 0));
        pallete.put("TOOLBAR_BACKGROUND", Drawing.getColorRGB(140, 140, 140));
        pallete.put("TOOLBAR_BACKGROUND_ACTIVE", Drawing.getColorRGB(170, 170, 170));
        pallete.put("TOOLBAR_BORDER", Drawing.getColorRGB(0, 0, 0));
        pallete.put("TOOLBAR_TEXT", Drawing.getColorRGB(0, 0, 0));
        
        // Typeface Styles
        this.typeface = new HashMap<String, Font>();
        typeface.put("BUTTON_TEXT", new Font("Courier New", Font.PLAIN, 16));
        typeface.put("BUTTON_TEXT_BOLD", new Font("Courier New", Font.BOLD, 16));
        typeface.put("MENUBAR_TEXT", new Font("Courier New", Font.PLAIN, 16));
        typeface.put("STANDARD", new Font("Andalus", Font.PLAIN, 26));
        typeface.put("TITLEBAR_TEXT", new Font("Andalus", Font.PLAIN, 22));
    }
    
    public void addColour(String id, Color color)
    {
        pallete.put(id, color);
    }
    
    public void addFont(String id, Font font)
    {
        typeface.put(id, font);
    }
    
    public static Color getColour(String id)
    {
        return pallete.get(id);
    }
    
    public static Font getFont(String id)
    {
        return typeface.get(id);
    }
    
}