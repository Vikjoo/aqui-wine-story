/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import javax.swing.JMenuItem;
import opencellar.application.ICommand;
import opencellar.application.MenuItemCollection;

public class MenuItem {
    private ICommand m_command = null;
    private JMenuItem m_item = null;
    private MenuItem m_parent = null;
    private MenuItemCollection m_childs = null;

    protected MenuItem(JMenuItem item, ICommand command) {
        if (item == null) {
            throw new IllegalArgumentException("item == null");
        }
        if (command == null) {
            throw new IllegalArgumentException("command == null");
        }
        this.m_command = command;
        this.m_item = item;
    }

    protected MenuItem(JMenuItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item == null");
        }
        this.m_item = item;
    }

    protected final ICommand getCommand() {
        return this.m_command;
    }

    public final void setCommand(ICommand command) {
        this.m_command = command;
    }

    protected final JMenuItem getItem() {
        return this.m_item;
    }

    public final void setText(String text) {
        this.m_item.setText(text);
    }

    public final String getText() {
        return this.m_item.getText();
    }

    public final void setEnable(boolean b) {
        this.m_item.setEnabled(b);
    }

    public final boolean isEnabled() {
        return this.m_item.isEnabled();
    }

    public final MenuItem getParent() {
        return this.m_parent;
    }

    protected final void setParent(MenuItem mi) {
        this.m_parent = mi;
    }

    public final void performClick() {
        if (this.isEnabled() && this.getCommand() != null) {
            this.getCommand().execute();
        }
    }

    public final MenuItemCollection getChilds() {
        if (this.m_childs == null) {
            this.m_childs = new MenuItemCollection(this);
        }
        return this.m_childs;
    }
}

