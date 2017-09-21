/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.BaseItem;
import opencellar.framework.ObjectType;

public class TypeOfWine
extends BaseItem {
    static final TypeOfWine empty = new TypeOfWine();

    protected TypeOfWine() {
    }

    public final ObjectType getType() {
        return ObjectType.TypeOfWine;
    }

    public static final TypeOfWine getEmpty() {
        return empty;
    }
}

