/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import opencellar.framework.Wine;

public interface IPreviewRenderer {
    public String getName();

    public void draw(Graphics2D var1, Rectangle var2, Wine var3);

    public Dimension getSize(Wine var1);
}

