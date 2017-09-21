/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum ComplexityType {
    None(0),
    Ordinary(1),
    Simple(2),
    Fin(3),
    Subtle(4),
    Elegant(5),
    Raced(6),
    Refined(7);
    
    private byte m_value = 0;

    private ComplexityType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final ComplexityType parse(byte b) {
        ComplexityType[] enums = ComplexityType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

