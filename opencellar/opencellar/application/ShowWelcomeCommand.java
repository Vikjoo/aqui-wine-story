/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.WindowType;
import opencellar.ui.WelcomeLayer;

public class ShowWelcomeCommand
extends Command {
    private WelcomeLayer layer = null;

    public ShowWelcomeCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        IWindow window = this.getApp().createWindow(WindowType.Welcome, new Object[0]);
        if (window != null) {
            window.show();
        }
    }
}

