package fr.univtours.polytech.bourseserasmus;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseLink {

	private static Connection conn = null;

	/**
	 * @return the conn
	 */
	public static Connection getConn() {
		return conn;
	}

	/**
	 * @param conn the conn to set
	 */
	public static void setConn(Connection conn) {
		DatabaseLink.conn = conn;
	}

	/**
	 * Méthode publique permettant de se connecter à la base de données
	 * @param databaseUrl
	 * @param username
	 * @param password
	 */
	public static void connect(String databaseUrl, String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex1) {
			System.out.println("Pilote non trouvé!");
			System.exit(1);
		}
		try {
			System.out.println("Connexion à la base de données ...");
			conn = DriverManager.getConnection(databaseUrl, username, password);
			System.out.println("Base de données connectée.");
		} catch (SQLException ex2) {
			System.out.println("Erreur JDBC: " + ex2);
			System.exit(1);
		}
	}
	
	public static void creationTables() throws SQLException {
		System.out.println("Création des tables ...");
		Etudiant.createTableEtudiant();
		Enseignant.createTableEnseignant();
		Destination.createTableDestination();
		Enseignement.createTableEnseignement();
		Bourse.createTableBourse();
		Candidature.createTableCandidature();
		EvaluationCandidature.createTableEvaluationCandidature();
		Candidature.createTablePlanCours();
		System.out.println("Tables créées.");
	}
	
}
