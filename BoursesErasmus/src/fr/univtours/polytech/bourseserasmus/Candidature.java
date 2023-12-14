package fr.univtours.polytech.bourseserasmus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Candidature {

	private int idCandidature;

	private float noteCandidature;

	private Etudiant etudiant;

	private List<Enseignement> listeEnseignements;

	private Bourse bourse;

	private List<EvaluationCandidature> listeEvaluationsCandidature;
	
	/**
	 * @param etudiant
	 * @param bourse
	 * @throws SQLException 
	 */
	public Candidature(Etudiant etudiant, Bourse bourse) throws SQLException {
		this.etudiant = etudiant;
		this.listeEnseignements = new ArrayList<>();
		this.bourse = bourse;
		this.listeEvaluationsCandidature = new ArrayList<>();
		insertCandidature(etudiant.getNumEtudiant(), bourse.getIdBourse());
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT MAX(IdCandidature) FROM Candidature";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			this.idCandidature = rs.getInt("MAX(IdCandidature)");
		}
		//Libération des ressources liées au statement
		stmt.close();
		etudiant.ajouterAListeCandidatures(this);
		bourse.ajouterAListeCandidatures(this);
	}

	/**
	 * @return the idCandidature
	 */
	public int getIdCandidature() {
		return idCandidature;
	}

	/**
	 * @param idCandidature the idCandidature to set
	 */
	public void setIdCandidature(int idCandidature) {
		this.idCandidature = idCandidature;
	}

	/**
	 * @return the noteCandidature
	 */
	public float getNoteCandidature() {
		return noteCandidature;
	}

	/**
	 * @param noteCandidature the noteCandidature to set
	 */
	public void setNoteCandidature(float noteCandidature) {
		this.noteCandidature = noteCandidature;
	}

	/**
	 * @return the etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}

	/**
	 * @param etudiant the etudiant to set
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	/**
	 * @return the listeEnseignements
	 */
	public List<Enseignement> getListeEnseignements() {
		return listeEnseignements;
	}

	/**
	 * @param listeEnseignements the listeEnseignements to set
	 */
	public void setListeEnseignements(List<Enseignement> listeEnseignements) {
		this.listeEnseignements = listeEnseignements;
	}
	
	public void ajouterAListeEnseignements(Enseignement enseignement) {
		this.listeEnseignements.add(enseignement);
	}
	
	public void retirerAListeEnseignements(Enseignement enseignement) {
		if(!this.listeEnseignements.contains(enseignement)) {
			System.out.println("ERREUR : L'enseignement n'est pas dans la liste");
		} else {
			this.listeEnseignements.remove(enseignement);
		}
	}

	/**
	 * @return the bourse
	 */
	public Bourse getBourse() {
		return bourse;
	}

	/**
	 * @param bourse the bourse to set
	 */
	public void setBourse(Bourse bourse) {
		this.bourse = bourse;
	}

	/**
	 * @return the listeEvaluationsCandidature
	 */
	public List<EvaluationCandidature> getListeEvaluationsCandidature() {
		return listeEvaluationsCandidature;
	}

	/**
	 * @param listeEvaluationsCandidature the listeEvaluationsCandidature to set
	 */
	public void setListeEvaluationsCandidature(List<EvaluationCandidature> listeEvaluationsCandidature) {
		this.listeEvaluationsCandidature = listeEvaluationsCandidature;
	}
	
	public void ajouterAListeEvaluationsCandidature(EvaluationCandidature evaluationcandidature) {
		this.listeEvaluationsCandidature.add(evaluationcandidature);
	}
	
	public void retirerAListeEvaluationsCandidature(EvaluationCandidature evaluationcandidature) {
		if(!this.listeEvaluationsCandidature.contains(evaluationcandidature)) {
			System.out.println("ERREUR : L' évaluation n'est pas dans la liste");
		} else {
			this.listeEvaluationsCandidature.remove(evaluationcandidature);
		}
	}
	
	@Override
	public String toString() {
		return "Candidature [idCandidature=" + idCandidature + ", noteCandidature=" + noteCandidature + ", etudiant="
				+ etudiant + ", listeEnseignements=" + listeEnseignements + ", bourse=" + bourse
				+ ", listeEvaluationsCandidature=" + listeEvaluationsCandidature + "]";
	}

	/**
	 * Méthode publique permettant de créer la table Candidature
	 * @throws SQLException
	 */
	public static void createTableCandidature() throws SQLException {
		// Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement();
		// Création de la requête qui va créer la table si elle n'existe pas déjà
		String requete = "CREATE TABLE IF NOT EXISTS Candidature("
				+ "IdCandidature INT NOT NULL AUTO_INCREMENT,"
				+ "NoteCandidature DECIMAL(5,3),"
				+ "IdBourse INT NOT NULL,"
				+ "NumEtudiant INT NOT NULL,"
				+ "PRIMARY KEY(IdCandidature),"
				+ "FOREIGN KEY(IdBourse) REFERENCES Bourse(IdBourse),"
				+ "FOREIGN KEY(NumEtudiant) REFERENCES Etudiant(NumEtudiant)"
				+ ")";
		// Exécution de la requête
		stmt.executeUpdate(requete);
		// Libération des ressources liées au statement
		stmt.close();
	}
	
	/**
	 * Méthode publique permettant de créer la table PlanCours
	 * @throws SQLException
	 */
	public static void createTablePlanCours() throws SQLException {
		// Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement();
		// Création de la requête qui va créer la table si elle n'existe pas déjà
		String requete = "CREATE TABLE IF NOT EXISTS Est_planifie("
				+ "IdEnseignement INT NOT NULL,"
				+ "IdCandidature INT NOT NULL,"
				+ "PRIMARY KEY(IdEnseignement, IdCandidature),"
				+ "FOREIGN KEY(IdEnseignement) REFERENCES Enseignement(IdEnseignement),"
				+ "FOREIGN KEY(IdCandidature) REFERENCES Candidature(IdCandidature)"
				+ ")";
		// Exécution de la requête
		stmt.executeUpdate(requete);
		// Libération des ressources liées au statement
		stmt.close();
	}
	
	public static void insertCandidature(int numEtudiant, int idBourse) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Candidature(NumEtudiant, IdBourse) VALUES (?,?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setInt(1, numEtudiant);
		prpdStmtInsert.setInt(2, idBourse);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
	
	public static void insertCoursPlan(int idEnseignement, int idCandidature) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Est_planifie(idEnseignement, idCandidature) VALUES (?,?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setInt(1, idEnseignement);
		prpdStmtInsert.setInt(2, idCandidature);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
	
}
