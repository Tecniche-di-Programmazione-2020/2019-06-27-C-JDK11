/**
 * Sample Skeleton for 'Crimes.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.Arco;
import it.polito.tdp.crimes.model.Model;
import it.polito.tdp.crimes.model.Tempo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class CrimesController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxCategoria"
    private ComboBox<String> boxCategoria; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Tempo> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="boxArco"
    private ComboBox<Arco> boxArco; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	if(boxCategoria.getValue()==null)txtResult.setText("Inserisci una categoria");
    	else if(boxGiorno.getValue()==null)txtResult.setText("Inserisci una data");
    	else {
    	txtResult.clear();
    	txtResult.appendText("Crea grafo...\n");
    	
    	List<Arco> stampa=model.creaGrafo(boxCategoria.getValue(), boxGiorno.getValue());
    	if(stampa.size()==0) {txtResult.appendText("Nessun arco...\n");}
    	else {
    		for(Arco a:stampa) {txtResult.appendText(a.toString()+"\n");
    		boxArco.getItems().add(a);}
    	}
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola percorso...\n");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCategoria != null : "fx:id=\"boxCategoria\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxArco != null : "fx:id=\"boxArco\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Crimes.fxml'.";
        //boxCategoria.getItems().addAll(model.getListOfCategories());
        //boxGiorno.getItems().addAll(model.getListOfDays());
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.inizializza();
    }
    
    private void inizializza() {
    	boxCategoria.getItems().addAll(model.getListOfCategories());
        boxGiorno.getItems().addAll(model.getListOfDays());
    	
    }
}
