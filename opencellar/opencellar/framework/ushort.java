/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

public class ushort
extends Number {
    public static final int MAX = 65535;
    private short m_value;

    public ushort() {
        this.m_value = 0;
    }

    public ushort(int value) {
        if (value < 0 || value > 65535) {
            throw new RuntimeException("Argument en dehors des limites prevues.");
        }
        this.m_value = (short)value;
    }

    public double doubleValue() {
        return this.m_value < 0 ? 65535 + this.m_value + 1 : this.m_value;
    }

    public float floatValue() {
        return this.m_value < 0 ? 65535 + this.m_value + 1 : this.m_value;
    }

    public long longValue() {
        return this.m_value < 0 ? 65535 + this.m_value + 1 : this.m_value;
    }

    public int intValue() {
        return this.m_value < 0 ? 65535 + this.m_value + 1 : this.m_value;
    }
}

