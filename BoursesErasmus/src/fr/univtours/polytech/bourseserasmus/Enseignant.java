package fr.univtours.polytech.bourseserasmus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Enseignant {

	private int numEnseignant;

	private String nomEnseignant;

	private String prenomEnseignant;

	/**
	 * @param numEnseignant
	 * @param nomEnseignant
	 * @param prenomEnseignant
	 * @throws SQLException 
	 */
	public Enseignant(int numEnseignant, String nomEnseignant, String prenomEnseignant) throws SQLException {
		this.numEnseignant = numEnseignant;
		this.nomEnseignant = nomEnseignant;
		this.prenomEnseignant = prenomEnseignant;
		insertEnseignant(numEnseignant, nomEnseignant, prenomEnseignant);
	}

	/**
	 * @return the numEnseignant
	 */
	public int getNumEnseignant() {
		return numEnseignant;
	}

	/**
	 * @param numEnseignant the numEnseignant to set
	 */
	public void setNumEnseignant(int numEnseignant) {
		this.numEnseignant = numEnseignant;
	}

	/**
	 * @return the nomEnseignant
	 */
	public String getNomEnseignant() {
		return nomEnseignant;
	}

	/**
	 * @param nomEnseignant the nomEnseignant to set
	 */
	public void setNomEnseignant(String nomEnseignant) {
		this.nomEnseignant = nomEnseignant;
	}

	/**
	 * @return the prenomEnseignant
	 */
	public String getPrenomEnseignant() {
		return prenomEnseignant;
	}

	/**
	 * @param prenomEnseignant the prenomEnseignant to set
	 */
	public void setPrenomEnseignant(String prenomEnseignant) {
		this.prenomEnseignant = prenomEnseignant;
	}

	@Override
	public String toString() {
		return "Enseignant [numEnseignant=" + numEnseignant + ", nomEnseignant=" + nomEnseignant + ", prenomEnseignant="
				+ prenomEnseignant + "]";
	}

	/**
	 * Méthode publique permettant de créer la table Enseignant
	 * @throws SQLException
	 */
	public static void createTableEnseignant() throws SQLException {
		// Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement();
		// Création de la requête qui va créer la table si elle n'existe pas déjà
		String requete = "CREATE TABLE IF NOT EXISTS Enseignant("
				+ "NumEnseignant INT NOT NULL,"
				+ "NomEnseignant VARCHAR(50),"
				+ "PrenomEnseignant VARCHAR(50),"
				+ "PRIMARY KEY(NumEnseignant)"
				+ ")";
		// Exécution de la requête
		stmt.executeUpdate(requete);
		// Libération des ressources liées au statement
		stmt.close();
	}
	
	/**
	 * Méthode publique permettant d'insérer dans la table Enseignant
	 * @param numEnseignant
	 * @param nomEnseignant
	 * @param prenomEnseignant
	 * @throws SQLException
	 */
	public static void insertEnseignant(int numEnseignant, String nomEnseignant, String prenomEnseignant) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtSelect = DatabaseLink.getConn().prepareStatement("SELECT NumEnseignant FROM Enseignant WHERE NumEnseignant=? LIMIT 1");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtSelect.setInt(1, numEnseignant);
		// Execution de la requête du PreparedStatement et stockage du résultat dans un ResultSet
		ResultSet rs = prpdStmtSelect.executeQuery();
		if (rs.next()) {
			System.out.println("Enseignant déjà dans la base de données.");
		} else {
			// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
			PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Enseignant(NumEnseignant, NomEnseignant, PrenomEnseignant) VALUES (?,?,?)");
			// Mise en place du PreparedStatement avec les paramètres
			prpdStmtInsert.setInt(1, numEnseignant);
			prpdStmtInsert.setString(2, nomEnseignant);
			prpdStmtInsert.setString(3, prenomEnseignant);
			// Execution de la requête du PreparedStatement
			prpdStmtInsert.executeUpdate();
			// Libération des ressources liées au PreparedStatement
			prpdStmtInsert.close();
		}
		// Libération des ressources liées au ResultSet
		rs.close();
		// Libération des ressources liées au PreparedStatement
		prpdStmtSelect.close();
	}
	
}
