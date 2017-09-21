/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class OCLine
extends JComponent {
    static final Color LINE_COLOR = Color.ORANGE;

    public void paint(Graphics g) {
        this.update(g);
    }

    public void update(Graphics g) {
        g.setColor(LINE_COLOR);
        g.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
    }
}

