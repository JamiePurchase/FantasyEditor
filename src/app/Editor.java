package app;

import app.Window;
import gfx.Drawing;
import gfx.Theme;
import input.InputKeyboard;
import input.InputKeyboardKey;
import input.InputMouse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import project.Project;
import project.ProjectService;
import state.State;
import state.StateMain;
import ui.Element;
import ui.FrameWindow;
import ui.Toolbar;

public class Editor extends JPanel implements Runnable
{
    // Editor
    private static String appTitle;
    private static int appSizeX, appSizeY;
    private static Window appFrame;
    private Thread appThread;
    private boolean appRunning;
    private static State appState;
    private static Theme appTheme;
    
    // Interface
    private static FrameWindow uiFrame;
    private static Toolbar uiMenu, uiStatus;
    //private static Map<String, Element> uiElements;
    
    // Input
    private static InputKeyboard inputKeyboard;
    private static InputMouse inputMouse;
    
    // Project
    private static Project project;
    private static boolean projectNull;

    public Editor()
    {
        // Application
        this.appTitle = "Fantasy Editor";
        this.appSizeX = 1366;
        this.appSizeY = 768;
        this.appTheme = new Theme();
        
        // Interface
        this.uiFrame = new FrameWindow("EDITOR_FRAME", this.appTitle, true);
        this.uiMenu = null;
        //this.uiStatus = new Toolbar("EDITOR_STATUS", 0, this.getStateSizeY(), this.appSizeX, 11);
        //clearInterfaceElements();

        // Input
        inputKeyboard = new InputKeyboard();
        inputMouse = new InputMouse();
        
        // Project
        project = null;
        projectNull = true;
        
        // Temp
        setProject("Test");
    }
    
    /*public static void addInterfaceElement(Element element)
    {
        System.out.println("Adding Interface Element: " + element.getRef());
        uiElements.put(element.getRef(), element);
        System.out.println("There are now " + uiElements.size() + " element(s)");
    }
    
    public static void clearInterfaceElements()
    {
        System.out.println("Clearing Interface Elements");
        uiElements = new HashMap<String, Element>();
        addInterfaceElement(uiFrame.getCloseButton());
    }*/
    
    private void createWindow()
    {
        this.appFrame = new Window(this.appTitle, this.appSizeX, this.appSizeY);
        this.appState = new StateMain();
    }
    
    public static int getAppHeight()
    {
        return appSizeY;
    }
    
    public static int getAppWidth()
    {
        return appSizeX;
    }

    public static InputKeyboard getInputKeyboard()
    {
        return inputKeyboard;
    }

    public static InputMouse getInputMouse()
    {
        return inputMouse;
    }
    
    /*public static String getInterfaceElement(Point point)
    {
        // Debug
        System.out.println("Getting Interface Elements");
        System.out.println("There are currently " + uiElements.size() + " element(s)");
        for(int e = 0; e < uiElements.size(); e++)
        {
            System.out.println(e + ": " + uiElements.get(e).getRef());
        }
        
        System.out.println("Editor.getInterfaceElement(" + point.x + "," + point.y + ")");
        
        for(int e = 0; e < uiElements.size(); e++)
        {
            Element element = uiElements.get(e);
            if(element.getVisible() && element.getNexus().contains(point))
            {
                return element.getRef();
            }
        }
        return "";
    }*/
    
    /*public static Element getInterfaceElement(String ref)
    {
        return uiElements.get(ref);
    }*/
    
    public static FrameWindow getInterfaceFrame()
    {
        return uiFrame;
    }
    
    public static Toolbar getInterfaceMenu()
    {
        return uiMenu;
    }
    
    public static Toolbar getInterfaceStatus()
    {
        return uiStatus;
    }
    
    public static String getPath()
    {
        return "C:/Users/Jamie/Documents/NetBeansProjects/FantasyEditor/";
    }
    
    public static Project getProject()
    {
        return project;
    }
    
    public static boolean getProjectNull()
    {
        return projectNull;
    }
    
    public static State getState()
    {
        return appState;
    }
    
    public static int getStatePosX()
    {
        return 11;
    }
    
    public static int getStatePosY()
    {
        return 36;
    }
    
    public static int getStateSizeX()
    {
        return appSizeX - 22;
    }
    
    public static int getStateSizeY()
    {
        // NOTE: Do this later
        return appSizeY - 36;
    }
    
    public static Color getThemeColour(String ref)
    {
        return appTheme.getColour(ref);
    }
    
    public static Font getThemeFont(String ref)
    {
        return appTheme.getFont(ref);
    }
    
    public static void keyPressed(InputKeyboardKey key)
    {
        if(getState() != null) {getState().keyPressed(key);}
    }
    
    public static void keyReleased(InputKeyboardKey key)
    {
        if(getState() != null) {getState().keyReleased(key);}
    }
    
    public static void mousePressed(MouseEvent event)
    {
        if(getState() != null)
        {
            getState().mousePressed(event);
        }
    }
    
    /*public static void mousePressed(MouseEvent e)
    {
        // Close Button
        if(uiFrame.getCloseButton().getNexus().contains(e.getPoint())) {System.exit(0);}
        
        // NOTE: we need to automatically check all visible/active clickable elements
        // if we clicked on one, we need to return the element reference
        
        // State Click
        if(getState() != null)
        {
            System.out.println("Mouse Pressed at " + e.getX() + "," + e.getY());
            String element = getInterfaceElement(e.getPoint());
            System.out.println("Element = " + element);
            if(element != null)
            {
                if(e.getButton() == MouseEvent.BUTTON1) {getState().mouseClick(element);}
                if(e.getButton() == MouseEvent.BUTTON3) {getState().mouseContext(element);}
            }
        }
    }*/
    
    public static void mouseReleased(MouseEvent e)
    {
        //
    }
    
    private void render()
    {
        BufferStrategy buffer = appFrame.getCanvas().getBufferStrategy();
        if(buffer == null)
        {
            appFrame.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics gfx = buffer.getDrawGraphics();
        renderEditor(gfx);
        buffer.show();
        gfx.dispose();
    }
    
    private void renderEditor(Graphics gfx)
    {
        // Background
        gfx.setColor(Theme.getColour("APP_BACKGROUND"));
        gfx.fillRect(0, 0, this.appSizeX, this.appSizeY);
        
        // Frame
        uiFrame.render(gfx);
        
        // Menu Toolbar
        uiMenu.render(gfx);
        
        // State Border
        /*gfx.setColor(Theme.getColour("BAR_BORDER"));
        gfx.drawRect(this.getStatePosX(), this.getStatePosY(), this.getStateSizeX(), this.getStateSizeY());*/
        
        // State Contents
        if(this.getState() != null) {this.getState().render(gfx);}
    }
    
    public void run()
    {
        // Render speed
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        // Create window
        createWindow();

        // Main game loop
        while(this.appRunning)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if(delta >= 1)
            {			
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000)
            {
                ticks = 0;
                timer = 0;
            }
        }

        // End game
        stop();
    }
    
    public static void setInterfaceMenu(Toolbar menu)
    {
        uiMenu = menu;
    }
    
    public static void setProject(String ref)
    {
        project = ProjectService.getProject(ref);
        projectNull = false;
        uiFrame.setTitle("Fantasy Editor - " + project.getTitle());
    }
    
    public static void setState(State newState)
    {
        appState = newState;
        appFrame.setFocus();
    }
        
    public synchronized void start()
    {
        if(this.appRunning == false)
        {
            this.appRunning = true;
            this.appThread = new Thread(this);
            this.appThread.start();
        }
    }

    public synchronized void stop()
    {
        if(this.appRunning == true)
        {
            try {this.appThread.join();}
            catch (InterruptedException e) {System.out.println(e);}
        }
    }

    private void tick()
    {
        this.getState().tick();
    }
    
}