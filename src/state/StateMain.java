package state;

import app.Editor;
import gfx.Drawing;
import input.InputKeyboardKey;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import ui.Action;
import ui.FrameModal;
import ui.Toolbar;

public class StateMain extends State
{
    public StateMain()
    {
        // Frame Title
        String title = "Fantasy Editor";
        if(!Editor.getProjectNull()) {title = "Fantasy Editor - " + Editor.getProject().getTitle();}
        Editor.getInterfaceFrame().setTitle(title);
        
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
            public void activate()
            {
                System.exit(0);
            }
        });
        menu.addMenu("EDITOR_MENU_EDITOR", "EDITOR");
        menu.getMenu(1).addMenu("EDITOR_MENU_EDITOR_BOARD", "BOARD");
        menu.addMenu("EDITOR_MENU_HELP", "HELP");
        menu.getMenu(2).addMenu("EDITOR_MENU_HELP_ABOUT", "ABOUT", new Action()
        {
            @Override
            public void activate()
            {
                Editor.getInterfaceMenu().collapse();
                Editor.getState().setModal(new FrameModal("MODAL_PROJECT_NEW", "New Project", 800, 400));
            }
        });
        Editor.setInterfaceMenu(menu);
        
        // Mouse Nexus
        this.mouseNexusAdd("EDITOR_QUIT", Editor.getInterfaceFrame().getCloseButton());
        this.mouseNexusAdd(menu.getMenu(0));
        this.mouseNexusAdd(menu.getMenu(0).getMenu(0));
        this.mouseNexusAdd(menu.getMenu(0).getMenu(1));
        this.mouseNexusAdd(menu.getMenu(0).getMenu(2));
        this.mouseNexusAdd(menu.getMenu(0).getMenu(3));
        this.mouseNexusAdd(menu.getMenu(1));
        this.mouseNexusAdd(menu.getMenu(1).getMenu(0));
        this.mouseNexusAdd(menu.getMenu(2));
        this.mouseNexusAdd(menu.getMenu(2).getMenu(0));
    }

    public void keyPressed(InputKeyboardKey key)
    {
        //
    }

    public void keyReleased(InputKeyboardKey key)
    {
        //
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