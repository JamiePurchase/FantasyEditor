package ui;

import java.awt.Graphics;
import java.util.ArrayList;

public class Tree
{
    private String ref;
    private ArrayList<TreeNode> nodes;
    private int posX, posY, sizeX, sizeY;
    
    public Tree(String ref)
    {
        this.ref = ref;
        this.nodes = new ArrayList<TreeNode>();
    }
    
    public void addNode(String ref, String text)
    {
        this.nodes.add(new TreeNode(ref, text));
    }
    
    public void render(Graphics gfx)
    {
        
    }
    
}