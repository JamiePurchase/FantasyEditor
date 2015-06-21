package app;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window
{
    public JFrame frame;
    private JPanel panel;
    private Canvas canvas;

    public Window(String title, int width, int height)
    {
        // Create the frame
        createFrame(title, width, height);

        // Create a JPanel
        this.panel = new JPanel();
        this.panel.addKeyListener(Editor.getInputKeyboard());
        this.frame.add(this.panel);
        this.setFocus();

        // Create the canvas
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(width, height));
        this.canvas.setMaximumSize(new Dimension(width, height));
        this.canvas.setMinimumSize(new Dimension(width, height));
        this.canvas.addMouseListener(Editor.getInputMouse());
        this.canvas.addMouseMotionListener(Editor.getInputMouse());

        // Add the canvas to the frame
        this.frame.add(this.canvas);
        this.frame.pack();
        
        // Request focus
        this.panel.requestFocus();
    }
    
    public void createFrame(String title, int width, int height)
    {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Canvas getCanvas()
    {
        return this.canvas;
    }
    
    public void setFocus()
    {
        this.panel.requestFocus();
    }
}