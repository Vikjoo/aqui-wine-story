/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
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
    
    private byte m_value = 0;

    private OlfactiveIntensityType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final OlfactiveIntensityType parse(byte b) {
        OlfactiveIntensityType[] enums = OlfactiveIntensityType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

