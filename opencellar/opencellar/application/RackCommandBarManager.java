/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import javax.swing.Icon;
import javax.swing.JPanel;
import opencellar.application.CommandBar;
import opencellar.application.CommandBarItem;
import opencellar.application.CommandBarManager;
import opencellar.application.CommandManager;
import opencellar.application.IApplication;
import opencellar.application.ICommand;
import opencellar.application.IWindow;
import opencellar.application.utils;

public final class RackCommandBarManager
extends CommandBarManager {
    private IWindow m_window;

    protected RackCommandBarManager(JPanel parent, IWindow window) {
        super(parent);
        this.m_window = window;
        this.create();
    }

    public final IWindow getWindow() {
        return this.m_window;
    }

    private void create() {
        CommandBar cb1 = super.create("Command");
        CommandBarItem adminRack = cb1.create("adminRack", utils.getIcon("adminrackicon"));
        adminRack.setCommand(this.getWindow().getApplication().getCommands().get("RackAdmin"));
        adminRack.setToolTip(this.getWindow().getApplication().getRS("ADM_PGE_RACK"));
        CommandBarItem delete = cb1.create("Delete", utils.getIcon("delete"));
        delete.setCommand(this.getWindow().getApplication().getCommands().get("DeleteRack"));
        delete.setToolTip(this.getWindow().getApplication().getRS("BTN_DELETE"));
        CommandBarItem help = cb1.create("Help", utils.getIcon("help"));
        help.setCommand(this.getWindow().getApplication().getCommands().get("OnlineHelp"));
        help.setToolTip(this.getWindow().getApplication().getRS("CBAR_HELP"));
    }
}

