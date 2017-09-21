/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.util.Date;

public final class Log {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void writeDebug(String s) {
        RandomAccessFile file = null;
        file = new RandomAccessFile("debug.log", "rw");
        file.seek(file.length());
        Date date = new Date();
        file.writeBytes(DateFormat.getInstance().format(date) + " : " + s + System.getProperty("line.separator"));
        if (file == null) return;
        try {
            file.close();
            return;
        }
        catch (Exception ex) {
            return;
        }
        catch (Exception ex) {
            if (file == null) return;
            try {
                file.close();
                return;
            }
            catch (Exception ex2) {
                return;
            }
            catch (Throwable throwable) {
                if (file == null) throw throwable;
                try {
                    file.close();
                    throw throwable;
                }
                catch (Exception ex3) {
                    // empty catch block
                }
                throw throwable;
            }
        }
    }
}

