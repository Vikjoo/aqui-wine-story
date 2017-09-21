/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.IApplication;
import opencellar.application.OSXAdapter;
import opencellar.application.RootKit;
import opencellar.application.utils;

public final class RKMacOSX
extends RootKit {
    public RKMacOSX(IApplication application) {
        super(application);
    }

    public void initialize() {
        if (utils.isMacOS()) {
            OSXAdapter.registerMacOSXApplication(this.getApp());
        }
    }

    public void uninitialize() {
    }
}

