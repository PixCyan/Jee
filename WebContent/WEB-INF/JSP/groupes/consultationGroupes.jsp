<%@ page import="projetEtape4.Groupe"%>

<!--  récupération des données -->
<jsp:useBean id="groupes" type="java.util.Collection<Groupe>" scope="request"/>

<div class="row">
	<div class="col-lg-12">
		<h3> Groupes </h3>
		<article class="panel-heading">
			<article class="panel-body">
				<table class="table table-striped table-bordered table-hover dataTable no-footer">
					<% for(Groupe g : groupes) { %>
							<tr><td> Groupe_LP :  <a href="<%= getServletContext().getContextPath()%>/do/detailsGroupe?id=<%= g.getId() %>"> <%= g.getNom() %></a> </td></tr>
						<% } %>
				</table>
			</article>
		</article>
	</div>
</div>