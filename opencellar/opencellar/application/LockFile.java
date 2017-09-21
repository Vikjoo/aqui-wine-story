/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.io.File;
import opencellar.application.utils;

public final class LockFile {
    static final String LOCK_FILE_EXTENSION = ".ocl";
    private String m_parent = null;

    public LockFile(String parent) {
        if (parent == null) {
            throw new IllegalArgumentException("parent == null");
        }
        if (parent.equals("")) {
            throw new IllegalArgumentException("parent == null");
        }
        this.m_parent = parent;
    }

    public boolean isLocked() {
        return new File(this.getLockFilePath()).exists();
    }

    private String getLockFilePath() {
        return utils.replace(this.m_parent, ".oc", ".ocl");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void lock() {
        File f = null;
        try {
            f = new File(this.getLockFilePath());
            f.createNewFile();
        }
        catch (Exception ex) {}
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void unlock() {
        File f = new File(this.getLockFilePath());
        try {
            if (f.exists()) {
                f.delete();
            }
        }
        catch (Exception ex) {}
    }
}

