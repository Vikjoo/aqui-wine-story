/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Rack
 *  opencellar.framework.RackItem
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import opencellar.application.LegendCollection;
import opencellar.application.RendererMode;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.Wine;

public interface ICellarRenderer {
    public String getName();

    public RackItem getItemAt(Point var1);

    public void draw(Graphics2D var1, Rectangle var2, Rack var3, Wine var4, LegendCollection var5, RendererMode var6);

    public ICellarRenderer create();
}

