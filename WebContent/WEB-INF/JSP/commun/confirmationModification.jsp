<!--  r�cup�ration des donn�es -->
<jsp:useBean id="modif" type="java.lang.Boolean" scope="request"/>
<div class="row">
	<div class="col-lg-12">
		<h3> Modification </h3>
		
		<% if(modif == true) { %>
		<div class="alert alert-success">
			<p> Vos changement ont bien �t� pris en compte </p>
		</div>
		<% } else { %>
		<div class="alert alert-danger">
			<p> Aucun changement ou variable vide </p>
		</div>
		<% } %>
	</div>
</div>