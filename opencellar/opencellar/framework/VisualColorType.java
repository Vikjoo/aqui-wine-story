/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum VisualColorType {
    None(0),
    YellowGreen(1),
    YellowStraw(2),
    HoweverBlade(3),
    YellowGold(4),
    OldGold(5),
    Amber(6),
    DarkAmber(7),
    RussetRed(8),
    BrouOfNut(9),
    Purple(10),
    Crimson(11),
    Garnet(12),
    Ruby(13),
    Vermilion(14),
    Brick(15),
    Orange(16),
    Tuilee(17),
    MahoganyTree(18),
    Brown(19);
    
    private byte m_value = 0;

    private VisualColorType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final VisualColorType parse(byte b) {
        VisualColorType[] enums = VisualColorType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

