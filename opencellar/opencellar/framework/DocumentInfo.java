/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.Date;
import opencellar.framework.BytesManager;
import opencellar.framework.CellarObject;
import opencellar.framework.DateTimeUtility;
import opencellar.framework.ObjectType;
import opencellar.framework.Utils;

public class DocumentInfo
extends CellarObject {
    private String m_name;
    private String m_comment;
    private String m_lastUsedKey = "1111";
    private boolean m_protected = false;
    private boolean m_readonly = false;
    private static String m_passwordHash = "          ";
    private byte m_minorVersion = 0;
    private byte m_majorVersion = 0;
    private Date m_creationTime = new Date();
    private Date m_updateTime = new Date();
    private String m_uid;
    private long m_objects = 0;

    protected DocumentInfo() {
    }

    public final ObjectType getType() {
        return ObjectType.Header;
    }

    public final String getName() {
        return this.m_name;
    }

    public final void setName(String newName) {
        this.m_name = Utils.ensureCapacity(newName, 30);
        super.setToUpdate();
    }

    public final String getComment() {
        return this.m_comment;
    }

    public final void setComment(String comment) {
        this.m_comment = Utils.ensureCapacity(comment, 60);
        super.setToUpdate();
    }

    public final String getLastKey() {
        return this.m_lastUsedKey;
    }

    protected final void setLastKey(String key) {
        this.m_lastUsedKey = key;
        super.setToUpdate();
    }

    public final boolean isProtected() {
        return this.m_protected;
    }

    public final void setProtected(boolean b) {
        if (this.m_protected != b) {
            this.m_protected = b;
            super.setToUpdate();
        }
    }

    public final boolean isReadonly() {
        return this.m_readonly;
    }

    public final void setReadonly(boolean b) {
        if (this.m_readonly != b) {
            this.m_readonly = b;
            super.setToUpdate();
        }
    }

    public final byte getMinorVersion() {
        return this.m_minorVersion;
    }

    public final void setMinorVersion(byte b) {
        this.m_minorVersion = b;
    }

    public final byte getMajorVersion() {
        return this.m_majorVersion;
    }

    public final void setMajorVersion(byte b) {
        this.m_majorVersion = b;
    }

    public final Date getCreationTime() {
        return this.m_creationTime;
    }

    public final Date getLastUpdate() {
        return this.m_updateTime;
    }

    public String getUid() {
        return this.m_uid;
    }

    protected final void increment() {
        ++this.m_objects;
    }

    public long getObjectsCount() {
        return this.m_objects;
    }

    protected final void setUid(String s) {
        this.m_uid = Utils.ensureCapacity(s, 40);
        super.setToUpdate();
    }

    protected final int getLength() {
        return super.getLength() + 168;
    }

    protected final void write(BytesManager m) {
        this.m_updateTime = new Date();
        m.write(this.getLastKey());
        m.write(this.isProtected());
        m.write(Utils.padRight(this.getName(), 30, " "));
        m.write(Utils.padRight(this.getComment(), 60, " "));
        m.write(DateTimeUtility.get(this.getCreationTime()));
        m.write(DateTimeUtility.get(this.getLastUpdate()));
        m.write(this.getMajorVersion());
        m.write(this.getMinorVersion());
        m.write(this.isReadonly());
        m.write(Utils.padRight(m_passwordHash, 10, " "));
        m.write(Utils.padRight(this.getUid(), 40, " "));
    }

    protected final void read(BytesManager m) {
        this.setLastKey(m.getString(4));
        this.setProtected(m.getBoolean());
        this.setName(m.getString(30));
        this.setComment(m.getString(60));
        this.m_creationTime = DateTimeUtility.get(m.getString(10));
        this.m_updateTime = DateTimeUtility.get(m.getString(10));
        this.setMajorVersion(m.getByte());
        this.setMinorVersion(m.getByte());
        this.setReadonly(m.getBoolean());
        m.getString(10);
        this.setUid(m.getString(40));
        super.setToNone();
    }
}

