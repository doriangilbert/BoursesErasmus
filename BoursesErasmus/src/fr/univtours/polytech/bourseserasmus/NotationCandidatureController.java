package fr.univtours.polytech.bourseserasmus;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NotationCandidatureController {
	
	@FXML
	private Label labelGetId;
	
	@FXML
	private TextField textFieldNote;
	
	@FXML
	private void initialize() {
		textFieldNote.setText("10");
	}
	
	@FXML
	protected void handleButtonConfirmer(ActionEvent event) throws IOException, SQLException {
		try {
			if (!textFieldNote.getText().isBlank()) {
				Float valeurTextField = Float.parseFloat(textFieldNote.getText());
				
				if (valeurTextField != null && valeurTextField >= 0 && valeurTextField <= 20) {
					EvaluationCandidature.insertEvaluationCandidature(PageAccueilController.getIdConnecte(), EvaluationCandidaturesController.getIdCandidatureANoter(), valeurTextField);
					
					try
					{
						Main.root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/bourseserasmus/PageAccueilView.fxml"));

						Scene scene = new Scene(Main.root, 640, 400);

						Main.primaryStage.setScene(scene);
						Main.primaryStage.show();
					} catch (Exception error) {
						error.printStackTrace();
					}
				} else {
					System.out.println("La note doit être comprise entre 0 et 20");
				}
			}
		} catch (NumberFormatException error) {
			System.out.println("La note doit être une valeur numérique");
		}
	}
	
	@FXML
	protected void handleButtonRetourAccueil(ActionEvent event) throws IOException {
		try
		{
			Main.root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/bourseserasmus/PageAccueilView.fxml"));

			Scene scene = new Scene(Main.root, 640, 400);

			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();
		}

		catch (Exception error)
		{
			error.printStackTrace();
		}
	}
	
}
