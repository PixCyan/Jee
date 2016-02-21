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
	private String urlModifierEtudiant;
	private String urlModifierNote;
	private String urlAjouterGroupe;
	
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
		urlModifierEtudiant = getServletConfig().getInitParameter("modifierEtudiant");
		urlModifierNote = getServletConfig().getInitParameter("modifierNote");
		urlAjouterGroupe = getServletConfig().getInitParameter("ajouterGroupe");
		
		// Cr√©ation de la factory permettant la cr√©ation d'EntityManager
		// (gestion des transactions)
		GestionFactory.open();

		///// INITIALISATION DE LA BD		
		if(GroupeDAO.getAll().isEmpty()){
			this.initBDD();
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
		
		// On r√©cup√®re la m√©thode d'envoi de la requ√™te
		String methode = request.getMethod().toLowerCase();
		
		// On r√©cup√®re l'action √† ex√©cuter
		String action = request.getPathInfo();
		if (action == null) {
			action = "/index";
			System.out.println("action == null");
		}
		
		// ExÈcution action
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
		} else if(methode.equals("get") && action.equals("/modifierEtudiant")) {
			showModifierEtudiant(request, response);
		} else if(methode.equals("get") && action.equals("/modifierNote")) {
			showModifierNote(request, response);
		} else if(methode.equals("get") && action.equals("/ajouterGroupe")) {
			showAjouterGroupe(request, response);
		} else if(methode.equals("get") && action.equals("/modifAbs")) {
			showTraitementAbs(request, response);
		}
	}
	
	private void showAjouterGroupe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Module> modules = ModuleDAO.getAll();
			
			request.setAttribute("modules", modules);
			request.setAttribute("content", urlAjouterGroupe);
			// Transfert le controle ‡ une autre servlet
			loadJSP(urlTemplate, request, response);
	}
	
	private void showModifierNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("idNote") != null && !request.getParameter("idNote").isEmpty()) {
			Note note = NoteDAO.retrieveById(Integer.parseInt(request.getParameter("idNote")));
			request.setAttribute("note", note);
			Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(request.getParameter("id")));
			List<Module> modules = GroupeDAO.retrieveById(etudiant.getGroupe().getId()).getModules();
			request.setAttribute("modules", modules);
			request.setAttribute("etudiant", etudiant);
			request.setAttribute("content", urlModifierNote);

			// Transfert le controle ‡ une autre servlet
			loadJSP(urlTemplate, request, response);
		}
	}
	
	
	private void showModifierEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(id));
		request.setAttribute("etudiant", etudiant);
		request.setAttribute("content", urlModifierEtudiant);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAjouterEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Groupe> groupes = GroupeDAO.getAll();
		
		request.setAttribute("groupes", groupes);
		request.setAttribute("content", urlAjouterEtudiant);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAjouterModule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Groupe> groupes = GroupeDAO.getAll();
		
		request.setAttribute("groupes", groupes);
		request.setAttribute("content", urlAjouterModule);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAjouterNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
			Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(request.getParameter("id")));
			List<Module> modules = GroupeDAO.retrieveById(etudiant.getGroupe().getId()).getModules();
			
			request.setAttribute("modules", modules);
			request.setAttribute("etudiant", etudiant);
			request.setAttribute("content", urlAjouterNote);

			// Transfert le controle ‡ une autre servlet
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

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showModulesGroupe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("groupe");
		Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(id));
		List<Module> modules = groupe.getModules();
		
		request.setAttribute("groupe", groupe);
		request.setAttribute("modules", modules);	
		request.setAttribute("content", urlModulesGroupe);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showTraitementAbs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("abs") != null && !request.getParameter("abs").isEmpty()) {
			int id = Integer.parseInt(request.getParameter("id"));
			int absence = Integer.parseInt(request.getParameter("abs"));
			if(absence == 1) {
				EtudiantDAO.addAbsences(id, 1);
			} else {
				EtudiantDAO.removeAbsences(id, 1);
			}
			showModifierAbsences(request, response);
		}
	}
		
	private void showConfirmationModif(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les Ètudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		if(request.getParameter("sujetNote") != null && !request.getParameter("sujetNote").isEmpty()
				&& request.getParameter("note") != null && !request.getParameter("note").isEmpty()
				&& request.getParameter("id") != null && !request.getParameter("id").isEmpty()
				&& request.getParameter("module") != null && !request.getParameter("module").isEmpty()) {
			int note = Integer.parseInt(request.getParameter("note"));
			if(note <= 0 || note > 20) {
				request.setAttribute("warning", "La note doit Ítre comprise entre 0 et 20 !");
				request.setAttribute("modif", false);
			} else {
				String id = request.getParameter("id");
				EntityManager em = GestionFactory.factory.createEntityManager();
				Etudiant etu = EtudiantDAO.retrieveById(Integer.parseInt(id));
				
				String sujetNote = request.getParameter("sujetNote");
				
				String mod = request.getParameter("module");
				Module module = ModuleDAO.retrieveById(Integer.parseInt(mod));
				
				EtudiantDAO.addNote(sujetNote, note, module, etu);
				
				request.setAttribute("warning", "");
				request.setAttribute("modif", true);
			}
		} else if(request.getParameter("prenom") != null && !request.getParameter("prenom").isEmpty()
				&& request.getParameter("nom") != null && !request.getParameter("nom").isEmpty()
				&& request.getParameter("groupe") != null && !request.getParameter("groupe").isEmpty()) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String id = request.getParameter("groupe");
			Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(id));
			
			EtudiantDAO.create(prenom, nom, groupe);
			request.setAttribute("warning", "");
			request.setAttribute("modif", true);
		} else if(request.getParameter("nomModule") != null && !request.getParameter("nomModule").isEmpty()
				&& request.getParameter("description") != null && !request.getParameter("description").isEmpty()
				&& request.getParameter("coeff") != null && !request.getParameter("coeff").isEmpty()) {
			List<Groupe> groupes = new ArrayList<>();
			for(Groupe g : GroupeDAO.getAll()) {
				if(request.getParameter(g.getNom()) != null && !request.getParameter(g.getNom()).isEmpty()) {
					groupes.add(g);
				}
			}
			if(groupes.isEmpty()) {
				request.setAttribute("warning", "Vous n'avez sÈlectionnÈ aucun groupe pour ce module !");
				request.setAttribute("modif", false);
			} else {
				String nomModule = request.getParameter("nomModule");
				String description = request.getParameter("description");
				String coeff = request.getParameter("coeff");
				Module module = ModuleDAO.create(nomModule, description, Integer.parseInt(coeff));
				ModuleDAO.addGroupes(groupes, module);
				request.setAttribute("warning", "");
				request.setAttribute("modif", true);
			}
		} else if(request.getParameter("removeEtu") != null) {
			String id = request.getParameter("removeEtu");
			EtudiantDAO.remove(Integer.parseInt(id));
			request.setAttribute("warning", "");
			request.setAttribute("modif", true);
		} else if(request.getParameter("removeModule") != null && !request.getParameter("removeModule").isEmpty()) {
			String id = request.getParameter("removeModule");
			//cascade type.REMOVE dans module ne semble pas fonctionner ou je l'ai mal compris
			Module module = ModuleDAO.retrieveById(Integer.parseInt(id));
			List<Note> notes = module.getNotes();
			for(Note n : notes) {
				NoteDAO.remove(n);
			}
			ModuleDAO.remove(module);
			request.setAttribute("warning", "");
			request.setAttribute("modif", true);
		} else if(request.getParameter("removeNote") != null && !request.getParameter("removeNote").isEmpty()) {
			String id = request.getParameter("removeNote");
			NoteDAO.remove(Integer.parseInt(id));
			request.setAttribute("warning", "");
			request.setAttribute("modif", true);
		} else if(request.getParameter("modifEtu") != null && !request.getParameter("modifEtu").isEmpty()) {
			String id = request.getParameter("modifEtu");
			Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(id));
			if(request.getParameter("modifNom") != null && !request.getParameter("modifNom").isEmpty()) {
				String nom =request.getParameter("modifNom");
				etudiant.setNom(nom);
			}
			if(request.getParameter("modifPrenom") != null && !request.getParameter("modifPrenom").isEmpty()) {
				String prenom = request.getParameter("modifPrenom");
				etudiant.setPrenom(prenom);
			}
			if(request.getParameter("modifAbs") != null && !request.getParameter("modifAbs").isEmpty()) {
				String abs = request.getParameter("modifAbs");
				etudiant.setNbAbsences(Integer.parseInt(abs));
			}	
			EtudiantDAO.update(etudiant);
			request.setAttribute("warning", "");
			request.setAttribute("modif", true);
		} else if(request.getParameter("nomGroupe") != null && !request.getParameter("nomGroupe").isEmpty()) {
			String nomGroupe = request.getParameter("nomGroupe");
			boolean groupeExist = false;
			for(Groupe g : GroupeDAO.getAll()) {
				if(g.getNom().equals(nomGroupe)) {
					groupeExist = true;
					break;
				}
			}
			if(groupeExist) {
				request.setAttribute("warning", "Ce groupe existe dÈj‡");
				request.setAttribute("modif", false);
			} else {
				Groupe groupe = GroupeDAO.create(request.getParameter("nomGroupe"));
				List<Module> modulesGroupe = new ArrayList<>();
				for(Module m : ModuleDAO.getAll()) {
					if(request.getParameter(m.getNom()) != null && !request.getParameter(m.getNom()).isEmpty()) {
						modulesGroupe.add(m);
					}
				}
				if(!modulesGroupe.isEmpty()) {
					GroupeDAO.addModules(modulesGroupe, groupe);
				}
				request.setAttribute("warning", "");
				request.setAttribute("modif", true);	
			}

		} else if(request.getParameter("removeGroupe") != null && !request.getParameter("removeGroupe").isEmpty()) {
			Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(request.getParameter("removeGroupe")));
			if(!groupe.getEtudiants().isEmpty()) {
				request.setAttribute("warning", "Ce groupe contient des Ètudiants, il ne peut pas Ítre supprimÈ !");
				request.setAttribute("modif", false);
			} else {
				System.out.println(groupe.getNom());
				GroupeDAO.remove(groupe);
				request.setAttribute("modif", true);
				request.setAttribute("warning", "");
			}
		} else {
			request.setAttribute("warning", "");
			request.setAttribute("modif", false);
		}
		request.setAttribute("content", urlConfirmationModification);
		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les Ètudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		String id = request.getParameter("id");
		Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(id));
		List<Note> notes = etudiant.getNotes();
		request.setAttribute("notes", notes);
		
		request.setAttribute("etudiant", etudiant);
		request.setAttribute("content", urlDetails);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showModules(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les Ètudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		List<Module> modules = ModuleDAO.getAll();
		request.setAttribute("modules", modules);	
		request.setAttribute("content", urlConsultationModules);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showModifierAbsences(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les Ètudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		String id = request.getParameter("id");
		Etudiant etudiant = EtudiantDAO.retrieveById(Integer.parseInt(id));
		request.setAttribute("etudiant", etudiant);
		request.setAttribute("content", urlModifierAbsences);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showDetailsGroupe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les Ètudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		String id = request.getParameter("id");
		Groupe groupe = GroupeDAO.retrieveById(Integer.parseInt(id));
		List<Etudiant> etudiants  = groupe.getEtudiants();

		request.setAttribute("groupe", groupe);	
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("content", urlDetailsGroupe);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les Ètudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("content", urlAll);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showGroupes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les Ètudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		List<Groupe> groupes =  GroupeDAO.getAll();
		
		request.setAttribute("groupes", groupes);
		request.setAttribute("content", urlGroupes);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les Ètudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		//Va chercher tous les Ètudiants
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("content", urlConsulterNotes);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAbsences(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-- Afficher tous les Ètudiants et leur moyenne
		//this.etudiants = GestionFactory.getEtudiants();
		
		//EtudiantDAO
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("content", urlConsulterAbsences);

		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	private void showAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Groupe> groupes = GroupeDAO.getAll();
		List<Module> modules = ModuleDAO.getAll();
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("groupes", groupes);
		request.setAttribute("modules", modules);
		request.setAttribute("content", urlAccueil);
		
		// Transfert le controle ‡ une autre servlet
		loadJSP(urlTemplate, request, response);
	}
	
	/**
	 * Charge la JSP indiqu√©e en param√®tre
	 * 
	 * @param url
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void loadJSP(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// L'interface RequestDispatcher permet de transf√©rer le contr√¥le √† une
		// autre servlet
		// Deux m√©thodes possibles :
		// - forward() : donne le contr√¥le √† une autre servlet. Annule le flux
		// de sortie de la servlet courante
		// - include() : inclus dynamiquement une autre servlet
		// + le contr√¥le est donn√© √† une autre servlet puis revient √† la servlet
		// courante (sorte d'appel de fonction).
		// + Le flux de sortie n'est pas supprim√© et les deux se cumulent

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	private void initBDD() {
		// Creation des groupes
		Groupe MIAM = GroupeDAO.create("MIAM");
		Groupe SIMO = GroupeDAO.create("SIMO");
		Groupe MESSI = GroupeDAO.create("MESSI");

		// Creation des Ètudiants
		EtudiantDAO.create("Francis", "Brunet-Manquat", MIAM);
		EtudiantDAO.create("Philippe", "Martin", MIAM);
		EtudiantDAO.create("Mario", "Cortes-Cornax", MIAM);
		EtudiantDAO.create("FranÁoise", "Coat", SIMO);
		EtudiantDAO.create("Laurent", "Bonnaud", MESSI);
		EtudiantDAO.create("SÈbastien", "Bourdon", MESSI);
		EtudiantDAO.create("Mathieu", "Gatumel", SIMO);
		
		//CrÈation de modules :
		Module m1 = ModuleDAO.create("Programmaion PHP", "Apprendre la programmation en PHP", 20);
		Module m2 = ModuleDAO.create("Programmation en Python", "Apprendre ‡ programmer en Python", 20);
		Module m3 = ModuleDAO.create("RÈfÈrencement web", "RÈfÈrencement web", 23);
		Module m4 = ModuleDAO.create("Maths", "Cours de Maths", 24);
		Module m5 = ModuleDAO.create("Programmation web en Java", "Programmation web en Java", 22);
		Module m6 = ModuleDAO.create("SI", "Cours de SystËmes d'informations",26);
		
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
