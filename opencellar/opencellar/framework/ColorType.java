/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum ColorType {
    Red(0),
    White(1),
    Rosy(2),
    Yellow(3),
    Misc(5),
    LiqueurLike(6),
    ReserveForFutureUse2(7),
    ReserveForFutureUse3(8),
    ReserveForFutureUse4(9),
    ReserveForFutureUse5(10);
    
    private byte m_value = 0;

    private ColorType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final ColorType parse(byte b) {
        ColorType[] enums = ColorType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return Red;
    }
}

