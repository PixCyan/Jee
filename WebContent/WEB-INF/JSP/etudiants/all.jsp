<%@ page import="projetEtape4.Etudiant"%>

<!--  récupération des données -->
<jsp:useBean id="etudiants" type="java.util.Collection<Etudiant>" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		
		
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
						<td> <a href="<%= getServletContext().getContextPath()%>/do/detailsGroupe?id=<%= etu.getGroupe().getId() %>"> <%= etu.getGroupe().getNom() %> </a></td></tr></tr>
					<% } %>
			</table>
			
			<div> 
				<p> Moyenne générale : <%= moyenne/nbNote %></p>
			</div>
		</div>
	</div>
</div>