/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.RackItem
 */
package opencellar.application;

import java.util.ArrayList;
import javax.swing.JPopupMenu;
import opencellar.application.IRackWindow;
import opencellar.application.IRackWindowListener;
import opencellar.framework.RackItem;

public class RackWindowEventDispatcher {
    private IRackWindow m_window;
    private ArrayList m_listeners = new ArrayList();

    public RackWindowEventDispatcher(IRackWindow window) {
        if (window == null) {
            throw new IllegalArgumentException("window == null");
        }
        this.m_window = window;
    }

    public final IRackWindow getWindow() {
        return this.m_window;
    }

    public final void addRackWindowListener(IRackWindowListener listener) {
        if (listener != null) {
            this.m_listeners.add(listener);
        }
    }

    public final void removeRackWindowListener(IRackWindowListener listener) {
        if (listener != null) {
            this.m_listeners.remove(listener);
        }
    }

    public final void notifyOnRackItemEnter(RackItem selectedItem) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            IRackWindowListener listener = (IRackWindowListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onRackItemEnter(this.m_window, selectedItem);
        }
    }

    public final void notifyOnRackItemLeave() {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            IRackWindowListener listener = (IRackWindowListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onRackItemLeave(this.m_window);
        }
    }

    public final void notifyOnPopup(JPopupMenu popupMenu) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            IRackWindowListener listener = (IRackWindowListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onPopup(this.m_window, popupMenu);
        }
    }
}

