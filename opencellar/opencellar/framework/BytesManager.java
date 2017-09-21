/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.nio.ByteBuffer;

public final class BytesManager {
    private int m_position = 0;
    private byte[] m_bytes;
    static final String ENCODING_TYPE = "ISO8859_1";

    protected BytesManager(int size) {
        this.m_bytes = new byte[size];
    }

    protected BytesManager(byte[] data) {
        this.m_bytes = data;
    }

    public byte[] getBytes() {
        return this.m_bytes;
    }

    public byte[] getBytes(int start) {
        byte[] temp = new byte[this.m_bytes.length - start];
        for (int i = start; i < this.m_bytes.length; ++i) {
            temp[start - i] = this.m_bytes[i];
        }
        return temp;
    }

    public void write(byte[] bytes) {
        for (int i = 0; i < bytes.length; ++i) {
            this.m_bytes[this.m_position] = bytes[i];
            ++this.m_position;
        }
    }

    public void write(byte[] bytes, int count) {
        for (int i = 0; i < bytes.length; ++i) {
            this.m_bytes[this.m_position] = bytes[i];
            if (i >= count) break;
            ++this.m_position;
        }
    }

    public void write(byte value) {
        this.m_bytes[this.m_position] = value;
        ++this.m_position;
    }

    public void writeByteEx(int value) {
        if (value > 127) {
            this.writeByte((byte)(value >>> 0 & 255));
        } else {
            this.writeByte((byte)value);
        }
    }

    public void writeByte(byte value) {
        this.m_bytes[this.m_position] = value;
        ++this.m_position;
    }

    public void write(String value) {
        try {
            byte[] stringArray = value.getBytes("ISO8859_1");
            this.write(stringArray, value.length());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void write(int value) {
        this.writeByte((byte)(value >>> 24 & 255));
        this.writeByte((byte)(value >>> 16 & 255));
        this.writeByte((byte)(value >>> 8 & 255));
        this.writeByte((byte)(value >>> 0 & 255));
    }

    public void write(short value) {
        this.writeByte((byte)(value >>> 8 & 255));
        this.writeByte((byte)(value >>> 0 & 255));
    }

    public final void writeEx(int val) {
        byte b1 = (byte)(val >> 8 & 255);
        byte b2 = (byte)(val & 255);
        this.writeByte(b1);
        this.writeByte(b2);
    }

    private static byte short1(short x) {
        return (byte)(x >> 8);
    }

    private static byte short0(short x) {
        return (byte)(x >> 0);
    }

    public final void writeUShort(short x) {
        this.writeByte(BytesManager.short0(x));
        this.writeByte(BytesManager.short1(x));
    }

    public void write(float value) {
        int i = Float.floatToIntBits(value);
        this.write((byte)(i >>> 0));
        this.write((byte)(i >>> 8));
        this.write((byte)(i >>> 16));
        this.write((byte)(i >>> 24));
    }

    public void write(boolean value) {
        if (value) {
            this.write(1);
        } else {
            this.write(0);
        }
    }

    public ByteBuffer getBuffer(int start) {
        int len = this.m_bytes.length - start;
        if (len < 1) {
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(len);
        for (int i = start; i < this.m_bytes.length; ++i) {
            buffer.put(this.m_bytes[i]);
        }
        return buffer;
    }

    public int getInt() {
        byte ch1 = this.m_bytes[this.m_position++];
        byte ch2 = this.m_bytes[this.m_position++];
        byte ch3 = this.m_bytes[this.m_position++];
        byte ch4 = this.m_bytes[this.m_position++];
        return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
    }

    public final int getUshort() {
        int length;
        byte b2;
        byte b1;
        if ((length = (int)(((b2 = this.m_bytes[this.m_position++]) & 255) << 8 | (b1 = this.m_bytes[this.m_position++]) & 255)) < 0) {
            length += 65536;
        }
        return length;
    }

    public boolean getBoolean() {
        if (this.m_bytes[this.m_position++] == 1) {
            return true;
        }
        return false;
    }

    public byte getByte() {
        byte b = this.m_bytes[this.m_position];
        ++this.m_position;
        return b;
    }

    public int getByteEx() {
        byte b = this.m_bytes[this.m_position];
        ++this.m_position;
        return b & 255;
    }

    public String getString(int chars) {
        String str = null;
        try {
            str = new String(this.m_bytes, this.m_position, chars, "ISO8859_1");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.m_position += chars;
        if (str != null) {
            return str.trim();
        }
        return str;
    }

    public final short getShort() {
        byte ch1 = this.m_bytes[this.m_position++];
        byte ch2 = this.m_bytes[this.m_position++];
        return (short)((ch1 << 8) + (ch2 << 0));
    }

    public final float getFloat() {
        int i = ((this.m_bytes[this.m_position + 0] & 255) << 0) + ((this.m_bytes[this.m_position + 1] & 255) << 8) + ((this.m_bytes[this.m_position + 2] & 255) << 16) + ((this.m_bytes[this.m_position + 3] & 255) << 24);
        this.m_position += 4;
        return Float.intBitsToFloat(i);
    }

    public final int getPosition() {
        return this.m_position;
    }
}

