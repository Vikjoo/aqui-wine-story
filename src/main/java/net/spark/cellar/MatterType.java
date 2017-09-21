
package net.spark.cellar;


public enum MatterType {
    None(0),
    Miss(1),
    Weak(2),
    Average(3),
    Beautiful(4),
    Strong(5),
    TooStrong(6);
    
    private int m_value = 0;

    private MatterType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final MatterType parse(int b) {
        MatterType[] enums = MatterType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

