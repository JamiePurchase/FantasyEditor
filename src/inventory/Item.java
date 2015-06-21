package inventory;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Item
{
    // Item Info
    private String ref;
    private String title, description;
    private BufferedImage icon;
    private int weight;
    
    // Rarity / Worth
    private int worth;
    private boolean keyItem;
    
    public abstract void renderMenu(Graphics gfx);

}