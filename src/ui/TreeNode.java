package ui;

import java.util.ArrayList;

public class TreeNode
{
    private String ref, text;
    private boolean expand;
    private ArrayList<TreeNode> nodes;
    
    public TreeNode(String ref, String text)
    {
        this.ref = ref;
        this.text = text;
        this.expand = false;
        this.nodes = new ArrayList<TreeNode>();
    }
}