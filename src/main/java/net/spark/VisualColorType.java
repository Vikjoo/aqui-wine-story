
package net.spark;

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
    
    private int m_value = 0;

    private VisualColorType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final VisualColorType parse(int b) {
        VisualColorType[] enums = VisualColorType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

