/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.io.File;
import java.util.Vector;
import opencellar.framework.Cellar;
import opencellar.framework.CellarReader;
import opencellar.framework.DocumentInfo;
import opencellar.framework.ICellarApplicationListener;
import opencellar.framework.MisMatchEventArgs;
import opencellar.framework.ObjectType;
import opencellar.framework.RecordHeader;
import opencellar.framework.TemplateFactory;

public final class CellarApplication {
    private Cellar m_activeCellar = null;
    private final byte MAJOR_VERSION = 0;
    private final byte MINOR_VERSION = 1;
    private Vector m_listeners = new Vector();

    private CellarApplication() {
    }

    public static CellarApplication create() {
        return new CellarApplication();
    }

    public final void dontForgetToRemoveIt() {
    }

    public boolean open(String cellarPath) {
        this.close();
        boolean success = false;
        if (new File(cellarPath).exists() && CellarApplication.isValidOpenCellarFile(cellarPath)) {
            this.m_activeCellar = new Cellar(cellarPath, this);
            this.notifyOnOpening();
            this.m_activeCellar.open();
            this.notifyOnOpen();
            if (this.m_activeCellar.getDocInfo().getMajorVersion() != this.getMajorVersion()) {
                success = false;
                this.close();
            } else {
                success = true;
            }
        }
        return success;
    }

    public boolean create(String cellarPath, boolean useTemplate, String name, String description) {
        this.close();
        boolean success = true;
        File f = new File(cellarPath);
        if (f.exists()) {
            success = f.delete();
        }
        if (useTemplate) {
            if (success) {
                this.m_activeCellar = new Cellar(cellarPath, this);
                this.m_activeCellar.createEmpty(name, description);
                TemplateFactory.fillTemplate(this.m_activeCellar);
                this.m_activeCellar.dispose();
                this.m_activeCellar = null;
                success = true;
            }
        } else if (success) {
            this.m_activeCellar = new Cellar(cellarPath, this);
            this.m_activeCellar.createEmpty(name, description);
            this.m_activeCellar.dispose();
            this.m_activeCellar = null;
            success = true;
        }
        if (success) {
            success = this.open(cellarPath);
        }
        return success;
    }

    public void close() {
        if (this.m_activeCellar != null) {
            this.notifyOnClosing();
            this.m_activeCellar.dispose();
            this.m_activeCellar = null;
            this.notifyOnClose();
        }
    }

    public Cellar activeCellar() {
        return this.m_activeCellar;
    }

    public static boolean isValidOpenCellarFile(String path) {
        boolean success = false;
        if (new File(path).exists()) {
            CellarReader reader = null;
            try {
                reader = new CellarReader(path);
                if (reader.readHeader() && reader.getRecord().getCode() == ObjectType.Header.getValue()) {
                    success = true;
                }
                reader.close();
            }
            catch (Exception ex) {
                // empty catch block
            }
        }
        return success;
    }

    public byte getMajorVersion() {
        return 0;
    }

    public byte getMinorVersion() {
        return 1;
    }

    public void addListener(ICellarApplicationListener listener) {
        if (listener != null) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(ICellarApplicationListener listener) {
        if (listener != null) {
            this.m_listeners.remove(listener);
        }
    }

    protected void notifyOnOpen() {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            ((ICellarApplicationListener)this.m_listeners.get(i)).onOpen(this);
        }
    }

    protected void notifyOnOpening() {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            ((ICellarApplicationListener)this.m_listeners.get(i)).onOpening(this);
        }
    }

    protected void notifyOnClosing() {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            ((ICellarApplicationListener)this.m_listeners.get(i)).onClosing(this);
        }
    }

    protected void notifyOnClose() {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            ((ICellarApplicationListener)this.m_listeners.get(i)).onClose(this);
        }
    }

    protected void notifyOnReadOnly() {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            ((ICellarApplicationListener)this.m_listeners.get(i)).onReadOnly(this);
        }
    }

    protected void notifyOnMisMatchVersion(MisMatchEventArgs args) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            ((ICellarApplicationListener)this.m_listeners.get(i)).onMisMatchVersion(this, args);
        }
    }
}

