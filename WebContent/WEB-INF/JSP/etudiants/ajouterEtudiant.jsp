<%@ page import="projetEtape4.Groupe"%>
<div class="row">
	<div class="col-lg-12">
		<!--  r�cup�ration des donn�es -->
		<jsp:useBean id="groupes" type="java.util.Collection<Groupe>" scope="request"/>
		
		<h3>Cr�er un nouvel �tudiant</h3>
		
		<form action="confirmationModification">
			<div class="form-group">
				<label> Nom de l'�tudiant : </label>
				<input class="form-control" type="text" name="nom" value="" placeholder="Dupont"/>
		 	</div>
			<div class="form-group">
				<label> Note : </label>
				<input class="form-control" type="text" name="prenom" value="" placeholder="Damien"/>
		 	</div>
		
			<div class="form-group">
				<label>Groupes </label>
				<SELECT name="groupe" size="<%= 1 %>">
							<% for(Groupe groupe : groupes){ %>
							<OPTION value="<%= groupe.getId() %>"> <%= groupe.getNom() %>
							<% } %>				
							</SELECT>
			</div>
		
			<button class="btn btn-default" type="submit"> Cr�er l'�tudiant </button>
		</form>
	</div>
</div>