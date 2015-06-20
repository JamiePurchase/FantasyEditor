package ui;

import input.InputMouseNexus;

public class ElementNexus extends InputMouseNexus
{
    private Element element;
    private boolean enabled;
    
    public ElementNexus(String ref, Element element)
    {
        super(ref, element.getRect());
        this.element = element;
        this.enabled = true;
        element.setNexus(this);
    }
    
    public Element getElement()
    {
        return this.element;
    }
    
    public boolean getEnabled()
    {
        return this.enabled;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}