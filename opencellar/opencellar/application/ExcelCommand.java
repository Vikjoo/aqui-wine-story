/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Cellar
 */
package opencellar.application;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import opencellar.application.Command;
import opencellar.application.DialogResult;
import opencellar.application.IApplication;
import opencellar.application.IGridWinesWindow;
import opencellar.application.IWindow;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.WindowType;
import opencellar.framework.Cellar;

public final class ExcelCommand
extends Command {
    public ExcelCommand(IApplication app) {
        super(app);
    }

    public void execute() {
        if (super.getApp().activeCellar() == null) {
            super.showWarningMessage(this.getApp().getRS("MSG_NULL_CELLAR"));
            return;
        }
        JFileChooser file = new JFileChooser();
        file.setAcceptAllFileFilterUsed(true);
        int ret = file.showSaveDialog(null);
        if (ret == 0) {
            IWindow window = this.getApp().createWindow(WindowType.WineList, new Object[0]);
            if (window == null) {
                return;
            }
            window.show();
            ((IGridWinesWindow)((Object)window)).export(file.getSelectedFile());
            this.getApp().showMessage(null, this.getApp().getRS("GRID_EXCEL_OK"), MessageType.Message, MessageIconType.Information, MessageButtonType.Default);
        }
    }
}

