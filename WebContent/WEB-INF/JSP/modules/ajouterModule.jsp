<%@ page import="projetEtape4.Groupe"%>

<!--  récupération des données -->
<jsp:useBean id="groupes" type="java.util.Collection<Groupe>" scope="request"/>
<div class="row">
	<div class="col-lg-12">
		<h3> Ajout d'un module </h3>
		
		<form action="confirmationModification">
			<div class="form-group">
				<label> Nom du module </label>
				<input class="form-control" type="text" name="nomModule" value="" placeholder="Programmation ADA"/>
		 	</div>
			<div class="form-group">
			    <label>Description</label>
			    <textarea class="form-control" rows="3" name="description"></textarea>
			</div>
			<div class="form-group">
			<label>Groupes LP</label>
			<% for(Groupe g : groupes) { %>
				<div class="checkbox">
				    <label>
				        <input type="checkbox" name="<%= g.getNom() %>" value="<%= g.getId() %>"><%= g.getNom() %>
				    </label>
				</div>	
			<% } %>
			</div>
			<div class="form-group">
			    <label>Coeff</label>
			    <SELECT name="coeff" size="<%= 1 %>">
							<% for(int i = 20; i <= 30; i++ ){ %>
							<OPTION value="<%= i %>"> <%= i %>
							<% } %>				
							</SELECT>
			</div>
		
			<input class="btn btn-default" type="submit" value="Créer le module">	
		</form>
	</div>
</div>