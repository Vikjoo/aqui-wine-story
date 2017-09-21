/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 */
package opencellar.application;

import opencellar.application.Command;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.IDialogWindow;
import opencellar.application.IWindow;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.WindowType;
import opencellar.framework.Cellar;

public final class NewCellarCommand
extends Command {
    public NewCellarCommand(IApplication application) {
        super(application);
    }

    public void execute() {
        DialogResult dr;
        IDialogWindow newCellarWindow;
        if (this.getApp().activeCellar() != null && (dr = this.getApp().showMessage(null, this.getApp().getRS("MSG_CLOSE_CELLAR"), MessageType.Confirm, MessageIconType.Question, MessageButtonType.YesNo)) == DialogResult.Yes) {
            this.getApp().close();
        }
        if (this.getApp().activeCellar() == null && (newCellarWindow = (IDialogWindow)((Object)this.getApp().createWindow(WindowType.NewCellar, new Object[0]))) != null) {
            newCellarWindow.showDialog();
            ((IWindow)((Object)newCellarWindow)).close();
        }
    }
}

