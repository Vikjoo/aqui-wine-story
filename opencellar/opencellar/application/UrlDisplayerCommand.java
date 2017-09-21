/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.IApplication;
import opencellar.application.INavigatorWindow;
import opencellar.application.IWindow;
import opencellar.application.WindowType;

public abstract class UrlDisplayerCommand
extends Command {
    private String m_url = "";

    public UrlDisplayerCommand(IApplication app, String url) {
        super(app);
        this.m_url = url;
    }

    public void execute() {
        this.getApp().setCursor(true);
        IWindow window = this.getApp().createWindow(WindowType.Navigator, new Object[0]);
        if (window != null) {
            ((INavigatorWindow)((Object)window)).setUrl(this.m_url);
            window.show();
        }
        this.getApp().setCursor(false);
    }
}

