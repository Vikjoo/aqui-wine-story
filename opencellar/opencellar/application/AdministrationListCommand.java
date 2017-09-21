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

public final class AdministrationListCommand
extends Command {
    public AdministrationListCommand(IApplication application) {
        super(application);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        this.getApp().setCursor(true);
        IWindow window = this.getApp().createWindow(WindowType.ListAdministration, new Object[0]);
        if (window != null) {
            window.show();
        }
        this.getApp().setCursor(false);
    }
}

