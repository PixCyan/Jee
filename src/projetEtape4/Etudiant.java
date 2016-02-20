package projetEtape4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Groupe
 *
 */
@Entity
public class Etudiant implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false) 
	private String prenom;
	
	@Column(nullable=false)
	private String nom;
	
	@Column(nullable=false)
	private int abs;
	
	@ManyToOne
	private Groupe groupe;
	
	@OneToMany(mappedBy="etudiant", fetch=FetchType.LAZY)
	private List<Note> notes;
	

	public Etudiant() {
		super();
		abs = 0;
		this.notes = new ArrayList<>();
	}  
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Groupe getGroupe() {
		return this.groupe;
	}
	
	public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
        if (!groupe.getEtudiants().contains(this)) {
        	groupe.getEtudiants().add(this);
        }
    }
	
	public int getAbs() {
		return abs;
	}

	public void setNbAbsences(int nbAbsences) {
		this.abs = nbAbsences;
	}
	
	public List<Note> getNotes() {
		return notes;
	}
   
	public int calculerMoyenne() {
		if(this.getNotes().isEmpty()) {
			return 0;
		} else {
			List<Note> notes = this.getNotes();
			int moyenne = 0;
			int nbNote = 0;
			for(Note n : notes) {
				moyenne += n.getNote() * n.getModule().getCoeff();
				nbNote += n.getModule().getCoeff();
			}
			return (moyenne/nbNote);
		}
		
	}
	
	@Override
	public String toString() {
		return "[" + this.getId() + "] " + this.getPrenom() + " " + this.getNom();
	}
}
