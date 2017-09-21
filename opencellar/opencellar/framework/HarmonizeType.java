/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum HarmonizeType {
    None(0),
    Unbalanced(1),
    LittleBbalanced(2),
    RatherBalancedWell(3),
    Balanced(4),
    Harmonious(5);
    
    private byte m_value = 0;

    private HarmonizeType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final HarmonizeType parse(byte b) {
        HarmonizeType[] enums = HarmonizeType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

