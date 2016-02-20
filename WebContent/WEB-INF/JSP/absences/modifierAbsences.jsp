<%@ page import="projetEtape4.Etudiant"%>

<!--  récupération des données -->
<jsp:useBean id="etudiant" class="projetEtape4.Etudiant" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<h3> Modification des absences de <%= etudiant.getNom() + " " +  etudiant.getPrenom()  %></h3>
		
		<p> Nom : <%= etudiant.getNom() %><br/>
		Prenom : <%= etudiant.getPrenom() %><br/>
		Absence(s) : </p>
		
		<form action="confirmationModification">
		 <input type="text" name="abs" value="<%= etudiant.getAbs() %>"/>
		 <input hidden=true type="text" name="id" value="<%= etudiant.getId() %>"/>
		  <input type="submit" value="Modifier le nombre d'absence">
		</form>
	</div>
</div>