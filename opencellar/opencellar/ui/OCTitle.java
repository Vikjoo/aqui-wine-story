/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.JComponent;
import opencellar.ui.UIHelper;

public final class OCTitle
extends JComponent {
    final String RC_NAMESPACE = "/opencellar/rc/misc/info.gif";
    private Image m_image = null;
    private String m_text = "";
    static final Color LINE_COLOR = Color.ORANGE;

    public OCTitle() {
        this.loadImage();
    }

    private final void loadImage() {
        this.m_image = UIHelper.getImage("/opencellar/rc/misc/info.gif");
        if (this.m_image != null) {
            this.repaint();
        }
    }

    public final void setText(String text) {
        if (text != null && !this.m_text.equals(text)) {
            this.m_text = text;
            this.repaint();
        }
    }

    public final String getText() {
        return this.m_text;
    }

    public void paint(Graphics g) {
        this.update(g);
    }

    public void update(Graphics g) {
        g.drawImage(this.m_image, 1, this.getHeight() / 2 - 5, this);
        Rectangle bounds = new Rectangle(15, 0, this.getWidth() - 15, this.getHeight());
        UIHelper.drawTextMiddleLeft(g, this.getText(), bounds, this.getForeground());
        g.setColor(LINE_COLOR);
        g.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
    }
}

