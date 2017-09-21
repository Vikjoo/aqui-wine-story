/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class OpenCellarFileFilter
extends FileFilter {
    static final String EXT = "oc";
    static final String DESCRIPTION = "Fichier Open Cellar (.oc)";

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String ext = this.getExtension(f);
        if (ext != null && ext.equals("oc")) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        return "Fichier Open Cellar (.oc)";
    }

    protected final String getExtension(File f) {
        String filename;
        int i;
        if (f != null && (i = (filename = f.getName()).lastIndexOf(46)) > 0 && i < filename.length() - 1) {
            return filename.substring(i + 1).toLowerCase();
        }
        return null;
    }
}

