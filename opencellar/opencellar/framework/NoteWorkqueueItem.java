/*
 * Decompiled with CFR 0_122.
 */
package opencellar.framework;

import opencellar.framework.CellarObject;
import opencellar.framework.CellarObjectCollection;
import opencellar.framework.Note;
import opencellar.framework.Wine;
import opencellar.framework.WorkqueueItem;
import opencellar.framework.WorkqueueItemType;

public final class NoteWorkqueueItem
extends WorkqueueItem {
    public NoteWorkqueueItem(Note item, WorkqueueItemType itemType) {
        super(item, itemType);
    }

    protected final void bind(Wine w) {
        if (this.getType() == WorkqueueItemType.Add) {
            Note parent = this.getItem();
            parent.setWine(w);
            parent.save();
            if (!w.getNotes().contains(parent)) {
                w.getNotes().add(parent);
            }
        } else if (this.getType() == WorkqueueItemType.Delete) {
            Note parent = this.getItem();
            parent.markAsDeleted();
            parent.setWine(null);
            if (w.getNotes().contains(parent)) {
                w.getNotes().remove(parent);
            }
            parent.save();
        }
    }

    public final Note getItem() {
        return (Note)super.getItem();
    }
}

