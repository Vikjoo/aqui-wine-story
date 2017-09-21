/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Rack
 *  opencellar.framework.RackItem
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import opencellar.application.CommandBarManager;
import opencellar.application.IApplication;
import opencellar.application.ICellarDependancy;
import opencellar.application.IRackWindow;
import opencellar.application.IRackWindowListener;
import opencellar.application.IWindow;
import opencellar.application.IWindowListener;
import opencellar.application.RackCommandBarManager;
import opencellar.application.RackWindowEventDispatcher;
import opencellar.application.Window;
import opencellar.application.WindowType;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.Wine;
import opencellar.ui.RackViewer;

public class RackWindow
extends Window
implements ICellarDependancy,
IRackWindow {
    private RackWindowEventDispatcher m_dispatcher = null;
    private CommandBarManager m_manager;
    private RackViewer layer;
    private Rack m_rack;

    protected RackWindow(IApplication application, Rack rack, IWindowListener systemListener) {
        super(application, systemListener);
        this.m_rack = rack;
        this.setDataSource();
    }

    public Rack getRack() {
        return this.layer.getSelectedRack();
    }

    public void setRack(Rack rack) {
        this.layer.setSelectedRack(rack);
    }

    public boolean supportCommandBars() {
        return true;
    }

    public CommandBarManager getCommandBars() {
        return this.m_manager;
    }

    private final void setDataSource() {
        this.layer.setApp(this.getApplication());
        this.m_manager = new RackCommandBarManager(this.layer.getCommandBarContainer(), this);
        this.m_dispatcher = new RackWindowEventDispatcher(this);
        this.layer.setDispatcher(this.m_dispatcher);
    }

    protected final void onCreateFrame() {
        this.getFrame().setClosable(true);
        this.getFrame().setIconifiable(true);
        this.getFrame().setResizable(true);
        this.getFrame().setSize(450, 530);
        this.getFrame().setMinimumSize(new Dimension(450, 530));
        this.getFrame().setPreferredSize(new Dimension(450, 530));
        this.setCaption("*");
        this.layer = new RackViewer();
        this.getFrame().add(this.layer);
        this.getFrame().pack();
    }

    protected void onClosing() {
        this.layer.close();
    }

    protected void onActivated() {
        Rack rk = this.getRack();
        if (rk != null) {
            this.setCaption(rk.getName().trim());
        } else {
            this.setCaption(this.getApplication().getRS("MSG_MY_CELLAR"));
        }
    }

    public WindowType getType() {
        return WindowType.Rack;
    }

    public final Wine getActiveWine() {
        return this.layer.getActiveWine();
    }

    public final void setActiveWine(Wine wine) {
        this.layer.setActiveWine(wine);
    }

    public RackItem getActiveItem() {
        return this.layer.getActiveItem();
    }

    public void addRackWindowListener(IRackWindowListener listener) {
        this.m_dispatcher.addRackWindowListener(listener);
    }

    public void removeRackWindowListener(IRackWindowListener listener) {
        this.m_dispatcher.removeRackWindowListener(listener);
    }
}

