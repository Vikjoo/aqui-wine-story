/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.awt.Color;
import java.util.ArrayList;
import opencellar.application.ColorScheme;
import opencellar.application.IApplication;
import opencellar.application.ICollectionListener;
import opencellar.application.Legend;
import opencellar.framework.Wine;

public abstract class LegendCollection {
    private IApplication m_app;
    private ArrayList m_list = new ArrayList();
    protected boolean enableEvents = true;
    private ArrayList m_eventsList = new ArrayList();

    protected LegendCollection(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        this.m_app = app;
    }

    public final IApplication getApp() {
        return this.m_app;
    }

    public final int size() {
        return this.m_list.size();
    }

    public final Legend get(int index) {
        if (index > -1 && index < this.m_list.size()) {
            return (Legend)this.m_list.get(index);
        }
        throw new IllegalArgumentException("index en dehors des limites");
    }

    public final void add(Legend legend, int position) {
        if (legend == null) {
            throw new IllegalArgumentException("legend == null");
        }
        this.m_list.add(position, legend);
        this.notifyListChange();
    }

    public final void add(Legend legend) {
        int position = this.m_list.size();
        this.add(legend, position);
        this.notifyListChange();
    }

    public final void remove(Legend legend) {
        this.m_list.remove(legend);
        this.notifyListChange();
    }

    public final void remove(int index) {
        this.m_list.remove(index);
        this.notifyListChange();
    }

    public final Color[] matches(Wine wine) {
        Color[] c = new Color[2];
        int size = this.m_list.size();
        int index = 0;
        for (int i = 0; i < size; ++i) {
            Legend legend = (Legend)this.m_list.get(i);
            if (legend == null || !legend.validate() || !legend.match(wine)) continue;
            c[index++] = legend.getColor();
            if (index == 2) break;
        }
        if (index < 2) {
            c[index] = ColorScheme.getWineColor(wine);
        }
        return c;
    }

    protected final void notifyListChange() {
        if (this.enableEvents) {
            for (int i = 0; i < this.m_eventsList.size(); ++i) {
                ICollectionListener listener = (ICollectionListener)this.m_eventsList.get(i);
                if (listener == null) continue;
                listener.onChange(this);
            }
        }
    }

    public final void addListener(ICollectionListener listener) {
        if (listener != null) {
            this.m_eventsList.add(listener);
        }
    }

    public final void removeListener(ICollectionListener listener) {
        if (listener != null) {
            this.m_eventsList.remove(listener);
        }
    }
}

