/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import opencellar.application.MenuItem;

public class MenuItemCollection {
    private MenuItem m_parent = null;
    private ArrayList m_list = new ArrayList();

    protected MenuItemCollection(MenuItem parent) {
        this.m_parent = parent;
    }

    public final int size() {
        return this.m_list.size();
    }

    public MenuItem create() {
        JMenuItem item = new JMenuItem();
        MenuItem mi = new MenuItem(item);
        mi.setParent(this.m_parent);
        this.m_parent.getItem().add(item);
        return mi;
    }
}

