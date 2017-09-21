
package net.spark.cellar;


public enum VisualIntensityType {
    None(0),
    Weak(1),
    Light(2),
    Clear(3),
    Supported(4),
    Sunk(5),
    Deep(6),
    Intense(7);
    
    private int m_value = 0;

    private VisualIntensityType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final VisualIntensityType parse(int b) {
        VisualIntensityType[] enums = VisualIntensityType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

