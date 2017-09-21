/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.util.ArrayList;
import opencellar.application.IOpenCellarListener;
import opencellar.application.OpenCellarEvent;

public final class OpenCellarEventDispatcher {
    private Object m_source = null;
    private ArrayList m_eventList = new ArrayList();

    public OpenCellarEventDispatcher(Object source) {
        this.m_source = source;
    }

    public final void add(IOpenCellarListener listener) {
        if (listener != null) {
            this.m_eventList.add(listener);
        }
    }

    public final void remove(IOpenCellarListener listener) {
        if (listener != null) {
            this.m_eventList.remove(listener);
        }
    }

    public final void dispatch(int eventCode) {
        OpenCellarEvent evt = new OpenCellarEvent(this.m_source, eventCode);
        int size = this.m_eventList.size();
        for (int i = 0; i < size; ++i) {
            IOpenCellarListener listener = (IOpenCellarListener)this.m_eventList.get(i);
            if (listener == null) continue;
            listener.notify(evt);
        }
    }
}

