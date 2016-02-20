package projetEtape4;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Groupe
 *
 */
@Entity
public class Groupe implements Serializable {
	private static final long serialVersionUID = 1L;
   
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique=true, nullable=false) 
	private String nom;
	
	@OneToMany(mappedBy="groupe", fetch=FetchType.LAZY)	// LAZY = fetch when needed, EAGER = fetch immediately
	private List<Etudiant> etudiants;
	
	@ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
		      name="GroupeHasModule",
		      joinColumns=@JoinColumn(name="GroupeID", referencedColumnName="ID"),
		      inverseJoinColumns=@JoinColumn(name="ModuleID", referencedColumnName="ID"))
	private List<Module> modules;
	
	public Groupe() {
		super();
		this.modules = new ArrayList<>();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
	}
	
	public List<Etudiant> getEtudiants() {
		return this.etudiants;
	}   
	
	public List<Module> getModules() {
		return modules;
	}
}
