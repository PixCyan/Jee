<%@ page import="projetEtape4.Groupe"%>
<%@ page import="projetEtape4.Module"%>

<!--  récupération des données -->
<jsp:useBean id="modules" type="java.util.Collection<Module>" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<h3> Modules </h3>
		
		<a href="<%= getServletContext().getContextPath()%>/do/ajouterModule">Créer un module</a>
		
		<article class="panel-heading">
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
							</tr>
						<% } %>
				</table>
			</article>
		</article>
	</div>
</div>