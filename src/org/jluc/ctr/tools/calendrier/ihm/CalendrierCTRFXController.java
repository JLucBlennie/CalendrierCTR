package org.jluc.ctr.tools.calendrier.ihm;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jluc.ctr.tools.calendrier.model.Calendrier;
import org.jluc.ctr.tools.calendrier.model.ClubStructure;
import org.jluc.ctr.tools.calendrier.model.Demandeur;
import org.jluc.ctr.tools.calendrier.model.Evenement;
import org.jluc.ctr.tools.calendrier.model.Moniteur;
import org.jluc.ctr.tools.calendrier.model.TypeActivite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class CalendrierCTRFXController {

    private Logger mLogger = LogManager.getLogger(CalendrierCTRFXController.class);
    private CalendrierCTRController mController;

    @FXML
    private Button mModifyBtn;

    @FXML
    private Button mApplyFilterBtn;

    @FXML
    private Button mCancelBtn;

    @FXML
    private TextField mContactTxt;

    @FXML
    private TextArea mCommentTextArea;

    @FXML
    private DatePicker mDateDebutPicker;

    @FXML
    private DatePicker mDateDemandePicker;

    @FXML
    private DatePicker mDateFinPicker;

    @FXML
    private ComboBox<Moniteur> mDelegueCTRCmb;

    @FXML
    private ComboBox<Demandeur> mDemandeurCmb;

    @FXML
    private GridPane mEventEditorPane;

    @FXML
    private TableView<Evenement> mEventTableView;

    @FXML
    private ComboBox<TypeActivite> mFiltreActiviteCmb;

    @FXML
    private ComboBox<Demandeur> mFiltreDemandeurCmb;

    @FXML
    private TextArea mLieuTxt;

    @FXML
    private Button mOkBtn;

    @FXML
    private TableView<Evenement> mEventConflitTableView;

    @FXML
    private ComboBox<ClubStructure> mOrganisateurCmb;

    @FXML
    private ComboBox<Demandeur> mPartenaireCmb;

    @FXML
    private ComboBox<Moniteur> mPresidentJuryCmb;

    @FXML
    private ComboBox<Moniteur> mRepCIBPLCmb;

    @FXML
    private TableColumn<Evenement, String> mDemandeurColumn;

    @FXML
    private TableColumn<Evenement, String> mLieuColumn;

    @FXML
    private TableColumn<Evenement, String> mTypeColumn;

    @FXML
    private TableColumn<Evenement, String> mDateDebutColumn;

    @FXML
    private TableColumn<Evenement, String> mDateFinColumn;

    @FXML
    private TableColumn<Evenement, String> mEventConflitDateFinColumn;

    @FXML
    private TableColumn<Evenement, String> mEventConflitDateDebutColumn;

    @FXML
    private TableColumn<Evenement, String> mEventConflitDemandeurColumn;

    @FXML
    private TableColumn<Evenement, String> mEventConflitLieuColumn;

    @FXML
    private TableColumn<Evenement, String> mEventConflitTypeColumn;

    @FXML
    public void initialize() {
    }

    public void init(CalendrierCTRController controller) {
        mController = controller;
        Calendrier model = controller.getModel();
        ObservableList<Evenement> data = FXCollections.observableArrayList(model.getEventsList());
        FilteredList<Evenement> filteredEvents = new FilteredList<>(data, evenement -> {
            Date today = new Date();
            return evenement.getDateDebut().after(today);
        });
        SortedList<Evenement> sortedEvents = new SortedList<>(filteredEvents);
        sortedEvents.comparatorProperty().bind(mEventTableView.comparatorProperty());
        mTypeColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("typeFX"));
        mDemandeurColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("demandeurFX"));
        mLieuColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieuFX"));
        mDateDebutColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateDebutFX"));
        mDateDebutColumn.setComparator(new Comparator<String>() {

            @Override
            public int compare(String t, String t1) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date d1 = format.parse(t);
                    Date d2 = format.parse(t1);
                    return Long.compare(d1.getTime(), d2.getTime());
                } catch (ParseException p) {
                    p.printStackTrace();
                }
                return -1;
            }
        });
        mDateFinColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateFinFX"));
        mDateFinColumn.setComparator(new Comparator<String>() {

            @Override
            public int compare(String t, String t1) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date d1 = format.parse(t);
                    Date d2 = format.parse(t1);
                    return Long.compare(d1.getTime(), d2.getTime());
                } catch (ParseException p) {
                    p.printStackTrace();
                }
                return -1;
            }
        });
        mEventEditorPane.setVisible(false);

        mEventTableView.setItems(sortedEvents);

        mEventTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                if (mEventTableView.getSelectionModel().getSelectedItem() != null) {
                    Evenement eventSelected = mEventTableView.getSelectionModel().getSelectedItem();
                    // criticalEvenement.set(eventSelected);
                    model.resetSelected();
                    eventSelected.setSelected(true);
                    fillEventEditor(model, eventSelected);
                }

            }
        });
        mEventTableView.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DOWN:
                case UP:
                    if (mEventTableView.getSelectionModel().getSelectedItem() != null) {
                        Evenement eventSelected = mEventTableView.getSelectionModel().getSelectedItem();
                        // criticalEvenement.set(eventSelected);
                        model.resetSelected();
                        eventSelected.setSelected(true);
                        fillEventEditor(model, eventSelected);
                    }
                    break;
                default:
                    break;
            }
        });

        // mEventTableView.setRowFactory(tableView -> new TableRow<Evenement>()
        // {
        // @Override
        // protected void updateItem(Evenement item, boolean empty) {
        // BooleanBinding critical =
        // itemProperty().isEqualTo(criticalEvenement).and(itemProperty().isNotNull());
        // styleProperty().bind(Bindings.when(critical).then("-fx-background-color:
        // red ;").otherwise(item != null ? getStyleFromType(item) : ""));
        // }
        // });

        mDemandeurCmb.setItems(FXCollections.observableArrayList(Calendrier.getInstance().DEMANDEURS).sorted());
        mPartenaireCmb.setItems(FXCollections.observableArrayList(Calendrier.getInstance().DEMANDEURS).sorted());
        mOrganisateurCmb.setItems(FXCollections.observableArrayList(Calendrier.getInstance().CLUBSTRUCTURES).sorted());
        mDelegueCTRCmb.setItems(FXCollections.observableArrayList(Calendrier.getInstance().MONITEURS).sorted());
        mPresidentJuryCmb.setItems(FXCollections.observableArrayList(Calendrier.getInstance().MONITEURS).sorted());
        mRepCIBPLCmb.setItems(FXCollections.observableArrayList(Calendrier.getInstance().MONITEURS).sorted());

        List<Demandeur> filtreDemandeurList = new ArrayList<Demandeur>();
        filtreDemandeurList.add(new Demandeur("Tous", ""));
        filtreDemandeurList.addAll(Calendrier.getInstance().DEMANDEURS);
        ObservableList<Demandeur> filtreDemandeur = FXCollections.observableArrayList(filtreDemandeurList);
        mFiltreDemandeurCmb.setItems(filtreDemandeur);
        mFiltreDemandeurCmb.setValue(filtreDemandeurList.get(0));

        ObservableList<TypeActivite> filtreActivite = FXCollections.observableArrayList(Arrays.asList(TypeActivite.values())).sorted();
        mFiltreActiviteCmb.setItems(filtreActivite);
        mFiltreActiviteCmb.setValue(TypeActivite.ALL);

        mApplyFilterBtn.setOnAction(event -> {
            filteredEvents.setPredicate(evenement -> {
                Date today = new Date();
                boolean isDateOK = evenement.getDateDebut().after(today);
                boolean isDemandeurOK = mFiltreDemandeurCmb.getValue().getName().equals("Tous") || evenement.getDemandeur().equals(mFiltreDemandeurCmb.getValue());
                boolean isActiviteOK = mFiltreActiviteCmb.getValue().equals(TypeActivite.ALL) || evenement.getType().getActivite().equals(mFiltreActiviteCmb.getValue());

                return isDateOK && isDemandeurOK && isActiviteOK;
            });
        });

        mModifyBtn.setOnAction(event -> {
            // Sauvegarde de l'evenement
            Evenement eventSelected = mEventTableView.getSelectionModel().getSelectedItem();
            modifiyEvenement(eventSelected);
        });

        mOkBtn.setOnAction(event -> {
            Evenement eventSelected = mEventTableView.getSelectionModel().getSelectedItem();
            modifiyEvenement(eventSelected);
            validateEvent(eventSelected);
        });
    }

    public void manageErrors() {
        // Gestion des erreurs lors du chargement
        if (!mController.getModel().ERRORS.isEmpty()) {
            // Il y a des errerus
            String msg = "Problème durant la lecture des évènements :";
            for (String erreur : mController.getModel().ERRORS) {
                msg += "\n" + erreur;
            }
            Alert alert = new Alert(AlertType.ERROR, msg, ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void validateEvent(Evenement eventSelected) {
        try {
            mController.validateEvenement(eventSelected);
            Alert alert = new Alert(AlertType.INFORMATION, "Ajout de l'évènement dans le calendrier CTR", ButtonType.OK);
            alert.showAndWait();

        } catch (IOException e) {
            mLogger.error("Probleme durant l'ajout de l'evenement dans le calendrier CTR", e);
            Alert alert = new Alert(AlertType.ERROR, "Problème durant l'ajout de l'évènement dans le calendrier CTR", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void modifiyEvenement(Evenement eventSelected) {
        eventSelected.setDateDebut(Date.from(mDateDebutPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        eventSelected.setDateFin(Date.from(mDateFinPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        eventSelected.setLieu(mLieuTxt.getText());
        eventSelected.setDemandeur(mDemandeurCmb.getValue());
        eventSelected.setComment(mCommentTextArea.getText());

        eventSelected.setPartenaire(mPartenaireCmb.getValue());
        eventSelected.setPresidentJury(mPresidentJuryCmb.getValue());
        eventSelected.setDelegueCTR(mDelegueCTRCmb.getValue());
        eventSelected.setRepCIBPL(mRepCIBPLCmb.getValue());
        eventSelected.setOrganisateur(mOrganisateurCmb.getValue());
    }

    private String getStyleFromType(Evenement evenement) {
        switch (evenement.getType().getActivite()) {
            case N4_GP:
                return "-fx-background-color: #baffba;";
            case HANDISUB:
                return "-fx-background-color: #ffd388;";
            case INITIATEUR:
                return "-fx-background-color: #ffffba;";
            case MF1:
                return "-fx-background-color: #baffff;";
            case MF2:
                return "-fx-background-color: #d3ff88;";
            case SECOURISME:
                return "-fx-background-color: #88d3ff;";
            case TIV:
                return "-fx-background-color: #ba88d3;";
            case TSI:
                return "-fx-background-color: #ba88ba;";
            default:
                return "";
        }
    }

    private void fillEventEditor(Calendrier model, Evenement eventSelected) {
        mEventEditorPane.setVisible(true);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        mDateDebutPicker.setValue(LocalDate.parse(CalendrierCTRController.DATE_FORMAT.format(eventSelected.getDateDebut()), formatter));
        mDateFinPicker.setValue(LocalDate.parse(CalendrierCTRController.DATE_FORMAT.format(eventSelected.getDateFin()), formatter));
        mDateDemandePicker.setValue(LocalDate.parse(CalendrierCTRController.DATE_FORMAT.format(eventSelected.getDateDemande()), formatter));
        mLieuTxt.setText(eventSelected.getLieu());
        mDemandeurCmb.setValue(eventSelected.getDemandeur());
        mCommentTextArea.setText(eventSelected.getComment());

        if (eventSelected.getPartenaire() != null)
            mPartenaireCmb.setValue(eventSelected.getPartenaire());
        else
            mPartenaireCmb.getSelectionModel().clearSelection();

        if (eventSelected.getPresidentJury() != null)
            mPresidentJuryCmb.setValue(eventSelected.getPresidentJury());
        else
            mPresidentJuryCmb.getSelectionModel().clearSelection();

        if (eventSelected.getDelegueCTR() != null)
            mDelegueCTRCmb.setValue(eventSelected.getDelegueCTR());
        else
            mDelegueCTRCmb.getSelectionModel().clearSelection();

        if (eventSelected.getRepCIBPL() != null)
            mRepCIBPLCmb.setValue(eventSelected.getRepCIBPL());
        else
            mRepCIBPLCmb.getSelectionModel().clearSelection();

        if (eventSelected.getOrganisateur() != null)
            mOrganisateurCmb.setValue(eventSelected.getOrganisateur());
        else {
            mOrganisateurCmb.getSelectionModel().clearSelection();
        }

        ObservableList<Evenement> data = FXCollections.observableArrayList(model.getEventsList());
        FilteredList<Evenement> filteredEvents = new FilteredList<>(data, evenement -> {
            Date today = new Date();
            boolean isDateOK = evenement.getDateDebut().after(today);
            boolean isConflitOK = (evenement.getDateDebut().before(eventSelected.getDateFin()) && evenement.getDateFin().after(eventSelected.getDateFin()))
                    || (evenement.getDateDebut().before(eventSelected.getDateDebut()) && evenement.getDateFin().after(eventSelected.getDateDebut()))
                    || (evenement.getDateDebut().after(eventSelected.getDateDebut()) && evenement.getDateFin().before(eventSelected.getDateFin()));

            return isDateOK && isConflitOK;
        });
        SortedList<Evenement> sortedEvents = new SortedList<>(filteredEvents);
        sortedEvents.comparatorProperty().bind(mEventConflitTableView.comparatorProperty());
        mEventConflitTypeColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("typeFX"));
        mEventConflitDemandeurColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("demandeurFX"));
        mEventConflitLieuColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieuFX"));
        mEventConflitDateDebutColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateDebutFX"));
        mEventConflitDateFinColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateFinFX"));

        mEventConflitTableView.setItems(sortedEvents);
    }
}
