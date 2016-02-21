package projetEtape4;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false) 
	private int note;
	
	@Column(nullable=false) 
	private String nom;
	
	@ManyToOne
	private Module module;
	
	@ManyToOne
	private Etudiant etudiant;
	
	public Note() {
		super();
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	public void setNote(int note) {
		this.note = note;
	}
	
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getId() {
		return id;
	}
	
	public Module getModule() {
		return module;
	}
	
	public int getNote() {
		return note;
	}
	
	public Etudiant getEtudiant() {
		return etudiant;
	}

}
