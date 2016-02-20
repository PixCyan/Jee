package projetEtape4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable=false) 
	private String nom;
	
	@Column(nullable=false) 
	private String description;
	
	@Column(nullable=false) 
	private int coeff;
	
	@OneToMany(mappedBy="module", fetch=FetchType.LAZY)
	private List<Note> notes;
	
	@ManyToMany(mappedBy="modules", fetch=FetchType.LAZY)	
	private List<Groupe> groupes;
	
	public Module() {
		super();
		this.groupes = new ArrayList<>();
		this.notes = new ArrayList<>();
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getId() {
		return id;
	}
	
	public int getCoeff() {
		return coeff;
	}
	
	public List<Groupe> getGroupes() {
		return groupes;
	}
	
	public List<Note> getNotes() {
		return notes;
	}
	
}
