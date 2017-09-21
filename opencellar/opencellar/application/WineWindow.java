/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.CellarObject
 *  opencellar.framework.ICellarObjectListener
 *  opencellar.framework.Note
 *  opencellar.framework.ObjectState
 *  opencellar.framework.ObjectType
 *  opencellar.framework.PurchaseSales
 *  opencellar.framework.Rack
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import opencellar.application.CommandBarManager;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.ICellarDependancy;
import opencellar.application.IWindow;
import opencellar.application.IWindowListener;
import opencellar.application.IWineWindow;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.Window;
import opencellar.application.WindowType;
import opencellar.application.WineCommandBarManager;
import opencellar.application.WineDialogType;
import opencellar.application.WineTabPageType;
import opencellar.application.utils;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.ICellarObjectListener;
import opencellar.framework.Note;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;
import opencellar.framework.PurchaseSales;
import opencellar.framework.Rack;
import opencellar.framework.Wine;
import opencellar.ui.UIHelper;
import opencellar.ui.WineLayer;

public final class WineWindow
extends Window
implements IWineWindow,
ICellarObjectListener,
ICellarDependancy {
    private CommandBarManager m_manager;
    private WineLayer layer;
    private Wine m_wine;
    private boolean isCanceling = false;
    static final String ASTERIX = " (*)";

    protected WineWindow(IApplication application, Wine wine, IWindowListener systemListener) {
        super(application, systemListener);
        if (wine == null) {
            throw new IllegalArgumentException("wine == null");
        }
        this.m_wine = wine;
        this.setDataSource();
    }

    public final void show() {
        if (this.m_wine != null && this.m_wine.getState() == ObjectState.Delete) {
            this.getApplication().showMessage(null, this.getApplication().getRS("WIN_CANNOT_OPEN_WINE"), MessageType.Message, MessageIconType.Error, MessageButtonType.Default);
        } else {
            super.show();
        }
    }

    public boolean supportCommandBars() {
        return true;
    }

    public CommandBarManager getCommandBars() {
        return this.m_manager;
    }

    private final void setDataSource() {
        this.m_wine.addListener((ICellarObjectListener)this);
        this.setCaptionEx();
        this.layer.init(this.getApplication(), this.m_wine);
        this.m_manager = new WineCommandBarManager(this.layer.getCommandBarPane(), this);
    }

    protected final void onCreateFrame() {
        this.getFrame().setClosable(true);
        this.getFrame().setIconifiable(true);
        this.getFrame().setResizable(false);
        this.getFrame().setSize(585, 590);
        this.getFrame().setPreferredSize(new Dimension(585, 590));
        this.setCaption("");
        this.layer = new WineLayer();
        this.getFrame().add(this.layer);
        this.getFrame().pack();
    }

    protected void onClosing() {
        if (!this.isCanceling && this.m_wine.getState() == ObjectState.New | this.m_wine.getState() == ObjectState.Update | this.layer.hasChange()) {
            String msg = utils.format(this.getApplication().getRS("WIN_SAVE_BEFORE_EXIT"), this.m_wine.getName().trim());
            super.show();
            if (this.getApplication().showMessage(null, msg, MessageType.Confirm, MessageIconType.Question, MessageButtonType.YesNo) == DialogResult.Yes) {
                this.save();
            }
        }
        this.m_wine.removeListener((ICellarObjectListener)this);
        this.layer.close();
    }

    public void setCaption(String s) {
        if (s == null || s.equals("")) {
            super.setCaption("(Vin sans nom)");
        } else {
            super.setCaption(s);
        }
    }

    public WindowType getType() {
        return WindowType.Wine;
    }

    public Wine getWine() {
        return this.m_wine;
    }

    public void save() {
        UIHelper.setCursor(this.getFrame(), true);
        this.layer.save();
        UIHelper.setCursor(this.getFrame(), false);
    }

    public void cancel() {
        this.isCanceling = true;
        this.layer.cancel();
        this.close();
        this.isCanceling = false;
    }

    public /* varargs */ void showDialog(WineDialogType dialogType, Object ... args) {
        if (dialogType == null) {
            return;
        }
        if (dialogType == WineDialogType.RackItems) {
            Rack target = null;
            if (args != null && args.length > 0 && args[0] instanceof Rack) {
                target = (Rack)args[0];
            }
            this.layer.showRackItemsWindow(target);
        } else if (dialogType == WineDialogType.PurchaseSales) {
            PurchaseSales target = null;
            if (args != null && args.length > 0 && args[0] instanceof PurchaseSales) {
                target = (PurchaseSales)args[0];
            }
            if (target == null) {
                target = (PurchaseSales)this.m_wine.getCellar().createItem(ObjectType.PurchaseSales);
                target.setWine(this.m_wine);
            }
            this.layer.showPurchaseSalesWindow(target);
        } else if (dialogType == WineDialogType.Notes) {
            Note target = null;
            if (args != null && args.length > 0 && args[0] instanceof Note) {
                target = (Note)args[0];
            }
            if (target == null) {
                target = (Note)this.m_wine.getCellar().createItem(ObjectType.Note);
                target.setWine(this.m_wine);
            }
            this.layer.showNoteWindow(target);
        }
    }

    public void setTabPage(WineTabPageType tabPage) {
        this.layer.setTabPage(tabPage);
    }

    public WineTabPageType getTabPage() {
        return this.layer.getTabPage();
    }

    public void onStateChanged(CellarObject source) {
        this.setCaptionEx();
    }

    public void onPropertyChanged(CellarObject source, String propertyName) {
        if (propertyName.equals("Name")) {
            this.setCaptionEx();
        }
    }

    private void setCaptionEx() {
        if (this.m_wine.getState() == ObjectState.New | this.m_wine.getState() == ObjectState.Update) {
            this.setCaption(this.m_wine.getName().trim() + " (*)");
        } else {
            this.setCaption(this.m_wine.getName().trim());
        }
    }
}

