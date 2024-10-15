package org.jluc.ctr.tools.calendrier.ihm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class CalendrierCTRApplication extends Application {

    private Scene mScene;
    private Stage mStage;
    private CalendrierCTRController mCtrl;

    private Logger mLogger = LogManager.getLogger(CalendrierCTRApplication.class);

    public static final ResourceBundle DICO_PROPERTIES = ResourceBundle.getBundle("dicoCTR", Locale.getDefault());
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
        LogManager.getLogger(CalendrierCTRApplication.class).debug("Démarrage de l'application.");
        // for (int i = 0; i < 24; i++) {
        // LogManager.getLogger(CalendrierCTRApplication.class).debug("uuid : "
        // + UUID.randomUUID());
        // }
        System.setProperty("javafx.preloader", SplashScreenLoader.class.getCanonicalName());
        // LauncherImpl.launchApplication(CalendrierCTRApplication.class,
        // SplashScreenLoader.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mLogger.debug("Démarrage de l'application");
        mStage = primaryStage;
        // Chargement du FXML
        final URL url = CalendrierCTRApplication.class.getResource("/CalendrierCTR.fxml");
        if (url == null){
            String msg = "Problème de chargement du FXML";
            throw new FileNotFoundException(msg);
        }
        mLogger.debug("Chemin du FXML : "+url.getPath());
        // Création du loader.
        final FXMLLoader fxmlLoader = new FXMLLoader(url);
        // Chargement du FXML.
        try {
            mScene = new Scene(fxmlLoader.load()/* , Color.TRANSPARENT */);
            mCtrl.setFXController((CalendrierCTRFXController) fxmlLoader.getController());
            mCtrl.initIHM();
        } catch (IOException e) {
            mLogger.error("Erreur de chargement du fichier FXML : " + url.getPath(), e);
            throw e;
        }
        // mScene.getStylesheets().add("resources/applicationview.css");
        mScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) {
                    System.out.println("Key Pressed: " + ke.getCode());
                    mStage.close();
                }
            }
        });
        // mStage.setResizable(false);
        // mStage.initStyle(StageStyle.TRANSPARENT);
        mStage.setScene(mScene);
        mStage.setTitle(CalendrierCTRApplication.APPLICATION_TITLE);
        mStage.show();
        // mCtrl.initEvenementsListe();
        notifyPreloader(new ProgressNotification(1));
        this.notifyPreloader(new StateChangeNotification(null));
        mCtrl.manageErrors();
    }

    @Override
    public void init() {
        try {
            mCtrl = new CalendrierCTRController();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            mLogger.error("Erreur dans le controller", e);
        }
        notifyPreloader(new ProgressNotification(0));
        notifyPreloader(new ProgressNotification(0.5));
        mCtrl.init(this);
        notifyPreloader(new ProgressNotification(0.8));
    }

    @Override
    public void stop() throws Exception {
        // Ici on va sauvegarder la BDD
        mCtrl.saveBDD();
    }

}
