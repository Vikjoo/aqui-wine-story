/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum LanguageType {
    French("FR"),
    English("EN"),
    Misc("MI");
    
    private String m_value;

    private LanguageType(String value) {
        this.m_value = value;
    }

    protected final String getValue() {
        return this.m_value;
    }

    public static final LanguageType parse(String s) {
        LanguageType[] enums = LanguageType.class.getEnumConstants();
        for (int i = 0; i < enums.length; ++i) {
            if (!enums[i].getValue().equals(s)) continue;
            return enums[i];
        }
        return French;
    }
}

