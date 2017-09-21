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
import opencellar.application.utils;

public final class DefaultCommandBarManager
extends CommandBarManager {
    private IApplication m_app;
    protected CommandBar mainCommandBar;

    public DefaultCommandBarManager(JPanel parent, IApplication app) {
        super(parent);
        if (app == null) {
            throw new IllegalArgumentException("app");
        }
        this.m_app = app;
        this.create();
    }

    public final IApplication getApplication() {
        return this.m_app;
    }

    private void create() {
        this.mainCommandBar = super.create("Command");
        CommandBarItem help = this.mainCommandBar.create("Help", utils.getIcon("help"));
        help.setCommand(this.getApplication().getCommands().get("OnlineHelp"));
        help.setToolTip(this.getApplication().getRS("CBAR_HELP"));
    }
}

