<%@ page import="projetEtape4.Module"%>

<!--  récupération des données -->
<jsp:useBean id="modules" type="java.util.Collection<Module>" scope="request"/>
<jsp:useBean id="etudiant" class="projetEtape4.Etudiant" scope="request"/>
<jsp:useBean id="note" class="projetEtape4.Note" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<h3> Ajout d'une note pour l'étudiant : <%= etudiant.getNom() + " " + etudiant.getPrenom() %></h3>
		
		<form action="confirmationModification">
			<div class="form-group">
				<label>Module : </label>
				<SELECT name="module" size="<%= 1 %>">
							<% for(Module module : modules){ %>
							<OPTION value="<%= module.getId() %>" <% if(module.getNom().equals(note.getModule().getNom())) { %> selected <% } %> > <%= module.getNom() %>
							<% } %>				
							</SELECT>
			</div>
		
			<div class="form-group">
				<label> Sujet de la note : </label>
				<input class="form-control" type="text" name="sujetNote" value="<%= note.getNom() %>"/>
		 	</div>
			<div class="form-group">
				<label> Note : </label>
				<input class="form-control" type="text" name="note" value="<%= note.getNote() %>"/>
		 	</div>
		
			
				
			<input hidden=true type="text" name="id" value="<%= etudiant.getId() %>"/>
			<button class="btn btn-default" type="submit"> Modifier la note </button>
		</form>
	</div>
</div>
