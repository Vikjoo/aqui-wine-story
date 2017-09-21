/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.Border;
import opencellar.ui.MdiDesktopPane;

class MDIDesktopPaneEx
extends DefaultDesktopManager {
    private MdiDesktopPane desktop;

    public MDIDesktopPaneEx(MdiDesktopPane desktop) {
        this.desktop = desktop;
    }

    public void endResizingFrame(JComponent f) {
        super.endResizingFrame(f);
        this.resizeDesktop();
    }

    public void endDraggingFrame(JComponent f) {
        super.endDraggingFrame(f);
        this.resizeDesktop();
    }

    public void setNormalSize() {
        JScrollPane scrollPane = this.getScrollPane();
        int x = 0;
        int y = 0;
        Insets scrollInsets = this.getScrollPaneInsets();
        if (scrollPane != null) {
            Dimension d = scrollPane.getVisibleRect().getSize();
            if (scrollPane.getBorder() != null) {
                d.setSize(d.getWidth() - (double)scrollInsets.left - (double)scrollInsets.right, d.getHeight() - (double)scrollInsets.top - (double)scrollInsets.bottom);
            }
            d.setSize(d.getWidth() - 20.0, d.getHeight() - 20.0);
            this.desktop.setAllSize(x, y);
            scrollPane.invalidate();
            scrollPane.validate();
        }
    }

    private Insets getScrollPaneInsets() {
        JScrollPane scrollPane = this.getScrollPane();
        if (scrollPane == null) {
            return new Insets(0, 0, 0, 0);
        }
        return this.getScrollPane().getBorder().getBorderInsets(scrollPane);
    }

    private JScrollPane getScrollPane() {
        JViewport viewPort;
        if (this.desktop.getParent() instanceof JViewport && (viewPort = (JViewport)this.desktop.getParent()).getParent() instanceof JScrollPane) {
            return (JScrollPane)viewPort.getParent();
        }
        return null;
    }

    protected void resizeDesktop() {
        int x = 0;
        int y = 0;
        JScrollPane scrollPane = this.getScrollPane();
        Insets scrollInsets = this.getScrollPaneInsets();
        if (scrollPane != null) {
            JInternalFrame[] allFrames = this.desktop.getAllFrames();
            for (int i = 0; i < allFrames.length; ++i) {
                if (allFrames[i].getX() + allFrames[i].getWidth() > x) {
                    x = allFrames[i].getX() + allFrames[i].getWidth();
                }
                if (allFrames[i].getY() + allFrames[i].getHeight() <= y) continue;
                y = allFrames[i].getY() + allFrames[i].getHeight();
            }
            Dimension d = scrollPane.getVisibleRect().getSize();
            if (scrollPane.getBorder() != null) {
                d.setSize(d.getWidth() - (double)scrollInsets.left - (double)scrollInsets.right, d.getHeight() - (double)scrollInsets.top - (double)scrollInsets.bottom);
            }
            if ((double)x <= d.getWidth()) {
                x = (int)d.getWidth() - 20;
            }
            if ((double)y <= d.getHeight()) {
                y = (int)d.getHeight() - 20;
            }
            this.desktop.setAllSize(x, y);
            scrollPane.invalidate();
            scrollPane.validate();
        }
    }
}

