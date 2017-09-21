/*
 * Decompiled with CFR 0_122.
 */
package opencellar.ui;

public final class CustomEvent {
    private Object m_source;
    private int m_eventId = 0;

    public CustomEvent(Object source, int eventId) {
        this.m_source = source;
        this.m_eventId = eventId;
    }

    public final Object getSource() {
        return this.m_source;
    }

    public final int getEventId() {
        return this.m_eventId;
    }
}

