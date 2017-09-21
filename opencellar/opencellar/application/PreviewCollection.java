/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.util.ArrayList;
import opencellar.application.ICollectionListener;
import opencellar.application.IPreviewRenderer;

public abstract class PreviewCollection {
    private ArrayList m_list = new ArrayList();
    protected boolean enableEvents = true;
    private ArrayList m_eventsList = new ArrayList();

    public final void add(IPreviewRenderer renderer) {
        if (renderer != null && !this.m_list.contains(renderer)) {
            this.m_list.add(renderer);
            this.notifyListChange();
        }
    }

    public final IPreviewRenderer get(int index) {
        if (index > -1 && index < this.m_list.size()) {
            return (IPreviewRenderer)this.m_list.get(index);
        }
        return null;
    }

    public final void remove(IPreviewRenderer renderer) {
        int pos = this.m_list.indexOf(renderer);
        if (pos > 0) {
            this.m_list.remove(pos);
            this.notifyListChange();
        } else if (pos == 0) {
            throw new IllegalArgumentException("Impossible de supprimer un rendu systeme.");
        }
    }

    public final void remove(int pos) {
        if (pos > 0 && pos < this.m_list.size()) {
            this.m_list.remove(pos);
            this.notifyListChange();
        } else if (pos == 0) {
            throw new IllegalArgumentException("Impossible de supprimer un rendu systeme.");
        }
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

    public final boolean contains(IPreviewRenderer renderer) {
        if (renderer == null) {
            return false;
        }
        return this.m_list.contains(renderer);
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

