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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EvaluationCandidaturesController {

	@FXML
	private TableView<CandidatureToDisplay> tableViewEvaluationCandidatures;
	@FXML 
	private TableColumn<CandidatureToDisplay, Integer> tableColumnIdCandidature;
	@FXML
	private TableColumn<CandidatureToDisplay, Integer> tableColumnIdBourse;
	@FXML
	private TableColumn<CandidatureToDisplay, Integer> tableColumnNumEtudiant;
	@FXML
	private TableColumn<CandidatureToDisplay, String> tableColumnNomEtudiant;
	@FXML
	private TableColumn<CandidatureToDisplay, Float> tableColumnMoyenneSemestre;
	
	@FXML
	private ComboBox<String> comboBoxCandidature;
	
	@FXML
	private void initialize() throws SQLException {
		
		tableColumnIdCandidature.setCellValueFactory(new PropertyValueFactory<>("idCandidature"));
		tableColumnIdBourse.setCellValueFactory(new PropertyValueFactory<>("idBourse"));
		tableColumnNumEtudiant.setCellValueFactory(new PropertyValueFactory<>("numEtudiant"));
		tableColumnNomEtudiant.setCellValueFactory(new PropertyValueFactory<>("nomEtudiant"));
		tableColumnMoyenneSemestre.setCellValueFactory(new PropertyValueFactory<>("moyenneSemestre"));
		
		ObservableList<CandidatureToDisplay> listCandidatures = FXCollections.observableArrayList();
		
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT IdCandidature, IdBourse, NumEtudiant, NomEtudiant, NoteMoyenneDernierSemestre FROM Candidature NATURAL JOIN Etudiant";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			listCandidatures.add(new CandidatureToDisplay(rs.getInt("IdCandidature"), rs.getInt("IdBourse"), rs.getInt("NumEtudiant"), rs.getString("NomEtudiant"), rs.getFloat("NoteMoyenneDernierSemestre")));
		}
		//Libération des ressources liées au statement
		stmt.close();
		tableViewEvaluationCandidatures.setItems(listCandidatures);
		
		ObservableList<String> listeIdCandidature = FXCollections.observableArrayList();
		
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt1=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete1 = "SELECT IdCandidature FROM Candidature";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs1 = stmt1.executeQuery(requete1);
		//Parcours du résultat et affichage des lignes
		while (rs1.next())
		{
			listeIdCandidature.add(rs1.getString("IdCandidature"));
			comboBoxCandidature.setValue(rs1.getString("IdCandidature"));
		}
		//Libération des ressources liées au statement
		stmt1.close();
		comboBoxCandidature.setItems(listeIdCandidature);
	}
	
	@FXML
	protected void handleButtonNoterCandidature(ActionEvent event) throws IOException {
		//TODO : Page de notation candidature
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
