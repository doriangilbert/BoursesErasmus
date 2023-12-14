package fr.univtours.polytech.bourseserasmus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Bourse {

	private int idBourse;

	private List<Candidature> listeCandidatures;

	private Destination destination;
	
	/**
	 * @param destination
	 * @throws SQLException 
	 */
	public Bourse(Destination destination) throws SQLException {
		this.destination = destination;
		this.listeCandidatures = new ArrayList<>();
		insertBourse(destination.getIdDestination());
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT MAX(IdBourse) FROM Bourse";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			this.idBourse = rs.getInt("MAX(IdBourse)");
		}
		//Libération des ressources liées au statement
		stmt.close();
		destination.ajouterAListeBourses(this);
	}

	/**
	 * @return the idBourse
	 */
	public int getIdBourse() {
		return idBourse;
	}

	/**
	 * @param idBourse the idBourse to set
	 */
	public void setIdBourse(int idBourse) {
		this.idBourse = idBourse;
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
		return "Bourse [idBourse=" + idBourse + ", destination="
				+ destination.getIdDestination() + "]";
	}
	
	/**
	 * Méthode publique permettant de créer la table Bourse
	 * @throws SQLException
	 */
	public static void createTableBourse() throws SQLException {
		// Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement();
		// Création de la requête qui va créer la table si elle n'existe pas déjà
		String requete = "CREATE TABLE IF NOT EXISTS Bourse("
				+ "IdBourse INT NOT NULL AUTO_INCREMENT,"
				+ "IdDestination INT NOT NULL,"
				+ "PRIMARY KEY(IdBourse),"
				+ "FOREIGN KEY(IdDestination) REFERENCES Destination(IdDestination)"
				+ ")";
		// Exécution de la requête
		stmt.executeUpdate(requete);
		// Libération des ressources liées au statement
		stmt.close();
	}
	
	public static void insertBourse(int idDestination) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Bourse(IdDestination) VALUES (?)");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtInsert.setInt(1, idDestination);
		// Execution de la requête du PreparedStatement
		prpdStmtInsert.executeUpdate();
		// Libération des ressources liées au PreparedStatement
		prpdStmtInsert.close();
	}
	
}
