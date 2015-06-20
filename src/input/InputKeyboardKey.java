package input;

public class InputKeyboardKey
{
    private String ref, type;
    private boolean pressed;
    
    public InputKeyboardKey(String ref, String type)
    {
        this.ref = ref;
        this.type = type;
        this.pressed = false;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public boolean isPressed()
    {
        return this.pressed;
    }
    
    public void press()
    {
        this.pressed = true;
    }
    
    public void release()
    {
        this.pressed = false;
    }
    
}