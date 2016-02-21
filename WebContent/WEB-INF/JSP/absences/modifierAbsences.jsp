<%@ page import="projetEtape4.Etudiant"%>

<!--  récupération des données -->
<jsp:useBean id="etudiant" class="projetEtape4.Etudiant" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<h3> Modification des absences de <%= etudiant.getNom() + " " +  etudiant.getPrenom()  %></h3>
		
		<p> Nom : <%= etudiant.getNom() %><br/>
		Prenom : <%= etudiant.getPrenom() %><br/>
		Absence(s) : </p>
		<a href="<%= getServletContext().getContextPath()%>/do/confirmationModification?abs=1">Ajouter une absence</a>
		<a href="<%= getServletContext().getContextPath()%>/do/confirmationModification?abs=2">Retirer une absence</a>
		
	</div>
</div>