package fr.univtours.polytech.bourseserasmus;
/**
 * 
 */

import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Dorian GILBERT
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Connexion à la base de données
			DatabaseLink.connect("jdbc:mysql://127.0.0.1:3306/bourseserasmus", "root", "root");
			// Création des tables
			DatabaseLink.creationTables();
			
			System.out.println("Peuplement des tables ...");
			
			System.out.println("Peuplement Etudiant");
			Etudiant etudiant1 = new Etudiant(22201234, "DUPONT", "Antoine", 15.0f);
			Etudiant etudiant2 = new Etudiant(22204321, "JACQUES", "Pierre", 13.0f);
			
			System.out.println("Peuplement Enseignant");
			Enseignant enseignant1 = new Enseignant(11101234, "LUML", "Vincent");
			Enseignant enseignant2 = new Enseignant(11104321, "LAPL", "Yannick");
			Enseignant enseignant3 = new Enseignant(11101478, "LALGO", "Gilles");
			
			System.out.println("Peuplement Destination");
			Destination destination1 = new Destination("ESPAGNE", 3, "Enrico DEL CORTE");
			Destination destination2 = new Destination("SUEDE", 1, "Bernard KALLAX");
			Destination destination3 = new Destination("ALLEMAGNE", 4, "Gunther SCHNITZEL");
			
			System.out.println("Peuplement Bourse");
			Bourse bourse1 = new Bourse(destination1);
			Bourse bourse2 = new Bourse(destination2);
			Bourse bourse3 = new Bourse(destination3);
			
			System.out.println("Peuplement Enseignement");
			Enseignement enseignement1 = new Enseignement("C++ programming", 8, 50, destination1);
			Enseignement enseignement2 = new Enseignement("Project management", 4, 25, destination1);
			Enseignement enseignement3 = new Enseignement("Java programming", 8, 50, destination1);
			Enseignement enseignement4 = new Enseignement("Programming project", 16, 70, destination1);
			
			Enseignement enseignement5 = new Enseignement("Game engines", 10, 25, destination2);
			Enseignement enseignement6 = new Enseignement("Maths for videogames", 8, 10, destination2);
			Enseignement enseignement7 = new Enseignement("C# programming", 15, 50, destination2);
			Enseignement enseignement8 = new Enseignement("Video game project", 15, 80, destination2);
			
			Enseignement enseignement9 = new Enseignement("Security of systems", 11, 36, destination3);
			Enseignement enseignement10 = new Enseignement("Criminalistics", 3, 5, destination3);
			Enseignement enseignement11 = new Enseignement("Cryptography", 8, 20, destination3);
			Enseignement enseignement12 = new Enseignement("Pentesting project", 21, 85, destination3);
			
			System.out.println("Peuplement Candidature");
			Candidature candidature1 = new Candidature(etudiant1, bourse2);
			Candidature candidature2 = new Candidature(etudiant1, bourse3);
			Candidature candidature3 = new Candidature(etudiant2, bourse1);
			
			System.out.println("Peuplement EvaluationCandidature");
			EvaluationCandidature evaluationcandidature1 = new EvaluationCandidature(18, enseignant2, candidature1);
			EvaluationCandidature evaluationcandidature2 = new EvaluationCandidature(16, enseignant1, candidature1);
			EvaluationCandidature evaluationcandidature3 = new EvaluationCandidature(15, enseignant3, candidature2);
			EvaluationCandidature evaluationcandidature4 = new EvaluationCandidature(17, enseignant2, candidature2);
			EvaluationCandidature evaluationcandidature5 = new EvaluationCandidature(12, enseignant1, candidature3);
			EvaluationCandidature evaluationcandidature6 = new EvaluationCandidature(14, enseignant3, candidature3);
			
            System.out.println("Ajout des plans de cours");
            candidature1.ajouterAuPlanDeCours(enseignement5);
            candidature1.ajouterAuPlanDeCours(enseignement7);
            candidature1.ajouterAuPlanDeCours(enseignement8);
            
            candidature2.ajouterAuPlanDeCours(enseignement9);
            candidature2.ajouterAuPlanDeCours(enseignement12);
            
            candidature3.ajouterAuPlanDeCours(enseignement1);
            candidature3.ajouterAuPlanDeCours(enseignement2);
            candidature3.ajouterAuPlanDeCours(enseignement3);
            candidature3.ajouterAuPlanDeCours(enseignement4);
			
			System.out.println("Peuplement terminé.");
			
		} catch (SQLException ex) {
			System.out.println("Erreur JDBC: " + ex);
			System.exit(1);
		}
		
		launch(args);
		
	}

}
