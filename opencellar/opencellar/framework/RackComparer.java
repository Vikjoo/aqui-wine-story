/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Comparator;
import opencellar.framework.Rack;

public class RackComparer
implements Comparator {
    public int compare(Object o1, Object o2) {
        int order2;
        Rack r1 = (Rack)o1;
        Rack r2 = (Rack)o2;
        int order1 = r1.getOrder();
        if (order1 > (order2 = r2.getOrder())) {
            return -1;
        }
        if (order1 < order2) {
            return 1;
        }
        return r1.getName().compareTo(r2.getName());
    }
}

