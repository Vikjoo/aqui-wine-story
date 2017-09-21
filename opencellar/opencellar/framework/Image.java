/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.awt.Toolkit;
import java.io.File;
import java.nio.ByteBuffer;
import javax.imageio.stream.FileImageInputStream;
import opencellar.framework.BytesManager;
import opencellar.framework.CellarObject;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;

public final class Image
extends CellarObject {
    private ByteBuffer buffer;
    public static final long MAX_IMAGE_LENGHT = 64512;

    protected Image() {
    }

    public final ObjectType getType() {
        return ObjectType.Image;
    }

    public final int getLength() {
        if (this.buffer == null) {
            return super.getLength();
        }
        return super.getLength() + this.buffer.capacity();
    }

    public final boolean isLoaded() {
        return this.buffer != null;
    }

    public final java.awt.Image getImage() {
        if (this.buffer == null) {
            return null;
        }
        java.awt.Image img = null;
        try {
            img = Toolkit.getDefaultToolkit().createImage(this.buffer.array());
        }
        catch (Exception ex) {
            img = null;
        }
        return img;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void capture(String path) {
        if (this.getState() != ObjectState.New) {
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.length() > 64512) {
            return;
        }
        long length = file.length();
        this.buffer = ByteBuffer.allocate((int)length);
        FileImageInputStream fi = null;
        try {
            fi = new FileImageInputStream(file);
            for (long offset = 0; length > offset; ++offset) {
                this.buffer.put(fi.readByte());
            }
        }
        catch (Exception ex) {
            this.buffer = null;
        }
        finally {
            if (fi != null) {
                try {
                    fi.close();
                }
                catch (Exception exx) {}
            }
        }
    }

    protected final void write(BytesManager m) {
        super.write(m);
        if (this.buffer != null && this.buffer.hasArray()) {
            m.write(this.buffer.array());
        }
    }

    protected final void read(BytesManager m) {
        super.read(m);
        this.buffer = m.getBuffer(4);
    }

    protected final boolean allowUpdate() {
        return false;
    }
}

