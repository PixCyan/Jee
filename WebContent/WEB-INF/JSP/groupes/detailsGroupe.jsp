<%@ page import="projetEtape4.Etudiant"%>

<!--  récupération des données -->
<jsp:useBean id="etudiants" type="java.util.Collection<Etudiant>" scope="request"/>
<jsp:useBean id="groupe" class="projetEtape4.Groupe" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<h3>Etudiants du groupe <%= groupe.getNom() %></h3>
		<button class="btn btn-primary" onclick="location.href='<%= getServletContext().getContextPath()%>/do/modulesGroupe?groupe=<%= groupe.getId() %>'">Modules du groupe </button>
		<article class="panel-heading">
			<table class="table table-striped table-bordered table-hover dataTable no-footer">
				<tr><th> Etudiants </th><th> Moyennes </th><th> Notes </th><th> Absences </th></tr>
				<% 
				int moyenne = 0;
				int nbNote = 0;
				for(Etudiant etu : etudiants) { 
					moyenne += etu.calculerMoyenne();
					nbNote ++;
				%>
						<tr><td><a href="<%= getServletContext().getContextPath()%>/do/details?id=<%= etu.getId() %>"> <%= etu.getNom() + " " + etu.getPrenom() %> </a></td><td> <%= etu.calculerMoyenne() %> </td>
						<td class="celModif"><a href="<%= getServletContext().getContextPath()%>/do/modifierNotes?id=<%= etu.getId() %>"> Consulter / Modifier </a></td>
						<td class="celModif"> <%=  etu.getAbs() + " " %><a href="<%= getServletContext().getContextPath()%>/do/modifierAbsences?id=<%= etu.getId() %>"> Modifier </a></td>
						<td class="celModif"><a href="<%= getServletContext().getContextPath()%>/do/details?id=<%= etu.getId() %>"> Détails </a></td>
						<td class="celModif"><a href="<%= getServletContext().getContextPath()%>/do/confirmationModification?removeEtu=<%= etu.getId() %>"> Supprimer </a></td>
						<td class="celModif"><a href="<%= getServletContext().getContextPath()%>/do/modifierEtudiant?id=<%= etu.getId() %>"> Modifier </a></td></tr>
					<% } %>
					<tr><td> Moyenne du groupe </td> <td> <%if(nbNote != 0) { %> <%= moyenne/nbNote %> <% } %></td></tr>
			</table>
		</article>
	</div>
</div>



