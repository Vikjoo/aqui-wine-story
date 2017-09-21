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

public final class ShowCellarTextCommand
extends Command {
    public ShowCellarTextCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        this.getApp().setCursor(true);
        IWindow gridWindow = this.getApp().createWindow(WindowType.WineList, new Object[0]);
        if (gridWindow != null) {
            gridWindow.show();
        }
        this.getApp().setCursor(false);
    }
}

