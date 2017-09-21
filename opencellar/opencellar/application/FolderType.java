/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum FolderType {
    Root(1),
    Language(2),
    Prefs(3);
    
    private int m_value = 0;

    private FolderType(int value) {
        this.m_value = value;
    }
}

