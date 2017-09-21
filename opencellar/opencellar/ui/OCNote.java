/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.EventListener;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import opencellar.ui.UIHelper;

public final class OCNote
extends JComponent
implements MouseListener {
    private Image m_starOn;
    private Image m_starOff;
    final String RC_NAMESPACE = "/opencellar/rc/misc/";
    private int m_note = 0;
    static Dimension RectangleSize = new Dimension(15, 14);

    public OCNote() {
        this.addMouseListener(this);
        this.loadRC();
    }

    private void loadRC() {
        this.m_starOn = UIHelper.getImage("/opencellar/rc/misc/staron.gif");
        this.m_starOff = UIHelper.getImage("/opencellar/rc/misc/staroff.gif");
    }

    public int getNote() {
        return this.m_note;
    }

    public void setNote(int note) {
        if (note > -1 && note < 6 && note != this.m_note) {
            this.m_note = note;
            this.repaint();
            this.notifyChange();
        }
    }

    protected void setNoteEx(int note) {
        if (note > -1 && note < 6 && note != this.m_note) {
            this.m_note = note;
            this.repaint();
        }
    }

    private void notifyChange() {
        ChangeEvent ce = new ChangeEvent(this);
        ChangeListener[] list = (ChangeListener[])super.getListeners(ChangeListener.class);
        for (int i = 0; i < list.length; ++i) {
            list[i].stateChanged(ce);
        }
    }

    public void addChangeListener(ChangeListener listener) {
        this.listenerList.add(ChangeListener.class, listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        this.listenerList.remove(ChangeListener.class, listener);
    }

    public void paint(Graphics g) {
        this.update(g);
    }

    public void update(Graphics g) {
        int top = this.getHeight() / 2 - 7;
        for (int i = 0; i < 5; ++i) {
            if (i + 1 <= this.m_note) {
                g.drawImage(this.m_starOn, OCNote.RectangleSize.width * i + 5, top, this);
                continue;
            }
            g.drawImage(this.m_starOff, OCNote.RectangleSize.width * i + 5, top, this);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
    }

    private int getNoteFromPoint(int x, int y) {
        int note = (int)((x - 5) / OCNote.RectangleSize.width) + 1;
        if (note > 5) {
            note = 5;
        }
        return note;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            this.setNote(0);
        } else {
            this.setNote(this.getNoteFromPoint(e.getX(), e.getY()));
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}

