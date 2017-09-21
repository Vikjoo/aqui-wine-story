/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.CellarObject
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.ICellarObjectListener
 *  opencellar.framework.Rack
 *  opencellar.framework.RackItem
 *  opencellar.framework.RackItemCollection
 *  opencellar.framework.RackItemComparer
 *  opencellar.framework.RackItemWorkqueue
 *  opencellar.framework.RackItemWorkqueueItem
 *  opencellar.framework.Wine
 *  opencellar.framework.WorkqueueItemType
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ICellarObjectListener;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemCollection;
import opencellar.framework.RackItemComparer;
import opencellar.framework.RackItemWorkqueue;
import opencellar.framework.RackItemWorkqueueItem;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;
import opencellar.ui.UIHelper;

public class TextRackViewer
extends JComponent
implements ICellarObjectListener,
MouseListener {
    private Image m_placeBottles;
    private Image m_viewRack;
    private Wine m_wine = null;
    private ArrayList m_itemsToDraw = new ArrayList();
    static final int TITLE_HEIGHT = 40;
    static final int RACK_TITLE_HEIGHT = 30;
    static final int ITEMS_LINE_HEIGHT = 30;
    static final int ITEMS_BY_LINE = 11;
    static final int ITEM_WIDTH = 40;
    private Hashtable m_rackRectangle = new Hashtable();
    static final int PADDING = 10;
    private Image buffer;
    private Font titleFont = UIHelper.getBoldDefaultFont();
    private Font defaultFont = UIHelper.getDefaultFont();
    private Font deletedFont = UIHelper.getStrikeOutFont();
    private String m_placeBottle = "";
    private Rack m_activeRack = null;

    public TextRackViewer(Wine wine) {
        if (wine == null) {
            throw new IllegalArgumentException("wine == null");
        }
        this.m_wine = wine;
        this.registerListeners();
        this.loadImages();
        this.calcHeight();
    }

    private void loadImages() {
        try {
            this.m_placeBottles = ImageIO.read(TextRackViewer.class.getResourceAsStream("/opencellar/rc/misc/viewallrack.gif"));
        }
        catch (Exception ex) {
            // empty catch block
        }
        try {
            this.m_viewRack = ImageIO.read(TextRackViewer.class.getResourceAsStream("/opencellar/rc/misc/viewrack.gif"));
        }
        catch (Exception ex) {
            // empty catch block
        }
    }

    public final Wine getWine() {
        return this.m_wine;
    }

    public final void close() {
        this.removeListeners();
    }

    private void registerListeners() {
        this.m_wine.addListener((ICellarObjectListener)this);
        this.addMouseListener(this);
    }

    private void removeListeners() {
        this.m_wine.removeListener((ICellarObjectListener)this);
    }

    public void onStateChanged(CellarObject source) {
    }

    public void onPropertyChanged(CellarObject source, String propertyName) {
        if (propertyName.equals("RackItems")) {
            this.calcHeight();
        } else if (propertyName.equals("RackItemWorkqueue")) {
            this.calcHeight();
        }
    }

    private void calcHeight() {
        int r;
        int i;
        int newHeight = 40;
        this.m_wine.getRackItems().sort();
        Rack rack = null;
        ArrayList<Rack> lstRack = new ArrayList<Rack>();
        for (i = 0; i < this.m_wine.getRackItems().size(); ++i) {
            if (this.m_wine.getRackItems().get(i).getParent() == rack) continue;
            newHeight += 30;
            rack = this.m_wine.getRackItems().get(i).getParent();
            lstRack.add(rack);
            r = (int)(Math.ceil((double)(this.getItemsByRack(rack) + this.m_wine.getRackItemQueue().getUnpresents(rack)) / 11.0) * 30.0);
            newHeight += r;
        }
        this.m_itemsToDraw.clear();
        for (i = 0; i < this.m_wine.getRackItemQueue().size(); ++i) {
            if (this.m_wine.getRackItemQueue().get(i).getType() != WorkqueueItemType.Add || lstRack.contains((Object)this.m_wine.getRackItemQueue().get(i).getItem().getParent()) || this.m_wine.getRackItems().contains(this.m_wine.getRackItemQueue().get(i).getItem())) continue;
            this.m_itemsToDraw.add(this.m_wine.getRackItemQueue().get(i).getItem());
        }
        Collections.sort(this.m_itemsToDraw, new RackItemComparer());
        rack = null;
        for (i = 0; i < this.m_itemsToDraw.size(); ++i) {
            if (((RackItem)this.m_itemsToDraw.get(i)).getParent() == rack) continue;
            newHeight += 30;
            rack = ((RackItem)this.m_itemsToDraw.get(i)).getParent();
            r = (int)(Math.ceil((double)this.getItemsByRack2(rack, this.m_itemsToDraw) / 11.0) * 30.0);
            newHeight += r;
        }
        this.buffer = null;
        if (newHeight != this.getHeight()) {
            Dimension dim = new Dimension(this.getWidth(), newHeight);
            super.setMinimumSize(dim);
            super.setPreferredSize(dim);
            super.setSize(dim);
        } else {
            this.repaint();
        }
    }

    private int getItemsByRack2(Rack rack, ArrayList al) {
        int num1 = 0;
        for (int i = 0; i < al.size(); ++i) {
            if (((RackItem)al.get(i)).getParent() != rack) continue;
            ++num1;
        }
        return num1;
    }

    private int getItemsByRack(Rack rack) {
        int num1 = 0;
        for (int i = 0; i < this.m_wine.getRackItems().size(); ++i) {
            if (this.m_wine.getRackItems().get(i).getParent() != rack) continue;
            ++num1;
        }
        return num1;
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.update(g);
    }

    public void update(Graphics g) {
        if (this.buffer == null) {
            int i;
            this.buffer = this.createImage(this.getWidth(), this.getHeight());
            this.m_rackRectangle.clear();
            Graphics2D g2d = (Graphics2D)this.buffer.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.ORANGE);
            g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
            Rectangle title = new Rectangle(0, 0, this.getWidth(), 40);
            this.drawTitle(g2d, title);
            int y = 40;
            int x = 10;
            int items = 1;
            Rectangle dest = new Rectangle(0, 0, 1, 1);
            Rack rack = null;
            ArrayList<Rack> lstRack = new ArrayList<Rack>();
            for (i = 0; i < this.m_wine.getRackItems().size(); ++i) {
                if (this.m_wine.getRackItems().get(i).getParent() != rack) {
                    if (rack != null) {
                        CellarObjectCollection alT = this.m_wine.getRackItemQueue().getAt(rack);
                        for (int num = 0; num < alT.size(); ++num) {
                            dest = new Rectangle(x, y, 40, 30);
                            this.drawItem(g2d, dest, (RackItem)alT.get(num));
                            x += 40;
                            if (++items <= 11) continue;
                            items = 1;
                            x = 10;
                            y += 30;
                        }
                        alT = null;
                    }
                    if (x != 10) {
                        y += 30;
                    }
                    dest = new Rectangle(0, y, this.getWidth(), 30);
                    this.drawRackName(g2d, dest, this.m_wine.getRackItems().get(i).getParent());
                    this.m_rackRectangle.put(dest, this.m_wine.getRackItems().get(i).getParent());
                    y += 30;
                    x = 10;
                    items = 1;
                    rack = this.m_wine.getRackItems().get(i).getParent();
                    lstRack.add(rack);
                }
                dest = new Rectangle(x, y, 40, 30);
                this.drawItem(g2d, dest, this.m_wine.getRackItems().get(i));
                x += 40;
                if (++items <= 11) continue;
                items = 1;
                x = 10;
                y += 30;
            }
            if (rack != null) {
                CellarObjectCollection alT = this.m_wine.getRackItemQueue().getAt(rack);
                for (int num = 0; num < alT.size(); ++num) {
                    dest = new Rectangle(x, y, 40, 30);
                    this.drawItem(g2d, dest, (RackItem)alT.get(num));
                    x += 40;
                    if (++items <= 11) continue;
                    items = 1;
                    x = 10;
                    y += 30;
                }
                alT = null;
            }
            if (x != 10) {
                y += 30;
            }
            x = 10;
            rack = null;
            items = 1;
            for (i = 0; i < this.m_itemsToDraw.size(); ++i) {
                if (((RackItem)this.m_itemsToDraw.get(i)).getParent() != rack) {
                    if (x != 10) {
                        y += 30;
                    }
                    dest = new Rectangle(0, y, this.getWidth(), 30);
                    this.drawRackName(g2d, dest, ((RackItem)this.m_itemsToDraw.get(i)).getParent());
                    this.m_rackRectangle.put(dest, ((RackItem)this.m_itemsToDraw.get(i)).getParent());
                    y += 30;
                    x = 10;
                    items = 1;
                    rack = ((RackItem)this.m_itemsToDraw.get(i)).getParent();
                    lstRack.add(rack);
                }
                dest = new Rectangle(x, y, 40, 30);
                this.drawItem(g2d, dest, (RackItem)this.m_itemsToDraw.get(i));
                x += 40;
                if (++items <= 11) continue;
                items = 1;
                x = 10;
                y += 30;
            }
            g2d.dispose();
        }
        g.drawImage(this.buffer, 0, 0, this);
    }

    private void drawRackName(Graphics2D g, Rectangle r, Rack rack) {
        if (this.m_viewRack != null) {
            g.drawImage(this.m_viewRack, r.x + 4, r.y + 6, null);
        }
        r.x += 25;
        g.setFont(this.titleFont);
        UIHelper.drawTextMiddleLeft(g, rack.getName(), r, this.getForeground());
        g.setColor(Color.ORANGE);
        r.x -= 25;
        g.drawLine(r.x, r.y + 25, r.x + r.width, r.y + 25);
    }

    private void drawTitle(Graphics2D g, Rectangle r) {
        if (this.m_placeBottles != null) {
            g.drawImage(this.m_placeBottles, r.x + 4, r.y + 11, null);
        }
        g.setFont(this.titleFont);
        r.x += 25;
        UIHelper.drawTextMiddleLeft(g, this.m_placeBottle, r, this.getForeground());
    }

    public final void setPlaceBottlesString(String s) {
        this.m_placeBottle = s;
    }

    private void drawItem(Graphics g, Rectangle r, RackItem item) {
        WorkqueueItemType type = this.m_wine.getRackItemQueue().getAt(item);
        if (type == WorkqueueItemType.Delete) {
            g.setFont(this.deletedFont);
            UIHelper.drawTextMiddleLeft(g, item.getLegend(), r, Color.RED);
        } else if (type == WorkqueueItemType.Add) {
            if (this.m_wine.getRackItems().contains(item)) {
                g.setFont(this.defaultFont);
                UIHelper.drawTextMiddleLeft(g, item.getLegend(), r, this.getForeground());
            } else {
                g.setFont(this.deletedFont);
                UIHelper.drawTextMiddleLeft(g, item.getLegend(), r, Color.ORANGE);
            }
        } else {
            g.setFont(this.defaultFont);
            UIHelper.drawTextMiddleLeft(g, item.getLegend(), r, this.getForeground());
        }
    }

    public Rack getRack() {
        return this.m_activeRack;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        Enumeration keys = this.m_rackRectangle.keys();
        while (keys.hasMoreElements()) {
            Rectangle r = (Rectangle)keys.nextElement();
            if (!r.contains(e.getPoint())) continue;
            Object item = this.m_rackRectangle.get(r);
            if (item instanceof Rack) {
                this.m_activeRack = (Rack)item;
                break;
            }
            this.m_activeRack = null;
            break;
        }
        super.firePropertyChange("rack", 0, 1);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}

