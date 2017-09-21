/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.MisMatchEventArgs
 */
package opencellar.application;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import opencellar.application.Application;
import opencellar.application.IApplication;
import opencellar.application.IApplicationListener;
import opencellar.application.IWindow;
import opencellar.application.MainMenuManager;
import opencellar.application.RootKit;
import opencellar.application.WindowMenuItem;
import opencellar.framework.MisMatchEventArgs;

final class RKWindowMenuItem
extends RootKit
implements IApplicationListener {
    private JMenu m_winMenu = null;

    public RKWindowMenuItem(IApplication application) {
        super(application);
    }

    public final void initialize() {
        this.getApp().addListener(this);
        this.m_winMenu = ((Application)this.getApp()).getMenuManager().getWindows();
    }

    public final void uninitialize() {
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
        WindowMenuItem item = new WindowMenuItem(window);
        this.m_winMenu.add(item);
        this.uncheck();
        item.setSelected(true);
    }

    public void onCloseWindow(IApplication source, IWindow window) {
        WindowMenuItem wmi = this.get(window);
        if (wmi != null) {
            this.m_winMenu.remove(wmi);
        }
    }

    public void onActivateWindow(IApplication source, IWindow window) {
        this.uncheck();
        WindowMenuItem wmi = this.get(window);
        if (wmi != null) {
            wmi.setSelected(true);
        }
    }

    private final WindowMenuItem get(IWindow window) {
        int length = this.m_winMenu.getItemCount();
        for (int i = 0; i < length; ++i) {
            WindowMenuItem wmi;
            JMenuItem item = this.m_winMenu.getItem(i);
            if (!(item instanceof WindowMenuItem) || (wmi = (WindowMenuItem)item).getWindow() != window) continue;
            return wmi;
        }
        return null;
    }

    private final void uncheck() {
        int length = this.m_winMenu.getItemCount();
        for (int i = 0; i < length; ++i) {
            JMenuItem item = this.m_winMenu.getItem(i);
            if (!(item instanceof WindowMenuItem) || !item.isSelected()) continue;
            item.setSelected(false);
        }
    }
}

