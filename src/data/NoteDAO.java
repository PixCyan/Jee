package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import projetEtape4.Etudiant;
import projetEtape4.GestionFactory;
import projetEtape4.Groupe;
import projetEtape4.Module;
import projetEtape4.Note;

public class NoteDAO {

	public static Note retrieveById(int id) {
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		Note note = em.find(Note.class, id);
	    // Close the entity manager
	    em.close();	
		return note;
	}
	
	public static Note create(String sujetNote, int notation, Module module, Etudiant etu) {
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		em.getTransaction().begin();
		Note note = new Note();
		note.setNom(sujetNote);
		note.setNote(notation);
		note.setModule(module);
		note.setEtudiant(etu);
		// Commit
		em.getTransaction().commit();
		// Close the entity manager
		em.close();
		return note;
	}
	
	public static Note update(Note note) {
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		em.getTransaction().begin();
		// Attacher une entité persistante (etudiant) à l’EntityManager courant  pour réaliser la modification
		em.merge(note);
		// Commit
		em.getTransaction().commit();
		// Close the entity manager
		em.close();
		return note;
	}
	
	public static void remove(Note note) {
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		em.getTransaction().begin();
		// Retrouver l'entité persistante et ses liens avec d'autres entités en vue de la suppression
		Note no = em.find(Note.class, note.getId());
		em.remove(no);
		// Commit
		em.getTransaction().commit();
		// Close the entity manager
		em.close();
		// if EclipseLink cache enable -->
		// GestionFactory.factory.getCache().evictAll();
	}

	public static void remove(int id) {
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Note AS n WHERE n.id = :id")
        .setParameter("id", id)
        .executeUpdate();
		// Commit
		em.getTransaction().commit();
		// Close the entity manager
		em.close();
		// if EclipseLink cache enable -->
		// GestionFactory.factory.getCache().evictAll();
	}
	
	public static int removeAll() {
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		em.getTransaction().begin();
		// RemoveAll
		int deletedCount = em.createQuery("DELETE FROM Note").executeUpdate();
		// Commit
		em.getTransaction().commit();
		// Close the entity manager
		em.close();	
		return deletedCount;
	}

	// Retourne l'ensemble des etudiants
	public static List<Note> getAll() {
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		// Recherche 
		Query q = em.createQuery("SELECT n FROM Note n");
		@SuppressWarnings("unchecked")
		List<Note> listNotes = q.getResultList();
		return listNotes;
	}

	// Retourne l'ensemble des etudiants d'un groupe donné
	public static List<Note> getAllByModule(Module mod) {
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		// Recherche 
		Query q = em.createQuery("SELECT m FROM Module m WHERE m.groupe = :mod");
		@SuppressWarnings("unchecked")
		List<Note> listNotes = q.getResultList();
		return listNotes;
	}
}
