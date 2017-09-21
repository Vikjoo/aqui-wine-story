/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.io.File;
import opencellar.application.FolderType;
import opencellar.application.IApplication;
import opencellar.application.RootKit;

public final class RKDirectories
extends RootKit {
    public RKDirectories(IApplication app) {
        super(app);
    }

    public void initialize() {
        String rootDir = this.getApp().getPath(FolderType.Root);
        this.createDir(rootDir + "prefs");
        this.createDir(rootDir + "addins");
        this.createDir(rootDir + "renderers");
        this.createDir(rootDir + "previews");
        this.createDir(rootDir + "scripts");
    }

    private void createDir(String path) {
        new File(path).mkdir();
    }
}

