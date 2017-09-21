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
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import opencellar.application.utils;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class JTableColumnSaver {
    private String m_fileName = null;
    private JTable m_table = null;

    public JTableColumnSaver(JTable table, String fileName) {
        if (table == null) {
            throw new IllegalArgumentException("table == null");
        }
        if (fileName == null) {
            throw new IllegalArgumentException("fileName == null");
        }
        this.m_table = table;
        this.m_fileName = fileName;
    }

    public final void load() {
        File f = new File(this.m_fileName);
        if (!f.exists()) {
            return;
        }
        SAXBuilder sxb = new SAXBuilder();
        Document document = null;
        try {
            document = sxb.build(f);
            Element root = document.getRootElement();
            List windows = root.getChildren("Column");
            Iterator i = windows.iterator();
            while (this.m_table.getColumnModel().getColumnCount() > 0) {
                TableColumn tc = this.m_table.getColumnModel().getColumn(0);
                this.m_table.getColumnModel().removeColumn(tc);
            }
            while (i.hasNext()) {
                Element current = (Element)i.next();
                int width = utils.tryParse(current.getAttributeValue("Width"), -1000);
                int modelIndex = utils.tryParse(current.getAttributeValue("ModelIndex"), -1000);
                TableColumn tc = new TableColumn(modelIndex, width);
                tc.setHeaderValue(this.m_table.getModel().getColumnName(modelIndex));
                this.m_table.getColumnModel().addColumn(tc);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean save() {
        boolean success = true;
        File file = new File(this.m_fileName);
        if (file.exists()) {
            success = file.delete();
        }
        if (!success) {
            return false;
        }
        TableColumnModel model = this.m_table.getColumnModel();
        try {
            Element root = new Element("Columns");
            Document document = new Document(root);
            for (int i = 0; i < model.getColumnCount(); ++i) {
                TableColumn tc = model.getColumn(i);
                Element col = new Element("Column");
                col.setAttribute("Width", String.valueOf(tc.getWidth()));
                col.setAttribute("ModelIndex", String.valueOf(tc.getModelIndex()));
                root.addContent((Content)col);
            }
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, (OutputStream)new FileOutputStream(this.m_fileName));
            success = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return success;
    }
}

