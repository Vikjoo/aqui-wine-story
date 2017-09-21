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
import opencellar.application.IWineWindow;
import opencellar.application.WineDialogType;
import opencellar.framework.Cellar;

public final class ShowWineRackItemsSelector
extends Command {
    public ShowWineRackItemsSelector(IApplication app) {
        super(app);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        if (this.getApp().activeWindow() instanceof IWineWindow) {
            IWineWindow wineWindow = (IWineWindow)((Object)this.getApp().activeWindow());
            wineWindow.showDialog(WineDialogType.RackItems, new Object[0]);
        } else {
            super.showWarningMessage("#debug : wineWindow -> null");
        }
    }
}

