<%@ page import="projetEtape4.Groupe"%>
<div class="row">
	<div class="col-lg-12">
		<!--  récupération des données -->
		<jsp:useBean id="groupes" type="java.util.Collection<Groupe>" scope="request"/>
		
		<h3>Créer un nouvel étudiant</h3>
		<p class="alert alert-warning"> Attention : Le choix du groupe est définitif, une fois l'étudiant créé vous ne pourrez pas le changer de groupe.</p>
		<form action="confirmationModification">
			<div class="form-group">
				<label> Nom : </label>
				<input class="form-control" type="text" name="nom" value="" placeholder="Dupont"/>
		 	</div>
			<div class="form-group">
				<label> Prénom : </label>
				<input class="form-control" type="text" name="prenom" value="" placeholder="Damien"/>
		 	</div>
			<div class="form-group">
				<label>Groupe : </label>
				<SELECT name="groupe" size="<%= 1 %>">
							<% for(Groupe groupe : groupes){ %>
							<OPTION value="<%= groupe.getId() %>"> <%= groupe.getNom() %>
							<% } %>				
							</SELECT>
			</div>
			<button class="btn btn-default" type="submit"> Créer l'étudiant </button>
		</form>
	</div>
</div>