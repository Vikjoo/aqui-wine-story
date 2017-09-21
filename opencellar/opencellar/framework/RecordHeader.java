/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

public class RecordHeader {
    private int m_code;
    private int m_length;

    protected RecordHeader(int code, int length) throws Exception {
        if (code < 0) {
            throw new Exception("code < 0");
        }
        if (length < 0) {
            throw new Exception("length < 0");
        }
        this.m_code = code;
        this.m_length = length;
    }

    public int getCode() {
        return this.m_code;
    }

    public int getLength() {
        return this.m_length;
    }
}

