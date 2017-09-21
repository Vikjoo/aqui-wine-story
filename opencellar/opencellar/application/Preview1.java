/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Area
 *  opencellar.framework.Classification
 *  opencellar.framework.Country
 *  opencellar.framework.Name
 *  opencellar.framework.RackItemCollection
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import opencellar.application.ColorScheme;
import opencellar.application.DefaultPreview;
import opencellar.framework.Area;
import opencellar.framework.Classification;
import opencellar.framework.Country;
import opencellar.framework.Name;
import opencellar.framework.RackItemCollection;
import opencellar.framework.Wine;
import opencellar.ui.UIHelper;

public class Preview1
extends DefaultPreview {
    static final Dimension BOUNDS = new Dimension(320, 130);
    protected static Font defaultFont = UIHelper.getDefaultFont();
    protected static Font boldFont = defaultFont.deriveFont(1);

    public Preview1() {
        super("Rendu par d\u00e9faut");
    }

    public Dimension getSize(Wine wine) {
        return BOUNDS;
    }

    public void draw(Graphics2D g, Rectangle bounds, Wine wine) {
        g.setColor(Color.LIGHT_GRAY);
        g.clearRect(0, 0, bounds.width, bounds.height);
        Rectangle back = new Rectangle(bounds.x, bounds.y, bounds.width, 30);
        this.drawGradientBackGround(g, Color.WHITE, ColorScheme.getWineColor(wine), back);
        back.x += 5;
        back.y += 7;
        back.width -= 10;
        back.height -= 10;
        this.drawText(g, this.getWineTitle(wine), back, boldFont, Color.BLACK);
        g.drawLine(bounds.x, 30, bounds.x + bounds.width, 30);
        back = new Rectangle(bounds.x, bounds.y + 30, bounds.width, 40);
        back.x += 5;
        back.y += 5;
        back.width -= 10;
        this.drawText(g, this.getWineGeo(wine), back, defaultFont, Color.BLACK);
        back = new Rectangle(bounds.x, bounds.y + 65, bounds.width, 30);
        back.x += 5;
        back.y += 5;
        back.width -= 10;
        this.drawText(g, wine.getClassification().getName(), back, defaultFont, Color.BLACK);
        back = new Rectangle(bounds.x, bounds.y + 90, bounds.width, 30);
        back.x += 5;
        back.y += 5;
        back.width -= 10;
        this.drawText(g, this.getWineYears(wine), back, defaultFont, Color.BLACK);
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1);
    }

    private String getWineTitle(Wine wine) {
        StringBuilder builder = new StringBuilder();
        if (wine.getYear() != 0) {
            builder.append(String.valueOf(wine.getYear()));
            builder.append(" - ");
        }
        builder.append(wine.getName());
        if (wine.isManualManagment()) {
            builder.append(" (" + String.valueOf(wine.getBottles()) + ")");
        } else {
            builder.append(" (" + String.valueOf(wine.getRackItems().size()) + ")");
        }
        return builder.toString();
    }

    private String getWineYears(Wine wine) {
        StringBuilder builder = new StringBuilder();
        if (wine.getConsumeMin() != 0) {
            builder.append("A boire de ");
            if (wine.getConsumeMin() != 0) {
                builder.append(String.valueOf(wine.getConsumeMin()));
            } else {
                builder.append(" - ");
            }
            builder.append(" \u00e0 ");
            if (wine.getConsumeMax() != 0) {
                builder.append(String.valueOf(wine.getConsumeMax()));
            } else {
                builder.append(" - ");
            }
        }
        if (wine.getBestMin() != 0) {
            builder.append(" Apog\u00e9e de ");
            if (wine.getBestMin() != 0) {
                builder.append(String.valueOf(wine.getBestMin()));
            } else {
                builder.append(" - ");
            }
            builder.append(" \u00e0 ");
            if (wine.getBestMax() != 0) {
                builder.append(String.valueOf(wine.getBestMax()));
            } else {
                builder.append(" - ");
            }
        }
        return builder.toString();
    }

    private String getWineGeo(Wine wine) {
        StringBuilder builder = new StringBuilder();
        if (!wine.getCountry().isEmpty()) {
            builder.append(wine.getCountry().getName());
            builder.append(" > ");
        }
        if (!wine.getArea().isEmpty()) {
            builder.append(wine.getArea().getName());
            builder.append(" > ");
        }
        if (!wine.getAppellation().isEmpty()) {
            builder.append(wine.getAppellation().getName());
            builder.append(" > ");
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 2);
        }
        return builder.toString();
    }
}

