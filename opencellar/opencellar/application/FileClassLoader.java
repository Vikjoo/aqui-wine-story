/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class FileClassLoader
extends ClassLoader {
    String path;

    public static Class load(String filename) {
        return new FileClassLoader().loadIt(new File(filename));
    }

    Class loadIt(File f) {
        String filename = f.getPath();
        this.path = filename.substring(0, filename.lastIndexOf(File.separator) + 1);
        if (!filename.endsWith(".class")) {
            filename = filename + ".class";
        }
        try {
            FileInputStream is = new FileInputStream(filename);
            byte[] buf = new byte[is.available()];
            is.read(buf, 0, is.available());
            is.close();
            Class c = this.defineClass(null, buf, 0, buf.length);
            if (c != null) {
                super.resolveClass(c);
            }
            return c;
        }
        catch (Exception e) {
            return null;
        }
    }

    protected Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            return this.findSystemClass(name);
        }
        catch (ClassNotFoundException e) {
            if (name.indexOf(".") >= 0) {
                name = name.substring(name.lastIndexOf(".") + 1);
            }
            return this.loadIt(new File(this.path + name));
        }
    }

    public URL getResource(String name) {
        try {
            File file = new File(this.path);
            String absPath = file.getAbsolutePath().replace(File.separatorChar, '/');
            return new URL("file:///" + absPath + name);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public InputStream getResourceAsStream(String name) {
        try {
            return new FileInputStream(this.path + name);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

