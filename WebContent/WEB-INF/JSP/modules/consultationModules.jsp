<%@ page import="projetEtape4.Groupe"%>
<%@ page import="projetEtape4.Module"%>

<!--  r�cup�ration des donn�es -->
<jsp:useBean id="modules" type="java.util.Collection<Module>" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<article class="panel-heading">
			<h3> Modules </h3>
			<button class="btn btn-primary" onclick="location.href='<%= getServletContext().getContextPath()%>/do/ajouterModule'">Cr�er un module</button>
		</article>
			<article class="panel-body">
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<thead><tr><th> Module </th><th> Description </th><th> Coeff </th><th>Groupes</th></tr></thead>
					<% for(Module m : modules) { %>
							<tr><td><%= m.getNom() %> </td><td><%= m.getDescription() %></td><td><%= m.getCoeff() %></td>
							<td> 
							<% 
							for(Groupe g : m.getGroupes()) {
								%><a href="<%= getServletContext().getContextPath()%>/do/detailsGroupe?id=<%= g.getId() %>"> <%= g.getNom() + "  " %></a>
							<% } %>
							</td>
							<td class="celModif"><a href="<%= getServletContext().getContextPath()%>/do/confirmationModification?removeModule=<%= m.getId() %>"> Supprimer </a></td>
							</tr>
						<% } %>
				</table>
			</article>
	</div>
</div>