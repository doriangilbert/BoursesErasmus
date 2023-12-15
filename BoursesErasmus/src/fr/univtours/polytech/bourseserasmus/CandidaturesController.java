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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CandidaturesController {

	@FXML
	private TableView<CandidatureToDisplay> tableViewCandidatures;
	@FXML 
	private TableColumn<CandidatureToDisplay, Integer> tableColumnIdCandidature;
	@FXML
	private TableColumn<CandidatureToDisplay, Integer> tableColumnIdBourse;
	@FXML
	private TableColumn<CandidatureToDisplay, Integer> tableColumnNumEtudiant;
	@FXML
	private TableColumn<CandidatureToDisplay, String> tableColumnNomEtudiant;
	@FXML
	private TableColumn<CandidatureToDisplay, Float> tableColumnNoteCandidature;
	
	@FXML
	private void initialize() throws SQLException {
		
		tableColumnIdCandidature.setCellValueFactory(new PropertyValueFactory<>("idCandidature"));
		tableColumnIdBourse.setCellValueFactory(new PropertyValueFactory<>("idBourse"));
		tableColumnNumEtudiant.setCellValueFactory(new PropertyValueFactory<>("numEtudiant"));
		tableColumnNomEtudiant.setCellValueFactory(new PropertyValueFactory<>("nomEtudiant"));
		tableColumnNoteCandidature.setCellValueFactory(new PropertyValueFactory<>("noteSemestre"));
		
		ObservableList<CandidatureToDisplay> listCandidatures = FXCollections.observableArrayList();
		
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT IdCandidature, IdBourse, NumEtudiant, NomEtudiant, NoteCandidature FROM Candidature NATURAL JOIN Etudiant";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			listCandidatures.add(new CandidatureToDisplay(rs.getInt("IdCandidature"), rs.getInt("IdBourse"), rs.getInt("NumEtudiant"), rs.getString("NomEtudiant"), (double) rs.getFloat("NoteCandidature")));
		}
		//Libération des ressources liées au statement
		stmt.close();
		tableViewCandidatures.setItems(listCandidatures);
	}
	
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
