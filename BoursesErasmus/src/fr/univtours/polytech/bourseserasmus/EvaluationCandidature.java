package fr.univtours.polytech.bourseserasmus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		return "EvaluationCandidature [note=" + note + ", enseignant=" + enseignant.getNumEnseignant() + ", candidature=" + candidature.getIdCandidature()
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
		PreparedStatement prpdStmtSelectEvaluations = DatabaseLink.getConn().prepareStatement("SELECT * FROM Evalue WHERE NumEnseignant = ? AND idCandidature = ?");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtSelectEvaluations.setInt(1, numEnseignant);
		prpdStmtSelectEvaluations.setInt(2, idCandidature);
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rsSelectEvaluations = prpdStmtSelectEvaluations.executeQuery();
		//Parcours du résultat et affichage des lignes
		if(rsSelectEvaluations.next()) {
			System.out.println("Candidature deja notee !");
		}
		else {
			int nbNotes = 0;
			
			// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
			PreparedStatement prpdStmtSelect = DatabaseLink.getConn().prepareStatement("SELECT COUNT(*) FROM Evalue WHERE idCandidature = ?");
			// Mise en place du PreparedStatement avec les paramètres
			prpdStmtSelect.setInt(1, idCandidature);
			//Exécution de la requête et stockage du résultat dans un objet ResulatSet
			ResultSet rs = prpdStmtSelect.executeQuery();
			//Parcours du résultat et affichage des lignes
			while (rs.next())
			{
				nbNotes = rs.getInt("COUNT(*)");
			}
			// Libération des ressources liées au PreparedStatement
			prpdStmtSelect.close();
			
			if (nbNotes < 2) {
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
				
				if (nbNotes == 1) {
					ArrayList<Float> notes = new ArrayList<>();
					
					// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
					PreparedStatement prpdStmtSelectE = DatabaseLink.getConn().prepareStatement("SELECT * FROM Evalue WHERE idCandidature = ?");
					// Mise en place du PreparedStatement avec les paramètres
					prpdStmtSelectE.setInt(1, idCandidature);
					//Exécution de la requête et stockage du résultat dans un objet ResulatSet
					ResultSet rsSelectE = prpdStmtSelectE.executeQuery();
					//Parcours du résultat et affichage des lignes
					while (rsSelectE.next())
					{
						notes.add(rsSelectE.getFloat("Note"));
					}
					// Libération des ressources liées au PreparedStatement
					prpdStmtSelectE.close();
					
					calculNoteCandidature(idCandidature, notes.get(0), notes.get(1));
				}
			} else {
				System.out.println("Deja deux notes !");
			}
		}
		// Libération des ressources liées au PreparedStatement
		prpdStmtSelectEvaluations.close();
		
	}
	
	public static void calculNoteCandidature(int idCandidature, float note1, float note2) throws SQLException {
		float moyenneCandidature = (note1 + note2)/2;
		
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtUpdate = DatabaseLink.getConn().prepareStatement("UPDATE Candidature SET NoteCandidature = ? WHERE IdCandidature = ?");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtUpdate.setFloat(1, moyenneCandidature);
		prpdStmtUpdate.setInt(2, idCandidature);
		// Execution de la requête du PreparedStatement
		prpdStmtUpdate.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtUpdate.close();
}
	
}
