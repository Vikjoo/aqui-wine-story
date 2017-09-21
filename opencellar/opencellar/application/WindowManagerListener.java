/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.Application;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.IWindowAdapter;
import opencellar.application.WindowManager;

public class WindowManagerListener
extends IWindowAdapter {
    private WindowManager m_wm = null;
    private IWindow m_activeWindow = null;

    public WindowManagerListener(WindowManager wm) {
        if (wm == null) {
            throw new IllegalArgumentException("wm == null");
        }
        this.m_wm = wm;
    }

    public void onClose(IWindow source) {
        if (source != null) {
            ((Application)this.m_wm.getApp()).notifyOnCloseWindow(source);
            this.m_wm.remove(source);
        }
        if (this.m_activeWindow != source) {
            this.setActiveWindow(source);
            ((Application)this.m_wm.getApp()).notifyOnActivateWindow(source);
        }
    }

    public void onActivate(IWindow source) {
        if (source != null) {
            this.setActiveWindow(source);
            ((Application)this.m_wm.getApp()).notifyOnActivateWindow(source);
        }
    }

    public IWindow getActive() {
        return this.m_activeWindow;
    }

    private void setActiveWindow(IWindow window) {
        if (this.m_activeWindow != window) {
            this.m_activeWindow = window;
        }
    }
}

