package it.polito.tdp.meteo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.bean.Model;
import it.polito.tdp.meteo.bean.Situazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MeteoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxCitta;

    @FXML
    private Button btnElencoDate;

    @FXML
    private TextField txtProbabilita;

    @FXML
    private TextField txtTecnici;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doElenco(ActionEvent event) {
    	
    	String citta=boxCitta.getValue();
    	if(citta==null){
    		txtResult.appendText("Selezionare una città.\n");
    		return;
    	}
    	
    	model.getSituazioni();
    	List<Situazione> lista=model.getStatistiche(citta);
    	txtResult.appendText("Elenco date:\n");
    	for(Situazione s: lista){
    		txtResult.appendText(s.getData().toString()+"\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	String citta=boxCitta.getValue();
    	if(citta==null){
    		txtResult.appendText("Selezionare una città.\n");
    		return;
    	}
    	double p=0;
    	try{
    		p=Double.parseDouble(txtProbabilita.getText());
    	}catch(NumberFormatException n){
    		txtResult.appendText("Inserire una numero decimale per la probabilità.\n");
    		return;
    	}
    	int tecnici=0;
    	try{
    		tecnici=Integer.parseInt(txtTecnici.getText());
    	}catch(NumberFormatException n){
    		txtResult.appendText("Inserire una numero intero per i tecnici.\n");
    		return;
    	}
    	
    	int costo=model.simula(citta,p,tecnici);
    	txtResult.appendText("Il costo complessivo della manutenzione è di: "+costo+" euro.\n");
    }

    @FXML
    void initialize() {
        assert boxCitta != null : "fx:id=\"boxCitta\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert btnElencoDate != null : "fx:id=\"btnElencoDate\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtProbabilita != null : "fx:id=\"txtProbabilita\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtTecnici != null : "fx:id=\"txtTecnici\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Meteo.fxml'.";
    }
    Model model;
	public void setModel(Model model) {
		this.model=model;
		
		boxCitta.getItems().addAll(model.getCitta());
	}
}

