/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import opencellar.application.CommandBar;

public class CommandBarManager {
    private JPanel m_parent;
    private ArrayList m_list = new ArrayList();

    protected CommandBarManager(JPanel parent) {
        if (parent == null) {
            throw new IllegalArgumentException("parent");
        }
        this.m_parent = parent;
        this.m_parent.setLayout(new FlowLayout(0));
    }

    protected final JPanel getParent() {
        return this.m_parent;
    }

    public final CommandBar create(String alias) {
        CommandBar command = new CommandBar(this, alias);
        this.m_list.add(command);
        this.getParent().add(command.getToolBar());
        return command;
    }

    public final CommandBar get(String alias) {
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            CommandBar cb = (CommandBar)this.m_list.get(i);
            if (!cb.getAlias().equals(alias)) continue;
            return cb;
        }
        return null;
    }
}

