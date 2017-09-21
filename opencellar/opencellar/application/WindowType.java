/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum WindowType {
    Application(1),
    Wine(2),
    Rack(3),
    WineList(4),
    NewCellar(5),
    ListAdministration(6),
    RackAdministration(7),
    Navigator(8),
    Welcome(9),
    Script(10),
    Confirm(11),
    Test(100);
    
    private int m_value = 0;

    private WindowType(int value) {
        this.m_value = value;
    }

    public final int getValue() {
        return this.m_value;
    }
}

