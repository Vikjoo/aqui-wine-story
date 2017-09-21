/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.io.RandomAccessFile;
import opencellar.framework.BytesManager;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.Index;

final class CellarObjectFinder {
    CellarObjectFinder() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected static final boolean findAndLoad(CellarObject co, Index index) {
        RandomAccessFile stream = null;
        stream = new RandomAccessFile(co.getCellar().getFilePath(), "rw");
        long offset = index.getOffset();
        long position = 0;
        stream.seek(offset -= 2);
        byte b1 = stream.readByte();
        byte b2 = stream.readByte();
        int length = (b2 & 255) << 8 | b1 & 255;
        if (length < 0) {
            length += 65536;
        }
        if (length > 0) {
            position = stream.getFilePointer();
            byte[] data = new byte[length];
            stream.read(data, 0, length);
            BytesManager bm = new BytesManager(data);
            co.read(bm);
            co.setOffset(position);
            co.setToNone();
        }
        if (stream == null) return true;
        try {
            stream.close();
            return true;
        }
        catch (Exception ex) {
            return true;
        }
        catch (Exception ex) {
            if (stream == null) return true;
            try {
                stream.close();
                return true;
            }
            catch (Exception ex2) {
                return true;
            }
            catch (Throwable throwable) {
                if (stream == null) throw throwable;
                try {
                    stream.close();
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

