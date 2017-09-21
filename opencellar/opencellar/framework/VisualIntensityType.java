/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum VisualIntensityType {
    None(0),
    Weak(1),
    Light(2),
    Clear(3),
    Supported(4),
    Sunk(5),
    Deep(6),
    Intense(7);
    
    private byte m_value = 0;

    private VisualIntensityType(byte value) {
        this.m_value = value;
    }

    public byte getValue() {
        return this.m_value;
    }

    public static final VisualIntensityType parse(byte b) {
        VisualIntensityType[] enums = VisualIntensityType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return None;
    }
}

