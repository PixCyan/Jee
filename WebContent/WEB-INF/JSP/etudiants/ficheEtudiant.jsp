<%@ page import="projetEtape4.Module"%>
<%@ page import="projetEtape4.Note"%>

<%-- Récupération des données --%>
<jsp:useBean id="etudiant" class="projetEtape4.Etudiant" scope="request"/>
<jsp:useBean id="notes" type="java.util.Collection<Note>" scope="request"/>


<div class="row">
	<div class="col-lg-4">
		<article class="panel-heading">
			<h3> Informations principales </h3>
		</article>
		<article class="panel-body">
			<table class="table table-striped table-bordered table-hover dataTable no-footer">
						<tr><td> Nom </td><td><%= etudiant.getNom() %> </td></tr>
						<tr><td>Prénom</td><td><%= etudiant.getPrenom() %></td></tr>
						<tr><td> Moyenne </td><td> <%= etudiant.calculerMoyenne() %></td></tr>
						<tr><td> Absences </td><td><%= etudiant.getAbs() %> </td></tr>
				</table>
		</article>	
	</div>
	<div class="col-lg-8">
		<article class="panel-heading">
			<h3> Notes </h3>
		</article>
		<article class="panel-body">
			<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<thead><tr><th> Sujet </th><th> Note </th></tr></thead>
					<% for(Note n : notes) { %>
						<tr><td><%= n.getModule().getNom() + " : " + n.getNom() %> </td><td><%= n.getNote() %></td>
						<td><a href="<%= getServletContext().getContextPath()%>/do/confirmationModification?removeNote=<%= n.getId() %>"> Supprimer </a></td></tr>
					<% } %>
			</table>
		</article>	
	</div>
</div>