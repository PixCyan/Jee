package data;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import projetEtape4.GestionFactory;
import projetEtape4.Groupe;
import projetEtape4.Module;

public class GroupeDAO {
	
	public static Groupe retrieveById(int id) {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
			
		//
		Groupe groupe = em.find(Groupe.class, id);
		
	    // Close the entity manager
	    em.close();
				
		return groupe;
	}

	public static Groupe create(String nom) {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		
		// create
		em.getTransaction().begin();

		// create new groupe
		Groupe groupe = new Groupe();
		groupe.setNom(nom);
		em.persist(groupe);

		// Commit
		em.getTransaction().commit();

		// Close the entity manager
		em.close();
		
		return groupe;
	}	
	
	public static int removeAll() {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();

		//
		em.getTransaction().begin();
		
		// RemoveAll
		int deletedCount = em.createQuery("DELETE FROM Groupe").executeUpdate();

		// Commit
		em.getTransaction().commit();
		
		// Close the entity manager
		em.close();
		
		return deletedCount;
	}
	
	public static void remove(Groupe groupe) {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		em.getTransaction().begin();
		// Retrouver l'entité persistante et ses liens avec d'autres entités en vue de la suppression
		Groupe g = em.find(Groupe.class, groupe.getId());
		em.remove(g);
		// Commit
		em.getTransaction().commit();
		// Close the entity manager
		em.close();
		// if EclipseLink cache enable -->
		// GestionFactory.factory.getCache().evictAll();
	}
	
	
	
	public static List<Groupe> getAll() {

		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
				
		// Recherche 
		Query q = em.createQuery("SELECT g FROM Groupe g");

		@SuppressWarnings("unchecked")
		List<Groupe> listGroupe = q.getResultList();
		
		return listGroupe;
	}
	
	public static List<Module> addModules(List<Module> modules, Groupe groupe) {
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		em.getTransaction().begin();
		for(Module m : modules) {
			m.getGroupes().add(groupe);
			groupe.getModules().add(m);
			em.merge(m);
			em.merge(groupe);
		}
		
		em.getTransaction().commit();
		em.close();	
		
		return modules;
	}
	
}
