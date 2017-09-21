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

public final class GridWinesCommandBarManager
extends CommandBarManager {
    private IWindow m_window;

    protected GridWinesCommandBarManager(JPanel parent, IWindow window) {
        super(parent);
        this.m_window = window;
        this.create();
    }

    public final IWindow getWindow() {
        return this.m_window;
    }

    private void create() {
        CommandBar cb1 = super.create("Command");
        CommandBarItem excel = cb1.create("Excel", utils.getIcon("excel"));
        excel.setCommand(this.getWindow().getApplication().getCommands().get("ExcelCommand"));
        excel.setToolTip(this.getWindow().getApplication().getRS("CBAR_EXCEL"));
        CommandBarItem refresh = cb1.create("Refresh", utils.getIcon("refresh"));
        refresh.setCommand(this.getWindow().getApplication().getCommands().get("RefreshGridWines"));
        CommandBarItem help = cb1.create("Help", utils.getIcon("help"));
        help.setCommand(this.getWindow().getApplication().getCommands().get("OnlineHelp"));
        help.setToolTip(this.getWindow().getApplication().getRS("CBAR_HELP"));
    }
}

