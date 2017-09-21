/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import javax.swing.JFileChooser;
import opencellar.application.AppSettings;
import opencellar.application.Application;
import opencellar.application.CommandBarManager;
import opencellar.application.IApplication;
import opencellar.application.INavigatorWindow;
import opencellar.application.IWindow;
import opencellar.application.IWindowListener;
import opencellar.application.MessageBoxFactory;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.SettingCollection;
import opencellar.application.WindowType;
import opencellar.ui.MainLayer;

public final class NavigatorWindow
implements IWindow,
INavigatorWindow {
    private String m_url = "";
    private IApplication m_app;

    protected NavigatorWindow(IApplication application) {
        if (application == null) {
            throw new IllegalArgumentException("application == null");
        }
        this.m_app = application;
    }

    public void setUrl(String url) {
        if (url != null) {
            this.m_url = url;
        }
    }

    public boolean isNavigatorSet() {
        return !this.getApplication().getSettings().get("Env").get("Navigator", "").equals("");
    }

    public void show() {
        String navigatorPath = this.getApplication().getSettings().get("Env").get("Navigator", "");
        if (navigatorPath.equals("")) {
            File exec;
            MessageBoxFactory.createEx(null, "Open Cellar", this.getApplication().getRS("MSG_CHOOSE_NAV_PATH"), MessageType.Message, MessageIconType.Information, MessageButtonType.Default);
            JFileChooser fileChooser = new JFileChooser();
            int ret = fileChooser.showOpenDialog(((Application)this.getApplication()).getMain());
            if (ret == 0 && (exec = fileChooser.getSelectedFile()) != null && exec.exists()) {
                this.getApplication().getSettings().get("Env").set("Navigator", exec.getAbsolutePath());
            }
        }
        navigatorPath = this.getApplication().getSettings().get("Env").get("Navigator", "");
        try {
            Runtime.getRuntime().exec(new String[]{navigatorPath, this.m_url});
        }
        catch (Exception ex) {
            MessageBoxFactory.createEx(null, "Open Cellar", "# debug :: exec invalide ->\npath : " + navigatorPath + "\nurl : " + this.m_url, MessageType.Message, MessageIconType.Error, MessageButtonType.Default);
            this.getApplication().getSettings().get("Env").set("Navigator", "");
        }
    }

    public void close() {
    }

    public boolean isModal() {
        return true;
    }

    public WindowType getType() {
        return WindowType.Navigator;
    }

    public String getCaption() {
        return "";
    }

    public void setCaption(String s) {
    }

    public Point getLocation() {
        return null;
    }

    public void setLocation(Point p) {
    }

    public void setSize(Dimension d) {
    }

    public Dimension getSize() {
        return null;
    }

    public boolean supportCommandBars() {
        return false;
    }

    public CommandBarManager getCommandBars() {
        return null;
    }

    public IApplication getApplication() {
        return this.m_app;
    }

    public void addListener(IWindowListener listener) {
    }

    public void removeListener(IWindowListener listener) {
    }
}

