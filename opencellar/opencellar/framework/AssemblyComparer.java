/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Comparator;
import opencellar.framework.Assembly;
import opencellar.framework.TypeOfWine;

public class AssemblyComparer
implements Comparator {
    public int compare(Object o1, Object o2) {
        Integer b2;
        Assembly a1 = (Assembly)o1;
        Assembly a2 = (Assembly)o2;
        Integer b1 = new Integer(a1.getPercent());
        int result = b1.compareTo(b2 = new Integer(a1.getPercent()));
        if (result == 0) {
            result = a1.getCepage().getName().compareTo(a2.getCepage().getName());
        }
        return result;
    }
}

