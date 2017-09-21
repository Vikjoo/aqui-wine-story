/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.util.ArrayList;
import opencellar.application.SettingCollection;

public class Settings {
    private ArrayList m_list = new ArrayList();

    public final int size() {
        return this.m_list.size();
    }

    public final SettingCollection get(String name) {
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            SettingCollection settings = (SettingCollection)this.m_list.get(i);
            if (!settings.getName().equals(name)) continue;
            return settings;
        }
        SettingCollection settings = new SettingCollection(name);
        this.m_list.add(settings);
        return settings;
    }

    public final SettingCollection get(int index) {
        if (index > -1 && index < this.m_list.size()) {
            return (SettingCollection)this.m_list.get(index);
        }
        return null;
    }
}

