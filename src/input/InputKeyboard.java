package input;

import app.Editor;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputKeyboard implements KeyListener
{
    // New
    public static Map<String, InputKeyboardKey> keyboard;
    
    public InputKeyboard()
    {
        // Hashmap of Keys
        this.keyboard = new HashMap<String, InputKeyboardKey>();
        
        // Command Keys
        this.keyboard.put("ENTER", new InputKeyboardKey("ENTER", "COMMAND"));
        this.keyboard.put("ESCAPE", new InputKeyboardKey("ESCAPE", "COMMAND"));
        this.keyboard.put("SPACE", new InputKeyboardKey("SPACE", "COMMAND"));
        
        // Directional Keys
        this.keyboard.put("DOWN", new InputKeyboardKey("DOWN", "DIRECTION"));
        this.keyboard.put("LEFT", new InputKeyboardKey("LEFT", "DIRECTION"));
        this.keyboard.put("RIGHT", new InputKeyboardKey("RIGHT", "DIRECTION"));
        this.keyboard.put("UP", new InputKeyboardKey("UP", "DIRECTION"));
        
        // Ability Keys
        this.keyboard.put("CTRL", new InputKeyboardKey("CTRL", "ABILITY"));
        this.keyboard.put("ALT", new InputKeyboardKey("ALT", "ABILITY"));
        
        // Peripheral Keys
        this.keyboard.put("PAGEUP", new InputKeyboardKey("PAGEUP", "PERIPHERAL"));
        this.keyboard.put("PAGEDOWN", new InputKeyboardKey("PAGEDOWN", "PERIPHERAL"));
        this.keyboard.put("HOME", new InputKeyboardKey("HOME", "PERIPHERAL"));
        this.keyboard.put("END", new InputKeyboardKey("END", "PERIPHERAL"));
    }
    
    private String getKeyName(KeyEvent e)
    {
        // Command Keys
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {return "ENTER";}
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {return "ESCAPE";}
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {return "SPACE";}
        
        // Directional Keys
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {return "DOWN";}
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {return "LEFT";}
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {return "RIGHT";}
        if(e.getKeyCode() == KeyEvent.VK_UP) {return "UP";}
        
        // Ability Keys
        if(e.getKeyCode() == KeyEvent.VK_ALT) {return "ALT";}
        if(e.getKeyCode() == KeyEvent.VK_CONTROL) {return "CTRL";}
        
        // Peripheral Keys
        if(e.getKeyCode() == KeyEvent.VK_PAGE_UP) {return "PAGEUP";}
        if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {return "PAGEDOWN";}
        if(e.getKeyCode() == KeyEvent.VK_HOME) {return "HOME";}
        if(e.getKeyCode() == KeyEvent.VK_END) {return "END";}
        
        // Unknown Key
        return "UNKNOWN";
    }
    
    public InputKeyboardKey getKeyObject(String name)
    {
        return this.keyboard.get(name);
    }

    public void keyTyped(KeyEvent e)
    {
        //
    }

    public void keyPressed(KeyEvent e)
    {
        //Console.echoRed("Key Pressed : " + this.getKeyName(e));
        if(!this.getKeyName(e).equals("UNKNOWN"))
        {
            Editor.keyPressed(this.keyboard.get(this.getKeyName(e)));
        }
    }

    public void keyReleased(KeyEvent e)
    {
        //Console.echoRed("Key Released : " + this.getKeyName(e));
        if(!this.getKeyName(e).equals("UNKNOWN"))
        {
            Editor.keyReleased(this.keyboard.get(this.getKeyName(e)));
        }
    }
    
}