/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import opencellar.application.IPreviewRenderer;
import opencellar.framework.Wine;
import opencellar.ui.UIHelper;

public abstract class DefaultPreview
implements IPreviewRenderer {
    private String m_name = "";

    public DefaultPreview(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name");
        }
        this.m_name = name;
    }

    public String getName() {
        return this.m_name;
    }

    public void draw(Graphics2D g, Rectangle bounds, Wine wine) {
    }

    public abstract Dimension getSize(Wine var1);

    protected final void drawGradientBackGround(Graphics2D g, Color c1, Color c2, Rectangle bounds) {
        Paint oldPaint = g.getPaint();
        GradientPaint gradientPaint = new GradientPaint(bounds.x, bounds.y, c1, bounds.x + bounds.width, bounds.y + bounds.height, c2);
        g.setPaint(gradientPaint);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setPaint(oldPaint);
    }

    protected final void drawText(Graphics2D g, String text, Rectangle bounds, Font font, Color color) {
        UIHelper.drawText(g, text, bounds, font, color);
    }
}

