/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.CellarObject
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.ICellarObjectListener
 *  opencellar.framework.ObjectState
 *  opencellar.framework.PurchaseSales
 *  opencellar.framework.PurchaseSalesComparer
 *  opencellar.framework.PurchaseSalesWorkqueue
 *  opencellar.framework.PurchaseSalesWorkqueueItem
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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import opencellar.application.utils;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ICellarObjectListener;
import opencellar.framework.ObjectState;
import opencellar.framework.PurchaseSales;
import opencellar.framework.PurchaseSalesComparer;
import opencellar.framework.PurchaseSalesWorkqueue;
import opencellar.framework.PurchaseSalesWorkqueueItem;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;
import opencellar.ui.CustomEventDispatcher;
import opencellar.ui.UIHelper;

public class TextPurchaseSalesViewer
extends JComponent
implements ICellarObjectListener,
MouseListener {
    private Wine m_wine = null;
    static final int PURCHASE_CONSUME_HEIGHT = 65;
    static final int PURCHASE_HEIGHT = 45;
    static final int CONSUME_HEIGHT = 45;
    static final int LINE_WIDTH = 60;
    static final int PADDING = 10;
    private Image buffer;
    private String m_itemConsumed = "";
    private String m_itemTotal = "";
    private String m_itemBought = "";
    private CustomEventDispatcher m_dispatcher = null;
    private Font titleFont = UIHelper.getBoldDefaultFont();
    private Font defaultFont = UIHelper.getDefaultFont();
    private Font deletedFont = UIHelper.getStrikeOutFont();
    private DecimalFormat m_df = new DecimalFormat("#.00");
    DateFormat m_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Hashtable m_purchaseRectangle = new Hashtable();
    private PurchaseSales m_activeSales = null;
    private Point lastPoint = null;

    public TextPurchaseSalesViewer(Wine wine) {
        if (wine == null) {
            throw new IllegalArgumentException("wine == null");
        }
        this.m_wine = wine;
        this.registerListeners();
        this.calcHeight();
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
        if (propertyName.equals("PurchaseSales")) {
            this.calcHeight();
        } else if (propertyName.equals("PurchaseSalesWorkqueue")) {
            this.calcHeight();
        }
    }

    private void calcHeight() {
        int i;
        int newHeight = 0;
        this.m_wine.getPurchasesSales().sort((Comparator)new PurchaseSalesComparer());
        for (i = 0; i < this.m_wine.getPurchaseSalesQueue().size(); ++i) {
            if (this.m_wine.getPurchaseSalesQueue().get(i).getType() != WorkqueueItemType.Add || this.m_wine.getPurchasesSales().contains((CellarObject)this.m_wine.getPurchaseSalesQueue().get(i).getItem())) continue;
            newHeight += this.getItemHeight(this.m_wine.getPurchaseSalesQueue().get(i).getItem());
        }
        for (i = 0; i < this.m_wine.getPurchasesSales().size(); ++i) {
            newHeight += this.getItemHeight((PurchaseSales)this.m_wine.getPurchasesSales().get(i));
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

    private int getItemHeight(PurchaseSales ps) {
        int ret = 0;
        boolean sold = ps.isBought();
        boolean consume = ps.isConsumed();
        ret = sold && consume ? 65 : (!sold && consume ? 45 : 45);
        return ret;
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.update(g);
    }

    public void update(Graphics g) {
        if (this.buffer == null) {
            int i;
            this.buffer = this.createImage(this.getWidth(), this.getHeight());
            this.m_purchaseRectangle.clear();
            Graphics2D g2d = (Graphics2D)this.buffer.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Rectangle dest = new Rectangle(0, 0, 0, 0);
            int top = 0;
            for (i = 0; i < this.m_wine.getPurchaseSalesQueue().size(); ++i) {
                if (this.m_wine.getPurchaseSalesQueue().get(i).getType() != WorkqueueItemType.Add || this.m_wine.getPurchasesSales().contains((CellarObject)this.m_wine.getPurchaseSalesQueue().get(i).getItem())) continue;
                dest = new Rectangle(0, top, this.getWidth(), this.getItemHeight(this.m_wine.getPurchaseSalesQueue().get(i).getItem()));
                this.drawItem(g2d, dest, this.m_wine.getPurchaseSalesQueue().get(i).getItem(), true);
                top += dest.height;
            }
            for (i = 0; i < this.m_wine.getPurchasesSales().size(); ++i) {
                dest = new Rectangle(0, top, this.getWidth(), this.getItemHeight((PurchaseSales)this.m_wine.getPurchasesSales().get(i)));
                this.drawItem(g2d, dest, (PurchaseSales)this.m_wine.getPurchasesSales().get(i), false);
                top += dest.height;
            }
            g2d.dispose();
        }
        g.drawImage(this.buffer, 0, 0, this);
    }

    public final void setStringResources(String consumed, String bought, String total) {
        this.m_itemTotal = total;
        this.m_itemConsumed = consumed;
        this.m_itemBought = bought;
    }

    public final void setDispatcher(CustomEventDispatcher dispatcher) {
        this.m_dispatcher = dispatcher;
    }

    private void drawItem(Graphics2D g, Rectangle r, PurchaseSales item, boolean fromQueue) {
        Font f = null;
        Color c = Color.BLACK;
        if (fromQueue) {
            f = this.defaultFont;
            c = Color.ORANGE;
        } else {
            WorkqueueItemType type = this.m_wine.getPurchaseSalesQueue().getType(item);
            if (type == WorkqueueItemType.Delete) {
                f = this.deletedFont;
                c = Color.RED;
            } else if (type == WorkqueueItemType.Add) {
                f = this.defaultFont;
                c = Color.ORANGE;
            } else {
                c = Color.BLACK;
                f = this.defaultFont;
            }
        }
        if (item.getState() == ObjectState.None) {
            // empty if block
        }
        Rectangle rect = new Rectangle(r.x + 10, r.y, r.width - 10, 30);
        this.drawDate(g, rect, item.getDate(), f, c);
        Rectangle bounds = new Rectangle(r.x + 10, r.y + 20, r.width - 20, r.height - 20);
        boolean sold = item.isBought();
        boolean consume = item.isConsumed();
        if (sold && consume) {
            bounds.height -= 15;
            UIHelper.drawTextMiddleLeft(g, utils.format(this.m_itemBought, String.valueOf(item.getPurchasedBottles()), this.m_df.format(item.getUnitPrice()), UIHelper.getCurrencySymbol()), bounds, Color.BLACK);
            UIHelper.drawTextMiddleRight(g, utils.format(this.m_itemTotal, this.m_df.format(item.getAmount()), UIHelper.getCurrencySymbol()), bounds, Color.BLACK);
            bounds.height -= 15;
            bounds.y += 25;
            UIHelper.drawTextMiddleLeft(g, utils.format(this.m_itemConsumed, String.valueOf(item.getConsumedBottles()), this.m_df.format(item.getUnitPrice())), bounds, Color.BLACK);
        } else if (!sold && consume) {
            UIHelper.drawTextMiddleLeft(g, utils.format(this.m_itemConsumed, String.valueOf(item.getConsumedBottles()), this.m_df.format(item.getUnitPrice())), bounds, Color.BLACK);
        } else {
            UIHelper.drawTextMiddleLeft(g, utils.format(this.m_itemBought, String.valueOf(item.getPurchasedBottles()), this.m_df.format(item.getUnitPrice()), UIHelper.getCurrencySymbol()), bounds, Color.BLACK);
            UIHelper.drawTextMiddleRight(g, utils.format(this.m_itemTotal, this.m_df.format(item.getAmount()), UIHelper.getCurrencySymbol()), bounds, Color.BLACK);
        }
        g.setColor(Color.ORANGE);
        g.drawLine(r.x + 10, r.y + (r.height - 1), r.x + r.width, r.y + (r.height - 1));
        this.m_purchaseRectangle.put(r, item);
    }

    private void drawDate(Graphics2D g, Rectangle bounds, Date date, Font font, Color foreground) {
        g.setFont(font);
        UIHelper.drawTextMiddleLeft(g, this.m_dateFormat.format(date), bounds, foreground);
    }

    public PurchaseSales getPurchaseSales() {
        return this.m_activeSales;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        this.lastPoint = e.getPoint();
        this.handleMouse(e);
    }

    public final Point getLastPoint() {
        return this.lastPoint;
    }

    private void handleMouse(MouseEvent e) {
        PurchaseSales sales;
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() > 1) {
            PurchaseSales sales2 = this.getAt(e.getPoint());
            if (sales2 != null) {
                this.m_dispatcher.setSource((Object)sales2);
                this.m_dispatcher.notify(1);
            }
        } else if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1 && (e.getModifiers() & 2) != 0) {
            PurchaseSales sales3 = this.getAt(e.getPoint());
            if (sales3 != null) {
                this.m_dispatcher.setSource((Object)sales3);
                this.m_dispatcher.notify(2);
            }
        } else if (e.isPopupTrigger() && (sales = this.getAt(e.getPoint())) != null) {
            this.m_dispatcher.setSource((Object)sales);
            this.m_dispatcher.notify(2);
        }
    }

    private PurchaseSales getAt(Point pt) {
        Enumeration keys = this.m_purchaseRectangle.keys();
        while (keys.hasMoreElements()) {
            Rectangle r = (Rectangle)keys.nextElement();
            if (!r.contains(pt)) continue;
            Object item = this.m_purchaseRectangle.get(r);
            if (item instanceof PurchaseSales) {
                return (PurchaseSales)item;
            }
            return null;
        }
        return null;
    }

    public void mouseReleased(MouseEvent e) {
        PurchaseSales sales;
        if (e.isPopupTrigger() && (sales = this.getAt(e.getPoint())) != null) {
            this.m_dispatcher.setSource((Object)sales);
            this.m_dispatcher.notify(2);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}

