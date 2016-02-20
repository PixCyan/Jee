package projetEtape4;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.EtudiantDAO;
import data.GroupeDAO;
import data.ModuleDAO;
import data.NoteDAO;
import projetEtape4.Etudiant;
import projetEtape4.Groupe;
import projetEtape4.GestionFactory;

public class Controleur extends HttpServlet{
	private Collection<Etudiant> etudiants;
	private String urlDetails;
	private String urlAccueil;
	private String urlTemplate;
	private String urlConsulterAbsences;
	private String urlConsulterNotes;
	private String urlAll;
	private String urlGroupes;
	private String urlDetailsGroupe;
	private String urlModifierAbsences;
	private String urlConfirmationModification;
	private String urlConsultationModules;
	private String urlModulesGroupe;
	private String urlModifierNotes;
	private String urlAjouterNote;
	private String urlAjouterModule;
	private String urlAjouterEtudiant;
	
	//Initialisation de la servlet
	public void init() throws ServletException {
		urlAccueil = getServletConfig().getInitParameter("accueil");
		urlConsulterAbsences = getServletConfig().getInitParameter("absences");
		urlConsulterNotes = getServletConfig().getInitParameter("notes");
		urlDetails = getServletConfig().getInitParameter("details");
		urlTemplate = getServletConfig().getInitParameter("template");
		urlAll =  getServletConfig().getInitParameter("all");
		urlGroupes = getServletConfig().getInitParameter("groupes");
		urlDetailsGroupe = getServletConfig().getInitParameter("detailsGroupe");
		urlModifierAbsences = getServletConfig().getInitParameter("modifierAbs");
		urlConfirmationModification = getServletConfig().getInitParameter("confirmationModification");
		urlConsultationModules = getServletConfig().getInitParameter("consultationModules");
		urlModulesGroupe = getServletConfig().getInitParameter("modulesGroupe");
		urlModifierNotes = getServletConfig().getInitParameter("modifierNotes");
		urlAjouterNote = getServletConfig().getInitParameter("ajouterNote");
		urlAjouterModule = getServletConfig().getInitParameter("ajouterModule");
		urlAjouterEtudiant = getServletConfig().getInitParameter("ajouterEtudiant");
		
		// Création de la factory permettant la création d'EntityManager
		// (gestion des transactions)
		GestionFactory.open();

		///// INITIALISATION DE LA BD		
		if(GroupeDAO.getAll().isEmpty()){
			// Creation des groupes
			Groupe MIAM = GroupeDAO.create("MIAM");
			Groupe SIMO = GroupeDAO.create("SIMO");
			Groupe MESSI = GroupeDAO.create("MESSI");

			// Creation des �tudiants
			EtudiantDAO.create("Francis", "Brunet-Manquat", MIAM);
			EtudiantDAO.create("Philippe", "Martin", MIAM);
			EtudiantDAO.create("Mario", "Cortes-Cornax", MIAM);
			EtudiantDAO.create("Fran�oise", "Coat", SIMO);
			EtudiantDAO.create("Laurent", "Bonnaud", MESSI);
			EtudiantDAO.create("S�bastien", "Bourdon", MESSI);
			EtudiantDAO.create("Mathieu", "Gatumel", SIMO);
			
			//Cr�ation de modules :
			Module m1 = ModuleDAO.create("Programmaion PHP", "Apprendre la programmation en langage PHP", 20);
			Module m2 = ModuleDAO.create("Programmation en Python", "Apprendre � programmer en Python", 20);
			Module m3 = ModuleDAO.create("R�f�rencement web", "R�f�rencement web", 23);
			Module m4 = ModuleDAO.create("Maths", "Cours de Maths", 24);
			Module m5 = ModuleDAO.create("Programmation J2EE", "Apprendre � programmer en langage J2EE", 22);
			Module m6 = ModuleDAO.create("SI", "Cours de Syst�mes d'informations",26);
			
			EntityManager em = GestionFactory.factory.createEntityManager();
			em.getTransaction().begin();
		
			m1.getGroupes().add(MIAM);
			m1.getGroupes().add(SIMO);
			m2.getGroupes().add(MESSI);
			m3.getGroupes().add(SIMO);
			m3.getGroupes().add(MIAM);
			m4.getGroupes().add(MESSI);
			m4.getGroupes().add(MIAM);
			m5.getGroupes().add(SIMO);
			m6.getGroupes().add(SIMO);
			
			MIAM.getModules().add(m1);
			MIAM.getModules().add(m3);
			MIAM.getModules().add(m4);
			MESSI.getModules().add(m2);
			MESSI.getModules().add(m4);
			SIMO.getModules().add(m5);
			SIMO.getModules().add(m6);
			SIMO.getModules().add(m1);
			SIMO.getModules().add(m3);
			
			em.merge(MIAM);
			em.merge(SIMO);
			em.merge(MESSI);
			em.getTransaction().commit();
			em.close();
		}
			
	}

	@Override
	public void destroy() {
		super.destroy();

		// Fermeture de la factory
		GestionFactory.close();
	}
	
	// POST
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// on passe la main au GET
		doGet(request, response);
	}
	
	// GET
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// On récupère la méthode d'envoi de la requête
		String methode = request.getMethod().toLowerCase();
		
		// On récupère l'action à exécuter
		String action = request.getPathInfo();
		if (action == null) {
			action = "/index";
			System.out.println("action == null");
		}
		
		// Ex�cution action
		if (methode.equals("get") && action.equals("/details")) {
			showDetails(request, response);
		} else if(methode.equals("get") && action.equals("/notes")) {
			showNotes(request, response);
		} else if(methode.equals("get") && action.equals("/absences")) {
			showAbsences(request, response);
		} else if(methode.equals("get") && action.equals("/accueil")) {
			showAccueil(request, response);
		} else if(methode.equals("get") && action.equals("/groupes")) {
			showGroupes(request, response);
		} else if(methode.equals("get") && action.equals("/all")) {
			showAll(request, response);
		} else if(methode.equals("get") && action.equals("/detailsGroupe")) {
			showDetailsGroupe(request, response);
		} else if(methode.equals("get") && action.equals("/modifierAbsences")) {
			showModifierAbsences(request, response);
		}  else if(methode.equals("get") && action.equals("/confirmationModification")) {
			showConfirmationModif(request, response);
		} else if(methode.equals("get") && action.equals("/consultationModules")) {
			showModules(request, response);
		} else if(methode.equals("get") && action.equals("/modulesGroupe")) {
			showModulesGroupe(request, response);
		} else if(methode.equals("get") && action.equals("/modifierNotes")) {
			showModifierNotes(request, response);
		} else if(methode.equals("get") && action.equals("/ajouterNote")) {
			showAjouterNote(request, response);
		} else if(methode.equals("get") && action.equals("/ajouterModule")) {
			showAjouterModule(request, response);
		} else if(methode.equals("get") && action.equals("/ajouterEtudiant")) {
			showAjouterEtudiant(request, response);
		}
	}
	
	private void showAjouterEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Groupe> groupes = GroupeDAO.getAll();
		
		request.setAttribute("groupes", groupes);
		request.setAttribute("content", urlAjouterEtudiant);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAjouterModule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Groupe> groupes = GroupeDAO.getAll();
		
		request.setAttribute("groupes", groupes);
		request.setAttribute("content", urlAjouterModule);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAjouterNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") != null) {
			Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(request.getParameter("id")));
			List<Module> modules = GroupeDAO.retrieveById(etudiant.getGroupe().getId()).getModules();
			
			request.setAttribute("modules", modules);
			request.setAttribute("etudiant", etudiant);
			request.setAttribute("content", urlAjouterNote);

			// Transfert le controle � une autre servlet
			loadJSP(urlTemplate, request, response);
		}
	}

	private void showModifierNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(id));
		List<Note> notes = etudiant.getNotes();
		
		request.setAttribute("notes", notes);
		request.setAttribute("etudiant", etudiant);
		request.setAttribute("content", urlModifierNotes);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showModulesGroupe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("groupe");
		Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(id));
		List<Module> modules = groupe.getModules();
		
		request.setAttribute("groupe", groupe);
		request.setAttribute("modules", modules);	
		request.setAttribute("content", urlModulesGroupe);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
		
	private void showConfirmationModif(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les �tudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		if(request.getParameter("abs") != null) {
			String id = request.getParameter("id");
			EntityManager em = GestionFactory.factory.createEntityManager();
			em.getTransaction().begin();
			Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(id));
			etudiant.setNbAbsences(Integer.parseInt(request.getParameter("abs")));
			em.merge(etudiant);
			// Commit
			em.getTransaction().commit();
			// Close the entity manager
			em.close();
			request.setAttribute("modif", true);
		} else if(request.getParameter("sujetNote") != null) {
			String id = request.getParameter("id");
			EntityManager em = GestionFactory.factory.createEntityManager();
			Etudiant etu = EtudiantDAO.retrieveById(Integer.parseInt(id));
			
			String sujetNote = request.getParameter("sujetNote");
			String note = request.getParameter("note");
			String mod = request.getParameter("module");
			Module module = ModuleDAO.retrieveById(Integer.parseInt(mod));
			
			EtudiantDAO.addNote(sujetNote, Integer.parseInt(note), module, etu);
			request.setAttribute("modif", true);
		} else if(request.getParameter("prenom") != null) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String id = request.getParameter("groupe");
			Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(id));
			
			EtudiantDAO.create(prenom, nom, groupe);
			request.setAttribute("modif", true);
		} else if(request.getParameter("nomModule") != null) {
			String nomModule = request.getParameter("nomModule");
			String description = request.getParameter("description");
			String coeff = request.getParameter("coeff");
			List<Groupe> groupes = GroupeDAO.getAll();
			Module module = ModuleDAO.create(nomModule, description, Integer.parseInt(coeff));
			List<Groupe> groups = new ArrayList<>();
			for(Groupe g : groupes) {
				if(request.getParameter(g.getNom()) != null) {
					Groupe gr = GroupeDAO.retrieveById(Integer.parseInt(request.getParameter(g.getNom())));
					groups.add(gr);
				}
			}	
			ModuleDAO.addGroupes(groups, module);		
			request.setAttribute("modif", true);
		}
		
		
		else {
			request.setAttribute("modif", false);
		}
		request.setAttribute("content", urlConfirmationModification);
		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les �tudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		String id = request.getParameter("id");
		Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(id));
		request.setAttribute("etudiant", etudiant);
		request.setAttribute("content", urlDetails);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showModules(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les �tudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		List<Module> modules = ModuleDAO.getAll();
		request.setAttribute("modules", modules);	
		request.setAttribute("content", urlConsultationModules);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showModifierAbsences(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les �tudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		String id = request.getParameter("id");
		Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(id));
		request.setAttribute("etudiant", etudiant);
		request.setAttribute("content", urlModifierAbsences);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showDetailsGroupe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les �tudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		String id = request.getParameter("id");
		Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(id));
		List<Etudiant> etudiants  = groupe.getEtudiants();
		
		request.setAttribute("groupe", groupe);	
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("content", urlDetailsGroupe);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les �tudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("content", urlAll);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showGroupes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les �tudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		List<Groupe> groupes =  GroupeDAO.getAll();
		
		request.setAttribute("groupes", groupes);
		request.setAttribute("content", urlGroupes);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les �tudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		//Va chercher tous les �tudiants
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("content", urlConsulterNotes);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAbsences(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les �tudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		
		//EtudiantDAO
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("content", urlConsulterAbsences);

		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("content", urlAccueil);
		
		// Transfert le controle � une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	/**
	 * Charge la JSP indiquée en paramètre
	 * 
	 * @param url
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loadJSP(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// L'interface RequestDispatcher permet de transférer le contrôle à une
		// autre servlet
		// Deux méthodes possibles :
		// - forward() : donne le contrôle à une autre servlet. Annule le flux
		// de sortie de la servlet courante
		// - include() : inclus dynamiquement une autre servlet
		// + le contrôle est donné à une autre servlet puis revient à la servlet
		// courante (sorte d'appel de fonction).
		// + Le flux de sortie n'est pas supprimé et les deux se cumulent

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}