package org.jluc.ctr.tools.calendrier.ihm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jluc.ctr.tools.calendrier.model.Calendrier;
import org.jluc.ctr.tools.calendrier.model.ClubStructure;
import org.jluc.ctr.tools.calendrier.model.Demandeur;
import org.jluc.ctr.tools.calendrier.model.Evenement;
import org.jluc.ctr.tools.calendrier.model.Moniteur;
import org.jluc.ctr.tools.calendrier.model.TypeActivite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class CalendrierCTRFXController {

//	private Logger mLogger = LogManager.getLogger(CalendrierCTRFXController.class);

	@FXML
	private Button mApplyFilterBtn;

	@FXML
	private Button mCancelBtn;

	@FXML
	private TextField mContactTxt;

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
	private TableColumn<Evenement, String> mLieuColoumn;

	@FXML
	private TableColumn<Evenement, String> mTypeColumn;

	@FXML
	private TableColumn<Evenement, String> mDateDebutColumn;

	@FXML
	private TableColumn<Evenement, String> mDateFinColumn;

	public void init(Calendrier model) {
		ObservableList<Evenement> data = FXCollections.observableArrayList(model.getEventsList());
		mTypeColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("typeFX"));
		mDemandeurColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("demandeurFX"));
		mLieuColoumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieuFX"));
		mDateDebutColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateDebutFX"));
		mDateFinColumn.setCellValueFactory(new PropertyValueFactory<Evenement, String>("dateFinFX"));

		mEventTableView.setItems(data);

		mEventTableView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1) {
				if (mEventTableView.getSelectionModel().getSelectedItem() != null) {
					Evenement eventSelected = mEventTableView.getSelectionModel().getSelectedItem();
					fillEventEditor(eventSelected);
				}

			}
		});

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

		ObservableList<TypeActivite> filtreActivite = FXCollections
				.observableArrayList(Arrays.asList(TypeActivite.values())).sorted();
		mFiltreActiviteCmb.setItems(filtreActivite);
		mFiltreActiviteCmb.setValue(TypeActivite.ALL);

		mApplyFilterBtn.setOnAction(event -> {
// TODO : Récupération des filtres et application pour réduire la liste
		});
	}

	private void fillEventEditor(Evenement eventSelected) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		mDateDebutPicker.setValue(
				LocalDate.parse(CalendrierCTRController.DATE_FORMAT.format(eventSelected.getDateDebut()), formatter));
		mDateFinPicker.setValue(
				LocalDate.parse(CalendrierCTRController.DATE_FORMAT.format(eventSelected.getDateFin()), formatter));
		mDateDemandePicker.setValue(
				LocalDate.parse(CalendrierCTRController.DATE_FORMAT.format(eventSelected.getDateDemande()), formatter));
		mLieuTxt.setText(eventSelected.getLieu());
		mDemandeurCmb.setValue(eventSelected.getDemandeur());

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

	}
}
