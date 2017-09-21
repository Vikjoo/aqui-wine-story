/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

public interface ISnifferListener {
    public static final int EVENT_INIT = 0;
    public static final int EVENT_CONNECTING = 1;
    public static final int EVENT_RETRIEVING = 2;
    public static final int EVENT_FINISH = 3;
    public static final int EVENT_ERROR = -1;

    public void eventDispatched(int var1);
}

