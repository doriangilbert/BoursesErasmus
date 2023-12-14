package fr.univtours.polytech.bourseserasmus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Destination {

	private int idDestination;

	private String nomDestination;

	private int nbPostesDispo;

	private String responsableLocal;

	private List<Enseignement> listeEnseignements;

	private List<Bourse> listeBourses;
	
	/**
	 * @param nomDestination
	 * @param nbPostesDispo
	 * @param responsableLocal
	 * @throws SQLException 
	 */
	public Destination(String nomDestination, int nbPostesDispo, String responsableLocal) throws SQLException {
		this.nomDestination = nomDestination;
		this.nbPostesDispo = nbPostesDispo;
		this.responsableLocal = responsableLocal;
		this.listeEnseignements = new ArrayList<>();
		this.listeBourses = new ArrayList<>();
		insertDestination(nomDestination, nbPostesDispo, responsableLocal);
		//Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt=DatabaseLink.getConn().createStatement(); 
		//Création de la requête qui va sélectionner les lignes dans la table
		String requete = "SELECT MAX(IdDestination) FROM Destination";
		//Exécution de la requête et stockage du résultat dans un objet ResulatSet
		ResultSet rs = stmt.executeQuery(requete);
		//Parcours du résultat et affichage des lignes
		while (rs.next())
		{
			this.idDestination = rs.getInt("MAX(IdDestination)");
		}
		//Libération des ressources liées au statement
		stmt.close();
	}

	/**
	 * @return the idDestination
	 */
	public int getIdDestination() {
		return idDestination;
	}

	/**
	 * @param idDestination the idDestination to set
	 */
	public void setIdDestination(int idDestination) {
		this.idDestination = idDestination;
	}

	/**
	 * @return the nomDestination
	 */
	public String getNomDestination() {
		return nomDestination;
	}

	/**
	 * @param nomDestination the nomDestination to set
	 */
	public void setNomDestination(String nomDestination) {
		this.nomDestination = nomDestination;
	}

	/**
	 * @return the nbPostesDispo
	 */
	public int getNbPostesDispo() {
		return nbPostesDispo;
	}

	/**
	 * @param nbPostesDispo the nbPostesDispo to set
	 */
	public void setNbPostesDispo(int nbPostesDispo) {
		this.nbPostesDispo = nbPostesDispo;
	}

	/**
	 * @return the responsableLocal
	 */
	public String getResponsableLocal() {
		return responsableLocal;
	}

	/**
	 * @param responsableLocal the responsableLocal to set
	 */
	public void setResponsableLocal(String responsableLocal) {
		this.responsableLocal = responsableLocal;
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
	 * @return the listeBourses
	 */
	public List<Bourse> getListeBourses() {
		return listeBourses;
	}

	/**
	 * @param listeBourses the listeBourses to set
	 */
	public void setListeBourses(List<Bourse> listeBourses) {
		this.listeBourses = listeBourses;
	}
	
	public void ajouterAListeBourses(Bourse bourse) {
		this.listeBourses.add(bourse);
	}
	
	public void retirerAListeBourses(Bourse bourse) {
		if(!this.listeBourses.contains(bourse)) {
			System.out.println("ERREUR : La bourse n'est pas dans la liste");
		} else {
			this.listeBourses.remove(bourse);
		}
	}

	@Override
	public String toString() {
		return "Destination [idDestination=" + idDestination + ", nomDestination=" + nomDestination + ", nbPostesDispo="
				+ nbPostesDispo + ", responsableLocal=" + responsableLocal + "]";
	}

	/**
	 * Méthode publique permettant de créer la table Destination
	 * @throws SQLException
	 */
	public static void createTableDestination() throws SQLException {
		// Création d'un objet Statement permettant d'exécuter la requête SQL
		Statement stmt = DatabaseLink.getConn().createStatement();
		// Création de la requête qui va créer la table si elle n'existe pas déjà
		String requete = "CREATE TABLE IF NOT EXISTS Destination("
				+ "IdDestination INT NOT NULL AUTO_INCREMENT,"
				+ "NomDestination VARCHAR(50),"
				+ "NbPostesDispo INT,"
				+ "ResponsableLocal VARCHAR(100),"
				+ "PRIMARY KEY(IdDestination)"
				+ ")";
		// Exécution de la requête
		stmt.executeUpdate(requete);
		// Libération des ressources liées au statement
		stmt.close();
	}
	
	/**
	 * Méthode publique permettant d'insérer dans la table Destination
	 * @param nomDestination
	 * @param nbPostesDispo
	 * @param responsableLocal
	 * @throws SQLException
	 */
	public static void insertDestination(String nomDestination, int nbPostesDispo, String responsableLocal) throws SQLException {
		// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
		PreparedStatement prpdStmtSelect = DatabaseLink.getConn().prepareStatement("SELECT IdDestination FROM Destination WHERE NomDestination=? LIMIT 1");
		// Mise en place du PreparedStatement avec les paramètres
		prpdStmtSelect.setString(1, nomDestination);
		// Execution de la requête du PreparedStatement et stockage du résultat dans un ResultSet
		ResultSet rs = prpdStmtSelect.executeQuery();
		if (rs.next()) {
			System.out.println("Destination déjà dans la base de données.");
		} else {
			// Création d'un objet PreparedStatement permettant d'exécuter la requête SQL
			PreparedStatement prpdStmtInsert = DatabaseLink.getConn().prepareStatement("INSERT IGNORE INTO Destination(NomDestination, NbPostesDispo, ResponsableLocal) VALUES (?,?,?)");
			// Mise en place du PreparedStatement avec les paramètres
			prpdStmtInsert.setString(1, nomDestination);
			prpdStmtInsert.setInt(2, nbPostesDispo);
			prpdStmtInsert.setString(3, responsableLocal);
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
