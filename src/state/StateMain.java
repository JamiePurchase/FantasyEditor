package state;

import app.Editor;
import gfx.Drawing;
import input.InputKeyboardKey;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import ui.Action;
import ui.Element;
import ui.FrameModal;
import ui.Label;
import ui.Panel;
import ui.PanelFloat;
import ui.Toolbar;

public class StateMain extends State
{
    private boolean uiProjectActive;
    private Panel uiProjectObject;
    
    public StateMain()
    {
        // Frame Title
        this.setTitle();
        
        // Modal
        this.setModal();
        
        // Menu Toolbar
        Toolbar menu = new Toolbar("EDITOR_MENU", 5, 30, Editor.getAppWidth() - 10);
        menu.addMenu("EDITOR_MENU_PROJECT", "PROJECT");
        menu.getMenu(0).addMenu("EDITOR_MENU_PROJECT_NEW", "NEW");
        menu.getMenu(0).addMenu("EDITOR_MENU_PROJECT_OPEN", "OPEN");
        menu.getMenu(0).addMenu("EDITOR_MENU_PROJECT_CLOSE", "CLOSE");
        menu.getMenu(0).addMenu("EDITOR_MENU_PROJECT_EXIT", "EXIT", false, new Action()
        {
            @Override
            public void activate() {System.exit(0);}
        });
        menu.addMenu("EDITOR_MENU_EDITOR", "EDITOR");
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_BOARD", "BOARD", false, new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateBoard());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_CHARACTER", "CHARACTER", false, new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateCharacter());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_ENEMY", "ENEMY", false, new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateEnemy());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_QUEST", "QUEST", false, new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateQuest());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_SCRIPT", "SCRIPT", false, new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateScript());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_TILESET", "TILESET", false, new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateTileset());}
        });
        menu.addMenu("EDITOR_MENU_HELP", "HELP");
        menu.getMenu(2).addMenu("EDITOR_MENU_HELP_ABOUT", "ABOUT", false, new Action()
        {
            @Override
            public void activate()
            {
                // Create the about modal
                FrameModal modal = new FrameModal("MODAL_PROJECT_NEW", "About", 800, 400);
                modal.addLabel("", "jFantasy Editor", 80, 70, 100, "CENTER");
                
                // Close menu and display modal
                Editor.getInterfaceMenu().collapse();
                Editor.getState().setModal(modal);
            }
        });
        Editor.setInterfaceMenu(menu);
        
        // Project Explorer
        this.uiProjectActive = true;
        this.uiProjectObject = new PanelFloat("EDITOR_PROJECT_EXPLORER", "Project Explorer", 50, 300, 200, 300);
        
        // NOTE: remember the positions and visibilty of optional menus with the config file
        // the application should resume work on the last opened project, settings intact
        
        // NOTE: we need to add a nexus to the uiProjectObject (is it possible to drag and move this?)
        
        // Create Nexus for each element (cascades down)
        this.mouseNexusAdd("EDITOR_QUIT", Editor.getInterfaceFrame().getCloseButton());
        menu.addNexusAll(this);
        
        // Set status message
        Editor.getInputMouse().setReport();
        Editor.setInterfaceStatus("Loading most recent project...");
    }

    public void keyPressed(InputKeyboardKey key)
    {
        //super.keyPressed(key);
    }

    public void keyReleased(InputKeyboardKey key)
    {
        //
    }
    
    public void mouseNexusAdd(Element element)
    {
        super.mouseNexusAdd(element);
    }
    
    public void mouseNexusAdd(String ref, Element element)
    {
        super.mouseNexusAdd(ref, element);
    }
    
    public void mouseNexusContext(String ref)
    {
        //
    }

    public void mousePressed(MouseEvent event)
    {
        this.mouseNexusCheck(event);
    }

    public void mouseReleased(MouseEvent event)
    {
        //
    }

    public void render(Graphics gfx)
    {
        // Modal
        if(this.getModalActive())
        {
            Drawing.fadeScreen(gfx, 0.5f);
            this.getModalFrame().render(gfx);
        }
        
        // Project Explorer
        if(this.uiProjectActive) {this.uiProjectObject.render(gfx);}
    }

    public void tick()
    {
        //
    }
    
}