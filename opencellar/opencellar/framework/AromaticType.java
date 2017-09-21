/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum AromaticType {
    None(0),
    VeryWeak(1),
    Weak(2),
    Average(3),
    Strong(4),
    VeryStrong(5);
    
    private byte m_value = 0;

    private AromaticType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final AromaticType parse(byte b) {
        AromaticType[] enums = AromaticType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

