/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;

public class OCComment
extends JTextArea
implements FocusListener,
KeyListener {
    private int defaultHeight = -1;
    private Container m_parent = null;

    public OCComment() {
        super.addFocusListener(this);
        super.addKeyListener(this);
        super.setLineWrap(true);
    }

    public void focusGained(FocusEvent e) {
        if (this.defaultHeight == -1) {
            this.defaultHeight = this.getHeight();
        }
        this.setSize(this.getWidth(), this.defaultHeight * 3);
    }

    public void focusLost(FocusEvent e) {
        this.setSize(this.getWidth(), this.defaultHeight);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 9) {
            e.consume();
            this.transferFocus();
        }
    }

    public void keyReleased(KeyEvent e) {
    }
}

