/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Rack
 *  opencellar.framework.RackBuilder
 *  opencellar.framework.RackItem
 *  opencellar.framework.RackNamingType
 *  opencellar.framework.RackType
 */
package opencellar.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JComponent;
import opencellar.framework.Rack;
import opencellar.framework.RackBuilder;
import opencellar.framework.RackItem;
import opencellar.framework.RackNamingType;
import opencellar.framework.RackType;

public final class RackBuilderComponent
extends JComponent
implements ComponentListener,
MouseListener {
    private RackBuilder m_builder;
    private int m_row = 1;
    private int m_column = 1;
    private int m_rowSpace = 1;
    private int m_columnSpace = 1;
    private RackType m_rackType = RackType.Default;
    private RackNamingType m_rackNamingType = RackNamingType.None;
    private Image buffer;
    private ArrayList m_hiddenItems = new ArrayList();
    private Hashtable m_itemsRectangle = new Hashtable();
    private int m_itemSize = -1;
    private final int pixelSpace = 15;
    private final int maxSize = 45;

    public RackBuilderComponent() {
        this.addComponentListener(this);
        this.addMouseListener(this);
    }

    public int getRow() {
        return this.m_row;
    }

    public int getColumn() {
        return this.m_column;
    }

    public void setBuilder(RackBuilder builder) {
        this.m_builder = builder;
        this.setLocalVariables();
        this.repaint();
    }

    public void bind() {
        int i;
        this.m_builder.setColumnSize(this.m_column);
        this.m_builder.setRowSize(this.m_row);
        this.m_builder.setSpace(this.m_columnSpace, this.m_rowSpace);
        this.m_builder.setRackNamingType(this.m_rackNamingType);
        this.m_builder.setRackType(this.m_rackType);
        this.m_builder.save();
        for (i = 0; i < this.m_hiddenItems.size(); ++i) {
            String parse = this.m_hiddenItems.get(i).toString();
            int column = Integer.parseInt(parse.substring(0, parse.indexOf("#")));
            int row = Integer.parseInt(parse.substring(parse.indexOf("#") + 1));
            this.m_builder.removeItem(row, column);
        }
        for (i = 0; i < this.m_row; ++i) {
            for (int j = 0; j < this.m_column; ++j) {
                if (this.m_builder.getRack().exists(j, i) || this.m_hiddenItems.contains(String.valueOf(j) + "#" + String.valueOf(i))) continue;
                RackItem ri = this.m_builder.createItem(i, j);
                ri.save();
            }
        }
        this.m_builder.notifyUpdate();
        this.setBuilder(this.m_builder);
    }

    public boolean setRow(int row) {
        if (row < 1) {
            return false;
        }
        if (this.m_row == row) {
            return true;
        }
        if (this.m_builder.canResizeRow(row)) {
            this.m_row = row;
            this.determineItemSize();
            this.repaint();
            return true;
        }
        return false;
    }

    public boolean setColumn(int column) {
        if (column < 1) {
            return false;
        }
        if (this.m_column == column) {
            return true;
        }
        if (this.m_builder.canResizeColumn(column)) {
            this.m_column = column;
            this.determineItemSize();
            this.repaint();
            return true;
        }
        return false;
    }

    public boolean setRowSpace(int rowSpace) {
        if (rowSpace < 1) {
            return false;
        }
        if (this.m_rowSpace == rowSpace) {
            return true;
        }
        this.m_rowSpace = rowSpace;
        this.determineItemSize();
        this.repaint();
        return true;
    }

    public boolean setColumnSpace(int columnSpace) {
        if (columnSpace < 1) {
            return false;
        }
        if (this.m_columnSpace == columnSpace) {
            return true;
        }
        this.m_columnSpace = columnSpace;
        this.determineItemSize();
        this.repaint();
        return true;
    }

    public void setRackType(RackType rt) {
        if (this.m_rackType != rt) {
            this.m_rackType = rt;
            this.determineItemSize();
            this.repaint();
        }
    }

    public void setRackNamingType(RackNamingType rt) {
        if (this.m_rackNamingType != rt) {
            this.m_rackNamingType = rt;
            this.determineItemSize();
            this.repaint();
        }
    }

    private void setLocalVariables() {
        this.m_itemsRectangle.clear();
        this.m_hiddenItems.clear();
        this.m_column = this.m_builder.getRack().getColumns();
        this.m_columnSpace = this.m_builder.getRack().getColumnSpace();
        this.m_row = this.m_builder.getRack().getRows();
        this.m_rowSpace = this.m_builder.getRack().getRowSpace();
        this.m_rackType = this.m_builder.getRack().getRackType();
        this.m_rackNamingType = this.m_builder.getRack().getRackNamingType();
        for (int i = 0; i < this.m_builder.getRack().getRows(); ++i) {
            for (int j = 0; j < this.m_builder.getRack().getColumns(); ++j) {
                if (this.m_builder.getRack().get(j, i) != null) continue;
                this.m_hiddenItems.add(String.valueOf(j) + "#" + String.valueOf(i));
            }
        }
        this.determineItemSize();
    }

    public void paint(Graphics g) {
        this.update(g);
    }

    public void update(Graphics g) {
        g.drawImage(this.draw(), 0, 0, this);
    }

    private Image draw() {
        if (this.buffer != null) {
            this.buffer.flush();
            this.buffer = null;
        }
        this.buffer = this.createImage(this.getWidth(), this.getHeight());
        Graphics2D g = (Graphics2D)this.buffer.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < this.m_row; ++i) {
            Rectangle rl = new Rectangle(0, this.m_itemSize * (i + 1) + (int)(Math.floor(i / this.m_rowSpace) * 15.0), this.m_itemSize, this.m_itemSize);
            this.drawLegend(g, rl, i, true);
            for (int j = 0; j < this.m_column; ++j) {
                int x = 0;
                int y = 0;
                if (this.m_rackType == RackType.HorizontalShiftOnFirstLine) {
                    if (i % 2 == 0) {
                        x += this.m_itemSize / 2;
                    }
                } else if (this.m_rackType == RackType.HorizontalShiftOnSecondLine) {
                    if (i % 2 != 0) {
                        x += this.m_itemSize / 2;
                    }
                } else if (this.m_rackType == RackType.VerticalShiftOnFirstLine) {
                    if (j % 2 == 0) {
                        y += this.m_itemSize / 2;
                    }
                } else if (this.m_rackType == RackType.VerticalShiftOnSecondLine && j % 2 != 0) {
                    y += this.m_itemSize / 2;
                }
                if (i == 0) {
                    rl = new Rectangle(this.m_itemSize * (j + 1) + (int)(Math.floor(j / this.m_columnSpace) * 15.0), 0, this.m_itemSize, this.m_itemSize);
                    this.drawLegend(g, rl, j, false);
                }
                Rectangle r = new Rectangle((x += j * this.m_itemSize + (int)(Math.floor(j / this.m_columnSpace) * 15.0)) + this.m_itemSize, (y += i * this.m_itemSize + (int)(Math.floor(i / this.m_rowSpace) * 15.0)) + this.m_itemSize, this.m_itemSize, this.m_itemSize);
                this.drawItem(g, r, i, j);
                this.addItem(r, i, j);
            }
        }
        return this.buffer;
    }

    private void addItem(Rectangle bounds, int row, int column) {
        RackItem item = this.m_builder.getRack().get(column, row);
        if ((item != null && item.isEmpty()) | item == null) {
            String key = String.valueOf(column) + "#" + String.valueOf(row);
            if (this.m_itemsRectangle.containsKey(key)) {
                this.m_itemsRectangle.remove(key);
            }
            this.m_itemsRectangle.put(key, bounds);
        }
    }

    private void drawLegend(Graphics2D g, Rectangle bounds, int value, boolean y) {
        String charToDraw = "";
        if (y) {
            if (this.m_rackNamingType == RackNamingType.BothLetter || this.m_rackNamingType == RackNamingType.NumericOnXLetterOnY) {
                charToDraw = String.valueOf((char)(65 + value));
            } else if (this.m_rackNamingType == RackNamingType.BothNumeric || this.m_rackNamingType == RackNamingType.LetterOnXNumericOnY) {
                charToDraw = String.valueOf(value + 1);
            }
        } else if (this.m_rackNamingType == RackNamingType.BothLetter || this.m_rackNamingType == RackNamingType.LetterOnXNumericOnY) {
            charToDraw = String.valueOf((char)(65 + value));
        } else if (this.m_rackNamingType == RackNamingType.BothNumeric || this.m_rackNamingType == RackNamingType.NumericOnXLetterOnY) {
            charToDraw = String.valueOf(value + 1);
        }
        if (!charToDraw.equals("")) {
            int ascent = g.getFontMetrics().getMaxAscent();
            int descent = g.getFontMetrics().getMaxDescent();
            int yy = bounds.y + (bounds.height / 2 - descent / 2 + ascent / 2);
            int ww = g.getFontMetrics().stringWidth(charToDraw);
            g.setColor(Color.BLACK);
            g.drawString(charToDraw, bounds.x + bounds.width / 2 - ww / 2, yy);
        }
    }

    private void drawItem(Graphics2D g, Rectangle bounds, int row, int column) {
        bounds.height -= 2;
        bounds.width -= 2;
        RackItem item = this.m_builder.getRack().get(column, row);
        if (item != null) {
            if (!item.isEmpty()) {
                g.setColor(Color.GREEN);
                g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
            } else if (this.m_hiddenItems.contains(String.valueOf(column) + "#" + String.valueOf(row))) {
                this.drawCross(g, bounds, Color.ORANGE);
            }
        } else if (this.m_hiddenItems.contains(String.valueOf(column) + "#" + String.valueOf(row))) {
            this.drawCross(g, bounds, Color.ORANGE);
        }
        this.drawRoundBorder(g, bounds, Color.LIGHT_GRAY);
    }

    private void drawCross(Graphics2D g, Rectangle bounds, Color color) {
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

    private void drawRoundBorder(Graphics2D g, Rectangle bounds, Color color) {
        g.setColor(color);
        g.drawOval(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    private void determineItemSize() {
        double iw;
        double ih;
        int tmpRowInterval = this.m_rowSpace;
        int tmpColumnInterval = this.m_columnSpace;
        if (tmpRowInterval > this.m_row) {
            tmpRowInterval = this.m_row;
        }
        if (tmpColumnInterval > this.m_column) {
            tmpColumnInterval = this.m_column;
        }
        double spaceHeight = 0.0;
        spaceHeight = Math.floor(this.m_row / tmpRowInterval) * 15.0;
        double height = (double)this.getHeight() - spaceHeight;
        int itemHeight = (int)Math.floor(height / (double)(this.m_row + 1));
        if ((itemHeight -= (int)Math.ceil(ih = (double)(itemHeight / 2 / (this.m_row + 1) + 1))) > 45) {
            itemHeight = 45;
        }
        double spaceWidth = 0.0;
        spaceWidth = Math.floor(this.m_column / tmpColumnInterval) * 15.0;
        double width = (double)this.getWidth() - spaceWidth;
        int itemWidth = (int)Math.floor(width / (double)(this.m_column + 1));
        if ((itemWidth -= (int)Math.ceil(iw = (double)(itemWidth / 2 / (this.m_column + 1) + 1))) > 45) {
            itemWidth = 45;
        }
        this.m_itemSize = Math.min(itemWidth, itemHeight);
    }

    public void componentResized(ComponentEvent e) {
        this.m_itemsRectangle.clear();
        this.determineItemSize();
        this.repaint();
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (this.isEnabled()) {
            this.handleClick(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private void handleClick(MouseEvent e) {
        Enumeration k = this.m_itemsRectangle.keys();
        while (k.hasMoreElements()) {
            String key = (String)k.nextElement();
            Rectangle bounds = (Rectangle)this.m_itemsRectangle.get(key);
            if (!bounds.contains(e.getPoint())) continue;
            if (this.m_hiddenItems.contains(key)) {
                this.m_hiddenItems.remove(key);
            } else {
                this.m_hiddenItems.add(key);
            }
            this.repaint(bounds);
            break;
        }
    }
}

