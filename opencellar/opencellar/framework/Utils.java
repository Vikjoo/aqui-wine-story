/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class Utils {
    protected static final int KEY_LENGTH = 4;
    protected static final boolean DEBUG = true;
    static final int MAX_BYTE_VALUE = 256;
    static final String PAD_STRING = " ";

    public static final boolean isValidByte(int val) {
        if (val > -1 && val < 256) {
            return true;
        }
        return false;
    }

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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static final void write(String s) {
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

    public static final String newGuid() {
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        int i = 0;
        while (builder.length() < 19) {
            int number = rnd.nextInt(90);
            if (number < 65) continue;
            builder.append((char)number);
            if (++i % 4 != 0) continue;
            builder.append("-");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static final String ensureCapacity(String s, int max) {
        if (s == null) {
            return "";
        }
        if (s.length() > max) {
            return s.substring(0, max);
        }
        return s;
    }

    public static final String padRight(String s, int length, String c) {
        int size;
        if (s == null) {
            s = "";
        }
        if ((size = s.length()) >= length) {
            return s;
        }
        StringBuffer buffer = new StringBuffer(s);
        for (int i = size; i < length; ++i) {
            buffer.append(" ");
        }
        return buffer.toString();
    }

    public static final String[] split(String str, char separatorChar) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return null;
        }
        Vector<String> list = new Vector<String>();
        int i = 0;
        int start = 0;
        boolean match = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match) {
                    list.addElement(str.substring(start, i).trim());
                    match = false;
                }
                start = ++i;
                continue;
            }
            match = true;
            ++i;
        }
        if (match) {
            list.addElement(str.substring(start, i).trim());
        }
        Object[] arr = new String[list.size()];
        list.copyInto(arr);
        return arr;
    }

    public static void copyFile(File in, File out) throws Exception {
        FileInputStream fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);
        byte[] buf = new byte[1024];
        int i = 0;
        while ((i = fis.read(buf)) != -1) {
            fos.write(buf, 0, i);
        }
        fis.close();
        fos.close();
    }
}

