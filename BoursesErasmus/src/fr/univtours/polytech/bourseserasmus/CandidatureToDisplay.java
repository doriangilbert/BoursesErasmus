package fr.univtours.polytech.bourseserasmus;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CandidatureToDisplay {

	private transient IntegerProperty idCandidature;
	
	private transient IntegerProperty idBourse;
	
	private transient IntegerProperty numEtudiant;
	
	private transient StringProperty nomEtudiant;
	
	private transient FloatProperty moyenneSemestre;
	
	private transient FloatProperty noteCandidature;

	private void InitProperties() {
		this.idCandidature = new SimpleIntegerProperty();
		this.idBourse = new SimpleIntegerProperty();
		this.numEtudiant = new SimpleIntegerProperty();
		this.nomEtudiant = new SimpleStringProperty();
		this.moyenneSemestre = new SimpleFloatProperty();
		this.noteCandidature = new SimpleFloatProperty();
	}
	
	public CandidatureToDisplay() {
		InitProperties();
	}
	
	/**
	 * @param idCandidature
	 * @param idBourse
	 * @param numEtudiant
	 * @param nomEtudiant
	 * @param moyenneSemestre
	 */
	public CandidatureToDisplay(int idCandidature, int idBourse, int numEtudiant, String nomEtudiant, float moyenneSemestre) {
		InitProperties();
		this.idCandidature = new SimpleIntegerProperty(idCandidature);
		this.idBourse = new SimpleIntegerProperty(idBourse);
		this.numEtudiant = new SimpleIntegerProperty(numEtudiant);
		this.nomEtudiant = new SimpleStringProperty(nomEtudiant);
		this.moyenneSemestre = new SimpleFloatProperty(moyenneSemestre);
	}
	
//	/**
//	 * @param idCandidature
//	 * @param idBourse
//	 * @param numEtudiant
//	 * @param nomEtudiant
//	 * @param noteCandidature
//	 */
//	public CandidatureToDisplay(int idCandidature, int idBourse, int numEtudiant, String nomEtudiant, float noteCandidature) {
//		InitProperties();
//		this.idCandidature = new SimpleIntegerProperty(idCandidature);
//		this.idBourse = new SimpleIntegerProperty(idBourse);
//		this.numEtudiant = new SimpleIntegerProperty(numEtudiant);
//		this.nomEtudiant = new SimpleStringProperty(nomEtudiant);
//		this.noteCandidature = new SimpleFloatProperty(noteCandidature);
//	}

	public int getIdCandidature() {
		return idCandidature.get();
	}

	public void setIdCandidature(int idCandidature) {
		this.idCandidature = new SimpleIntegerProperty(idCandidature);
	}

	public int getIdBourse() {
		return idBourse.get();
	}

	public void setIdBourse(int idBourse) {
		this.idBourse = new SimpleIntegerProperty(idBourse);
	}

	public int getNumEtudiant() {
		return numEtudiant.get();
	}

	public void setNumEtudiant(int numEtudiant) {
		this.numEtudiant = new SimpleIntegerProperty(numEtudiant);
	}

	public String getNomEtudiant() {
		return nomEtudiant.get();
	}

	public void setNomEtudiant(String nomEtudiant) {
		this.nomEtudiant = new SimpleStringProperty(nomEtudiant);
	}

	public float getMoyenneSemestre() {
		return moyenneSemestre.get();
	}

	public void setMoyenneSemestre(float moyenneSemestre) {
		this.moyenneSemestre = new SimpleFloatProperty(moyenneSemestre);
	}
	
	public float getNoteCandidature() {
		return noteCandidature.get();
	}

	public void setNoteCandidature(float noteCandidature) {
		this.noteCandidature = new SimpleFloatProperty(noteCandidature);
	}
	
}
