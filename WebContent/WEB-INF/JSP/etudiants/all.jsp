<%@ page import="projetEtape4.Etudiant"%>

<!--  r�cup�ration des donn�es -->
<jsp:useBean id="etudiants" type="java.util.Collection<Etudiant>" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		
		
		<article class="panel-heading">
			<h3> Liste des �tudiants</h3>
			<a href="<%= getServletContext().getContextPath()%>/do/ajouterEtudiant">Cr�er un �tudiant</a>
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
				<p> Moyenne g�n�rale : <%= moyenne/nbNote %></p>
			</div>
		</div>
	</div>
</div>