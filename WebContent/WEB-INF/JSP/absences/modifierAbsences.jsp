<%@ page import="projetEtape4.Etudiant"%>

<!--  récupération des données -->
<jsp:useBean id="etudiant" class="projetEtape4.Etudiant" scope="request"/>

<div class="row">
	<div class="col-lg-8">
		<article class="panel-heading">
				<h3> Modification des absences de <%= etudiant.getNom() + " " +  etudiant.getPrenom()  %> </h3>
			</article>
			<article class="panel-body">
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
							<tr><td> Nom </td><td><%= etudiant.getNom() %> </td></tr>
							<tr><td>Prénom</td><td><%= etudiant.getPrenom() %></td></tr>
							<tr><td> Absences </td><td><%= etudiant.getAbs() %> </td></tr>
					</table>
			</article>
		<button class="btn btn-primary" onclick="location.href='<%= getServletContext().getContextPath()%>/do/modifAbs?abs=1&id=<%= etudiant.getId()%>'">Ajouter une absence</button>
		<button class="btn btn-primary" onclick="location.href='<%= getServletContext().getContextPath()%>/do/modifAbs?abs=2&id=<%= etudiant.getId()%>'">Retirer une absence</button>
	</div>
</div>