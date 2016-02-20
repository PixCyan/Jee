<%@ page import="projetEtape4.Etudiant"%>

<!--  récupération des données -->
<jsp:useBean id="etudiants" type="java.util.Collection<Etudiant>" scope="request"/>
<div class="row">
	<div class="col-lg-12">
		<h3> Liste des étudiants et leurs absences</h3>
		<article class="panel-heading">
			<article class="panel-body">
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<thead><tr><th> Nom </th><th> Absence(s) </th></tr></thead>
					<% for(Etudiant etu : etudiants) { %>
							<tr><td><a href="<%= getServletContext().getContextPath()%>/do/details?id=<%= etu.getId() %>"> <%= etu.getNom() + " " + etu.getPrenom() %> </a> </td>
							<td> <%=  etu.getAbs() %></td><td class="celModif"><a href="<%= getServletContext().getContextPath()%>/do/modifierAbsences?id=<%= etu.getId() %>"> Modifier </a></td></tr>
						<% } %>
				</table>
			</article>
		</article>
	</div>
</div>