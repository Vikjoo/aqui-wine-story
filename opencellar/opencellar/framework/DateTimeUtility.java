/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.text.SimpleDateFormat;
import java.util.Date;
import opencellar.framework.Utils;

public class DateTimeUtility {
    static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    static final String get(Date date) {
        return sdf.format(date);
    }

    static final Date get(String s) {
        if (s != null && s.length() == 10) {
            String[] sv = Utils.split(s, '/');
            try {
                Date date = sdf.parse(s);
                return date;
            }
            catch (Exception ex) {
                // empty catch block
            }
        }
        return new Date();
    }
}

