/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.io.PrintStream;
import java.io.RandomAccessFile;
import opencellar.framework.BytesManager;
import opencellar.framework.Cellar;
import opencellar.framework.CellarObject;
import opencellar.framework.DocumentInfo;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;

final class CellarWriter {
    CellarWriter() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    static final void write(CellarObject co) {
        RandomAccessFile stream = null;
        stream = new RandomAccessFile(co.getCellar().getFilePath(), "rw");
        BytesManager bm = null;
        if (co.getState() == ObjectState.New) {
            stream.seek(stream.length());
            bm = new BytesManager(4);
            bm.writeUShort((short)co.getType().getValue());
            bm.writeUShort((short)co.getLength());
            stream.write(bm.getBytes());
        } else if (co.getState() == ObjectState.Delete) {
            stream.seek(co.getOffset() - 4);
            bm = new BytesManager(4);
            bm.writeUShort((short)ObjectType.None.getValue());
            bm.writeUShort((short)co.getLength());
            stream.write(bm.getBytes());
        } else {
            stream.seek(co.getOffset());
        }
        if (co.getLength() > 0 && co.getState() != ObjectState.Delete && co.getState() != ObjectState.None) {
            co.setOffset(stream.getFilePointer());
            bm = new BytesManager(co.getLength());
            co.write(bm);
            stream.write(bm.getBytes());
        }
        co.setToNone();
        if (stream == null) return;
        try {
            stream.close();
            return;
        }
        catch (Exception ex) {
            return;
        }
        catch (Exception ex) {
            if (stream == null) return;
            try {
                stream.close();
                return;
            }
            catch (Exception ex2) {
                return;
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static final void writeHeader(DocumentInfo di) {
        RandomAccessFile stream = null;
        try {
            stream = new RandomAccessFile(di.getCellar().getFilePath(), "rw");
            BytesManager bm = null;
            if (di.getState() == ObjectState.New) {
                bm = new BytesManager(4);
                bm.writeUShort((short)di.getType().getValue());
                bm.writeUShort((short)di.getLength());
                stream.write(bm.getBytes());
            } else {
                try {
                    stream.seek(4);
                }
                catch (Exception ex) {
                    System.out.println("seek " + ex.toString());
                }
            }
            if (di.getLength() > 0) {
                di.setOffset(stream.getFilePointer());
                bm = new BytesManager(di.getLength());
                di.write(bm);
                stream.write(bm.getBytes());
            }
            di.setToNone();
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (Exception ex) {}
            }
        }
    }
}

