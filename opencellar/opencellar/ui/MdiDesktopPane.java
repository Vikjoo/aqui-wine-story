/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.DesktopManager;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import opencellar.ui.MDIDesktopPaneEx;

public class MdiDesktopPane
extends JDesktopPane {
    private static int FRAME_OFFSET = 20;
    private MDIDesktopPaneEx manager;

    public MdiDesktopPane() {
        this.manager = new MDIDesktopPaneEx(this);
        this.setDesktopManager(this.manager);
        this.setDragMode(1);
    }

    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, y, w, h);
        this.checkDesktopSize();
    }

    public Component add(JInternalFrame frame) {
        Point p;
        JInternalFrame[] array = this.getAllFrames();
        Component retval = super.add(frame);
        this.checkDesktopSize();
        if (array.length > 0) {
            p = array[0].getLocation();
            p.x += FRAME_OFFSET * 2;
        } else {
            p = new Point(0, 0);
        }
        if (frame.getLocation().x == 0 && frame.getLocation().y == 0) {
            frame.setLocation(p.x, p.y);
        }
        return retval;
    }

    public void remove(Component c) {
        super.remove(c);
        this.checkDesktopSize();
    }

    public void cascadeFrames() {
        int x = 0;
        int y = 0;
        JInternalFrame[] allFrames = this.getAllFrames();
        this.manager.setNormalSize();
        int frameHeight = this.getBounds().height - 5 - allFrames.length * FRAME_OFFSET;
        int frameWidth = this.getBounds().width - 5 - allFrames.length * FRAME_OFFSET;
        for (int i = allFrames.length - 1; i >= 0; --i) {
            allFrames[i].setSize(frameWidth, frameHeight);
            allFrames[i].setLocation(x, y);
            x += FRAME_OFFSET;
            y += FRAME_OFFSET;
        }
    }

    public void tileFrames() {
        JInternalFrame[] allFrames = this.getAllFrames();
        this.manager.setNormalSize();
        int frameHeight = this.getBounds().height / allFrames.length;
        int y = 0;
        for (int i = 0; i < allFrames.length; ++i) {
            allFrames[i].setSize(this.getBounds().width, frameHeight);
            allFrames[i].setLocation(0, y);
            y += frameHeight;
        }
    }

    public void setAllSize(Dimension d) {
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);
    }

    public void setAllSize(int width, int height) {
        this.setAllSize(new Dimension(width, height));
    }

    private void checkDesktopSize() {
        if (this.getParent() != null && this.isVisible()) {
            this.manager.resizeDesktop();
        }
    }
}

