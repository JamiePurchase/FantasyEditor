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
import ui.Toolbar;

public class StateMain extends State
{
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
        menu.getMenu(0).addMenu("EDITOR_MENU_PROJECT_EXIT", "EXIT", new Action()
        {
            @Override
            public void activate() {System.exit(0);}
        });
        menu.addMenu("EDITOR_MENU_EDITOR", "EDITOR");
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_BOARD", "BOARD", new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateBoard());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_CHARACTER", "CHARACTER", new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateCharacter());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_ENEMY", "ENEMY", new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateEnemy());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_QUEST", "QUEST", new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateQuest());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_SCRIPT", "SCRIPT", new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateScript());}
        });
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_TILESET", "TILESET", new Action()
        {
            @Override
            public void activate() {Editor.setState(new StateTileset());}
        });
        menu.addMenu("EDITOR_MENU_HELP", "HELP");
        menu.getMenu(2).addMenu("EDITOR_MENU_HELP_ABOUT", "ABOUT", new Action()
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
        
        // Create Nexus for each element (cascades down)
        this.mouseNexusAdd("EDITOR_QUIT", Editor.getInterfaceFrame().getCloseButton());
        menu.addNexusAll(this);
        
        // Set status message
        Editor.setInterfaceStatusMessage("Loading most recent project...");
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
    
    public void mouseNexusClick(String ref)
    {
        /*if(ref.equals("EDITOR_QUIT"))
        {
            System.exit(0);
        }
        if(ref.equals("EDITOR_MENU_PROJECT_EXIT"))
        {
            System.exit(0);
        }
        if(ref.equals("EDITOR_MENU_PROJECT_NEW"))
        {
            // Test);
        }
        
        // Test
        if(ref.equals("EDITOR_MENU_HELP"))
        {
            Editor.setState(new StateScript());
        }*/

        // Temp
        /*if(Editor.getInterfaceMenu().getMenu(0).getNexus().contains(e.getPoint()))
        {
            Editor.getInterfaceMenu().getMenu(0).setExpand(true);
        }*/
    }
    
    public void mouseNexusContext(String ref)
    {
        //
    }

    public void mousePressed(MouseEvent event)
    {
        this.mouseNexusCheck(event.getPoint());
    }

    public void mouseReleased(MouseEvent event)
    {
        //
    }

    public void render(Graphics gfx)
    {
        if(this.getModalActive())
        {
            Drawing.fadeScreen(gfx, 0.5f);
            this.getModalFrame().render(gfx);
        }
    }

    public void tick()
    {
        //
    }
    
}