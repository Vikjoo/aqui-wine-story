/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import opencellar.application.CommandBarItem;
import opencellar.application.CommandBarManager;

public class CommandBar {
    private CommandBarManager m_parent;
    private String m_alias;
    private ArrayList m_list = new ArrayList();
    private JToolBar m_bar;

    protected CommandBar(CommandBarManager parent, String alias) {
        if (alias == null || alias.equals("")) {
            throw new IllegalArgumentException("alias == null");
        }
        if (parent == null) {
            throw new IllegalArgumentException("parent");
        }
        this.m_parent = parent;
        this.m_alias = alias;
        this.createUI();
    }

    public final CommandBarManager getParent() {
        return this.m_parent;
    }

    public final String getAlias() {
        return this.m_alias;
    }

    protected final JToolBar getToolBar() {
        return this.m_bar;
    }

    private final void createUI() {
        this.m_bar = new JToolBar();
        this.m_bar.setFloatable(false);
    }

    public final CommandBarItem create(String alias, Icon icon) {
        CommandBarItem item = new CommandBarItem(this, alias, icon);
        this.m_list.add(item);
        this.getToolBar().add(item.getButton());
        return item;
    }

    public final boolean isVisible() {
        return this.m_bar.isVisible();
    }

    public final void setVisible(boolean b) {
        if (this.m_bar.isVisible() != b) {
            this.m_bar.setVisible(b);
        }
    }
}

