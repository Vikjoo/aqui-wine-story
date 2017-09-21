/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Component;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import opencellar.ui.OCAutoCompleteComboBox;

final class InternalAutoComplete
extends PlainDocument {
    JComboBox comboBox;
    ComboBoxModel model;
    JTextComponent editor;
    boolean selecting = false;

    protected InternalAutoComplete(OCAutoCompleteComboBox comboBox) {
        this.comboBox = comboBox;
        this.model = comboBox.getModel();
        this.editor = (JTextComponent)comboBox.getEditor().getEditorComponent();
    }

    public void remove(int offs, int len) throws BadLocationException {
        if (this.selecting) {
            return;
        }
        super.remove(offs, len);
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (this.selecting) {
            return;
        }
        super.insertString(offs, str, a);
        Object item = this.lookupItem(this.getText(0, this.getLength()));
        this.setSelectedItem(item);
        if (item != null) {
            this.setText(item.toString());
        }
        this.highlightCompletedText(offs + str.length());
    }

    private void setText(String text) throws BadLocationException {
        super.remove(0, this.getLength());
        super.insertString(0, text, null);
    }

    private void highlightCompletedText(int start) {
        this.editor.setSelectionStart(start);
        this.editor.setSelectionEnd(this.getLength());
    }

    private void setSelectedItem(Object item) {
        this.selecting = true;
        this.model.setSelectedItem(item);
        this.selecting = false;
    }

    private Object lookupItem(String pattern) {
        int n = this.model.getSize();
        for (int i = 0; i < n; ++i) {
            Object currentItem = this.model.getElementAt(i);
            if (!this.startsWithIgnoreCase(currentItem.toString(), pattern)) continue;
            return currentItem;
        }
        return null;
    }

    private boolean startsWithIgnoreCase(String str1, String str2) {
        return str1.toUpperCase().startsWith(str2.toUpperCase());
    }
}

