/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

public final class OpenCellarEvent {
    private Object m_source = null;
    private int m_eventValue;

    public OpenCellarEvent(Object source, int eventValue) {
        this.m_source = source;
        this.m_eventValue = eventValue;
    }

    public final Object getSource() {
        return this.m_source;
    }

    public final int getEvent() {
        return this.m_eventValue;
    }
}

