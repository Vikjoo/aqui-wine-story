/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.Contact;
import opencellar.framework.ObjectType;

public class Owner
extends Contact {
    static final Owner empty = new Owner("0000");

    protected Owner() {
    }

    protected Owner(String sysUid) {
        super.setSystemUid(sysUid);
    }

    public final ObjectType getType() {
        return ObjectType.Owner;
    }

    public static final Owner getEmpty() {
        return empty;
    }
}

