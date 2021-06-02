package gui;

import automat.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class Controller {

    AutomatVerwaltung automat;


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
    private TextField obstsortTextField;
    @FXML
    private TextField kremsortTextField;
    @FXML
    private TextField fachnummerToRemoveTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private ListView cakeListView;
    @FXML
    private Label message;
    @FXML
    private TableView<KuchenTabelle> kuchenTableView;
    @FXML
    private TableColumn<KuchenTabelle, Integer> fachnummerColumm;
    @FXML
    private TableColumn<KuchenTabelle, String> herstellerColumm;
    @FXML
    private TableColumn<KuchenTabelle, String> inspektionsdatumColumm;
    @FXML
    private TableColumn<KuchenTabelle, Long> haltbarkeitColumm;



    private StringProperty herstellerName = new SimpleStringProperty();
    private StringProperty preis = new SimpleStringProperty();
    private StringProperty naehrwert = new SimpleStringProperty();
    private StringProperty haltbarkeit = new SimpleStringProperty();
    private StringProperty kremSort = new SimpleStringProperty();
    private StringProperty obstSort = new SimpleStringProperty();
    private StringProperty fachnummerToRemove = new SimpleStringProperty();
    private ObservableList<KuchenTabelle> tabelleItems;

    public String getHerstellerName() {
        return herstellerName.get();
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


    public String getKremSort() {
        return kremSort.get();
    }

    public StringProperty kremSortProperty() {
        return kremSort;
    }

    public void setKremSort(String kremSort) {
        this.kremSort.set(kremSort);
    }

    public String getObstSort() {
        return obstSort.get();
    }

    public StringProperty obstSortProperty() {
        return obstSort;
    }

    public void setObstSort(String obstSort) {
        this.obstSort.set(obstSort);
    }




    private String CAKEADDED = "Kuchen wurde hinzugefügt";
    private String CAKEADDEDERROR = "Kuchen könnte nicht hinzugefügt werden";
    private String CAKEREMOVED = "Kuchen wurde geloscht";
    private String CAKEAREMOVEDERROR = "Kuchen könnte nicht geloscht werden";



    @FXML private void initialize() throws AutomatException {
        this.automat =  new AutomatVerwaltung(20);
        this.automat.addHersteller(new HerstellerImpl("h1"));
        this.automat.addHersteller(new HerstellerImpl("h2"));
        this.herstellerNameField.textProperty().bindBidirectional(this.herstellerName);
        this.preisTextField.textProperty().bindBidirectional(this.preis);
        this.naehrwertTextField.textProperty().bindBidirectional(this.naehrwert);
        this.haltbarkeitTextField.textProperty().bindBidirectional(this.haltbarkeit);
        this.kremsortTextField.textProperty().bindBidirectional(this.kremSort);
        this.obstsortTextField.textProperty().bindBidirectional(this.obstSort);
        this.fachnummerToRemoveTextField.textProperty().bindBidirectional(fachnummerToRemove);

        tabelleItems = FXCollections.observableArrayList();
        fachnummerColumm.setCellValueFactory(new PropertyValueFactory<KuchenTabelle, Integer>("fachnummer"));
        herstellerColumm.setCellValueFactory(new PropertyValueFactory<KuchenTabelle, String>("herstellerName"));
        inspektionsdatumColumm.setCellValueFactory(new PropertyValueFactory<KuchenTabelle, String>("inspektionsdatum"));
        haltbarkeitColumm.setCellValueFactory(new PropertyValueFactory<KuchenTabelle,Long>("haltbarkeit"));
        kuchenTableView.setItems(tabelleItems);


        kremsortTextField.setDisable(false);
        obstsortTextField.setDisable(false);

    }

    @FXML
    public String selectCakeType() {

       if (obstkuchenButton.isSelected()) {
            kremsortTextField.setDisable(true);
            obstsortTextField.setDisable(false);
           String obstsorte = obstsortTextField.getText();
            return "Obstkuchen";

        } else if (kremkuchenButton.isSelected()) {
           obstsortTextField.setDisable(true);
           kremsortTextField.setDisable(false);
            String kremsorte = kremsortTextField.getText();
            return "Kremkuchen";

        } else if (obsttorteButton.isSelected()) {
            String obstsorte = obstsortTextField.getText();
            String kremsorte = kremsortTextField.getText();
           obstsortTextField.setDisable(false);
           kremsortTextField.setDisable(false);
           return "Obsttorte";
        }

       return null;

        }


    @FXML
    public void onAddCake() throws InterruptedException, AutomatException {

            try {

               String caketype = selectCakeType();

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

                if (caketype == "Obstkuchen") {
                    Cake obstkuchen = new ObstkuchenImpl(new HerstellerImpl(getHerstellerName()), new BigDecimal(getPreis()), Integer.parseInt(getNaehrwert()), Duration.ofDays(Long.parseLong(getHaltbarkeit())), allergenCollection, new Date(), -1, new Date(), getObstSort());
                    this.automat.addKuchen(obstkuchen);
                } else if (caketype == "Kremkuchen") {
                    Cake kremkuchen = new KremkuchenImpl(new HerstellerImpl(getHerstellerName()), new BigDecimal(getPreis()), Integer.parseInt(getNaehrwert()), Duration.ofDays(Long.parseLong(getHaltbarkeit())), allergenCollection, new Date(), -1, new Date(), getKremSort());
                    this.automat.addKuchen(kremkuchen);
                } else if (caketype == "Obsttorte") {
                    Cake obsttorte = new ObsttorteImpl(new HerstellerImpl(getHerstellerName()), new BigDecimal(getPreis()), Integer.parseInt(getNaehrwert()), Duration.ofDays(Long.parseLong(getHaltbarkeit())), allergenCollection, new Date(), -1, new Date(), getObstSort(), getKremSort());
                    this.automat.addKuchen(obsttorte);
                }

                herstellerNameField.clear();
                preisTextField.clear();
                naehrwertTextField.clear();
                haltbarkeitTextField.clear();
                erdnuss.setSelected(false);
                gluten.setSelected(false);
                seasem.setSelected(false);
                haselnuss.setSelected(false);
                obstsortTextField.clear();
                kremsortTextField.clear();

                message.setTextFill(Color.GREEN);
                message.setText(CAKEADDED);

                onListCakes();

            } catch (Exception e) {
                message.setTextFill(Color.RED);
                message.setText(CAKEADDEDERROR);
            }

        }

    @FXML
    public void onRemoveCake() throws InterruptedException, AutomatException {

        try {
            this.automat.removeKuchen(Integer.parseInt(getFachnummerToRemove()));
            onListCakes();
            fachnummerToRemoveTextField.clear();
            message.setTextFill(Color.GREEN);
            message.setText(CAKEREMOVED);
        } catch (Exception e){
            message.setTextFill(Color.RED);
            message.setText(CAKEAREMOVEDERROR);
        }


    }

    private void onListCakes() throws AutomatException {

        KuchenTabelle cakeTabelle;
        tabelleItems.clear();

        for (int i = 0; i < automat.getCakeList().length; i++) {

            if (this.automat.getCakeList()[i] != null) {
                Cake cake = this.automat.getCakeList()[i];

                cakeTabelle = new KuchenTabelle(cake.getHersteller().getName(), cake.getFachnummer(), cake.getInspektionsdatum().toString(), cake.getHaltbarkeit().toDays());

                if (!tabelleItems.contains(cakeTabelle)) {
                    tabelleItems.add(cakeTabelle);
                }
            }
        }




    }


}

