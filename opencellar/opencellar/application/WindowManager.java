/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.Application;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.WindowCollection;
import opencellar.application.WindowFactory;
import opencellar.application.WindowManagerListener;
import opencellar.application.WindowType;

public final class WindowManager
extends WindowCollection {
    private WindowManagerListener m_appListener;

    public WindowManager(IApplication application) {
        super(application);
        this.m_appListener = new WindowManagerListener(this);
    }

    protected final void onAdd(IWindow window) {
        ((Application)this.getApp()).notifyOnCreateWindow(window);
    }

    protected final void onRemove(IWindow window) {
    }

    public /* varargs */ IWindow create(WindowType wt, Object ... args) {
        IWindow window = WindowFactory.create(this.getApp(), wt, this.m_appListener, args);
        if (window != null && !window.isModal() && !super.contains(window)) {
            this.add(window);
        }
        return window;
    }

    public IWindow getActiveWindow() {
        return this.m_appListener.getActive();
    }
}

