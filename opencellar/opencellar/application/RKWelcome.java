/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.MisMatchEventArgs
 */
package opencellar.application;

import opencellar.application.AppSettings;
import opencellar.application.CommandManager;
import opencellar.application.IApplication;
import opencellar.application.IApplicationListener;
import opencellar.application.ICommand;
import opencellar.application.IWindow;
import opencellar.application.RootKit;
import opencellar.application.SettingCollection;
import opencellar.framework.MisMatchEventArgs;

public class RKWelcome
extends RootKit
implements IApplicationListener {
    public RKWelcome(IApplication application) {
        super(application);
    }

    public void initialize() {
        this.getApp().addListener(this);
    }

    public void uninitialize() {
    }

    public void onActivateWindow(IApplication source, IWindow window) {
    }

    public void onClose(IApplication source) {
    }

    public void onCloseWindow(IApplication source, IWindow window) {
    }

    public void onClosing(IApplication source) {
    }

    public void onCreateWindow(IApplication source, IWindow window) {
    }

    public void onMisMatchVersion(IApplication source, MisMatchEventArgs args) {
    }

    public void onOpen(IApplication source) {
    }

    public void onOpening(IApplication source) {
    }

    public void onReadOnly(IApplication source) {
    }

    public void onShutDown(IApplication source) {
    }

    public void onStart(IApplication source) {
        String s = this.getApp().getSettings().get("App").get("StartPage", "T");
        if (s.startsWith("T")) {
            this.getApp().getCommands().get("ShowWelcomePage").execute();
        }
        this.getApp().removeListener(this);
    }

    public void onStarting(IApplication source) {
    }
}

