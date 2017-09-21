/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum RendererMode {
    Window(0),
    Select(1);
    
    private final int m_value;

    private RendererMode(int value) {
        this.m_value = value;
    }

    protected final int getValue() {
        return this.m_value;
    }
}

