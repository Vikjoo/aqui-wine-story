/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.jdom.Content
 *  org.jdom.Document
 *  org.jdom.Element
 *  org.jdom.input.SAXBuilder
 *  org.jdom.output.Format
 *  org.jdom.output.XMLOutputter
 */
package opencellar.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import opencellar.application.FolderType;
import opencellar.application.IApplication;
import opencellar.application.Setting;
import opencellar.application.SettingCollection;
import opencellar.application.Settings;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public final class AppSettings
extends Settings {
    private IApplication m_app;
    final String FILENAME = "settings.xml";

    protected AppSettings(IApplication application) {
        this.m_app = application;
        this.load();
    }

    public final IApplication getApp() {
        return this.m_app;
    }

    public final boolean save() {
        boolean success = false;
        try {
            Element root = new Element("AppSettings");
            Document document = new Document(root);
            for (int i = 0; i < this.size(); ++i) {
                SettingCollection settings = this.get(i);
                Element setting = new Element("Section");
                setting.setAttribute("Name", settings.getName());
                root.addContent((Content)setting);
                for (int j = 0; j < settings.size(); ++j) {
                    Element prop = new Element("Property");
                    prop.setAttribute("Key", settings.get(j).getKey());
                    prop.setAttribute("Value", settings.get(j).getValue());
                    setting.addContent((Content)prop);
                }
            }
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, (OutputStream)new FileOutputStream(this.getFileName()));
            success = true;
        }
        catch (Exception e) {
            this.getApp().writeDebug("AppSettings::save::error");
            e.printStackTrace();
        }
        return success;
    }

    private String getFileName() {
        return this.getApp().getPath(FolderType.Root) + System.getProperty("file.separator") + "settings.xml";
    }

    protected final boolean load() {
        File f = new File(this.getFileName());
        if (!f.exists()) {
            return true;
        }
        boolean success = false;
        SAXBuilder sxb = new SAXBuilder();
        Document document = null;
        try {
            document = sxb.build(f);
            Element root = document.getRootElement();
            List sections = root.getChildren("Section");
            for (Element current : sections) {
                List setEx = current.getChildren("Property");
                for (Element prop : setEx) {
                    this.get(current.getAttributeValue("Name")).set(prop.getAttributeValue("Key"), prop.getAttributeValue("Value"));
                }
            }
            success = true;
        }
        catch (Exception e) {
            this.getApp().writeDebug("AppSettings::load::error");
            e.printStackTrace();
        }
        return success;
    }
}

