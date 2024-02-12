package org.jluc.ctr.tools.calendrier;

import com.sun.javafx.application.LauncherImpl;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.jluc.ctr.tools.calendrier.ihm.CalendrierCTRApplication;
import org.jluc.ctr.tools.calendrier.ihm.SplashScreenLoader;

public class CalendrierCTRLauncher {
    public static final ResourceBundle DICO_PROPERTIES = ResourceBundle.getBundle("resources/dicoCTR", Locale.getDefault());
    public static final String APPLICATION_TITLE = DICO_PROPERTIES.getString("app.title");// "Gestion
                                                                                          // des
                                                                                          // Stages
                                                                                          // et
                                                                                          // Exaemens
                                                                                          // de
                                                                                          // la
                                                                                          // CTR
                                                                                          // by
                                                                                          // JLuc";

    /**
     * @param args
     */
    public static void main(String[] args) {
        LogManager.getLogger(CalendrierCTRLauncher.class).debug("Démarrage de l'application.");
        LauncherImpl.launchApplication(CalendrierCTRApplication.class, SplashScreenLoader.class, args);
    }

}
