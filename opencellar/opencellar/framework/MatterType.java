/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum MatterType {
    None(0),
    Miss(1),
    Weak(2),
    Average(3),
    Beautiful(4),
    Strong(5),
    TooStrong(6);
    
    private byte m_value = 0;

    private MatterType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final MatterType parse(byte b) {
        MatterType[] enums = MatterType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

