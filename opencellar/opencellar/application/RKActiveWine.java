/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.MisMatchEventArgs
 *  opencellar.framework.RackItem
 *  opencellar.framework.Wine
 */
package opencellar.application;

import javax.swing.JPopupMenu;
import opencellar.application.IApplication;
import opencellar.application.IApplicationListener;
import opencellar.application.IRackWindow;
import opencellar.application.IRackWindowListener;
import opencellar.application.IWindow;
import opencellar.application.IWineWindow;
import opencellar.application.RootKit;
import opencellar.application.WindowCollection;
import opencellar.application.WindowType;
import opencellar.framework.MisMatchEventArgs;
import opencellar.framework.RackItem;
import opencellar.framework.Wine;

public class RKActiveWine
extends RootKit
implements IApplicationListener,
IRackWindowListener {
    private Wine m_activeWine = null;
    private RackItem m_selectedItem = null;

    public RKActiveWine(IApplication application) {
        super(application);
    }

    public void initialize() {
        this.getApp().addListener(this);
    }

    public void uninitialize() {
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
    }

    public void onClosing(IApplication source) {
    }

    public void onClose(IApplication source) {
    }

    public void onReadOnly(IApplication source) {
    }

    public void onMisMatchVersion(IApplication source, MisMatchEventArgs args) {
    }

    public void onCreateWindow(IApplication source, IWindow window) {
        if (window.getType() == WindowType.Rack) {
            IRackWindow rackWindow = (IRackWindow)((Object)window);
            rackWindow.addRackWindowListener(this);
        }
    }

    public void onCloseWindow(IApplication source, IWindow window) {
        if (window.getType() == WindowType.Rack) {
            IRackWindow rackWindow = (IRackWindow)((Object)window);
            rackWindow.removeRackWindowListener(this);
        }
    }

    private final void sendMessage(Wine wine) {
        WindowCollection wc = this.getApp().getWindows().get(WindowType.Rack);
        int size = wc.size();
        for (int i = 0; i < size; ++i) {
            IRackWindow rackWindow = (IRackWindow)((Object)wc.get(i));
            rackWindow.setActiveWine(wine);
        }
    }

    public void onActivateWindow(IApplication source, IWindow window) {
        if (window.getType() == WindowType.Rack && this.m_activeWine != null) {
            this.m_activeWine = null;
            this.sendMessage(this.m_activeWine);
        } else if (window.getType() == WindowType.Wine) {
            Wine newWine = ((IWineWindow)((Object)window)).getWine();
            if (newWine != this.m_activeWine) {
                this.m_activeWine = newWine;
                this.sendMessage(this.m_activeWine);
            }
        } else if (this.m_activeWine != null) {
            this.m_activeWine = null;
            this.sendMessage(this.m_activeWine);
        }
    }

    public void onRackItemLeave(IRackWindow source) {
        if (this.m_selectedItem != null) {
            this.sendMessage(this.m_activeWine);
        }
        this.m_selectedItem = null;
    }

    public void onRackItemEnter(IRackWindow source, RackItem selectedItem) {
        this.m_selectedItem = selectedItem;
        if (this.m_selectedItem != null && !this.m_selectedItem.isEmpty()) {
            this.sendMessage(this.m_selectedItem.getWine());
        }
    }

    public void onPopup(IRackWindow source, JPopupMenu popup) {
    }
}

