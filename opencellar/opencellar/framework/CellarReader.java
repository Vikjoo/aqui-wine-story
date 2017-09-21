/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import opencellar.framework.BytesManager;
import opencellar.framework.CellarReaderException;
import opencellar.framework.RecordHeader;

public class CellarReader {
    private String m_cellarPath;
    private long m_fileLength;
    private RandomAccessFile m_file;
    private RecordHeader m_recordHeader;
    private BytesManager m_manager;
    private long m_offset = 0;

    public CellarReader(String cellarPath) throws FileNotFoundException, CellarReaderException {
        if (!new File(cellarPath).exists()) {
            throw new FileNotFoundException();
        }
        this.m_cellarPath = cellarPath;
        try {
            this.open();
        }
        catch (Exception ex) {
            throw new CellarReaderException("Cannot open file");
        }
    }

    public String getCellarPath() {
        return this.m_cellarPath;
    }

    public long getFileLength() {
        return this.m_fileLength;
    }

    public long getPosition() {
        long pointer = 0;
        try {
            pointer = this.m_file.getFilePointer();
        }
        catch (Exception ex) {
            pointer = -1;
        }
        return pointer;
    }

    private void open() throws Exception {
        this.m_file = new RandomAccessFile(this.getCellarPath(), "r");
        this.m_fileLength = this.m_file.length();
    }

    public void close() {
        if (this.m_file != null) {
            try {
                this.m_file.close();
            }
            catch (Exception ex) {
                // empty catch block
            }
        }
    }

    public RecordHeader getRecord() {
        return this.m_recordHeader;
    }

    public void moveNextRecord() {
        try {
            this.m_file.seek(this.getPosition() + (long)this.m_recordHeader.getLength());
        }
        catch (Exception ex) {
            System.err.println("Erreur " + ex.toString());
        }
    }

    public boolean readHeader() {
        boolean flag = false;
        long pointer = this.getPosition();
        if (pointer > -1 && pointer < this.m_fileLength) {
            try {
                byte b1 = this.m_file.readByte();
                byte b2 = this.m_file.readByte();
                short code = (short)((b2 & 255) << 8 | b1 & 255);
                b1 = this.m_file.readByte();
                b2 = this.m_file.readByte();
                int length = (b2 & 255) << 8 | b1 & 255;
                if (length < 0) {
                    length += 65536;
                }
                this.m_recordHeader = new RecordHeader(code, length);
                flag = true;
            }
            catch (Exception ex) {
                this.m_recordHeader = null;
                System.out.println("Erreur readheader !!!!" + ex.toString());
            }
        }
        return flag;
    }

    public BytesManager getBytesManager() {
        return this.m_manager;
    }

    public boolean readData() {
        boolean flag = false;
        this.m_manager = null;
        if (this.m_recordHeader.getLength() > 0) {
            this.m_offset = this.getPosition();
            byte[] data = new byte[this.m_recordHeader.getLength()];
            try {
                this.m_file.read(data, 0, data.length);
                this.m_manager = new BytesManager(data);
                flag = true;
            }
            catch (Exception ex) {
                // empty catch block
            }
        }
        return flag;
    }

    public String readKey(long position) {
        long currentPosition = this.getPosition();
        try {
            this.m_file.seek(position);
            byte[] tmp = new byte[4];
            this.m_file.read(tmp, 0, 4);
            this.m_file.seek(currentPosition);
            StringBuffer buffer = new StringBuffer(4);
            for (int i = 0; i < 4; ++i) {
                buffer.append(tmp[i]);
            }
            return buffer.toString();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public long getOffset() {
        return this.m_offset;
    }
}

