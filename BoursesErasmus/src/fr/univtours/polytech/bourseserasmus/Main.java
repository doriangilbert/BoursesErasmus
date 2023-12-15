package fr.univtours.polytech.bourseserasmus;
/**
 * 
 */

import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Stage primaryStage;
	public static Parent root;
	
	@Override
	public void start(Stage primaryStage) {
		
		Main.primaryStage = primaryStage;
		
		try {
			root = FXMLLoader.load(getClass().getResource("/fr/univtours/polytech/bourseserasmus/PageAccueilView.fxml"));

			Scene scene = new Scene(root, 640, 400);
			Main.primaryStage.setTitle("BoursesErasmus");
			Main.primaryStage.setScene(scene);
			Main.primaryStage.show();
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
			
			/*System.out.println("Peuplement EvaluationCandidature");
			EvaluationCandidature evaluationcandidature1 = new EvaluationCandidature(18, enseignant2, candidature1);
			EvaluationCandidature evaluationcandidature2 = new EvaluationCandidature(16, enseignant1, candidature1);
			EvaluationCandidature evaluationcandidature3 = new EvaluationCandidature(15, enseignant3, candidature2);
			EvaluationCandidature evaluationcandidature4 = new EvaluationCandidature(17, enseignant2, candidature2);
			EvaluationCandidature evaluationcandidature5 = new EvaluationCandidature(12, enseignant1, candidature3);
			EvaluationCandidature evaluationcandidature6 = new EvaluationCandidature(14, enseignant3, candidature3);*/
			
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
			
			System.out.println("Liste des objets créés :");
			
			System.out.println(etudiant1);
			System.out.println(etudiant2);
			System.out.println(enseignant1);
			System.out.println(enseignant2);
			System.out.println(enseignant3);
			System.out.println(destination1);
			System.out.println(destination2);
			System.out.println(destination3);
			System.out.println(bourse1);
			System.out.println(bourse2);
			System.out.println(bourse3);
			System.out.println(enseignement1);
			System.out.println(enseignement2);
			System.out.println(enseignement3);
			System.out.println(enseignement4);
			System.out.println(enseignement5);
			System.out.println(enseignement6);
			System.out.println(enseignement7);
			System.out.println(enseignement8);
			System.out.println(enseignement9);
			System.out.println(enseignement10);
			System.out.println(enseignement11);
			System.out.println(enseignement12);
			System.out.println(candidature1);
			System.out.println(candidature2);
			System.out.println(candidature3);
			/*System.out.println(evaluationcandidature1);
			System.out.println(evaluationcandidature2);
			System.out.println(evaluationcandidature3);
			System.out.println(evaluationcandidature4);
			System.out.println(evaluationcandidature5);
			System.out.println(evaluationcandidature6);*/
			
			
		} catch (SQLException ex) {
			System.out.println("Erreur JDBC: " + ex);
			System.exit(1);
		}
		
		launch(args);
		
	}

}
