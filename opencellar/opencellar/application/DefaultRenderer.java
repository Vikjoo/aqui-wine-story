/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Rack
 *  opencellar.framework.RackItem
 *  opencellar.framework.RackItemWorkqueue
 *  opencellar.framework.RackNamingType
 *  opencellar.framework.RackType
 *  opencellar.framework.Wine
 *  opencellar.framework.WorkqueueItemType
 */
package opencellar.application;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.Enumeration;
import java.util.Hashtable;
import opencellar.application.ColorScheme;
import opencellar.application.ICellarRenderer;
import opencellar.application.LegendCollection;
import opencellar.application.RendererMode;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemWorkqueue;
import opencellar.framework.RackNamingType;
import opencellar.framework.RackType;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;
import opencellar.ui.UIHelper;

public class DefaultRenderer
implements ICellarRenderer {
    private String m_name;
    private Hashtable m_items = new Hashtable();
    protected static final int MIN_ITEMS_SIZE = 6;
    static final int SPACE_AROUND_ITEM = 2;
    private RendererMode m_mode;
    private LegendCollection m_legends;
    private Wine m_wine;
    private Rack m_rack;
    private Rectangle m_bounds;
    private int m_itemSize = -1;
    private final int pixelSpace = 15;
    private final int maxSize = 45;

    public DefaultRenderer(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name");
        }
        this.m_name = name;
    }

    public final String getName() {
        return this.m_name;
    }

    public final RackItem getItemAt(Point p) {
        Enumeration keys = this.m_items.keys();
        while (keys.hasMoreElements()) {
            Rectangle r = (Rectangle)keys.nextElement();
            if (!r.contains(p)) continue;
            return (RackItem)this.m_items.get(r);
        }
        return null;
    }

    protected final void add(Rectangle bounds, RackItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item");
        }
        if (bounds == null) {
            throw new IllegalArgumentException("bounds");
        }
        this.m_items.put(bounds, item);
    }

    public final void draw(Graphics2D g, Rectangle bounds, Rack rack, Wine selectedWine, LegendCollection legends, RendererMode mode) {
        this.m_bounds = bounds;
        this.m_rack = rack;
        this.m_wine = selectedWine;
        this.m_legends = legends;
        this.m_mode = mode;
        this.m_items.clear();
        this.determineItemSize(bounds);
        if (this.m_itemSize >= 6) {
            this.onBeginDraw();
            this.onDraw(g);
            this.onEndDraw();
        }
    }

    protected final int getItemSize() {
        return this.m_itemSize;
    }

    protected void onDraw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int row = this.m_rack.getRows();
        int column = this.m_rack.getColumns();
        int rowSpace = this.m_rack.getRowSpace();
        int columnSpace = this.m_rack.getColumnSpace();
        RackType rt = this.m_rack.getRackType();
        for (int i = 0; i < row; ++i) {
            Rectangle rl = new Rectangle(0, this.m_itemSize * (i + 1) + (int)(Math.floor(i / rowSpace) * 15.0), this.m_itemSize, this.m_itemSize);
            this.drawLegend(g, rl, i, true);
            for (int j = 0; j < column; ++j) {
                int x = 0;
                int y = 0;
                if (rt == RackType.HorizontalShiftOnFirstLine) {
                    if (i % 2 == 0) {
                        x += this.m_itemSize / 2;
                    }
                } else if (rt == RackType.HorizontalShiftOnSecondLine) {
                    if (i % 2 != 0) {
                        x += this.m_itemSize / 2;
                    }
                } else if (rt == RackType.VerticalShiftOnFirstLine) {
                    if (j % 2 == 0) {
                        y += this.m_itemSize / 2;
                    }
                } else if (rt == RackType.VerticalShiftOnSecondLine && j % 2 != 0) {
                    y += this.m_itemSize / 2;
                }
                if (i == 0) {
                    rl = new Rectangle(this.m_itemSize * (j + 1) + (int)(Math.floor(j / columnSpace) * 15.0), 0, this.m_itemSize, this.m_itemSize);
                    this.drawLegend(g, rl, j, false);
                }
                Rectangle r = new Rectangle((x += j * this.m_itemSize + (int)(Math.floor(j / columnSpace) * 15.0)) + this.m_itemSize, (y += i * this.m_itemSize + (int)(Math.floor(i / rowSpace) * 15.0)) + this.m_itemSize, this.m_itemSize, this.m_itemSize);
                this.drawItem(g, r, this.m_rack.get(j, i), i, j);
            }
        }
    }

    private void drawLegend(Graphics2D g, Rectangle bounds, int value, boolean y) {
        String charToDraw = "";
        RackNamingType rnt = this.m_rack.getRackNamingType();
        if (y) {
            if (rnt == RackNamingType.BothLetter || rnt == RackNamingType.NumericOnXLetterOnY) {
                charToDraw = String.valueOf((char)(65 + value));
            } else if (rnt == RackNamingType.BothNumeric || rnt == RackNamingType.LetterOnXNumericOnY) {
                charToDraw = String.valueOf(value + 1);
            }
        } else if (rnt == RackNamingType.BothLetter || rnt == RackNamingType.LetterOnXNumericOnY) {
            charToDraw = String.valueOf((char)(65 + value));
        } else if (rnt == RackNamingType.BothNumeric || rnt == RackNamingType.NumericOnXLetterOnY) {
            charToDraw = String.valueOf(value + 1);
        }
        if (!charToDraw.equals("")) {
            UIHelper.drawCenterText(g, charToDraw, bounds, Color.BLACK);
        }
    }

    protected void drawItem(Graphics2D g, Rectangle bounds, RackItem item, int row, int column) {
        if (this.m_mode == RendererMode.Window) {
            this.drawRoundItem(g, bounds, item, row, column);
        } else if (this.m_mode == RendererMode.Select) {
            this.drawRoundSelectionItem(g, bounds, item, row, column);
        }
    }

    protected final void drawRoundSelectionItem(Graphics2D g, Rectangle bounds, RackItem item, int row, int column) {
        if (item != null) {
            bounds.height -= 2;
            bounds.width -= 2;
            Color borderColor = Color.LIGHT_GRAY;
            if (item.isEmpty()) {
                WorkqueueItemType type = this.m_wine.getRackItemQueue().getType(item);
                if (type == WorkqueueItemType.Add) {
                    this.fillRoundItem(g, bounds, ColorScheme.RK_WINE_ADDED);
                }
            } else if (item.getWine() == this.getSelectedWine()) {
                WorkqueueItemType type = this.m_wine.getRackItemQueue().getType(item);
                if (type == WorkqueueItemType.Add) {
                    this.fillRoundItem(g, bounds, ColorScheme.RK_WINE);
                } else if (type == WorkqueueItemType.Delete) {
                    this.drawCross(g, bounds, Color.LIGHT_GRAY);
                    borderColor = ColorScheme.RK_WINE;
                } else {
                    this.fillRoundItem(g, bounds, ColorScheme.RK_WINE);
                }
            } else {
                this.drawCross(g, bounds, Color.LIGHT_GRAY);
                borderColor = Color.ORANGE;
            }
            this.drawRoundBorder(g, bounds, borderColor);
            this.add(bounds, item);
        }
    }

    protected final void drawRoundItem(Graphics2D g, Rectangle bounds, RackItem item, int row, int column) {
        if (item != null) {
            bounds.height -= 2;
            bounds.width -= 2;
            if (item.isEmpty()) {
                this.drawRoundBorder(g, bounds, Color.GRAY);
            } else if (item.getWine() == this.getSelectedWine()) {
                this.fillRoundItem(g, bounds, this.m_legends.matches(item.getWine()));
                this.drawRoundBorder(g, bounds, Color.BLACK, 2);
            } else {
                this.fillRoundItem(g, bounds, this.m_legends.matches(item.getWine()));
                this.drawRoundBorder(g, bounds, Color.GRAY);
            }
            this.add(bounds, item);
        }
    }

    protected final void drawCross(Graphics2D g, Rectangle bounds, Color color) {
        Rectangle r = (Rectangle)bounds.clone();
        int w = bounds.width / 3;
        r.grow(- w, - w);
        Stroke old = g.getStroke();
        BasicStroke bs = new BasicStroke(2.0f);
        g.setStroke(bs);
        g.setColor(color);
        g.drawLine(r.x, r.y, r.x + r.width, r.y + r.height);
        g.drawLine(r.x, r.y + r.height, r.x + r.width, r.y);
        g.setStroke(old);
    }

    protected final void drawRoundBorder(Graphics2D g, Rectangle bounds, Color color) {
        g.setColor(color);
        g.drawOval(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    protected final void drawRoundBorder(Graphics2D g, Rectangle bounds, Color color, int width) {
        Stroke oldStroke = g.getStroke();
        g.setStroke(new BasicStroke(width));
        g.setColor(color);
        g.drawOval(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setStroke(oldStroke);
    }

    protected final void fillRoundItem(Graphics2D g, Rectangle bounds, Color color) {
        g.setColor(color);
        g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    protected final void fillRoundItem(Graphics2D g, Rectangle bounds, Color[] color) {
        g.setColor(color[0]);
        if (color[1] == null) {
            g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
        } else {
            g.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, 180, 180);
            g.setColor(color[1]);
            g.fillArc(bounds.x, bounds.y, bounds.width, bounds.height, 0, 180);
        }
    }

    public ICellarRenderer create() {
        return new DefaultRenderer(this.m_name);
    }

    protected final RendererMode getMode() {
        return this.m_mode;
    }

    protected final LegendCollection getLegends() {
        return this.m_legends;
    }

    protected final Wine getSelectedWine() {
        return this.m_wine;
    }

    protected void onBeginDraw() {
    }

    protected void onEndDraw() {
    }

    protected final Rack getRack() {
        return this.m_rack;
    }

    protected final Rectangle getBounds() {
        return this.m_bounds;
    }

    private void determineItemSize(Rectangle bounds) {
        double iw;
        double ih;
        int tmpRowInterval = this.m_rack.getRowSpace();
        int tmpColumnInterval = this.m_rack.getColumnSpace();
        if (tmpRowInterval > this.m_rack.getRows()) {
            tmpRowInterval = this.m_rack.getRows();
        }
        if (tmpColumnInterval > this.m_rack.getColumns()) {
            tmpColumnInterval = this.m_rack.getColumns();
        }
        double spaceHeight = 0.0;
        spaceHeight = Math.floor(this.m_rack.getRows() / tmpRowInterval) * 15.0;
        double height = (double)bounds.height - spaceHeight;
        int itemHeight = (int)Math.floor(height / (double)(this.m_rack.getRows() + 1));
        if ((itemHeight -= (int)Math.ceil(ih = (double)(itemHeight / 2 / (this.m_rack.getRows() + 1) + 1))) > 45) {
            itemHeight = 45;
        }
        double spaceWidth = 0.0;
        spaceWidth = Math.floor(this.m_rack.getColumns() / tmpColumnInterval) * 15.0;
        double width = (double)bounds.width - spaceWidth;
        int itemWidth = (int)Math.floor(width / (double)(this.m_rack.getColumns() + 1));
        if ((itemWidth -= (int)Math.ceil(iw = (double)(itemWidth / 2 / (this.m_rack.getColumns() + 1) + 1))) > 45) {
            itemWidth = 45;
        }
        this.m_itemSize = Math.min(itemWidth, itemHeight);
    }
}

