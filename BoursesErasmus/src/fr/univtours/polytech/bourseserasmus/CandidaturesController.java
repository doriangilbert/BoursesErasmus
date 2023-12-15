package fr.univtours.polytech.bourseserasmus;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class CandidaturesController {

	
	@FXML
	protected void handleButtonRetourAccueil(ActionEvent event) throws IOException {
		try
		{
			Main.root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/bourseserasmus/PageAccueilView.fxml"));

			Scene scene = new Scene(Main.root, 640, 400);

			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
}
