package fr.univtours.polytech.bourseserasmus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class PageAccueilController {

	@FXML
	private ComboBox<String> comboBoxEnseignant;
	
	@FXML
	private void initialize(){
		//TODO : Initilisation de la comboBox avec les enseignants
	}
	
	@FXML
	protected void handleButtonSeConnecter(ActionEvent event){
		//TODO : Récupérer l'enseignant sélectionné dans la comboBox et ouvrir la page correspondante
		System.out.println("Bouton Se connecter appuyé !");
	}
	
}
