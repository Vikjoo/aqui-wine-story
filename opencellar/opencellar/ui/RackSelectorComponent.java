/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Rack
 *  opencellar.framework.RackItem
 *  opencellar.framework.RackItemWorkqueue
 *  opencellar.framework.RackItemWorkqueueItem
 *  opencellar.framework.Wine
 *  opencellar.framework.WorkqueueItemType
 */
package opencellar.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import javax.swing.JComponent;
import opencellar.application.ICellarRenderer;
import opencellar.application.LegendCollection;
import opencellar.application.RendererMode;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemWorkqueue;
import opencellar.framework.RackItemWorkqueueItem;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;

public final class RackSelectorComponent
extends JComponent
implements ComponentListener,
MouseListener {
    private Rack m_rack;
    private Wine m_wine;
    private LegendCollection m_legends;
    private ICellarRenderer m_renderer = null;
    private Image buffer;

    public RackSelectorComponent() {
        this.addComponentListener(this);
        this.addMouseListener(this);
    }

    public void setDatasource(Rack rack, Wine wine) {
        this.m_rack = rack;
        this.m_wine = wine;
        this.recreateCache(true);
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
            this.m_renderer.draw(g2d, bounds, this.m_rack, this.m_wine, this.m_legends, RendererMode.Select);
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
        if (this.isEnabled()) {
            this.handleClick(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private void handleClick(MouseEvent e) {
        RackItem item = this.m_renderer.getItemAt(e.getPoint());
        if (item != null) {
            if (item.isEmpty()) {
                WorkqueueItemType type = this.m_wine.getRackItemQueue().getType(item);
                if (type == WorkqueueItemType.Unknow || type == WorkqueueItemType.Delete) {
                    this.m_wine.getRackItemQueue().set(new RackItemWorkqueueItem(item, WorkqueueItemType.Add));
                } else if (type == WorkqueueItemType.Add) {
                    this.m_wine.getRackItemQueue().set(new RackItemWorkqueueItem(item, WorkqueueItemType.Delete));
                }
                this.recreateCache(true);
            } else if (item.getWine() == this.m_wine) {
                WorkqueueItemType type = this.m_wine.getRackItemQueue().getType(item);
                if (type == WorkqueueItemType.Add || type == WorkqueueItemType.Unknow) {
                    this.m_wine.getRackItemQueue().set(new RackItemWorkqueueItem(item, WorkqueueItemType.Delete));
                } else if (type == WorkqueueItemType.Delete) {
                    this.m_wine.getRackItemQueue().set(new RackItemWorkqueueItem(item, WorkqueueItemType.Add));
                }
                this.recreateCache(true);
            }
        }
    }
}

