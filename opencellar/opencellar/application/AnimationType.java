/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum AnimationType {
    Error(0),
    Open(1),
    Work(2);
    
    private int m_value;

    private AnimationType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }
}

