/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.BaseItem;
import opencellar.framework.ObjectType;

public class Area
extends BaseItem {
    static final Area empty = new Area("0000");

    protected Area() {
    }

    protected Area(String sysId) {
        super.setSystemUid(sysId);
    }

    public final ObjectType getType() {
        return ObjectType.Area;
    }

    public static final Area getEmpty() {
        return empty;
    }
}

