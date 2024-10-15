package org.jluc.ctr.tools.calendrier.ihm;

import org.jluc.ctr.tools.calendrier.model.Evenement;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ApplicationView extends VBox {
    private TableView<Evenement> mEventsTable = new TableView<Evenement>();

    public ApplicationView() {
        this.setPrefSize(1304, 743);
        this.setBackground(Background.EMPTY);
        Label lblIntro = new Label("Liste des dates demandées par les CTD et Structures de BPL");
        VBox.setVgrow(lblIntro, Priority.ALWAYS);
        lblIntro.getStyleClass().add("intro");
        this.getChildren().add(lblIntro);
        VBox.setVgrow(mEventsTable, Priority.ALWAYS);
        this.getChildren().add(mEventsTable);
    }

    public TableView<Evenement> getEventsTable() {
        return mEventsTable;
    }
}
