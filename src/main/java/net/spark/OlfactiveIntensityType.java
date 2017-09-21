
package net.spark;


public enum OlfactiveIntensityType {
    None(0),
    Weak(1),
    Discrete(2),
    Moderate(3),
    Aromatic(4),
    Expressive(5),
    Bouquete(6),
    Developed(7),
    Intense(8);
    
    private int m_value = 0;

    private OlfactiveIntensityType(int value) {
        this.m_value = value;
    }

    public int getValue() {
        return this.m_value;
    }

    public static final OlfactiveIntensityType parse(int b) {
        OlfactiveIntensityType[] enums = OlfactiveIntensityType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

