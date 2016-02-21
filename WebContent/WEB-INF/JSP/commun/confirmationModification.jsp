<!--  récupération des données -->
<jsp:useBean id="modif" type="java.lang.Boolean" scope="request"/>
<jsp:useBean id="warning" type="java.lang.String" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<h3> Modification </h3>
		
		<% if(modif) { %>
		<div class="alert alert-success">
			<p> Vos changements ont bien été pris en compte </p>
		</div>
		<% } else { %>
		<div class="alert alert-danger">
			<p> Aucun changement ou variable vide </p>
		</div>
		<% } %>
		
		<% if(!warning.equals("")) { %>
		<div class="alert alert-warning">
			<p><%= warning %></p>
		</div>
		<% } %>
		
	</div>
</div>