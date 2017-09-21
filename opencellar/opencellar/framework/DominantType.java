/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum DominantType {
    None(0),
    Balanced(1),
    VeryAcid(2),
    Acid(3),
    Marrowy(4),
    VeryMarrowy(5);
    
    private byte m_value = 0;

    private DominantType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final DominantType parse(byte b) {
        DominantType[] enums = DominantType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

