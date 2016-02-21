<%@ page import="projetEtape4.Groupe"%>
<div class="row">
	<div class="col-lg-12">
		<!--  récupération des données -->
		<jsp:useBean id="etudiant" class="projetEtape4.Etudiant" scope="request"/>
		
		<h3>Modifier un étudiant</h3>
		
		<form action="confirmationModification">
			<div class="form-group">
				<p> Groupe : <%= etudiant.getGroupe().getNom() %>
			</div>
			<div class="form-group">
				<label> Nom  : </label>
				<input class="form-control" type="text" name="modifNom" value="<%= etudiant.getNom() %>" placeholder="Dupont"/>
		 	</div>
			<div class="form-group">
				<label> Prénom : </label>
				<input class="form-control" type="text" name="modifPrenom" value="<%= etudiant.getPrenom() %>" placeholder="Damien"/>
		 	</div>
		 	<div class="form-group">
				<label> Absence(s) : </label>
				<input class="form-control" type="text" name="modifAbs" value="<%= etudiant.getAbs() %>" placeholder="0"/>
		 	</div>
		 	
			<input hidden=true type="text" name="modifEtu" value="<%= etudiant.getId() %>"/>
			<button class="btn btn-default" type="submit"> Modifier l'étudiant </button>
		</form>
	</div>
</div>