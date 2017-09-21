/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.sun.tools.javac.Main
 */
package opencellar.application;

import com.sun.tools.javac.Main;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import opencellar.application.FileClassLoader;
import opencellar.application.IApplication;

public class ScriptHost {
    private String m_script = null;
    private IApplication m_app = null;
    private Main javac = new Main();

    public ScriptHost(IApplication app, String script) {
        if (app == null) {
            throw new IllegalArgumentException("app == null");
        }
        if (script == null) {
            throw new IllegalArgumentException("script == null");
        }
        this.m_script = script;
        this.m_app = app;
    }

    public final IApplication getApplication() {
        return this.m_app;
    }

    public int run() {
        File file = null;
        try {
            file = File.createTempFile("jav", ".java", new File(System.getProperty("user.dir")));
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return -1;
        }
        file.deleteOnExit();
        String filename = file.getName();
        String classname = filename.substring(0, filename.length() - 5);
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileOutputStream(file));
        }
        catch (Exception ex) {
            return -1;
        }
        out.println("package opencellar.application;");
        out.println("import opencellar.framework.*;");
        out.println("public class " + classname + " {");
        out.println("    public static void main(String[] args) throws Exception {");
        out.print("        ");
        out.println("    }");
        out.println("    public static void run(IApplication app) throws Exception {");
        out.print("        ");
        out.println(this.m_script);
        out.println("    }");
        out.println("}");
        out.flush();
        out.close();
        String[] optionsAndSources = new String[]{"-source", "1.5", "-target", "1.5", filename};
        PrintWriter listing = new PrintWriter(System.out, false);
        int status = Main.compile((String[])optionsAndSources, (PrintWriter)listing);
        if (status == 0) {
            try {
                File path = new File(file.getParent(), classname + ".class");
                path.deleteOnExit();
                Class theClass = FileClassLoader.load(path.getAbsolutePath());
                Method main = theClass.getMethod("run", IApplication.class);
                main.invoke(null, this.getApplication());
            }
            catch (InvocationTargetException ex) {
                ex.getTargetException().printStackTrace();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return status;
    }
}

