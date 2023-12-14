package fr.univtours.polytech.bourseserasmus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Administrateur
 *
 */
public class Enseignement {

	private int idEnseignement;

	private String nomEnseignement;

	private int nbCredits;

	private float volumeHoraire;

	private Destination destination;

	/**
	 * @param nomEnseignement
	 * @param nbCredits
	 * @param volumeHoraire
	 * @param destination
	 * @throws SQLException
	 */
	public Enseignement(String nomEnseignement, int nbCredits, float volumeHoraire, Destination destination) throws SQLException {
		this.nomEnseignement = nomEnseignement;
		this.nbCredits = nbCredits;
		this.volumeHoraire = volumeHoraire;
		this.destination = destination;
		insertEnseignement(nomEnseignement, nbCredits, volumeHoraire, destination.getIdDestination());
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT MAX(IdEnseignement) FROM Enseignement";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			this.idEnseignement = rs.getInt("MAX(IdEnseignement)");
		}
		//Libération des ressources liées au statement
		stmt.close();
		destination.ajouterAListeEnseignements(this);
	}

	/**
	 * @return the idEnseignement
	 */
	public int getIdEnseignement() {
		return idEnseignement;
	}

	/**
	 * @param idEnseignement the idEnseignement to set
	 */
	public void setIdEnseignement(int idEnseignement) {
		this.idEnseignement = idEnseignement;
	}

	/**
	 * @return the nomEnseignement
	 */
	public String getNomEnseignement() {
		return nomEnseignement;
	}

	/**
	 * @param nomEnseignement the nomEnseignement to set
	 */
	public void setNomEnseignement(String nomEnseignement) {
		this.nomEnseignement = nomEnseignement;
	}

	/**
	 * @return the nbCredits
	 */
	public int getNbCredits() {
		return nbCredits;
	}

	/**
	 * @param nbCredits the nbCredits to set
	 */
	public void setNbCredits(int nbCredits) {
		this.nbCredits = nbCredits;
	}

	/**
	 * @return the volumeHoraire
	 */
	public float getVolumeHoraire() {
		return volumeHoraire;
	}

	/**
	 * @param volumeHoraire the volumeHoraire to set
	 */
	public void setVolumeHoraire(float volumeHoraire) {
		this.volumeHoraire = volumeHoraire;
	}

	/**
	 * @return the destination
	 */
	public Destination getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "Enseignement [idEnseignement=" + idEnseignement + ", nomEnseignement=" + nomEnseignement
				+ ", nbCredits=" + nbCredits + ", volumeHoraire=" + volumeHoraire + ", destination=" + destination.getIdDestination()
				+ "]";
	}

	/**
	 * Méthode publique permettant de créer la table Enseignement
	 * @throws SQLException
	 */
	public static void createTableEnseignement() throws SQLException {
		// Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement();
		// Création de la requête qui va créer la table si elle n'existe pas déjà
		String requete = "CREATE TABLE IF NOT EXISTS Enseignement("
				+ "IdEnseignement INT NOT NULL AUTO_INCREMENT,"
				+ "NomEnseignement VARCHAR(150),"
				+ "NbCredits INT,"
				+ "VolumeHoraire DECIMAL(15,2),"
				+ "IdDestination INT NOT NULL,"
				+ "PRIMARY KEY(IdEnseignement),"
				+ "FOREIGN KEY(IdDestination) REFERENCES Destination(IdDestination)"
				+ ")";
		// Exécution de la requête
		stmt.executeUpdate(requete);
		// Libération des ressources liées au statement
		stmt.close();
	}
	
	public static void insertEnseignement(String nomEnseignement, int nbCredits, float volumeHoraire, int idDestination) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Enseignement(NomEnseignement, NbCredits, VolumeHoraire, IdDestination) VALUES (?,?,?,?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setString(1, nomEnseignement);
		prpdStmtInsert.setInt(2, nbCredits);
		prpdStmtInsert.setFloat(3, volumeHoraire);
		prpdStmtInsert.setInt(4, idDestination);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
}
