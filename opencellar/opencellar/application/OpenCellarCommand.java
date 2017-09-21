/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import opencellar.application.Application;
import opencellar.application.Command;
import opencellar.application.IApplication;
import opencellar.application.OpenCellarFileFilter;
import opencellar.ui.MainLayer;

public final class OpenCellarCommand
extends Command {
    public OpenCellarCommand(IApplication application) {
        super(application);
    }

    public void execute() {
        this.getApp().close();
        final JFileChooser file = new JFileChooser();
        file.setAcceptAllFileFilterUsed(false);
        file.setFileFilter(new OpenCellarFileFilter());
        int ret = file.showOpenDialog(((Application)this.getApp()).getMain());
        if (ret == 0) {
            SwingUtilities.invokeLater(new Runnable(){

                public void run() {
                    OpenCellarCommand.this.getApp().open(file.getSelectedFile().getAbsolutePath());
                }
            });
        }
    }

}

