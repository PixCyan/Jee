<%-- Récupération des données --%>
<jsp:useBean id="etudiant" class="projetEtape4.Etudiant" scope="request"/>
<div class="row">
	<div class="col-lg-12">
		<p> Nom : <%= etudiant.getNom() %><br/>
		Prenom : <%= etudiant.getPrenom() %><br/>
		Absence(s) : <%= etudiant.getAbs() %> </p>
	</div>
</div>