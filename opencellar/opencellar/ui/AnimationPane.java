/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public final class AnimationPane
extends JPanel {
    private Image m_image;

    public final void setImage(Image img) {
        this.m_image = img;
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.m_image != null) {
            g.drawImage(this.m_image, 0, 0, this);
        }
    }
}

