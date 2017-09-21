/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JDialog;
import opencellar.application.Application;
import opencellar.application.CommandBarManager;
import opencellar.application.IApplication;
import opencellar.application.IDialogWindow;
import opencellar.application.IWindow;
import opencellar.application.IWindowListener;
import opencellar.application.WindowType;
import opencellar.ui.MainLayer;

public abstract class DialogWindow
implements IWindow,
IDialogWindow {
    private JDialog m_frame;
    private IApplication m_app;

    protected DialogWindow(IApplication application) {
        if (application == null) {
            throw new IllegalArgumentException("application == null");
        }
        this.m_app = application;
        this.createFrame();
    }

    private void createFrame() {
        this.m_frame = new JDialog((Frame)((Application)this.getApplication()).getMain(), true);
        this.m_frame.setDefaultCloseOperation(0);
        this.onCreateFrame();
        this.m_frame.setResizable(false);
        this.m_frame.pack();
    }

    protected void onCreateFrame() {
    }

    protected final JDialog getFrame() {
        return this.m_frame;
    }

    protected final void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.m_frame.getSize();
        this.m_frame.setLocation(screenSize.width / 2 - frameSize.width / 2, screenSize.height / 2 - frameSize.height / 2);
    }

    public int showDialog() {
        return -1;
    }

    public void show() {
    }

    public void close() {
        this.m_frame.dispose();
    }

    public final boolean isModal() {
        return true;
    }

    public abstract WindowType getType();

    public String getCaption() {
        return null;
    }

    public void setCaption(String s) {
    }

    public Point getLocation() {
        return null;
    }

    public void setLocation(Point p) {
    }

    public void setSize(Dimension d) {
    }

    public Dimension getSize() {
        return null;
    }

    public boolean supportCommandBars() {
        return false;
    }

    public CommandBarManager getCommandBars() {
        return null;
    }

    public IApplication getApplication() {
        return this.m_app;
    }

    public void addListener(IWindowListener listener) {
    }

    public void removeListener(IWindowListener listener) {
    }
}

