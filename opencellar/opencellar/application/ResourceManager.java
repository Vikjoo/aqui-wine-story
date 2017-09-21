/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Hashtable;
import opencellar.application.FolderType;
import opencellar.application.IApplication;
import opencellar.application.LanguageType;
import opencellar.application.utils;

public final class ResourceManager {
    private IApplication m_app;
    static final String COMMENT = "#";
    static final String EQUAL = "=";
    static final String EMPTY = "";
    private Hashtable m_rc = new Hashtable(1000);
    final String UNDEFINED = "undefined";

    public ResourceManager(IApplication app) {
        if (app == null) {
            throw new IllegalArgumentException("app");
        }
        this.m_app = app;
    }

    public IApplication getApp() {
        return this.m_app;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void load(LanguageType lt) {
        InputStreamReader file = null;
        BufferedReader buff = null;
        try {
            if (lt == LanguageType.French) {
                buff = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/opencellar/rc/fr.txt"), "UTF-16"));
            } else {
                String fileName = this.getApp().getPath(FolderType.Language) + lt.getValue();
                File f = new File(fileName);
                if (!f.exists()) {
                    this.load(LanguageType.French);
                    return;
                }
                file = new FileReader(fileName);
                buff = new BufferedReader(file);
            }
            String line = null;
            while ((line = buff.readLine()) != null) {
                this.parseLine(line.trim());
            }
        }
        catch (IOException e) {
            this.getApp().writeDebug("ResourceManager::load::error");
            e.printStackTrace();
        }
        finally {
            try {
                buff.close();
                if (file != null) {
                    file.close();
                }
            }
            catch (IOException e) {
                this.getApp().writeDebug("ResourceManager::load::finally::error");
                e.printStackTrace();
            }
        }
    }

    protected final void parseLine(String s) {
        int equal;
        if (!"".equals(s) && !s.startsWith("#") && (equal = s.indexOf("=")) > 1 && equal + 1 < s.length()) {
            String resourceName = s.substring(0, equal).trim();
            String resourceValue = utils.replace(s.substring(equal + 1).trim(), "#10", "\n");
            if (!this.m_rc.containsKey(resourceName)) {
                this.m_rc.put(resourceName, resourceValue);
            }
        }
    }

    public final String getString(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key");
        }
        if (this.m_rc.containsKey(key)) {
            return this.m_rc.get(key).toString();
        }
        return "undefined";
    }
}

