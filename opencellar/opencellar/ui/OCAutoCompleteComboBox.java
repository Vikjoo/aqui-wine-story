/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import opencellar.ui.InternalAutoComplete;

public final class OCAutoCompleteComboBox
extends JComboBox
implements FocusListener {
    private JTextComponent m_editor;

    public OCAutoCompleteComboBox() {
        this.setEditable(true);
        this.m_editor = (JTextComponent)this.getEditor().getEditorComponent();
        this.m_editor.setDocument(new InternalAutoComplete(this));
        this.m_editor.addFocusListener(this);
    }

    public void focusGained(FocusEvent e) {
        if (this.getSelectedItem() != null) {
            this.m_editor.setSelectionStart(0);
            this.m_editor.setSelectionEnd(this.getSelectedItem().toString().length());
        }
    }

    public void focusLost(FocusEvent e) {
        FocusEvent fe = new FocusEvent(this, e.getID());
        FocusListener[] list = super.getFocusListeners();
        for (int i = 0; i < list.length; ++i) {
            list[i].focusLost(fe);
        }
    }
}

