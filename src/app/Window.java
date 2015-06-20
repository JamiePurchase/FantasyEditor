package app;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window
{
    private JFrame frame;
    private JPanel panel;
    private Canvas canvas;

    public Window(String title, int width, int height)
    {        
        // Create the frame
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Create a JPanel
        panel = new JPanel();
        panel.addKeyListener(Editor.getInputKeyboard());
        frame.add(panel);
        this.setFocus();

        // Create the canvas
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.addMouseListener(Editor.getInputMouse());
        canvas.addMouseMotionListener(Editor.getInputMouse());

        // Add the canvas to the frame
        frame.add(canvas);
        frame.pack();
        
        // Request focus
        this.panel.requestFocus();
    }

    public Canvas getCanvas()
    {
        return canvas;
    }
    
    public void setFocus()
    {
        panel.requestFocus();
    }
}