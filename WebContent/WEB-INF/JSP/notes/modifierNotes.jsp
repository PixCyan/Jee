<%@ page import="projetEtape4.Etudiant"%>
<%@ page import="projetEtape4.Module"%>
<%@ page import="projetEtape4.Note"%>

<!--  r�cup�ration des donn�es -->
<jsp:useBean id="etudiant" class="projetEtape4.Etudiant" scope="request"/>
<jsp:useBean id="notes" type="java.util.Collection<Note>" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<article class="panel-heading">
			<h3> Modification des notes de <%= etudiant.getNom() + " " +  etudiant.getPrenom()  %></h3>
			
			<button class="btn btn-primary" onclick="location.href='<%= getServletContext().getContextPath()%>/do/ajouterNote?id=<%= etudiant.getId() %>'">Ajouter une note</button>
		</article>
		<div class="panel-body">
			<table class="table table-striped table-bordered table-hover dataTable no-footer">
				<thead><tr><th> Sujet </th><th> Note </th></tr></thead>
				<% for(Note n : notes) { %>
					<tr><td><%= n.getModule().getNom() + " : " + n.getNom() %> </td><td><%= n.getNote() %></td>
					<td><a href="<%= getServletContext().getContextPath()%>/do/confirmationModification?removeNote=<%= n.getId() %>"> Supprimer </a></td>
					<td><a href="<%= getServletContext().getContextPath()%>/do/modifierNote?idNote=<%= n.getId() %>&id=<%= etudiant.getId() %>"> Modifier </a></td></tr>
				<% } %>
			</table>
		</div>
		
	</div>
</div>