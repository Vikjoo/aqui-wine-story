/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MaxLengthDocument
extends PlainDocument {
    private int maxLength;

    public MaxLengthDocument(int maxLength) {
        this.maxLength = maxLength;
    }

    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
        if (this.getLength() + str.length() > this.maxLength) {
            Toolkit.getDefaultToolkit().beep();
        } else {
            super.insertString(offset, str, a);
        }
    }
}

