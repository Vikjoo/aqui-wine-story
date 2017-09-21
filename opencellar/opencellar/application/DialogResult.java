/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum DialogResult {
    Default(-1),
    Yes(0),
    No(1),
    Cancel(2);
    
    private int m_value = 0;

    private DialogResult(int value) {
        this.m_value = value;
    }

    public final int getValue() {
        return this.m_value;
    }

    public static final DialogResult parse(int b) {
        DialogResult[] enums = DialogResult.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (enums[i].getValue() != b) continue;
            return enums[i];
        }
        return Default;
    }
}

