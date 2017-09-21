/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import java.util.ArrayList;
import java.util.Comparator;
import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.Note;
import opencellar.framework.NoteComparer;
import opencellar.framework.NoteWorkqueueItem;
import opencellar.framework.Wine;
import opencellar.framework.Workqueue;
import opencellar.framework.WorkqueueItem;
import opencellar.framework.WorkqueueItemType;

public final class NoteWorkqueue
extends Workqueue {
    protected NoteWorkqueue(Wine w) {
        super(w, "NoteWorkqueue");
    }

    public final void sort() {
        super.getWine().getNotes().sort(new NoteComparer());
    }

    protected final void postApply() {
        super.getWine().notifyOnPropertyChanged("Notes");
    }

    public final NoteWorkqueueItem get(int index) {
        if (this.isValidIndex(index)) {
            return (NoteWorkqueueItem)this.m_list.get(index);
        }
        return null;
    }

    public WorkqueueItemType getType(Note note) {
        return super.get(note);
    }

    public final void set(NoteWorkqueueItem item) {
        super.set(item);
    }

    public final void remove(WorkqueueItemType type, Note note) {
        super.remove(type, note);
        super.notifyParent();
    }

    public final NoteWorkqueueItem create(Note note, WorkqueueItemType itemType) {
        return new NoteWorkqueueItem(note, itemType);
    }
}

