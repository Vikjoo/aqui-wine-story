/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.CellarApplication
 */
package opencellar.application;

import java.awt.Container;
import java.awt.Cursor;
import javax.swing.JDialog;
import opencellar.application.Application;
import opencellar.application.DialogWindow;
import opencellar.application.IApplication;
import opencellar.application.WindowType;
import opencellar.framework.CellarApplication;
import opencellar.ui.INewCellarPaneListener;
import opencellar.ui.NewCellarPane;
import opencellar.ui.NewCellarPaneEvent;

public final class NewCellarWindow
extends DialogWindow
implements INewCellarPaneListener {
    private NewCellarPane m_newCellarPanel;

    protected NewCellarWindow(IApplication application) {
        super(application);
    }

    protected void onCreateFrame() {
        this.getFrame().setTitle("Open Cellar Cross Platform 0.1");
        this.m_newCellarPanel = new NewCellarPane();
        this.m_newCellarPanel.addNewCellarPaneListener(this);
        this.m_newCellarPanel.performTranslatation(this.getApplication());
        this.getFrame().setContentPane(this.m_newCellarPanel);
    }

    public int showDialog() {
        this.centerFrame();
        int ret = 0;
        this.getFrame().setVisible(true);
        return ret;
    }

    public final WindowType getType() {
        return WindowType.NewCellar;
    }

    public void onCreate(NewCellarPaneEvent evt) {
        this.getFrame().setCursor(Cursor.getPredefinedCursor(3));
        this.createCellar(evt.getPath(), evt.isTemplateUsed());
        this.getFrame().setCursor(Cursor.getPredefinedCursor(0));
        this.getFrame().setVisible(false);
    }

    public void onAbort() {
        this.getFrame().setVisible(false);
    }

    private void createCellar(String path, boolean useTemplate) {
        Application app = (Application)super.getApplication();
        if (useTemplate) {
            app.getCellarApplication().create(path, true, "CrossPlatform", "");
        } else {
            app.getCellarApplication().create(path, false, "CrossPlatform", "");
        }
    }
}

