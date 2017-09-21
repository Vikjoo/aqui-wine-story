/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.RackItem
 *  opencellar.framework.Wine
 */
package opencellar.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import javax.swing.JComponent;
import opencellar.application.IPreviewRenderer;
import opencellar.framework.RackItem;
import opencellar.framework.Wine;

public class PreviewComponent
extends JComponent {
    private RackItem m_item;
    private IPreviewRenderer m_renderer = null;
    private Image buffer = null;

    public PreviewComponent() {
        super.setOpaque(false);
    }

    public final void setItem(RackItem item) {
        if (this.m_item != item) {
            this.m_item = item;
            this.recreateCache(true);
        }
    }

    public final RackItem getItem() {
        return this.m_item;
    }

    public final void setRenderer(IPreviewRenderer renderer) {
        if (this.m_renderer != renderer) {
            this.m_renderer = renderer;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.update(g);
    }

    public void update(Graphics g) {
        if (this.buffer != null) {
            g.drawImage(this.buffer, 0, 0, this);
        }
    }

    public void paintAll(Graphics g) {
    }

    public void paintComponents(Graphics g) {
    }

    public final void recreateCache(boolean repaint) {
        if (this.buffer != null) {
            this.buffer.flush();
            this.buffer = null;
        }
        Rectangle bounds = new Rectangle(0, 0, this.getWidth(), this.getHeight());
        this.buffer = this.createVolatileImage(this.getWidth(), this.getHeight());
        Graphics2D g2d = (Graphics2D)this.buffer.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        if (this.m_item != null && !this.m_item.isEmpty()) {
            this.m_renderer.draw(g2d, bounds, this.m_item.getWine());
        }
        g2d.dispose();
        g2d = null;
        if (repaint) {
            // empty if block
        }
    }
}

