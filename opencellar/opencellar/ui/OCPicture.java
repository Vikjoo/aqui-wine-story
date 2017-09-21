/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JComponent;

public final class OCPicture
extends JComponent {
    private Image m_image = null;
    private Image m_scaleImg = null;

    public final void setImage(Image image) {
        this.m_image = image;
        this.repaint();
    }

    private void applyTransform() {
        this.m_scaleImg = OCPicture.scale(this.m_image, this.getWidth(), this.getHeight());
    }

    public void paint(Graphics g) {
        this.update(g);
    }

    public void update(Graphics g) {
        this.drawImage(g);
    }

    private void drawImage(Graphics g) {
        if (this.m_image != null) {
            int wi = this.m_image.getWidth(this);
            int hw = this.m_image.getHeight(this);
            if (wi <= this.getWidth() && hw <= this.getHeight()) {
                g.drawImage(this.m_image, this.getWidth() / 2 - wi / 2, this.getHeight() / 2 - hw / 2, this);
            } else {
                if (wi > this.getWidth() && hw > this.getHeight()) {
                    this.m_image = this.m_image.getScaledInstance(this.getWidth(), this.getHeight(), 4);
                } else if (wi > this.getWidth() && hw < this.getHeight()) {
                    this.m_image = this.m_image.getScaledInstance(this.getWidth(), -1, 4);
                } else if (wi < this.getWidth() && hw > this.getHeight()) {
                    this.m_image = this.m_image.getScaledInstance(-1, this.getHeight(), 4);
                }
                wi = this.m_image.getWidth(this);
                hw = this.m_image.getHeight(this);
                g.drawImage(this.m_image, this.getWidth() / 2 - wi / 2, this.getHeight() / 2 - hw / 2, this);
            }
        }
    }

    public static Image scale(Image source, int width, int height) {
        if (source == null) {
            return null;
        }
        BufferedImage buf = new BufferedImage(width, height, 2);
        Graphics2D g = buf.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return buf;
    }
}

