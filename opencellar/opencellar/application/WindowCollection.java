/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.util.ArrayList;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.WindowType;

public class WindowCollection {
    private IApplication m_app;
    private ArrayList m_list = new ArrayList();

    public WindowCollection(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        this.m_app = app;
    }

    public final IApplication getApp() {
        return this.m_app;
    }

    protected final void add(IWindow window) {
        if (window == null) {
            throw new IllegalArgumentException("window == nul");
        }
        this.m_list.add(window);
        this.onAdd(window);
    }

    public final boolean contains(IWindow win) {
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            IWindow window = (IWindow)this.m_list.get(i);
            if (window != win) continue;
            return true;
        }
        return false;
    }

    protected void onAdd(IWindow window) {
    }

    public final int size() {
        return this.m_list.size();
    }

    public final IWindow get(int index) {
        if (index > -1 && index < this.m_list.size()) {
            return (IWindow)this.m_list.get(index);
        }
        return null;
    }

    public final WindowCollection get(WindowType wt) {
        WindowCollection wc = new WindowCollection(this.getApp());
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            IWindow window = (IWindow)this.m_list.get(i);
            if (window == null || window.getType() != wt) continue;
            wc.add(window);
        }
        return wc;
    }

    public final int getWindowCount(WindowType wt) {
        int size = 0;
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            IWindow window = (IWindow)this.m_list.get(i);
            if (window == null || window.getType() != wt) continue;
            ++size;
        }
        return size;
    }

    public final IWindow getOne(WindowType wt) {
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            IWindow window = (IWindow)this.m_list.get(i);
            if (window == null || window.getType() != wt) continue;
            return window;
        }
        return null;
    }

    protected final void remove(IWindow window) {
        if (window != null) {
            this.m_list.remove(window);
            this.onRemove(window);
        }
    }

    protected void onRemove(IWindow window) {
    }
}

