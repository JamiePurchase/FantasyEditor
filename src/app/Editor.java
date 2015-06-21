package app;

import gfx.Theme;
import input.InputKeyboard;
import input.InputKeyboardKey;
import input.InputMouse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import project.Project;
import project.ProjectService;
import state.State;
import state.StateMain;
import tileset.TilesetService;
import tileset.TilesetStructure;
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
    
    // Input
    private static InputKeyboard inputKeyboard;
    private static InputMouse inputMouse;
    
    // Project
    private static Project project;
    private static boolean projectNull;
    
    // Structures
    private static TilesetStructure structTilesets;

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
        this.createStatus();

        // Input
        inputKeyboard = new InputKeyboard();
        inputMouse = new InputMouse();
        
        // Project
        project = null;
        projectNull = true;
        
        // Temp
        setProject("Test");
        
        // Structures
        structTilesets = new TilesetStructure();
        structTilesets.addTileset("test", TilesetService.getTileset("test"));
    }
    
    private void createStatus()
    {
        this.uiStatus = new Toolbar("EDITOR_STATUS", 5, 733, Editor.getAppWidth() - 10);
        this.uiStatus.addLabel("EDITOR_STATUS_MESSSAGE", "", 10, 20, 1200, "LEFT");
        this.uiStatus.addLabel("EDITOR_STATUS_CLOCK", "", 1346, 20, 1200, "RIGHT");
        this.uiStatus.addLabel("EDITOR_STATUS_LABEL2", "", 300, 20, 300, "LEFT");
        this.uiStatus.addLabel("EDITOR_STATUS_LABEL3", "", 600, 20, 300, "LEFT");
        this.uiStatus.addLabel("EDITOR_STATUS_LABEL4", "", 900, 20, 300, "LEFT");
    }
    
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
    
    public static String getPathEditor()
    {
        return "C:/Users/Jamie/Documents/NetBeansProjects/FantasyEditor/";
    }
    
    public static String getPathEngine()
    {
        return "C:/Users/Jamie/Documents/NetBeansProjects/FantasyEngine/";
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
        if(getState() != null) {getState().mousePressed(event);}
    }
    
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
        
        // Interface
        uiFrame.render(gfx);
        uiStatus.render(gfx);
        
        // State Contents
        if(this.getState() != null) {this.getState().render(gfx);}
        
        // Interface (foreground)
        uiMenu.render(gfx);
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
    
    public static void setInterfaceStatus(String message)
    {
        uiStatus.getLabel(0).setText(message);
    }
    
    public static void setInterfaceStatus(String message, String boardPos, String boardTile, String boardInfo)
    {
        uiStatus.getLabel(0).setText(message);
        uiStatus.getLabel(2).setText(boardPos);
        uiStatus.getLabel(3).setText(boardTile);
        uiStatus.getLabel(4).setText(boardInfo);
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
    
    public static BufferedImage structTilesetGetTile(String terrain)
    {
        String[] data = terrain.split("\\|");
        return structTilesets.getTileset(data[0]).getTileAt(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
    }

    private void tick()
    {
        // Status Clock
        this.tickStatusClock();
        
        // State Tick
        this.getState().tick();
    }
    
    private void tickStatusClock()
    {
        uiStatus.getLabel(1).setText(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
    }
    
}