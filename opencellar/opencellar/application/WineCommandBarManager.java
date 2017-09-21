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

public final class WineCommandBarManager
extends CommandBarManager {
    private IWindow m_window;

    protected WineCommandBarManager(JPanel parent, IWindow window) {
        super(parent);
        this.m_window = window;
        this.create();
    }

    public final IWindow getWindow() {
        return this.m_window;
    }

    private void create() {
        CommandBar cb1 = super.create("Command");
        CommandBarItem save = cb1.create("Save", utils.getIcon("save"));
        save.setCommand(this.getWindow().getApplication().getCommands().get("SaveWine"));
        save.setToolTip(this.getWindow().getApplication().getRS("CBAR_SAVE"));
        CommandBarItem cancel = cb1.create("Cancel", utils.getIcon("cancel"));
        cancel.setCommand(this.getWindow().getApplication().getCommands().get("CancelWine"));
        cancel.setToolTip(this.getWindow().getApplication().getRS("CBAR_CANCEL"));
        CommandBarItem delete = cb1.create("Delete", utils.getIcon("delete"));
        delete.setCommand(this.getWindow().getApplication().getCommands().get("DeleteWine"));
        delete.setToolTip(this.getWindow().getApplication().getRS("WIN_CMD_DELETE"));
        CommandBarItem adminList = cb1.create("adminList", utils.getIcon("list"));
        adminList.setCommand(this.getWindow().getApplication().getCommands().get("ListAdmin"));
        adminList.setToolTip(this.getWindow().getApplication().getRS("MNU_TOOLS_LIST"));
        CommandBar cb2 = super.create("Action");
        CommandBarItem rackItems = cb2.create("RackItems", utils.getIcon("rackitems"));
        rackItems.setCommand(this.getWindow().getApplication().getCommands().get("ShowRackItems"));
        rackItems.setToolTip(this.getWindow().getApplication().getRS("CBAR_RACK_ITEMS"));
        CommandBarItem newNote = cb2.create("NewNote", utils.getIcon("newnote"));
        newNote.setCommand(this.getWindow().getApplication().getCommands().get("NewNote"));
        newNote.setToolTip(this.getWindow().getApplication().getRS("CBAR_NEW_NOTE"));
        CommandBarItem newPurchaseSales = cb2.create("NewPurchaseSales", utils.getIcon("newpurchase"));
        newPurchaseSales.setCommand(this.getWindow().getApplication().getCommands().get("NewPurchaseSales"));
        newPurchaseSales.setToolTip(this.getWindow().getApplication().getRS("CBAR_NEW_PURCHASE"));
    }
}

