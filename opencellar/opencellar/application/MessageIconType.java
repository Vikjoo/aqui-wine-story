/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum MessageIconType {
    None(-1),
    Error(0),
    Information(1),
    Warning(2),
    Question(3);
    
    private int m_value = 0;

    private MessageIconType(int value) {
        this.m_value = value;
    }

    public final int getValue() {
        return this.m_value;
    }
}

