/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Rack
 *  opencellar.framework.RackItem
 *  opencellar.framework.Wine
 */
package opencellar.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import opencellar.application.ICellarRenderer;
import opencellar.application.LegendCollection;
import opencellar.application.RendererMode;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.Wine;
import opencellar.ui.IRackViewerListener;
import opencellar.ui.RackViewerEvent;

public final class RackViewerComponent
extends JComponent
implements ComponentListener,
MouseListener,
MouseMotionListener {
    private Rack m_rack;
    private Wine m_wine;
    private LegendCollection m_legends;
    private ICellarRenderer m_renderer = null;
    private Image buffer = null;
    private Point lastPoint = null;
    private RackItem m_item = null;
    private RackItem m_overItem = null;
    private ArrayList m_listeners = new ArrayList();

    public RackViewerComponent() {
        this.addComponentListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void setRack(Rack rack) {
        this.m_rack = rack;
        this.recreateCache(true);
    }

    public Rack getRack() {
        return this.m_rack;
    }

    public void setActiveWine(Wine wine) {
        this.m_wine = wine;
        this.recreateCache(true);
    }

    public final Wine getActiveWine() {
        return this.m_wine;
    }

    public void setLegends(LegendCollection legends) {
        this.m_legends = legends;
        this.recreateCache(false);
    }

    public void setRenderer(ICellarRenderer renderer) {
        if (renderer == null) {
            throw new IllegalArgumentException("renderer");
        }
        this.m_renderer = renderer;
        this.recreateCache(false);
    }

    public void paint(Graphics g) {
        this.update(g);
    }

    public void update(Graphics g) {
        if (this.buffer == null) {
            Rectangle bounds = new Rectangle(0, 0, this.getWidth(), this.getHeight());
            this.buffer = this.createImage(this.getWidth(), this.getHeight());
            Graphics2D g2d = (Graphics2D)this.buffer.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            if (this.m_rack != null) {
                this.m_renderer.draw(g2d, bounds, this.m_rack, this.m_wine, this.m_legends, RendererMode.Window);
            }
            g2d.dispose();
            g2d = null;
        }
        g.drawImage(this.buffer, 0, 0, this);
    }

    public final void recreateCache(boolean repaint) {
        if (this.buffer != null) {
            this.buffer.flush();
            this.buffer = null;
        }
        if (repaint) {
            this.repaint();
        }
    }

    public void componentResized(ComponentEvent e) {
        if (this.m_rack != null) {
            this.recreateCache(true);
        }
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        this.lastPoint = e.getPoint();
        if (this.isEnabled()) {
            this.handleClick(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (this.isEnabled() && e.isPopupTrigger()) {
            this.notifyOnPopup(e.getPoint());
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private void handleClick(MouseEvent e) {
        RackItem item = this.m_renderer.getItemAt(e.getPoint());
        this.setItem(item);
        if (item == null) {
            return;
        }
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1 && (e.getModifiers() & 2) != 0) {
            this.notifyOnPopup(e.getPoint());
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            if (e.getClickCount() > 1) {
                this.notifyOnDoubleClick();
            }
        } else if (e.isPopupTrigger()) {
            this.notifyOnPopup(e.getPoint());
        }
    }

    private final void setItem(RackItem item) {
        if (item != this.m_item) {
            this.m_item = item;
        }
    }

    private final void setItemEx(RackItem item, Point location) {
        if (item == null && this.m_overItem != null) {
            this.notifyOnItemLeave();
            this.m_overItem = null;
            return;
        }
        if (this.m_overItem != item) {
            this.notifyOnItemLeave();
        }
        if (this.m_overItem != item) {
            this.notifyOnItemLeave();
        }
        this.m_overItem = item;
        this.notifyOnItemEnter(location);
    }

    public final RackItem getHoverItem() {
        return this.m_overItem;
    }

    public final RackItem getWorkingItem() {
        return this.m_item;
    }

    public RackItem getItemAt(Point p) {
        return this.m_renderer.getItemAt(p);
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        this.setItemEx(this.m_renderer.getItemAt(e.getPoint()), e.getPoint());
    }

    public void addListener(IRackViewerListener listener) {
        if (listener != null) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(IRackViewerListener listener) {
        if (listener != null) {
            this.m_listeners.remove(listener);
        }
    }

    protected void notifyOnPopup(Point location) {
        RackViewerEvent evt = new RackViewerEvent(this, location);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            IRackViewerListener listener = (IRackViewerListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onPopup(evt);
        }
    }

    protected void notifyOnDoubleClick() {
        RackViewerEvent evt = new RackViewerEvent(this);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            IRackViewerListener listener = (IRackViewerListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onDoubleClick(evt);
        }
    }

    protected void notifyOnDrop(RackViewerEvent evt) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            IRackViewerListener listener = (IRackViewerListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onDrop(evt);
        }
    }

    protected void notifyOnItemEnter(Point location) {
        RackViewerEvent evt = new RackViewerEvent(this, location);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            IRackViewerListener listener = (IRackViewerListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onItemEnter(evt);
        }
    }

    protected void notifyOnItemLeave() {
        RackViewerEvent evt = new RackViewerEvent(this);
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            IRackViewerListener listener = (IRackViewerListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onItemLeave(evt);
        }
    }

    protected void notifyOnBeginDrop(RackViewerEvent evt) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            IRackViewerListener listener = (IRackViewerListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onBeginDrop(evt);
        }
    }
}

