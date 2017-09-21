/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.util.HashMap;
import opencellar.application.ICommand;

public class CommandCollection {
    private HashMap m_commands = new HashMap();

    protected CommandCollection() {
    }

    public final ICommand get(String name) {
        return (ICommand)this.m_commands.get(name);
    }

    public final boolean register(String name, ICommand command) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("name == null");
        }
        if (command == null) {
            throw new IllegalArgumentException("command == null");
        }
        boolean success = false;
        if (!this.m_commands.containsKey(name)) {
            this.m_commands.put(name, command);
            success = true;
        }
        return success;
    }

    public final int size() {
        return this.m_commands.size();
    }

    public final boolean contains(String name) {
        return this.m_commands.containsKey(name);
    }

    public final boolean unregister(String name) {
        boolean success = false;
        if (this.m_commands.containsKey(name)) {
            this.m_commands.remove(name);
            success = true;
        }
        return success;
    }
}

