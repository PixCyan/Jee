<%@ page import="projetEtape4.Groupe"%>
<div class="row">
	<div class="col-lg-12">
		<!--  r�cup�ration des donn�es -->
		<jsp:useBean id="etudiant" class="projetEtape4.Etudiant" scope="request"/>
		
		<h3>Modifier un �tudiant</h3>
		
		<form action="confirmationModification">
			<div class="form-group">
				<p> Groupe : <%= etudiant.getGroupe().getNom() %>
			</div>
			<div class="form-group">
				<label> Nom  : </label>
				<input class="form-control" type="text" name="modifNom" value="<%= etudiant.getNom() %>" placeholder="Dupont"/>
		 	</div>
			<div class="form-group">
				<label> Pr�nom : </label>
				<input class="form-control" type="text" name="modifPrenom" value="<%= etudiant.getPrenom() %>" placeholder="Damien"/>
		 	</div>
		 	<div class="form-group">
				<label> Absence(s) : </label>
				<input class="form-control" type="text" name="modifAbs" value="<%= etudiant.getAbs() %>" placeholder="0"/>
		 	</div>
		 	
			<input hidden=true type="text" name="modifEtu" value="<%= etudiant.getId() %>"/>
			<button class="btn btn-default" type="submit"> Modifier l'�tudiant </button>
		</form>
	</div>
</div>