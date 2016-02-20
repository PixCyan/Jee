<%@ page import="projetEtape4.Etudiant"%>

<!--  récupération des données -->
<jsp:useBean id="etudiants" type="java.util.Collection<Etudiant>" scope="request"/>
<jsp:useBean id="groupe" class="projetEtape4.Groupe" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<h3>Etudiants du groupe <%= groupe.getNom() %></h3>
		<a href="<%= getServletContext().getContextPath()%>/do/modulesGroupe?groupe=<%= groupe.getId() %>"">Modules du groupe </a>
		<article class="panel-heading">
			<table class="table table-striped table-bordered table-hover dataTable no-footer">
				<tr><td> Etudiants </td><td> Moyennes </td></tr>
				<% 
				int moyenne = 0;
				int nbNote = 0;
				for(Etudiant etu : etudiants) { 
					moyenne += etu.calculerMoyenne();
					nbNote ++;
				%>
						<tr><td><a href="<%= getServletContext().getContextPath()%>/do/details?id=<%= etu.getId() %>"> <%= etu.getNom() + " " + etu.getPrenom() %> </a></td><td> <%= etu.calculerMoyenne() %> </td></tr>
					<% } %>
			</table>
			
			<div> 
				<p> Moyenne du groupe : <%= moyenne/nbNote %></p>
			</div>
		</article>
	</div>
</div>