/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum OperatorType {
    Equals(0),
    NotEquals(1),
    Lower(2),
    Higher(3);
    
    private int m_value;

    private OperatorType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }
}

