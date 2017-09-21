/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.util.ArrayList;
import opencellar.application.SnifferResult;

public final class SnifferResultCollection {
    private ArrayList m_list = new ArrayList();

    protected final void add(SnifferResult sr) {
        this.m_list.add(sr);
    }

    public final SnifferResult get(int index) {
        if (index > -1 && index < this.m_list.size()) {
            return (SnifferResult)this.m_list.get(index);
        }
        throw new IllegalArgumentException("index out of bounds !");
    }
}

