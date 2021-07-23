package gui;

import automat.*;
import io.JOSPersistence;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;

public class Controller {

    AutomatVerwaltung automatVerwaltung;
    AutomatPlaceHolder automat;
    JOSPersistence persistence;


    @FXML
    private RadioButton kremkuchenButton;
    @FXML
    private RadioButton obstkuchenButton;
    @FXML
    private RadioButton obsttorteButton;
    @FXML
    private TextField herstellerNameField;
    @FXML
    private CheckBox erdnuss;
    @FXML
    private CheckBox gluten;
    @FXML
    private CheckBox haselnuss;
    @FXML
    private CheckBox seasem;
    @FXML
    private TextField naehrwertTextField;
    @FXML
    private TextField haltbarkeitTextField;
    @FXML
    private TextField preisTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField fachnummerToRemoveTextField;
    @FXML
    private TextField fachnummerToInspectTextField;
    @FXML
    private Button addCakeButton;
    @FXML
    private Button removeCakeButton;
    @FXML
    private Button inspectButton;
    @FXML
    private TextField herstellerTextField;
    @FXML
    private Button addHersteller;
    @FXML
    private Button removeHersteller;
    @FXML
    private Button allergeneEnthalten;
    @FXML
    private Button allergemeNichtEnthalten;
    @FXML
    private Button saveJOSButton;
    @FXML
    private Button loadJOSButton;
    @FXML
    private ListView cakeListView;
    @FXML
    private Label message;
    @FXML
    private Label allergenenEnthaltenLabel;
    @FXML
    private Label allergenenNichtEntaltenLabel;
    @FXML
    private TableView<KuchenTabelle> kuchenTableView;
    @FXML
    private TableColumn<KuchenTabelle, Integer> fachnummerColumm;
    @FXML
    private TableColumn<KuchenTabelle, String> herstellerColumm;
    @FXML
    private TableColumn<KuchenTabelle, String> inspektionsdatumColumm;
    @FXML
    private TableColumn<KuchenTabelle, String> kuchentypColumm;
    @FXML
    private TableColumn<KuchenTabelle, Long> haltbarkeitColumm;
    @FXML
    private TableView<HerstellerTabelle> herstellerTableView;
    @FXML
    private TableColumn<HerstellerTabelle, Integer> kuchenAnzahlColumm;
    @FXML
    private TableColumn<HerstellerTabelle, String> herstellerNameColumm;
    @FXML
    private RadioButton kremkuchenListRadioButton;
    @FXML
    private RadioButton obstkuchenListRadioButton;
    @FXML
    private RadioButton obsttorteListRadioButton;
    @FXML
    private RadioButton allListRadioButton;



    private StringProperty herstellerName = new SimpleStringProperty();
    private StringProperty hersteller = new SimpleStringProperty();
    private StringProperty preis = new SimpleStringProperty();
    private StringProperty naehrwert = new SimpleStringProperty();
    private StringProperty haltbarkeit = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty fachnummerToRemove = new SimpleStringProperty();
    private StringProperty fachnummerToInspect = new SimpleStringProperty();
    private ObservableList<KuchenTabelle> tabelleKuchenItems;
    private ObservableList<HerstellerTabelle> herstellerTabelleItems;


    public String getHerstellerName() {
        return herstellerName.get();
    }

    public String getHersteller(){
        return hersteller.get();
    }

    public void setHersteller(String hersteller){
        this.hersteller.set(hersteller);
    }

    public void setHerstellerName(String herstellerName) {
        this.herstellerName.set(herstellerName);
    }

    public String getPreis() {
        return preis.get();
    }

    public StringProperty preisProperty() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis.set(preis);
    }

    public String getHaltbarkeit() {
        return haltbarkeit.get();
    }

    public StringProperty haltbarkeitProperty() {
        return haltbarkeit;
    }

    public void setHaltbarkeit(String haltbarkeit) {
        this.haltbarkeit.set(haltbarkeit);
    }


    public String getNaehrwert() {
        return naehrwert.get();
    }

    public StringProperty naehrwertProperty() {
        return naehrwert;
    }

    public void setNaehrwert(String naehrwert) {
        this.naehrwert.set(naehrwert);
    }

    public String getFachnummerToRemove() {
        return fachnummerToRemove.get();
    }

    public StringProperty fachnummerToRemoveProperty() {
        return fachnummerToRemove;
    }

    public void setFachnummerToRemove(String fachnummerToRemove) {
        this.fachnummerToRemove.set(fachnummerToRemove);
    }

    public String getFachnummerToInspect() {
        return fachnummerToInspect.get();
    }

    public StringProperty fachnummerToInspectProperty() {
        return fachnummerToInspect;
    }

    public void setFachnummerToInspect(String fachnummerToInspect) {
        this.fachnummerToInspect.set(fachnummerToInspect);
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    private String CAKEADDED = "Kuchen wurde hinzugefügt";
    private String CAKEADDEDERROR = "Kuchen könnte nicht hinzugefügt werden";
    private String CAKEREMOVED = "Kuchen wurde geloscht";
    private String CAKEAREMOVEDERROR = "Kuchen könnte nicht geloscht werden";
    private String HERSTELLERADDED = "Hersteller wurde hinzugefügt";
    private String HERSTELLERADDEDERROR = "Hersteller könnte nicht hinzugefügt werden";
    private String  HERSTELLERREMOVED = "Hersteller wurde geloscht";
    private String HERSTELLERREMOVEDERROR = "Hersteller könnte nicht geloscht werden";
    private String CAKEINSPECTED = "Kuchen wurde inspiziert";




    @FXML private void initialize() throws AutomatException {
        this.automatVerwaltung =  new AutomatVerwaltung(20);
        this.automat = new AutomatPlaceHolder(automatVerwaltung);
        this.persistence = new JOSPersistence(automat);
        this.herstellerNameField.textProperty().bindBidirectional(this.herstellerName);
        this.herstellerTextField.textProperty().bindBidirectional(this.hersteller);
        this.preisTextField.textProperty().bindBidirectional(this.preis);
        this.naehrwertTextField.textProperty().bindBidirectional(this.naehrwert);
        this.haltbarkeitTextField.textProperty().bindBidirectional(this.haltbarkeit);
        this.nameTextField.textProperty().bindBidirectional(this.name);
        this.fachnummerToRemoveTextField.textProperty().bindBidirectional(fachnummerToRemove);
        this.fachnummerToInspectTextField.textProperty().bindBidirectional(fachnummerToInspect);

        herstellerTabelleItems = FXCollections.observableArrayList();
        tabelleKuchenItems = FXCollections.observableArrayList();

        herstellerNameColumm.setCellValueFactory(new PropertyValueFactory<HerstellerTabelle, String>("herstellerName"));
        kuchenAnzahlColumm.setCellValueFactory(new PropertyValueFactory<HerstellerTabelle, Integer>("kuchenAnzahl"));


        fachnummerColumm.setCellValueFactory(new PropertyValueFactory<KuchenTabelle, Integer>("fachnummer"));
        herstellerColumm.setCellValueFactory(new PropertyValueFactory<KuchenTabelle, String>("herstellerName"));
        inspektionsdatumColumm.setCellValueFactory(new PropertyValueFactory<KuchenTabelle, String>("inspektionsdatum"));
        haltbarkeitColumm.setCellValueFactory(new PropertyValueFactory<KuchenTabelle,Long>("haltbarkeit"));
        kuchentypColumm.setCellValueFactory(new PropertyValueFactory<KuchenTabelle, String>("kuchentyp"));
        kuchenTableView.setItems(tabelleKuchenItems);
        herstellerTableView.setItems(herstellerTabelleItems);



    }

    @FXML
    public Kuchentyp selectCakeType() {

       if (obstkuchenButton.isSelected()) {
            return Kuchentyp.OBSTKUCHEN;

        } else if (kremkuchenButton.isSelected()) {
            return Kuchentyp.KREMKUCHEN;

        } else if (obsttorteButton.isSelected()) {
           return Kuchentyp.OBSTTORTE;
        }

       return null;

        }


    @FXML
    public void onAddCake() throws InterruptedException, AutomatException {

            try {
               Kuchentyp kuchentyp= selectCakeType();

                Collection<Allergen> allergenCollection = new LinkedList<>();

                if (erdnuss.isSelected()) {
                    allergenCollection.add(Allergen.Erdnuss);
                }
                if (haselnuss.isSelected()) {
                    allergenCollection.add(Allergen.Haselnuss);
                }
                if (gluten.isSelected()) {
                    allergenCollection.add(Allergen.Gluten);
                }
                if (seasem.isSelected()) {
                    allergenCollection.add(Allergen.Sesamsamen);
                }

                KuchenKomponent kuchenBoden = new KuchenBoden(kuchentyp, new HerstellerImpl(getHerstellerName()), BigDecimal.valueOf(0), 0, Duration.ofDays(1000), new HashSet<>());
                KuchenKomponent kuchenBelag = new KuchenBelag(kuchenBoden, new BigDecimal(getPreis()), Integer.parseInt(getNaehrwert()), Duration.ofDays(Long.parseLong(getHaltbarkeit())), allergenCollection, getName());
                this.automat.getAutomat().addKuchen(kuchenBelag);

                
                herstellerNameField.clear();
                preisTextField.clear();
                naehrwertTextField.clear();
                haltbarkeitTextField.clear();
                erdnuss.setSelected(false);
                gluten.setSelected(false);
                seasem.setSelected(false);
                haselnuss.setSelected(false);
                nameTextField.clear();

                message.setTextFill(Color.GREEN);
                message.setText(CAKEADDED);

                onListCakes();
                onListHersteller();

            } catch (Exception e) {
                message.setTextFill(Color.RED);
                message.setText(CAKEADDEDERROR);
            }

        }

    @FXML
    public void onRemoveCake() throws InterruptedException, AutomatException {

        try {
            this.automat.getAutomat().removeKuchen(Integer.parseInt(getFachnummerToRemove()));
            onListCakes();
            fachnummerToRemoveTextField.clear();
            message.setTextFill(Color.GREEN);
            message.setText(CAKEREMOVED);
        } catch (Exception e){
            message.setTextFill(Color.RED);
            message.setText(CAKEAREMOVEDERROR);
        }


    }

    @FXML
    public void onInspectCake() {
        try {
            this.automat.getAutomat().inspectCake(Integer.parseInt(getFachnummerToInspect()));
            onListCakes();
            fachnummerToInspectTextField.clear();
            message.setTextFill(Color.GREEN);
            message.setText(CAKEINSPECTED);
        } catch (Exception e){
            message.setTextFill(Color.RED);
            message.setText(CAKEAREMOVEDERROR);
        }
    }


    @FXML
    private void onListCakes() throws AutomatException {

        KuchenTabelle cakeTabelle = null;
        tabelleKuchenItems.clear();

            if (allListRadioButton.isSelected()) {
                for (int i = 0; i < automat.getAutomat().getCakeList().length; i++) {
                    if (this.automat.getAutomat().getCakeList()[i] != null) {
                        KuchenKomponent cake = this.automat.getAutomat().getCakeList()[i];
                        cakeTabelle = new KuchenTabelle(cake.getHersteller().getName(), cake.getKuchentyp().toString(), cake.getFachnummer(), cake.getInspektionsdatum().toString(), cake.getVerbliebeneHaltbarkeit().toDays());
                        if (!tabelleKuchenItems.contains(cakeTabelle)) {
                            tabelleKuchenItems.add(cakeTabelle);
                        }
                    }

                }
            } else if (kremkuchenListRadioButton.isSelected()) {
                listKuchentyp(Kuchentyp.KREMKUCHEN, cakeTabelle);

            } else if (obstkuchenListRadioButton.isSelected()) {
                listKuchentyp(Kuchentyp.OBSTKUCHEN, cakeTabelle);

            } else if (obsttorteListRadioButton.isSelected()) {
                listKuchentyp(Kuchentyp.OBSTTORTE, cakeTabelle);

            }
        }


    private void listKuchentyp(Kuchentyp kuchentyp, KuchenTabelle cakeTabelle){
        KuchenKomponent[] kuchenKomponents = automat.getAutomat().getAlleKuchenEinesTyps(kuchentyp.toString());
        for (int i = 0; i < kuchenKomponents.length; i++) {
            if (kuchenKomponents[i] != null) {
                KuchenKomponent cake = kuchenKomponents[i];
                cakeTabelle = new KuchenTabelle(cake.getHersteller().getName(), cake.getKuchentyp().toString(), cake.getFachnummer(), cake.getInspektionsdatum().toString(), cake.getVerbliebeneHaltbarkeit().toDays());
                if (!tabelleKuchenItems.contains(cakeTabelle)) {
                    tabelleKuchenItems.add(cakeTabelle);
                }
            }
        }
    }

     @FXML
        private void onListHersteller() throws AutomatException {

         HerstellerTabelle herstellerTabelle;
         herstellerTabelleItems.clear();

         for (Hersteller hersteller : this.automat.getAutomat().getHerstellerListMitKuchenAnzahl().keySet()) {
             String herstellername = hersteller.getName();
             int anzahlKuchen = this.automat.getAutomat().getHerstellerListMitKuchenAnzahl().get(hersteller);

             herstellerTabelle = new HerstellerTabelle(herstellername,anzahlKuchen);

             if (!herstellerTabelleItems.contains(herstellerTabelle)) {
                 herstellerTabelleItems.add(herstellerTabelle);
             }
         }

    }

         public void onAddHersteller () {

        try{
            Hersteller h = new HerstellerImpl(getHersteller());
            this.automat.getAutomat().addHersteller(h);
            onListHersteller();
            herstellerTextField.clear();
            message.setTextFill(Color.GREEN);
            message.setText(HERSTELLERADDED);

        } catch (AutomatException e) {
            message.setTextFill(Color.RED);
            message.setText(HERSTELLERADDEDERROR); }

         }

    public void onRemoveHersteller() {

        try {
            this.automat.getAutomat().removeHersteller(getHersteller());
            onListHersteller();
            herstellerTextField.clear();
            message.setTextFill(Color.GREEN);
            message.setText(HERSTELLERREMOVED);

        } catch (AutomatException e) {
            message.setTextFill(Color.RED);
            message.setText(HERSTELLERREMOVEDERROR); }
    }

    public void allergeneEnthalten() {
        EnumSet<Allergen>  allergens = this.automat.getAutomat().getVorhandeneAllergene();
        this.allergenenEnthaltenLabel.setText(allergens.toString());
    }

    public void allergeneNichtEnthalten() {
        EnumSet<Allergen>  allergens = this.automat.getAutomat().getNichtVorhandeneAllergene();
        this.allergenenNichtEntaltenLabel.setText(allergens.toString());
    }

    public void saveJOS() throws IOException {
        persistence.serialize();
    }

    public void loadJOS() throws AutomatException, IOException {
        persistence.deserialize();
        onListHersteller();
        onListCakes();
    }
}


