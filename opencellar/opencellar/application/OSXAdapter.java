/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import com.apple.eawt.Application;
import com.apple.eawt.ApplicationAdapter;
import com.apple.eawt.ApplicationEvent;
import com.apple.eawt.ApplicationListener;
import opencellar.application.CommandManager;
import opencellar.application.IApplication;
import opencellar.application.ICommand;

public class OSXAdapter
extends ApplicationAdapter {
    private static OSXAdapter theAdapter;
    private static Application theApplication;
    private IApplication mainApp;

    private OSXAdapter(IApplication inApp) {
        this.mainApp = inApp;
    }

    public void handleAbout(ApplicationEvent ae) {
        if (this.mainApp == null) {
            throw new IllegalStateException("handleAbout: MyApp instance detached from listener");
        }
        ae.setHandled(true);
        this.mainApp.getCommands().get("About").execute();
    }

    public void handlePreferences(ApplicationEvent ae) {
        if (this.mainApp == null) {
            throw new IllegalStateException("handlePreferences: MyApp instance detached from listener");
        }
        ae.setHandled(true);
    }

    public void handleQuit(ApplicationEvent ae) {
        if (this.mainApp == null) {
            throw new IllegalStateException("handleQuit: MyApp instance detached from listener");
        }
        ae.setHandled(false);
        this.mainApp.exit();
    }

    public static void registerMacOSXApplication(IApplication inApp) {
        if (theApplication == null) {
            theApplication = new Application();
        }
        OSXAdapter.enablePrefs(false);
        if (theAdapter == null) {
            theAdapter = new OSXAdapter(inApp);
        }
        theApplication.addApplicationListener(theAdapter);
    }

    public static void enablePrefs(boolean enabled) {
        if (theApplication == null) {
            theApplication = new Application();
        }
        theApplication.setEnabledPreferencesMenu(enabled);
    }
}

