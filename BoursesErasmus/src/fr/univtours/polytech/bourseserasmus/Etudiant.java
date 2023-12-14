package fr.univtours.polytech.bourseserasmus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Etudiant {

	private int numEtudiant;

	private String nomEtudiant;

	private String prenomEtudiant;

	private float noteMoyenneDernierSemestre;

	private List<Candidature> listeCandidatures;
	
	/**
	 * @param numEtudiant
	 * @param nomEtudiant
	 * @param prenomEtudiant
	 * @param noteMoyenneDernierSemestre
	 * @throws SQLException 
	 */
	public Etudiant(int numEtudiant, String nomEtudiant, String prenomEtudiant, float noteMoyenneDernierSemestre) throws SQLException {
		this.numEtudiant = numEtudiant;
		this.nomEtudiant = nomEtudiant;
		this.prenomEtudiant = prenomEtudiant;
		this.noteMoyenneDernierSemestre = noteMoyenneDernierSemestre;
		this.listeCandidatures = new ArrayList<>();
		insertEtudiant(numEtudiant, nomEtudiant, prenomEtudiant, noteMoyenneDernierSemestre);
	}

	/**
	 * @return the numEtudiant
	 */
	public int getNumEtudiant() {
		return numEtudiant;
	}

	/**
	 * @param numEtudiant the numEtudiant to set
	 */
	public void setNumEtudiant(int numEtudiant) {
		this.numEtudiant = numEtudiant;
	}

	/**
	 * @return the nomEtudiant
	 */
	public String getNomEtudiant() {
		return nomEtudiant;
	}

	/**
	 * @param nomEtudiant the nomEtudiant to set
	 */
	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = nomEtudiant;
	}

	/**
	 * @return the prenomEtudiant
	 */
	public String getPrenomEtudiant() {
		return prenomEtudiant;
	}

	/**
	 * @param prenomEtudiant the prenomEtudiant to set
	 */
	public void setPrenomEtudiant(String prenomEtudiant) {
		this.prenomEtudiant = prenomEtudiant;
	}

	/**
	 * @return the noteMoyenneDernierSemestre
	 */
	public float getNoteMoyenneDernierSemestre() {
		return noteMoyenneDernierSemestre;
	}

	/**
	 * @param noteMoyenneDernierSemestre the noteMoyenneDernierSemestre to set
	 */
	public void setNoteMoyenneDernierSemestre(float noteMoyenneDernierSemestre) {
		this.noteMoyenneDernierSemestre = noteMoyenneDernierSemestre;
	}

	/**
	 * @return the listeCandidatures
	 */
	public List<Candidature> getListeCandidatures() {
		return listeCandidatures;
	}

	/**
	 * @param listeCandidatures the listeCandidatures to set
	 */
	public void setListeCandidatures(List<Candidature> listeCandidatures) {
		this.listeCandidatures = listeCandidatures;
	}
	
	public void ajouterAListeCandidatures(Candidature candidature) {
		this.listeCandidatures.add(candidature);
	}
	
	public void retirerAListeCandidatures(Candidature candidature) {
		if(!this.listeCandidatures.contains(candidature)) {
			System.out.println("ERREUR : La candidature n'est pas dans la liste");
		} else {
			this.listeCandidatures.remove(candidature);
		}
	}

	@Override
	public String toString() {
		return "Etudiant [numEtudiant=" + numEtudiant + ", nomEtudiant=" + nomEtudiant + ", prenomEtudiant="
				+ prenomEtudiant + ", noteMoyenneDernierSemestre=" + noteMoyenneDernierSemestre + "]";
	}

	/**
	 * Méthode publique permettant de créer la table Etudiant
	 * @throws SQLException
	 */
	public static void createTableEtudiant() throws SQLException {
		// Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement();
		// Création de la requête qui va créer la table si elle n'existe pas déjà
		String requete = "CREATE TABLE IF NOT EXISTS Etudiant("
				+ "NumEtudiant INT NOT NULL,"
				+ "NomEtudiant VARCHAR(50),"
				+ "PrenomEtudiant VARCHAR(50),"
				+ "NoteMoyenneDernierSemestre DECIMAL(5,3),"
				+ "PRIMARY KEY(NumEtudiant)"
				+ ")";
		// Exécution de la requête
		stmt.executeUpdate(requete);
		// Libération des ressources liées au statement
		stmt.close();
	}
	
	/**
	 * Méthode publique permettant d'insérer dans la table Etudiant
	 * @param numEtudiant
	 * @param nomEtudiant
	 * @param prenomEtudiant
	 * @param noteMoyenneDernierSemestre
	 * @throws SQLException
	 */
	public static void insertEtudiant(int numEtudiant, String nomEtudiant, String prenomEtudiant, float noteMoyenneDernierSemestre) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtSelect = DatabaseLink.getConn().prepareStatement("SELECT NumEtudiant FROM Etudiant WHERE NumEtudiant=? LIMIT 1");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtSelect.setInt(1, numEtudiant);
		// Execution de la requête du PreparedStatement et stockage du résultat dans un ResultSet
		ResultSet rs = prpdStmtSelect.executeQuery();
		if (rs.next()) {
			System.out.println("Etudiant déjà dans la base de données.");
		} else {
			// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
			PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Etudiant(NumEtudiant, NomEtudiant, PrenomEtudiant, NoteMoyenneDernierSemestre) VALUES (?,?,?,?)");
			// Mise en place du PreparedStatement avec les paramètres
			prpdStmtInsert.setInt(1, numEtudiant);
			prpdStmtInsert.setString(2, nomEtudiant);
			prpdStmtInsert.setString(3, prenomEtudiant);
			prpdStmtInsert.setFloat(4, noteMoyenneDernierSemestre);
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
