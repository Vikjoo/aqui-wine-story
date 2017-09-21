/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.MisMatchEventArgs
 */
package opencellar.application;

import javax.swing.Icon;
import javax.swing.JPanel;
import opencellar.application.Application;
import opencellar.application.CommandBar;
import opencellar.application.CommandBarItem;
import opencellar.application.CommandBarManager;
import opencellar.application.CommandManager;
import opencellar.application.IApplication;
import opencellar.application.IApplicationListener;
import opencellar.application.ICommand;
import opencellar.application.IWindow;
import opencellar.application.Manager;
import opencellar.application.utils;
import opencellar.framework.MisMatchEventArgs;
import opencellar.ui.MainLayer;

public class MainCommandBarManager
extends Manager
implements IApplicationListener {
    private CommandBar cb2 = null;
    private CommandBarItem cellarProperties = null;
    private CommandBarManager m_manager;

    public MainCommandBarManager(IApplication app) {
        super(app);
        app.addListener(this);
    }

    protected void build() {
        this.m_manager = new CommandBarManager(this.internalGetApp().getMain().toolBarsPanel);
        CommandBar cb1 = this.m_manager.create("Command");
        CommandBarItem newCellar = cb1.create("newCellar", utils.getIcon("newcellar"));
        newCellar.setCommand(this.getApp().getCommands().get("NewCellar"));
        newCellar.setToolTip(this.getApp().getRS("MAIN_CB_NEWCELLAR_CAPTION"));
        CommandBarItem openCellar = cb1.create("openCellar", utils.getIcon("open"));
        openCellar.setCommand(this.getApp().getCommands().get("OpenCellar"));
        openCellar.setToolTip(this.getApp().getRS("BAR_OPEN_CELLAR"));
        this.cellarProperties = cb1.create("cellarProperties", utils.getIcon("property"));
        this.cellarProperties.setEnabled(false);
        this.cb2 = this.m_manager.create("Wine");
        CommandBarItem newWine = this.cb2.create("newWine", utils.getIcon("newwine"));
        newWine.setCommand(this.getApp().getCommands().get("NewWine"));
        newWine.setToolTip(this.getApp().getRS("MAIN_CB_WINE_CAPTION"));
        CommandBarItem myCellar = this.cb2.create("myCellar", utils.getIcon("myrack"));
        myCellar.setCommand(this.getApp().getCommands().get("ShowRackWindow"));
        myCellar.setToolTip(this.getApp().getRS("MAIN_CB_VISUAL_TEXT"));
        CommandBarItem myTextCellar = this.cb2.create("myTextCellar", utils.getIcon("winelist"));
        myTextCellar.setCommand(this.getApp().getCommands().get("ShowCellarTextCommand"));
        myTextCellar.setToolTip(this.getApp().getRS("MAIN_CB_LIST_TEXT"));
        CommandBarItem adminList = this.cb2.create("adminList", utils.getIcon("list"));
        adminList.setCommand(this.getApp().getCommands().get("ListAdmin"));
        adminList.setToolTip(this.getApp().getRS("MNU_TOOLS_LIST"));
        CommandBarItem adminRack = this.cb2.create("adminRack", utils.getIcon("adminrackicon"));
        adminRack.setCommand(this.getApp().getCommands().get("RackAdmin"));
        adminRack.setToolTip(this.getApp().getRS("ADM_PGE_RACK"));
        this.cb2.setVisible(false);
        CommandBar cb3 = this.m_manager.create("Misc");
        CommandBarItem scriptEditor = cb3.create("scriptEditor", utils.getIcon("script"));
        scriptEditor.setCommand(this.getApp().getCommands().get("ShowScript"));
        scriptEditor.setToolTip(this.getApp().getRS("MAIN_CB_SCRIPT_TEXT"));
        CommandBarItem webSite = cb3.create("webSite", utils.getIcon("online"));
        webSite.setCommand(this.getApp().getCommands().get("OnlineSite"));
        webSite.setToolTip(this.getApp().getRS("MAIN_CB_WEB_TEXT"));
        CommandBarItem onlineHelp = cb3.create("onlineHelp", utils.getIcon("help"));
        onlineHelp.setCommand(this.getApp().getCommands().get("OnlineHelp"));
        onlineHelp.setToolTip(this.getApp().getRS("MNU_HELP_CENTER"));
    }

    protected final CommandBarManager getCommandManager() {
        return this.m_manager;
    }

    public void onStarting(IApplication source) {
    }

    public void onStart(IApplication source) {
    }

    public void onShutDown(IApplication source) {
    }

    public void onOpening(IApplication source) {
    }

    public void onOpen(IApplication source) {
        this.cb2.setVisible(true);
    }

    public void onClosing(IApplication source) {
    }

    public void onClose(IApplication source) {
        this.cb2.setVisible(false);
    }

    public void onReadOnly(IApplication source) {
    }

    public void onMisMatchVersion(IApplication source, MisMatchEventArgs args) {
    }

    public void onCreateWindow(IApplication source, IWindow window) {
    }

    public void onCloseWindow(IApplication source, IWindow window) {
    }

    public void onActivateWindow(IApplication source, IWindow window) {
    }
}

