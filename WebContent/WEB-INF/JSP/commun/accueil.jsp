<%@ page import="projetEtape4.Groupe"%>
<%@ page import="projetEtape4.Etudiant"%>
<%@ page import="projetEtape4.Module"%>

<jsp:useBean id="etudiants" type="java.util.Collection<Etudiant>" scope="request"/>
<jsp:useBean id="groupes" type="java.util.Collection<Groupe>" scope="request"/>
<jsp:useBean id="modules" type="java.util.Collection<Module>" scope="request"/>

<div class="row">
	<article class="col-lg-6">
		<h2> Accueil </h2>
		<div class="alert alert-success">
			<p> Bienvenue, </br>
			Cette page affiche les étudiants, les groupes et les modules disponibles sur l'application. </br>
			Utilisez le menu pour avoir accès à toutes les options. </p>
		</div>
	</article>
</div>	
<div class="row">
	<article class="col-lg-7">
		<!-- Affichage réduit des étudiants -->
		<article class="panel-heading">
			<h3> Liste des étudiants</h3>
			<a href="<%= getServletContext().getContextPath()%>/do/ajouterEtudiant">Créer un étudiant</a>
		</article>
		<div class="panel-body">
			<table class="table table-striped table-bordered table-hover dataTable no-footer">
				<thead><tr><th> Etudiants </th><th> Moyennes </th><th> Groupes </th></tr></thead>
				<% 
				int moyenne = 0;
				int nbNote = 0;
				for(Etudiant etu : etudiants) { 
					moyenne += etu.calculerMoyenne();
					nbNote ++; %>
						<tr><td><a href="<%= getServletContext().getContextPath()%>/do/details?id=<%= etu.getId() %>"> <%= etu.getNom() + " " + etu.getPrenom() %> </a> </td><td> <%= etu.calculerMoyenne() %> </td>
						<td> <a href="<%= getServletContext().getContextPath()%>/do/detailsGroupe?id=<%= etu.getGroupe().getId() %>"> <%= etu.getGroupe().getNom() %> </a></td></tr>
					<% } %>
			</table>
			
			<div> 
				<p> Moyenne générale : <%= moyenne/nbNote %></p>
			</div>
		</div>
	</article>
	<article class="col-lg-3">
		<jsp:include page="<%= getServletContext().getInitParameter(\"groupes\")%>"/>
	</article>
</div>		
<div class="row">
	<article class="col-lg-12">
		<jsp:include page="<%= getServletContext().getInitParameter(\"consultationModules\")%>"/>
	</article>
</div>		

