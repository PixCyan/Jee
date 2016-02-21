<%@ page import="projetEtape4.Module"%>

<!--  récupération des données -->
<jsp:useBean id="modules" type="java.util.Collection<Module>" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<h3> Ajout d'un module </h3>
		
		<form action="confirmationModification">
			<div class="form-group">
				<label> Nom du groupe </label>
				<input class="form-control" type="text" name="nomGroupe" value="" placeholder="ADA"/>
		 	</div>
			<div class="form-group">
				<% for(Module m : modules) { %>
				<div class="checkbox">
				    <label>
				        <input type="checkbox" name="<%= m.getNom() %>" value="<%= m.getId() %>"><%= m.getNom() %>
				    </label>
				</div>	
			<% } %>
				</div>
			</div>
			<input class="btn btn-default" type="submit" value="Créer le groupe">
		</form>
	</div>
</div>