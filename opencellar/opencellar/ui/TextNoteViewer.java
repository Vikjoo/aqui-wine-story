/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.CellarObject
 *  opencellar.framework.CellarObjectCollection
 *  opencellar.framework.ICellarObjectListener
 *  opencellar.framework.Note
 *  opencellar.framework.NoteComparer
 *  opencellar.framework.NoteWorkqueue
 *  opencellar.framework.NoteWorkqueueItem
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
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.ICellarObjectListener;
import opencellar.framework.Note;
import opencellar.framework.NoteComparer;
import opencellar.framework.NoteWorkqueue;
import opencellar.framework.NoteWorkqueueItem;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItemType;
import opencellar.ui.CustomEventDispatcher;
import opencellar.ui.UIHelper;

public class TextNoteViewer
extends JComponent
implements ICellarObjectListener,
MouseListener {
    private Image m_starOn;
    final String RC_NAMESPACE = "/opencellar/rc/misc/";
    private Wine m_wine = null;
    static final int DEFAULT_HEIGHT = 45;
    static final int PADDING = 10;
    private Image buffer;
    private CustomEventDispatcher m_dispatcher = null;
    private Font titleFont = UIHelper.getBoldDefaultFont();
    private Font defaultFont = UIHelper.getDefaultFont();
    private Font deletedFont = UIHelper.getStrikeOutFont();
    private DecimalFormat m_df = new DecimalFormat("#.00");
    DateFormat m_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Hashtable m_noteRectangle = new Hashtable();
    private Point lastPoint = null;

    public TextNoteViewer(Wine wine) {
        if (wine == null) {
            throw new IllegalArgumentException("wine == null");
        }
        this.m_wine = wine;
        this.registerListeners();
        this.calcHeight();
        this.loadRC();
    }

    private void loadRC() {
        this.m_starOn = UIHelper.getImage("/opencellar/rc/misc/staron.gif");
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
        if (propertyName.equals("Notes")) {
            this.calcHeight();
        } else if (propertyName.equals("NoteWorkqueue")) {
            this.calcHeight();
        }
    }

    private void calcHeight() {
        int i;
        int newHeight = 0;
        this.m_wine.getNotes().sort((Comparator)new NoteComparer());
        for (i = 0; i < this.m_wine.getNoteQueue().size(); ++i) {
            if (this.m_wine.getNoteQueue().get(i).getType() != WorkqueueItemType.Add || this.m_wine.getNotes().contains((CellarObject)this.m_wine.getNoteQueue().get(i).getItem())) continue;
            newHeight += this.getItemHeight(this.m_wine.getNoteQueue().get(i).getItem());
        }
        for (i = 0; i < this.m_wine.getNotes().size(); ++i) {
            newHeight += this.getItemHeight((Note)this.m_wine.getNotes().get(i));
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

    private int getItemHeight(Note note) {
        return 45;
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.update(g);
    }

    public void update(Graphics g) {
        if (this.buffer == null) {
            int i;
            this.buffer = this.createImage(this.getWidth(), this.getHeight());
            this.m_noteRectangle.clear();
            Graphics2D g2d = (Graphics2D)this.buffer.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Rectangle dest = new Rectangle(0, 0, 0, 0);
            int top = 0;
            for (i = 0; i < this.m_wine.getNoteQueue().size(); ++i) {
                if (this.m_wine.getNoteQueue().get(i).getType() != WorkqueueItemType.Add || this.m_wine.getNotes().contains((CellarObject)this.m_wine.getNoteQueue().get(i).getItem())) continue;
                dest = new Rectangle(0, top, this.getWidth(), this.getItemHeight(this.m_wine.getNoteQueue().get(i).getItem()));
                this.drawItem(g2d, dest, this.m_wine.getNoteQueue().get(i).getItem(), true);
                top += dest.height;
            }
            for (i = 0; i < this.m_wine.getNotes().size(); ++i) {
                dest = new Rectangle(0, top, this.getWidth(), this.getItemHeight((Note)this.m_wine.getNotes().get(i)));
                this.drawItem(g2d, dest, (Note)this.m_wine.getNotes().get(i), false);
                top += dest.height;
            }
            g2d.dispose();
        }
        g.drawImage(this.buffer, 0, 0, this);
    }

    public final void setDispatcher(CustomEventDispatcher dispatcher) {
        this.m_dispatcher = dispatcher;
    }

    private void drawItem(Graphics2D g, Rectangle r, Note item, boolean fromQueue) {
        Font f = null;
        Color c = Color.BLACK;
        if (fromQueue) {
            f = this.defaultFont;
            c = Color.ORANGE;
        } else {
            WorkqueueItemType type = this.m_wine.getNoteQueue().getType(item);
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
        Rectangle rect = new Rectangle(r.x + 10, r.y, r.width - 10, 30);
        this.drawDate(g, rect, item.getCreationTime(), f, c);
        int avg = item.getAverage();
        int right = rect.x + rect.width - 90;
        for (int i = 0; i < avg; ++i) {
            g.drawImage(this.m_starOn, right + i * 15, rect.y + 5, this);
        }
        Rectangle bounds = new Rectangle(r.x + 10, r.y + 20, r.width - 20, r.height - 20);
        UIHelper.drawTextMiddleLeft(g, item.getName().trim(), bounds, Color.BLACK);
        g.setColor(Color.ORANGE);
        g.drawLine(r.x + 10, r.y + (r.height - 1), r.x + r.width, r.y + (r.height - 1));
        this.m_noteRectangle.put(r, item);
    }

    private void drawDate(Graphics2D g, Rectangle bounds, Date date, Font font, Color foreground) {
        g.setFont(font);
        UIHelper.drawTextMiddleLeft(g, this.m_dateFormat.format(date), bounds, foreground);
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
        Note note;
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() > 1) {
            Note note2 = this.getAt(e.getPoint());
            if (note2 != null) {
                this.m_dispatcher.setSource((Object)note2);
                this.m_dispatcher.notify(11);
            }
        } else if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1 && (e.getModifiers() & 2) != 0) {
            Note note3 = this.getAt(e.getPoint());
            if (note3 != null) {
                this.m_dispatcher.setSource((Object)note3);
                this.m_dispatcher.notify(12);
            }
        } else if (e.isPopupTrigger() && (note = this.getAt(e.getPoint())) != null) {
            this.m_dispatcher.setSource((Object)note);
            this.m_dispatcher.notify(12);
        }
    }

    private Note getAt(Point pt) {
        Enumeration keys = this.m_noteRectangle.keys();
        while (keys.hasMoreElements()) {
            Rectangle r = (Rectangle)keys.nextElement();
            if (!r.contains(pt)) continue;
            Object item = this.m_noteRectangle.get(r);
            if (item instanceof Note) {
                return (Note)item;
            }
            return null;
        }
        return null;
    }

    public void mouseReleased(MouseEvent e) {
        Note note;
        if (e.isPopupTrigger() && (note = this.getAt(e.getPoint())) != null) {
            this.m_dispatcher.setSource((Object)note);
            this.m_dispatcher.notify(12);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}

