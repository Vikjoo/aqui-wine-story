/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.CellarApplication
 *  opencellar.framework.MisMatchEventArgs
 */
package opencellar.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import opencellar.application.Application;
import opencellar.application.CommandManager;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.IApplicationListener;
import opencellar.application.ICommand;
import opencellar.application.IWindow;
import opencellar.application.Manager;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.utils;
import opencellar.framework.CellarApplication;
import opencellar.framework.MisMatchEventArgs;
import opencellar.ui.MainLayer;

public final class MainMenuManager
extends Manager
implements ActionListener,
IApplicationListener {
    private JMenu fileMenu;
    private JMenu fileNewMenu;
    private JMenuItem fileNewCellarMenu;
    private JMenuItem fileNewWine;
    private JMenuItem fileOpenCellar;
    private JMenuItem fileCloseCellar;
    private JMenuItem fileSearch;
    private JMenuItem fileProperty;
    private JMenuItem fileSave;
    private JMenu viewMenu;
    private JMenuItem viewGraphicsCellar;
    private JMenuItem viewTextCellar;
    private JMenu addinMenu;
    private JMenu prefMenu;
    private JMenu administration;
    private JMenuItem adminList;
    private JMenuItem adminRack;
    private JMenuItem adminLegend;
    private JMenu wineRenderers;
    private JMenu renderers;
    private JMenuItem scriptsEditor;
    private JMenu winMenu;
    private JMenuItem winCascad;
    private JMenuItem winMozHorizontal;
    private JMenuItem winMozVertical;
    private JMenu helpMenu;
    private JMenuItem contactUs;
    private JMenuItem webSite;
    private JMenuItem about;
    private JMenuItem welcomePage;

    public MainMenuManager(IApplication app) {
        super(app);
    }

    protected final void build() {
        this.getApp().addListener(this);
        this.fileMenu = new JMenu(this.getApp().getRS("MNU_FILE"));
        this.fileNewMenu = new JMenu(this.getApp().getRS("MNU_FILE_NEW"));
        this.fileNewCellarMenu = new JMenuItem(this.getApp().getRS("MNU_FILE_NEW_CELLAR"));
        this.fileNewCellarMenu.setActionCommand("NewCellar");
        this.fileNewCellarMenu.addActionListener(this);
        this.fileNewWine = new JMenuItem(this.getApp().getRS("MNU_FILE_NEW_WINE"));
        this.fileNewWine.setActionCommand("NewWine");
        this.fileNewWine.addActionListener(this);
        this.fileOpenCellar = new JMenuItem(this.getApp().getRS("MNU_FILE_OPEN"));
        this.fileOpenCellar.setActionCommand("OpenCellar");
        this.fileOpenCellar.addActionListener(this);
        this.fileCloseCellar = new JMenuItem(this.getApp().getRS("MNU_FILE_CLOSE"));
        this.fileCloseCellar.setActionCommand("CloseCellar");
        this.fileCloseCellar.addActionListener(this);
        this.fileSearch = new JMenuItem(this.getApp().getRS("MNU_FILE_SEARCH"));
        this.fileSearch.setActionCommand("ShowCellarTextCommand");
        this.fileSearch.addActionListener(this);
        this.fileProperty = new JMenuItem(this.getApp().getRS("MNU_FILE_PROPERTY"));
        this.fileProperty.setActionCommand("");
        this.fileProperty.addActionListener(this);
        this.fileProperty.setEnabled(false);
        this.fileSave = new JMenuItem(this.getApp().getRS("MNU_FILE_BACKUP"));
        this.fileSave.setActionCommand("");
        this.fileSave.addActionListener(this);
        JMenuItem exitMenuItem = new JMenuItem(this.getApp().getRS("MNU_FILE_EXIT"));
        exitMenuItem.setActionCommand("Exit");
        exitMenuItem.addActionListener(this);
        this.fileMenu.add(this.fileNewMenu);
        this.fileNewMenu.add(this.fileNewCellarMenu);
        this.fileNewMenu.add(this.fileNewWine);
        this.fileMenu.add(this.fileOpenCellar);
        this.fileMenu.add(this.fileCloseCellar);
        this.fileMenu.addSeparator();
        this.fileMenu.add(this.fileSearch);
        this.fileMenu.addSeparator();
        this.fileMenu.add(this.fileProperty);
        if (!utils.isMacOS()) {
            this.fileMenu.addSeparator();
            this.fileMenu.add(exitMenuItem);
        }
        this.internalGetApp().getMain().getJMenuBar().add(this.fileMenu);
        this.viewMenu = new JMenu(this.getApp().getRS("MNU_VIEW"));
        this.viewGraphicsCellar = new JMenuItem(this.getApp().getRS("ID_GRAPHIC_CELLAR"));
        this.viewGraphicsCellar.setActionCommand("ShowRackWindow");
        this.viewGraphicsCellar.addActionListener(this);
        this.viewMenu.add(this.viewGraphicsCellar);
        this.viewTextCellar = new JMenuItem(this.getApp().getRS("ID_TEXT_CELLAR"));
        this.viewTextCellar.setActionCommand("ShowCellarTextCommand");
        this.viewTextCellar.addActionListener(this);
        this.viewMenu.add(this.viewTextCellar);
        this.internalGetApp().getMain().getJMenuBar().add(this.viewMenu);
        this.addinMenu = new JMenu(this.getApp().getRS("MNU_ADDIN"));
        JMenuItem noneAddin = new JMenuItem(this.getApp().getRS("NO_DETECTED_ADDIN"));
        noneAddin.setEnabled(false);
        this.addinMenu.add(noneAddin);
        this.internalGetApp().getMain().getJMenuBar().add(this.addinMenu);
        this.prefMenu = new JMenu(this.getApp().getRS("MNU_TOOLS"));
        this.administration = new JMenu(this.getApp().getRS("MNU_TOOLS_ADMIN"));
        this.adminList = new JMenuItem(this.getApp().getRS("MNU_TOOLS_LIST"));
        this.adminList.setActionCommand("ListAdmin");
        this.adminList.addActionListener(this);
        this.adminRack = new JMenuItem(this.getApp().getRS("MNU_TOOLS_RACK"));
        this.adminRack.setActionCommand("RackAdmin");
        this.adminRack.addActionListener(this);
        this.adminLegend = new JMenuItem(this.getApp().getRS("MNU_TOOLS_LEGEND"));
        this.adminLegend.addActionListener(this);
        this.adminLegend.setEnabled(false);
        this.administration.add(this.adminList);
        this.administration.add(this.adminRack);
        this.administration.add(this.adminLegend);
        this.prefMenu.add(this.administration);
        this.prefMenu.addSeparator();
        this.renderers = new JMenu(this.getApp().getRS("MNU_TOOLS_RENDERER"));
        this.wineRenderers = new JMenu(this.getApp().getRS("MNU_TOOLS_PREVIEW"));
        String removeMe = "(Par d\u00e9faut)";
        this.renderers.add("(Par d\u00e9faut)");
        this.wineRenderers.add("(Par d\u00e9faut)");
        this.prefMenu.add(this.renderers);
        this.prefMenu.add(this.wineRenderers);
        this.prefMenu.addSeparator();
        this.scriptsEditor = new JMenuItem(this.getApp().getRS("MNU_SCRIPT"));
        this.scriptsEditor.setActionCommand("ShowScript");
        this.scriptsEditor.addActionListener(this);
        this.prefMenu.add(this.scriptsEditor);
        this.internalGetApp().getMain().getJMenuBar().add(this.prefMenu);
        this.winMenu = new JMenu(this.getApp().getRS("MNU_WIN"));
        this.winCascad = new JMenuItem(this.getApp().getRS("MNU_WIN_CASCAD"));
        this.winCascad.addActionListener(this);
        this.winMozHorizontal = new JMenuItem(this.getApp().getRS("MNU_WIN_HOR"));
        this.winMozHorizontal.addActionListener(this);
        this.winMozVertical = new JMenuItem(this.getApp().getRS("MNU_WIN_VER"));
        this.winMozVertical.addActionListener(this);
        this.internalGetApp().getMain().getJMenuBar().add(this.winMenu);
        this.helpMenu = new JMenu(this.getApp().getRS("MNU_HELP"));
        this.about = new JMenuItem(this.getApp().getRS("MNU_ABOUT"));
        this.about.setActionCommand("About");
        this.about.addActionListener(this);
        this.contactUs = new JMenuItem(this.getApp().getRS("MNU_CONTACT_US"));
        this.contactUs.setActionCommand("OnlineContact");
        this.welcomePage = new JMenuItem(this.getApp().getRS("MNU_START_UP"));
        this.welcomePage.setActionCommand("ShowWelcomePage");
        this.webSite = new JMenuItem(this.getApp().getRS("MNU_OC_ONLINE"));
        this.webSite.setActionCommand("OnlineSite");
        this.contactUs.addActionListener(this);
        this.webSite.addActionListener(this);
        this.welcomePage.addActionListener(this);
        this.helpMenu.add(this.welcomePage);
        this.helpMenu.addSeparator();
        this.helpMenu.add(this.contactUs);
        this.helpMenu.add(this.webSite);
        if (!utils.isMacOS()) {
            this.helpMenu.addSeparator();
            this.helpMenu.add(this.about);
        }
        this.internalGetApp().getMain().getJMenuBar().add(this.helpMenu);
        this.executeOnClose();
    }

    private void executeOnClose() {
        this.fileSearch.setEnabled(false);
        this.fileNewWine.setEnabled(false);
        this.fileSave.setEnabled(false);
        this.fileCloseCellar.setEnabled(false);
        this.administration.setEnabled(false);
        this.viewGraphicsCellar.setEnabled(false);
        this.viewTextCellar.setEnabled(false);
    }

    private void executeOnOpen() {
        this.fileSearch.setEnabled(true);
        this.fileNewWine.setEnabled(true);
        this.fileSave.setEnabled(true);
        this.fileCloseCellar.setEnabled(true);
        this.administration.setEnabled(true);
        this.viewGraphicsCellar.setEnabled(true);
        this.viewTextCellar.setEnabled(true);
    }

    protected final JMenu getWindows() {
        return this.winMenu;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.fileSave) {
            ((Application)this.getApp()).getCellarApplication().dontForgetToRemoveIt();
            return;
        }
        ICommand command = this.getApp().getCommands().get(e.getActionCommand());
        if (command != null) {
            command.execute();
        } else {
            this.getApp().showMessage(null, "La commande \"" + e.getActionCommand() + "\" est introuvable.", MessageType.Message, MessageIconType.Warning, MessageButtonType.Default);
        }
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
        this.executeOnOpen();
    }

    public void onClosing(IApplication source) {
    }

    public void onClose(IApplication source) {
        this.executeOnClose();
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

