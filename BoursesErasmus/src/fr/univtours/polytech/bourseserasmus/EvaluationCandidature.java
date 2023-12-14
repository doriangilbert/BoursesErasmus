package fr.univtours.polytech.bourseserasmus;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EvaluationCandidature {

	private float note;

	private Enseignant enseignant;

	private Candidature candidature;

	/**
	 * @param note
	 * @param enseignant
	 * @param candidature
	 * @throws SQLException 
	 */
	public EvaluationCandidature(float note, Enseignant enseignant, Candidature candidature) throws SQLException {
		this.note = note;
		this.enseignant = enseignant;
		this.candidature = candidature;
		insertEvaluationCandidature(enseignant.getNumEnseignant(), candidature.getIdCandidature(), note);
		candidature.ajouterAListeEvaluationsCandidature(this);
	}

	/**
	 * @return the note
	 */
	public float getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(float note) {
		this.note = note;
	}

	/**
	 * @return the enseignant
	 */
	public Enseignant getEnseignant() {
		return enseignant;
	}

	/**
	 * @param enseignant the enseignant to set
	 */
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	/**
	 * @return the candidature
	 */
	public Candidature getCandidature() {
		return candidature;
	}

	/**
	 * @param candidature the candidature to set
	 */
	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}

	@Override
	public String toString() {
		return "EvaluationCandidature [note=" + note + ", enseignant=" + enseignant + ", candidature=" + candidature
				+ "]";
	}
	
	/**
	 * Méthode publique permettant de créer la table EvaluationCandidature
	 * @throws SQLException
	 */
	public static void createTableEvaluationCandidature() throws SQLException {
		// Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement();
		// Création de la requête qui va créer la table si elle n'existe pas déjà
		String requete = "CREATE TABLE IF NOT EXISTS Evalue("
				+ "NumEnseignant INT NOT NULL,"
				+ "IdCandidature INT NOT NULL,"
				+ "Note DECIMAL(5,3),"
				+ "PRIMARY KEY(NumEnseignant, IdCandidature),"
				+ "FOREIGN KEY(NumEnseignant) REFERENCES Enseignant(NumEnseignant),"
				+ "FOREIGN KEY(IdCandidature) REFERENCES Candidature(IdCandidature)"
				+ ")";
		// Exécution de la requête
		stmt.executeUpdate(requete);
		// Libération des ressources liées au statement
		stmt.close();
	}
	
	public static void insertEvaluationCandidature(int numEnseignant, int idCandidature, float note) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Evalue(NumEnseignant, IdCandidature, Note) VALUES (?,?,?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setInt(1, numEnseignant);
		prpdStmtInsert.setInt(2, idCandidature);
		prpdStmtInsert.setFloat(3, note);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
	
}
