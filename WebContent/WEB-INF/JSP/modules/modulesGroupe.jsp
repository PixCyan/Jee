<%@ page import="projetEtape4.Groupe"%>
<%@ page import="projetEtape4.Module"%>

<!--  récupération des données -->
<jsp:useBean id="modules" type="java.util.Collection<Module>" scope="request"/>
<jsp:useBean id="groupe" class="projetEtape4.Groupe" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<article class="panel-heading">
			<h3> Modules associés au groupe : <%= groupe.getNom() %> </h3>
		</article>
		<article class="panel-body">
			<table class="table table-striped table-bordered table-hover dataTable no-footer">
				<tr><td> Module </td><td> Description </td><td> Coeff </td></tr>
				<% for(Module m : modules) { %>
						<tr><td><%= m.getNom() %> </td><td><%= m.getDescription() %></td><td><%= m.getCoeff() %></td>
						<td class="celModif"><a href="<%= getServletContext().getContextPath()%>/do/confirmationModification?removeModule=<%= m.getId() %>"> Supprimer </a></td></tr>
					<% } %>
			</table>
		</article>
	</div>
</div>