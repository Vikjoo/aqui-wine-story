/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Comparator;
import opencellar.framework.RackItem;

public class RackItemComparer
implements Comparator {
    public int compare(Object o1, Object o2) {
        RackItem riX = (RackItem)o1;
        RackItem riY = (RackItem)o2;
        int result = riX.getParentId().compareTo(riY.getParentId());
        if (result == 0) {
            int column = riX.getColumn() - riY.getColumn();
            int row = riX.getRow() - riY.getRow();
            if (row > 0) {
                return 1;
            }
            if (row < 0) {
                return -1;
            }
            if (column > 0) {
                return 1;
            }
            if (column < 0) {
                return -1;
            }
            return 0;
        }
        return result;
    }
}

