/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import opencellar.framework.BytesManager;
import opencellar.framework.CellarApplication;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.CellarObjectCollectionManager;
import opencellar.framework.CellarObjectFactory;
import opencellar.framework.CellarObjectFinder;
import opencellar.framework.CellarParser;
import opencellar.framework.CellarReader;
import opencellar.framework.CellarWriter;
import opencellar.framework.Cuvee;
import opencellar.framework.DocumentInfo;
import opencellar.framework.ICellarListener;
import opencellar.framework.IIndexable;
import opencellar.framework.Index;
import opencellar.framework.KeyGenerator;
import opencellar.framework.MisMatchEventArgs;
import opencellar.framework.ObjectState;
import opencellar.framework.ObjectType;
import opencellar.framework.Rack;
import opencellar.framework.RackItem;
import opencellar.framework.RackItemCollection;
import opencellar.framework.RecordHeader;
import opencellar.framework.TableIndex;
import opencellar.framework.TableIndexManager;
import opencellar.framework.Utils;
import opencellar.framework.Wine;

public final class Cellar {
    private CellarApplication m_app;
    private String m_filePath = null;
    private Vector m_listeners = new Vector();
    protected final RackItemCollection rackItems = new RackItemCollection(null);
    private DocumentInfo m_docInfo;
    private CellarObjectCollectionManager m_collections;
    private TableIndexManager m_tableIndex;

    protected Cellar(String filePath, CellarApplication app) {
        this.m_filePath = filePath;
        this.m_app = app;
        this.m_docInfo = new DocumentInfo();
        this.m_docInfo.setCellar(this);
        this.m_collections = new CellarObjectCollectionManager();
        this.m_tableIndex = new TableIndexManager();
    }

    protected CellarApplication getApplication() {
        return this.m_app;
    }

    public final String getFilePath() {
        return this.m_filePath;
    }

    public String getSystemUid() {
        return this.m_docInfo.getUid();
    }

    public void generateNewId() {
        this.m_docInfo.setUid(Utils.newGuid());
        CellarWriter.writeHeader(this.m_docInfo);
    }

    public String getName() {
        return this.m_docInfo.getName();
    }

    public final void setHeaders(String name, String description) {
        this.m_docInfo.setName(name);
        this.m_docInfo.setComment(description);
        CellarWriter.writeHeader(this.m_docInfo);
    }

    public String getDescription() {
        return this.m_docInfo.getComment();
    }

    public CellarObject createItem(ObjectType type) {
        CellarObject co = CellarObjectFactory.create(this, type);
        if (co != null) {
            co.setState(ObjectState.New);
        }
        return co;
    }

    protected final void save(CellarObject co) {
        boolean add;
        CellarObjectCollection oc;
        if (this.isReadOnly()) {
            return;
        }
        if (this.m_docInfo.getLastKey() == KeyGenerator.LAST_KEY) {
            this.setReadOnly(true);
        }
        boolean delete = co.getState() == ObjectState.Delete;
        boolean update = co.getState() == ObjectState.Update;
        boolean bl = add = co.getState() == ObjectState.New;
        if (add) {
            String key = KeyGenerator.GetNextId(this.m_docInfo.getLastKey());
            co.setSystemUid(key);
            this.m_docInfo.setLastKey(key);
        }
        if (update && !co.allowUpdate()) {
            return;
        }
        if (delete && !co.allowDelete()) {
            return;
        }
        CellarWriter.write(co);
        if (co.getType() != ObjectType.Header) {
            CellarWriter.writeHeader(this.m_docInfo);
        }
        if (add || delete) {
            this.set(co, add);
        } else if (update && (oc = this.m_collections.get(co.getType())) != null) {
            oc.needSorting = true;
        }
        if (add) {
            this.notifyOnNewItem(co);
        } else if (update) {
            this.notifyOnUpdateItem(co);
        } else if (delete) {
            this.notifyOnDeleteItem(co);
        }
    }

    public boolean isReadOnly() {
        return this.m_docInfo.isReadonly();
    }

    protected final void createEmpty(String name, String comment) {
        this.m_docInfo = new DocumentInfo();
        this.m_docInfo.setCellar(this);
        this.m_docInfo.setState(ObjectState.New);
        this.m_docInfo.setOffset(0);
        this.m_docInfo.setMajorVersion(this.m_app.getMajorVersion());
        this.m_docInfo.setMinorVersion(this.m_app.getMinorVersion());
        this.m_docInfo.setName(name);
        this.m_docInfo.setComment(comment);
        this.m_docInfo.setUid(Utils.newGuid());
        CellarWriter.writeHeader(this.m_docInfo);
    }

    protected void setReadOnly(boolean value) {
        if (this.m_docInfo.isReadonly() != value) {
            this.m_docInfo.setReadonly(value);
            this.notifyReadOnly();
        }
    }

    protected void notifyReadOnly() {
        this.getApplication().notifyOnReadOnly();
    }

    public void addlistener(ICellarListener listener) {
        if (listener != null) {
            this.m_listeners.add(listener);
        }
    }

    public void removeListener(ICellarListener listener) {
        this.m_listeners.remove(listener);
    }

    protected void dispose() {
        this.m_collections.clear();
        this.m_tableIndex.clear();
    }

    protected void open() {
        this.parseFile();
    }

    private void parseFile() {
        Wine w;
        boolean success = false;
        CellarReader reader = null;
        try {
            reader = new CellarReader(this.getFilePath());
            success = true;
        }
        catch (Exception ex) {
            Utils.write(ex.toString());
        }
        if (!success) {
            return;
        }
        CellarParser parser = new CellarParser(reader, this);
        while (reader.readHeader()) {
            this.m_docInfo.increment();
            RecordHeader rh = reader.getRecord();
            if (rh.getCode() == 11) {
                if (!reader.readData()) continue;
                this.m_docInfo.read(reader.getBytesManager());
                this.m_docInfo.setToNone();
                if (this.m_docInfo.isReadonly()) {
                    this.setReadOnly(true);
                    this.notifyReadOnly();
                }
                if (this.m_docInfo.getLastKey() == KeyGenerator.LAST_KEY && !this.m_docInfo.isReadonly()) {
                    this.setReadOnly(true);
                }
                if (!new File(this.getFilePath()).canWrite() && !this.m_docInfo.isReadonly()) {
                    this.setReadOnly(true);
                }
                if (this.m_docInfo.getMajorVersion() == this.getApplication().getMajorVersion()) continue;
                reader.close();
                success = false;
                MisMatchEventArgs mea = new MisMatchEventArgs(this.m_docInfo.getMajorVersion(), this.getApplication().getMajorVersion(), this.getFilePath());
                this.getApplication().notifyOnMisMatchVersion(mea);
                break;
            }
            if (parser.parseHeader(rh, reader.getPosition())) {
                if (!reader.readData()) continue;
                parser.parseItem(rh, reader.getBytesManager(), reader.getOffset());
                continue;
            }
            reader.moveNextRecord();
        }
        if (reader != null) {
            reader.close();
        }
        if (!success) {
            return;
        }
        CellarObjectCollection ws = this.m_collections.get(ObjectType.Wine);
        CellarObjectCollection rs = this.m_collections.get(ObjectType.Rack);
        int len = this.rackItems.size();
        for (int i = 0; i < len; ++i) {
            RackItem ri = this.rackItems.get(i);
            Rack rack = (Rack)rs.get(ri.getParentId());
            ri.setParent(rack);
            rack.getItems().add(ri);
            w = (Wine)ws.getFast(ri.getWineId());
            if (w == null) continue;
            w.getRackItems().add(ri);
        }
        CellarObjectCollection cs = this.m_collections.get(ObjectType.InternalCuvee);
        len = cs.size();
        for (int i = 0; i < len; ++i) {
            Cuvee c = (Cuvee)cs.get(i);
            w = (Wine)ws.get(c.getWineId());
            if (w == null) continue;
            w.setInternalCuvee(c);
        }
        this.rackItems.clear();
        ws.useFastIndex = false;
    }

    protected final DocumentInfo getDocInfo() {
        return this.m_docInfo;
    }

    public final CellarObjectCollection getCollection(ObjectType type) {
        CellarObjectCollection coc = this.m_collections.get(type);
        if (coc != null && coc.internalUse) {
            return null;
        }
        return coc;
    }

    protected final void add(CellarObject co) {
        CellarObjectCollection coc = this.m_collections.get(co.getType());
        if (coc != null) {
            coc.add(co);
        }
    }

    protected final void addIndex(ObjectType code, Index i) {
        TableIndex ti = this.m_tableIndex.get(code);
        if (ti != null) {
            ti.set(i);
        }
    }

    protected final CellarObjectCollection getChilds(CellarObject parent, ObjectType childType) {
        CellarObjectCollection oc = new CellarObjectCollection(ObjectType.None);
        TableIndex tableindex = this.m_tableIndex.get(childType);
        if (tableindex != null) {
            ArrayList al = tableindex.find(parent.getSystemUid());
            for (int j = 0; j < al.size(); ++j) {
                Index ix = (Index)al.get(j);
                CellarObject bo = this.createItem(childType);
                bo.setState(ObjectState.None);
                bo.setOffset(ix.getOffset());
                if (!CellarObjectFinder.findAndLoad(bo, ix)) continue;
                oc.add(bo);
            }
            al.clear();
            al = null;
        }
        return oc;
    }

    protected final void set(CellarObject obj, boolean add) {
        TableIndex tableindex;
        CellarObjectCollection coc = this.m_collections.get(obj.getType());
        if (coc != null) {
            if (add && !coc.contains(obj)) {
                coc.add(obj);
                coc.needSorting = true;
            } else if (!add && coc.contains(obj)) {
                coc.remove(obj);
                coc.needSorting = true;
            }
        }
        if ((tableindex = this.m_tableIndex.get(obj.getType())) != null) {
            Index i = tableindex.get(obj.getSystemUid());
            if (i == null && add) {
                i = new Index();
                i.setOffset(obj.getOffset());
                i.setPk(obj.getSystemUid());
                if (obj instanceof IIndexable) {
                    i.setFk(((IIndexable)((Object)obj)).getFk());
                }
                tableindex.set(i);
            } else if (i != null && !add) {
                tableindex.remove(i.getPk());
            }
        }
    }

    protected final CellarObject get(ObjectType type, String systemUid) {
        Index i;
        CellarObjectCollection coc = this.m_collections.get(type);
        if (coc != null) {
            return coc.get(systemUid);
        }
        TableIndex ti = this.m_tableIndex.get(type);
        if (ti != null && (i = ti.get(systemUid)) != null) {
            CellarObject bo = this.createItem(type);
            bo.setState(ObjectState.None);
            bo.setOffset(i.getOffset());
            if (CellarObjectFinder.findAndLoad(bo, i)) {
                return bo;
            }
        }
        return null;
    }

    protected final void notifyOnNewItem(CellarObject co) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            ICellarListener listener = (ICellarListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onNewItem(this, co);
        }
    }

    protected final void notifyOnUpdateItem(CellarObject co) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            ICellarListener listener = (ICellarListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onUpdateItem(this, co);
        }
    }

    protected final void notifyOnDeleteItem(CellarObject co) {
        for (int i = 0; i < this.m_listeners.size(); ++i) {
            ICellarListener listener = (ICellarListener)this.m_listeners.get(i);
            if (listener == null) continue;
            listener.onDeleteItem(this, co);
        }
    }
}

