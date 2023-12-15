package fr.univtours.polytech.bourseserasmus;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PageAccueilController {

	private static int idConnecte;
	
	@FXML
	private ComboBox<String> comboBoxEnseignant;
	
	@FXML
	private Label labelIdConnecte;
	
	public static int getIdConnecte() {
		return idConnecte;
	}

	public static void setIdConnecte(int idConnecte) {
		PageAccueilController.idConnecte = idConnecte;
	}

	@FXML
	private void initialize() throws SQLException {
		ObservableList<String> listeNumEnseignant = FXCollections.observableArrayList();
		
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT NumEnseignant FROM Enseignant";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			listeNumEnseignant.add(rs.getString("NumEnseignant"));
			comboBoxEnseignant.setValue(rs.getString("NumEnseignant"));
		}
		//Libération des ressources liées au statement
		stmt.close();
		comboBoxEnseignant.setItems(listeNumEnseignant);
	}
	
	@FXML
	protected void handleButtonSeConnecter(ActionEvent event) throws IOException {
		try
		{
			Main.root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/bourseserasmus/EvaluationCandidaturesView.fxml"));

			Scene scene = new Scene(Main.root, 640, 400);

			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();
			
			labelIdConnecte = (Label) Main.root.lookup("#labelIdConnecte");
			labelIdConnecte.setText(comboBoxEnseignant.getValue());
			
			idConnecte = Integer.parseInt(comboBoxEnseignant.getValue());
		} catch (Exception error) {
			error.printStackTrace();
		}
		
		
	}
	
	@FXML
	protected void handleButtonListeCandidatures(ActionEvent event) throws IOException {
		try
		{
			Main.root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/bourseserasmus/CandidaturesView.fxml"));

			Scene scene = new Scene(Main.root, 640, 400);

			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();

		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
}
