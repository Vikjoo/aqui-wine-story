/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.util.ArrayList;
import opencellar.application.Setting;

public final class SettingCollection {
    private String m_name;
    private ArrayList m_list = new ArrayList();

    protected SettingCollection(String name) {
        this.m_name = name;
    }

    public final String getName() {
        return this.m_name;
    }

    public final void set(String name, String value) {
        Setting setting = this.internalGet(name);
        if (setting != null) {
            setting.setValue(value);
        } else {
            setting = new Setting(name, value);
            this.m_list.add(setting);
        }
    }

    public final Setting internalGet(String name) {
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            Setting setting = (Setting)this.m_list.get(i);
            if (!setting.getKey().equals(name)) continue;
            return setting;
        }
        return null;
    }

    public final String get(String name) {
        return this.get(name, "");
    }

    public final int get(String name, int defaultValue) {
        int result = -1;
        try {
            result = Integer.parseInt(this.get(name));
        }
        catch (Exception ex) {
            result = defaultValue;
        }
        return result;
    }

    public final String get(String name, String defaultValue) {
        int length = this.m_list.size();
        for (int i = 0; i < length; ++i) {
            Setting setting = (Setting)this.m_list.get(i);
            if (!setting.getKey().equals(name)) continue;
            return setting.getValue();
        }
        return defaultValue;
    }

    public final int size() {
        return this.m_list.size();
    }

    public final Setting get(int index) {
        if (index > -1 && index < this.m_list.size()) {
            return (Setting)this.m_list.get(index);
        }
        return null;
    }
}

