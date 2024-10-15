package org.jluc.ctr.tools.calendrier.ihm;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreenLoader extends Preloader {
    /** the mSplash screen stage of this mSplash screen loader */
    private Stage mSplashScreen;

    /** the spash scene of this mSplash screen loader */
    private Scene mSplashScene;

    private SplashView mSplash;

    /**
     * Initializes this mSplash screen loader loading the mSplash view and
     * caching the mSplash images.
     * 
     * @see Application#init()
     */
    @Override
    public void init() throws Exception {
        mSplash = new SplashView();
        mSplashScene = new Scene(mSplash);
        mSplashScene.setFill(Color.TRANSPARENT);
    }

    /**
     * Starts this mSplash screen loader.
     * 
     * @see Preloader#start(Stage)
     */
    @Override
    public void start(Stage stage) throws Exception {
        mSplashScreen = stage;
        mSplashScreen.initStyle(StageStyle.TRANSPARENT);
        mSplashScreen.setScene(this.mSplashScene);
        mSplashScreen.setAlwaysOnTop(true);
        mSplashScreen.show();
    }

    /**
     * Handles an application notification which closes this mSplash screen
     * loader once the application has loaded.
     * 
     * @see Preloader#handleApplicationNotification(PreloaderNotification)
     */
    @Override
    public void handleApplicationNotification(PreloaderNotification notification) {
        if (notification instanceof StateChangeNotification) {
            mSplashScreen.close();
        }

        if (notification instanceof ProgressNotification) {
            ProgressNotification pn = (ProgressNotification) notification;
            mSplash.updateProgress(pn.getProgress());
        }
    }
}
