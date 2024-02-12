package org.jluc.ctr.tools.calendrier.ihm;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class SplashView extends VBox {

    private static final String SPLASH_IMAGE_PATH = "./resources/images/logo.png";
    private ImageView mImageView;
    private ProgressBar mProgressBar = new ProgressBar();

    public SplashView() {
            this.setBackground(Background.EMPTY);
            mImageView = new ImageView(SPLASH_IMAGE_PATH);
            this.getChildren().add(mImageView);
            this.getChildren().add(mProgressBar);
            this.setAlignment(Pos.CENTER);
        }

    public void updateProgress(double value) {
        mProgressBar.setProgress(value);
    }
}
