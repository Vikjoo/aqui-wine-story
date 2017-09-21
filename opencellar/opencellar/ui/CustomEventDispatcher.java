/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

import java.util.ArrayList;
import opencellar.ui.CustomEvent;
import opencellar.ui.ICustomListener;

public final class CustomEventDispatcher {
    private Object m_source = null;
    private ArrayList m_list = new ArrayList();

    public CustomEventDispatcher(Object source) {
        this.m_source = source;
    }

    public final void setSource(Object source) {
        this.m_source = source;
    }

    public final void add(ICustomListener listener) {
        if (listener != null) {
            this.m_list.add(listener);
        }
    }

    public final void remove(ICustomListener listener) {
        if (listener != null) {
            this.m_list.remove(listener);
        }
    }

    public final int size() {
        return this.m_list.size();
    }

    public final void notify(int eventId) {
        int size = this.m_list.size();
        CustomEvent evt = new CustomEvent(this.m_source, eventId);
        for (int i = 0; i < size; ++i) {
            ICustomListener listener = (ICustomListener)this.m_list.get(i);
            if (listener == null) continue;
            listener.eventDispatched(evt);
        }
    }
}

