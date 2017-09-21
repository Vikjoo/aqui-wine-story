/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.WindowType;
import opencellar.framework.Cellar;

public final class ShowRackWindowCommand
extends Command {
    public ShowRackWindowCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        this.getApp().setCursor(true);
        IWindow rackWindow = this.getApp().createWindow(WindowType.Rack, new Object[0]);
        if (rackWindow != null) {
            rackWindow.show();
        }
        this.getApp().setCursor(false);
    }
}

