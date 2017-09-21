/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import opencellar.application.Application;
import opencellar.application.CommandBarManager;
import opencellar.application.IApplication;
import opencellar.application.IWindow;
import opencellar.application.IWindowListener;
import opencellar.application.WindowType;
import opencellar.ui.MainLayer;
import opencellar.ui.MdiDesktopPane;

public abstract class Window
implements IWindow,
InternalFrameListener {
    protected JInternalFrame frame;
    private IApplication m_app;
    private ArrayList m_listeners = new ArrayList();

    protected Window(IApplication application, IWindowListener systemListener) {
        if (application == null) {
            throw new IllegalArgumentException("application == null");
        }
        this.m_app = application;
        this.addListener(systemListener);
        this.createFrame();
    }

    protected void createFrame() {
        this.frame = new JInternalFrame();
        this.frame.addInternalFrameListener(this);
        this.onCreateFrame();
        ((Application)this.getApplication()).getDesktop().add(this.frame);
    }

    protected void onCreateFrame() {
    }

    public final Rectangle getParentBounds() {
        return ((Application)this.m_app).getMain().desktop.getBounds();
    }

    protected final JInternalFrame getFrame() {
        return this.frame;
    }

    public void show() {
        if (this.frame.isIcon()) {
            try {
                this.frame.setIcon(false);
            }
            catch (Exception ex) {
                // empty catch block
            }
        }
        this.frame.setVisible(true);
        this.frame.moveToFront();
        try {
            this.frame.setSelected(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            this.frame.setClosed(true);
        }
        catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isModal() {
        return false;
    }

    public abstract WindowType getType();

    public String getCaption() {
        return this.frame.getTitle();
    }

    public void setCaption(String s) {
        this.frame.setTitle(s);
        this.notifyOnCaptionChange();
    }

    public Point getLocation() {
        return this.frame.getLocation();
    }

    public void setLocation(Point p) {
        this.frame.setLocation(p);
    }

    public void setSize(Dimension d) {
        if (this.frame.isResizable()) {
            this.frame.setSize(d);
            this.frame.setPreferredSize(d);
        }
    }

    public Dimension getSize() {
        return this.frame.getSize();
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
        if (listener != null) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(IWindowListener listener) {
        if (listener != null) {
            this.m_listeners.remove(listener);
        }
    }

    protected final void notifyOnClose() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IWindowListener listener = (IWindowListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onClose(this);
        }
    }

    protected final void notifyOnActivate() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IWindowListener listener = (IWindowListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onActivate(this);
        }
    }

    protected final void notifyOnCaptionChange() {
        int length = this.m_listeners.size();
        for (int i = 0; i < length; ++i) {
            IWindowListener listener = (IWindowListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onCaptionChange(this);
        }
    }

    protected void onClosing() {
    }

    protected void onActivated() {
    }

    public void internalFrameClosing(InternalFrameEvent e) {
        this.onClosing();
        this.notifyOnClose();
    }

    public void internalFrameClosed(InternalFrameEvent e) {
    }

    public void internalFrameOpened(InternalFrameEvent e) {
    }

    public void internalFrameIconified(InternalFrameEvent e) {
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    public void internalFrameActivated(InternalFrameEvent e) {
        this.onActivated();
        this.notifyOnActivate();
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
    }
}

