/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import opencellar.application.DialogResult;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;

public class MessageBoxFactory {
    public static DialogResult create(JComponent owner, String title, String message, MessageType mt, MessageIconType mit, MessageButtonType mbt) {
        if (owner == null) {
            throw new IllegalArgumentException("owner == null");
        }
        if (title == null) {
            throw new IllegalArgumentException("title == null");
        }
        if (message == null) {
            throw new IllegalArgumentException("message == null");
        }
        DialogResult dr = DialogResult.Default;
        if (mt == MessageType.Message) {
            JOptionPane.showInternalMessageDialog(owner, message, title, mit.getValue());
        } else if (mt == MessageType.Confirm) {
            int ret = JOptionPane.showInternalConfirmDialog(owner, message, title, mbt.getValue(), mit.getValue());
            dr = DialogResult.parse(ret);
        }
        return dr;
    }

    public static DialogResult createEx(JComponent owner, String title, String message, MessageType mt, MessageIconType mit, MessageButtonType mbt) {
        if (title == null) {
            throw new IllegalArgumentException("title == null");
        }
        if (message == null) {
            throw new IllegalArgumentException("message == null");
        }
        DialogResult dr = DialogResult.Default;
        if (mt == MessageType.Message) {
            JOptionPane.showMessageDialog(owner, message, title, mit.getValue());
        } else if (mt == MessageType.Confirm) {
            int ret = JOptionPane.showConfirmDialog(owner, message, title, mbt.getValue(), mit.getValue());
            dr = DialogResult.parse(ret);
        }
        return dr;
    }
}

