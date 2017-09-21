/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Comparator;

public class CellarObjectComparer
implements Comparator {
    public int compare(Object o1, Object o2) {
        return o1.toString().compareTo(o2.toString());
    }
}

