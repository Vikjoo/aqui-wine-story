/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.Contact;
import opencellar.framework.ObjectType;

public class Provider
extends Contact {
    static final Provider empty = new Provider("0000");

    protected Provider() {
    }

    protected Provider(String sysUid) {
        super.setSystemUid(sysUid);
    }

    public final ObjectType getType() {
        return ObjectType.Provider;
    }

    public static final Provider getEmpty() {
        return empty;
    }
}

