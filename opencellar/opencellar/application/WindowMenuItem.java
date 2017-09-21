/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButtonMenuItem;
import opencellar.application.IWindow;
import opencellar.application.IWindowListener;

public final class WindowMenuItem
extends JRadioButtonMenuItem
implements IWindowListener,
ActionListener {
    private IWindow m_window;

    public WindowMenuItem(IWindow window) {
        if (window == null) {
            throw new IllegalArgumentException("window == null");
        }
        this.m_window = window;
        this.m_window.addListener(this);
        this.addActionListener(this);
        this.setText(window.getCaption());
    }

    public final IWindow getWindow() {
        return this.m_window;
    }

    public void onActivate(IWindow source) {
    }

    public void onCaptionChange(IWindow source) {
        this.setText(source.getCaption());
    }

    public void onClose(IWindow source) {
    }

    public void actionPerformed(ActionEvent e) {
        this.m_window.show();
        if (!this.isSelected()) {
            this.setSelected(true);
        }
    }
}

