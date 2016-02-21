<%@ page import="projetEtape4.Etudiant"%>

<!--  récupération des données -->
<jsp:useBean id="etudiants" type="java.util.Collection<Etudiant>" scope="request"/>
<div class="row">
	<div class="col-lg-12">
		
		<article class="panel-heading">
			<h3> Liste des étudiants et leur moyenne</h3>
		</article>
		<div class="panel-body">
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
							<td> <a href="<%= getServletContext().getContextPath()%>/do/detailsGroupe?id=<%= etu.getGroupe().getId() %>"> <%= etu.getGroupe().getNom() %> </a></td>
							<td class="celModif"><a href="<%= getServletContext().getContextPath()%>/do/modifierNotes?id=<%= etu.getId() %>"> Consulter / Modifier </a></td>
							<td class="celModif"><a href="<%= getServletContext().getContextPath()%>/do/details?id=<%= etu.getId() %>"> Détails </a></td></tr>
						<% } %>
						<tr><td> Moyenne globale </td> <td> <%if(nbNote != 0) { %> <%= moyenne/nbNote %> <% } %></td></tr>
				</table>
			</div>
		</div>
	</div>
</div>