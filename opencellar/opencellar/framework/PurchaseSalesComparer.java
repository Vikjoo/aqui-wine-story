/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Comparator;
import java.util.Date;
import opencellar.framework.PurchaseSales;

public class PurchaseSalesComparer
implements Comparator {
    public int compare(Object o1, Object o2) {
        PurchaseSales a1 = (PurchaseSales)o1;
        PurchaseSales a2 = (PurchaseSales)o2;
        return a2.getDate().compareTo(a1.getDate());
    }
}

