/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.WindowType;

public final class ShowScriptCommand
extends Command {
    public ShowScriptCommand(IApplication application) {
        super(application);
    }

    public void execute() {
        IWindow window = this.getApp().createWindow(WindowType.Script, new Object[0]);
        if (window != null) {
            window.show();
        }
    }
}

