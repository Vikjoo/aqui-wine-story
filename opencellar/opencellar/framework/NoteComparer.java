/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Comparator;
import java.util.Date;
import opencellar.framework.Note;

public class NoteComparer
implements Comparator {
    public int compare(Object o1, Object o2) {
        Note n1 = (Note)o1;
        Note n2 = (Note)o2;
        int result = n2.getCreationTime().compareTo(n1.getCreationTime());
        if (result == 0) {
            int n2a;
            int n1a = n1.getAverage();
            if (n1a > (n2a = n2.getAverage())) {
                return -1;
            }
            if (n1a < n2a) {
                return 1;
            }
        }
        return result;
    }
}

