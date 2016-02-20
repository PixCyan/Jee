package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import projetEtape4.Etudiant;
import projetEtape4.GestionFactory;
import projetEtape4.Groupe;
import projetEtape4.Module;
import projetEtape4.Note;

public class ModuleDAO {
	
	public static Module retrieveById(int id) {
			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
			Module module = em.find(Module.class, id);
		    // Close the entity manager
		    em.close();	
			return module;
		}
		
		public static Module create(String nom, String description, int coeff) {
			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();			
			//
			em.getTransaction().begin();
			Module module = new Module();
			module.setNom(nom);
			module.setDescription(description);
			module.setCoeff(coeff);
			//groupe.getModules().add(module);
			em.persist(module);
			// Commit
			em.getTransaction().commit();
			// Close the entity manager
			em.close();
			
			return module;
		}
		
		public static Module update(Module module) {
			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
			em.getTransaction().begin();
			// Attacher une entité persistante (etudiant) à l’EntityManager courant  pour réaliser la modification
			em.merge(module);
			// Commit
			em.getTransaction().commit();
			// Close the entity manager
			em.close();
			return module;
		}
		
		public static void remove(Module module) {
			
			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
			em.getTransaction().begin();
			// Retrouver l'entité persistante et ses liens avec d'autres entités en vue de la suppression
			Module mod = em.find(Module.class, module.getId());
			em.remove(mod);
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
			em.createQuery("DELETE FROM Module AS m WHERE m.id = :id")
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
			int deletedCount = em.createQuery("DELETE FROM Module").executeUpdate();
			// Commit
			em.getTransaction().commit();
			// Close the entity manager
			em.close();	
			return deletedCount;
		}
	
		// Retourne l'ensemble des etudiants
		public static List<Module> getAll() {
			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
			// Recherche 
			Query q = em.createQuery("SELECT m FROM Module m");
			@SuppressWarnings("unchecked")
			List<Module> listModules = q.getResultList();
			return listModules;
		}
		
		public static List<Groupe> addGroupes(List<Groupe> groupes, Module module) {
			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
			em.getTransaction().begin();
			for(Groupe g : groupes) {
				module.getGroupes().add(g);
				g.getModules().add(module);
				em.merge(module);
				em.merge(g);
			}
			
			em.getTransaction().commit();
			em.close();	
			
			return groupes;
		}
		
		public static void addModule(String nomModule, String description, List<Groupe> groupes, int coeff) {
			EntityManager em = GestionFactory.factory.createEntityManager();
			em.getTransaction().begin();
			Module module = create(nomModule, description, coeff);
			addGroupes(groupes, module);
			
			em.persist(module);
			// Commit
			em.getTransaction().commit();
			// Close the entity manager
			em.close();
		}
}
