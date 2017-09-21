/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import opencellar.application.CommandBar;
import opencellar.application.ICommand;
import opencellar.application.utils;

public final class CommandBarItem
implements ActionListener {
    private JButton m_button;
    private Icon m_image;
    private String m_alias = null;
    private ICommand m_command;
    private CommandBar m_parent;

    public CommandBarItem(CommandBar parent, String alias, Icon icon) {
        if (icon == null) {
            throw new IllegalArgumentException("image == null");
        }
        if (alias == null || alias.equals("")) {
            throw new IllegalArgumentException("alias == null");
        }
        if (parent == null) {
            throw new IllegalArgumentException("parent == null");
        }
        this.m_image = icon;
        this.m_alias = alias;
        this.m_parent = parent;
        this.createUI();
    }

    private final void createUI() {
        this.m_button = new JButton();
        this.m_button.setIcon(this.getImage());
        this.m_button.addActionListener(this);
    }

    public final void setToolTip(String toolTip) {
        String newTip = "<HTML>" + utils.replace(toolTip, "\n", "<br>") + "</HTML>";
        this.m_button.setToolTipText(newTip);
    }

    protected final JButton getButton() {
        return this.m_button;
    }

    public final Icon getImage() {
        return this.m_image;
    }

    public final String getAlias() {
        return this.m_alias;
    }

    public final void setEnabled(boolean b) {
        this.m_button.setEnabled(b);
    }

    public final boolean isEnabled() {
        return this.m_button.isEnabled();
    }

    public final void performClick() {
        if (this.isEnabled() && this.m_command != null) {
            this.m_command.execute();
        }
    }

    public final void setCommand(ICommand command) {
        this.m_command = command;
    }

    public final ICommand getCommand() {
        return this.m_command;
    }

    public final CommandBar getParent() {
        return this.m_parent;
    }

    public final void remove() {
    }

    public void actionPerformed(ActionEvent e) {
        this.performClick();
    }
}

