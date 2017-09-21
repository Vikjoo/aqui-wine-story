/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 *  opencellar.framework.ObjectState
 *  opencellar.framework.Wine
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.IWineWindow;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.framework.Cellar;
import opencellar.framework.ObjectState;
import opencellar.framework.Wine;

public class DeleteWineCommand
extends Command {
    public DeleteWineCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        if (this.getApp().activeWindow() instanceof IWineWindow) {
            IWineWindow wineWindow = (IWineWindow)((Object)this.getApp().activeWindow());
            Wine wine = wineWindow.getWine();
            if (wine.getState() != ObjectState.New) {
                if (this.getApp().showMessage(null, this.getApp().getRS("WIN_CONFIRM_DELETE"), MessageType.Confirm, MessageIconType.Question, MessageButtonType.YesNo) == DialogResult.Yes) {
                    ((IWindow)((Object)wineWindow)).close();
                    wine.delete();
                }
            } else {
                this.getApp().showMessage(null, this.getApp().getRS("WIN_CMD_DELETE_NEW"), MessageType.Message, MessageIconType.Warning, MessageButtonType.Default);
            }
        } else {
            super.showWarningMessage("#debug : wineWindow -> null");
        }
    }
}

