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
import opencellar.application.MessageBoxFactory;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.utils;

public class NoteCommandBarManager
extends CommandBarManager {
    private IApplication m_app;
    protected CommandBar mainCommandBar;

    public NoteCommandBarManager(JPanel parent, IApplication app) {
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
        CommandBarItem loisel = this.mainCommandBar.create("Loisel", utils.getIcon("loisel"));
        loisel.setCommand(new ICommand(){

            public void execute() {
                MessageBoxFactory.createEx(null, "Open Cellar", "Les \u00e9l\u00e9ments de cette fiche sont la propri\u00e9t\u00e9 intellectuelle de Mr Loisel.\nPlus d'informations sur http://www.chateauloisel.com/", MessageType.Message, MessageIconType.Information, MessageButtonType.Default);
            }
        });
        loisel.setToolTip("A propos de...");
        CommandBarItem help = this.mainCommandBar.create("Help", utils.getIcon("help"));
        help.setCommand(this.getApplication().getCommands().get("OnlineHelp"));
        help.setToolTip(this.getApplication().getRS("CBAR_HELP"));
    }

}

