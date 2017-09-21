/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  opencellar.framework.Wine
 */
package opencellar.application;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import opencellar.application.ISnifferListener;
import opencellar.application.SnifferResult;
import opencellar.application.SnifferResultCollection;
import opencellar.application.utils;
import opencellar.framework.Wine;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class Sniffer {
    private final int m_year;
    private final String m_name;
    private SnifferResultCollection m_results = new SnifferResultCollection();
    private ArrayList m_list = new ArrayList();

    public Sniffer(Wine theWine) {
        if (theWine == null) {
            throw new IllegalArgumentException("theWine == null");
        }
        this.m_year = theWine.getYear();
        this.m_name = theWine.getName().trim();
    }

    public final void snif() {
        this.createThead();
    }

    private final void createThead() {
        Thread thread = new Thread(new Runnable(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void run() {
                Sniffer.this.notifyEx(0);
                try {
                    Thread.currentThread();
                    Thread.sleep(2000);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                boolean success = true;
                Sniffer.this.notifyEx(1);
                InputStream in = null;
                try {
                    in = new URL("http://localhost/webopencellar/Finder.aspx?Search=" + Sniffer.this.m_name + "&Year=" + String.valueOf(Sniffer.this.m_year)).openStream();
                }
                catch (Exception ex) {
                    success = false;
                    ex.printStackTrace();
                }
                if (!success) {
                    Sniffer.this.notifyEx(-1);
                    return;
                }
                try {
                    Thread.currentThread();
                    Thread.sleep(2000);
                }
                catch (Exception ex) {
                    // empty catch block
                }
                Sniffer.this.notifyEx(2);
                try {
                    DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
                    DocumentBuilder constructeur = fabrique.newDocumentBuilder();
                    Document document = constructeur.parse(in);
                    NodeList nodes = document.getElementsByTagName("Result");
                    for (int i = 0; i < nodes.getLength(); ++i) {
                        Node n = nodes.item(i);
                        SnifferResult sr = new SnifferResult();
                        sr.systemUid = n.getAttributes().getNamedItem("Uid").getNodeValue();
                        sr.name = n.getAttributes().getNamedItem("Name").getNodeValue();
                        sr.vendor = n.getAttributes().getNamedItem("Vendor").getNodeValue();
                        sr.unitPrice = utils.tryParse(n.getAttributes().getNamedItem("UnitPrice").getNodeValue(), 0.0f);
                        sr.pageRank = utils.tryParse(n.getAttributes().getNamedItem("PageRank").getNodeValue(), 0);
                        Sniffer.this.m_results.add(sr);
                    }
                }
                catch (Exception e) {
                    success = false;
                    e.printStackTrace();
                }
                if (!success) {
                    Sniffer.this.notifyEx(-1);
                    return;
                }
                try {
                    Thread.currentThread();
                    Thread.sleep(1000);
                }
                catch (Exception ex) {
                    // empty catch block
                }
                if (in != null) {
                    try {
                        in.close();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                Sniffer.this.notifyEx(3);
            }
        });
        thread.start();
    }

    public final SnifferResultCollection getResults() {
        return this.m_results;
    }

    public final void add(ISnifferListener listener) {
        if (listener != null) {
            this.m_list.add(listener);
        }
    }

    public final void remove(ISnifferListener listener) {
        if (listener != null) {
            this.m_list.remove(listener);
        }
    }

    public final int size() {
        return this.m_list.size();
    }

    protected final void notifyEx(final int eventId) {
        SwingUtilities.invokeLater(new Runnable(){

            public void run() {
                int size = Sniffer.this.m_list.size();
                for (int i = 0; i < size; ++i) {
                    ISnifferListener listener = (ISnifferListener)Sniffer.this.m_list.get(i);
                    if (listener == null) continue;
                    listener.eventDispatched(eventId);
                }
            }
        });
    }

}

