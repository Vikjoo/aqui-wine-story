/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

class KeyGenerator {
    static int MAX = 3111697;
    static byte FIRST_CHAR = 49;
    static byte LAST_CHAR = 90;
    static String LAST_KEY = "ZZZZ";

    KeyGenerator() {
    }

    static int GetPosition(String key) {
        int result = 1;
        byte[] bytes = key.getBytes();
        for (int i = 0; i < bytes.length; ++i) {
            result *= bytes[i] - FIRST_CHAR + 1;
        }
        return result;
    }

    static String GetNextId(String key) {
        String returnKey = "";
        byte[] s = key.getBytes();
        byte cpt = 0;
        int j = s.length - 1;
        cpt = s[j];
        if (cpt != LAST_CHAR) {
            s[j] = cpt = (byte)(cpt + 1);
        } else {
            s[j] = FIRST_CHAR;
            do {
                if (--j <= -1) {
                    continue;
                }
                if (s[j] != LAST_CHAR) {
                    byte[] arrby = s;
                    int n = j;
                    arrby[n] = (byte)(arrby[n] + 1);
                    break;
                }
                s[j] = FIRST_CHAR;
            } while (true);
        }
        for (int i = 0; i < s.length; ++i) {
            returnKey = returnKey + s[i];
        }
        return returnKey;
    }
}

