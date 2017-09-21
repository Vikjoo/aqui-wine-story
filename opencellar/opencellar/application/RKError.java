/*
 * Decompiled with CFR 0_122.
 */
package opencellar.application;

import opencellar.application.IApplication;
import opencellar.application.Log;
import opencellar.application.MessageBoxFactory;
import opencellar.application.MessageButtonType;
import opencellar.application.MessageIconType;
import opencellar.application.MessageType;
import opencellar.application.RootKit;

public class RKError
extends RootKit {
    public RKError(IApplication application) {
        super(application);
    }

    public void initialize() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

            public void uncaughtException(Thread t, Throwable e) {
                String errorMessage = "Une exception non g\u00e9r\u00e9e de type \"" + e.getClass().toString() + "\" s'est produite dans l'application." + "\n\nNote : un fichier \"debug.log\" contenant la description d\u00e9taill\u00e9e de l'erreur a \u00e9t\u00e9 cr\u00e9e dans le repertoire d'installation du programme.\nMerci de l'envoyer \u00e0 l'adresse suivante : support@open-cellar.com";
                e.printStackTrace();
                Log.writeDebug("Erreur : pile");
                Log.writeDebug(e.toString());
                StackTraceElement[] trace = e.getStackTrace();
                for (int i = 0; i < trace.length; ++i) {
                    Log.writeDebug("at " + trace[i]);
                }
                MessageBoxFactory.createEx(null, "Erreur - Open Cellar", errorMessage, MessageType.Message, MessageIconType.Error, MessageButtonType.Default);
            }
        });
    }

    public void uninitialize() {
    }

}

