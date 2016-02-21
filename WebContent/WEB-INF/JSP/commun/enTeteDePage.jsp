<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="projetEtape4.Groupe"%>
<%@ page import="data.GroupeDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head lang="fr">
		<title><%=getServletContext().getInitParameter("title")%></title>
		<link type="text/css" rel="stylesheet" href="../ressources/css/startbootstrap-sb-admin-2-1.0.8/bower_components/bootstrap/dist/css/bootstrap.min.css" />
		
		<!-- MetisMenu CSS -->
		<link href="../ressources/css/startbootstrap-sb-admin-2-1.0.8/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
		
		<!-- Timeline CSS -->
		<link href="../ressources/css/startbootstrap-sb-admin-2-1.0.8/dist/css/timeline.css" rel="stylesheet">
		
		<!-- Custom CSS -->
		<link href="../ressources/css/startbootstrap-sb-admin-2-1.0.8/dist/css/sb-admin-2.css" rel="stylesheet">
		
		<!-- Morris Charts CSS -->
		<link href="../ressources/css/startbootstrap-sb-admin-2-1.0.8/bower_components/morrisjs/morris.css" rel="stylesheet">
		
		<!-- Custom Fonts -->
		<link href="../ressources/css/startbootstrap-sb-admin-2-1.0.8/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>

<div id="wrapper">

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	    <div class="navbar-header">
	        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	        </button>
	        <a class="navbar-brand">IUT2 Grenoble - Projet J2EE</a>
	    </div>
	   
	    <div class="navbar-default sidebar" role="navigation">
	        <div class="sidebar-nav navbar-collapse">
	            <ul class="nav" id="side-menu">
	                <li>
		                    	<a href="accueil"></i> Accueil </a>
		            </li>
	                <li>
	                    <a href="#"><i class="fa fa-group fa-fw"></i> Etudiants <span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
		                    <li>
		                    	<a href="all"></i> Liste de tous les étudiants</a>
		                	</li>
	                        <li>
	                            <a href="ajouterEtudiant"> Créer un étudiant </a>
	                        </li>
	                        <li>
	                            <a href="notes"> Consulter les notes </a>
	                        </li>
	                         <li>
	                            <a href="absences"> Consulter les absences </a>
	                        </li>
	                    </ul>
	                    <!-- /.nav-second-level -->
	                </li>
	                
	                <li>
	                    <a href="#"><i class="fa fa-sitemap fa-fw"></i> Groupes <span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
	                    		<li>
		                            <a href="<%= getServletContext().getContextPath()%>/do/groupes">Gérer les groupes</a>
		                        </li>
	                    		<li>
		                            <a href="<%= getServletContext().getContextPath()%>/do/ajouterGroupe">Créer un groupe</a>
		                        </li>
	                    	<% for(Groupe g : GroupeDAO.getAll()) { %>
		                    	<li>
		                            <a href="<%= getServletContext().getContextPath()%>/do/detailsGroupe?id=<%= g.getId() %>"> <%= g.getNom() %></a>
		                        </li>
	                    	<% } %>
	                        <li>
	                            <a href="#"><i class="fa  fa-folder-open fa-fw"></i> Modules <span class="fa arrow"></span></a>
	                            <ul class="nav nav-third-level">
	                                <li>
	                                    <a href="consultationModules">Liste des modules</a>
	                                </li>
	                                <li>
	                                    <a href="ajouterModule">Créer un module</a>
	                                </li>
	                            </ul>
	                            <!-- /.nav-third-level -->
	                        </li>
	                    </ul>
	                    <!-- /.nav-second-level -->
	                </li>
	            </ul>
	        </div>
	        <!-- /.sidebar-collapse -->
	    </div>
	    <!-- /.navbar-static-side -->
	</nav>
	</div>
</html>

<!--
<a href="accueil">Accueil</a>
<a href="notes">Consulter les notes</a>
<a href="absences">Consulter les absences</a>
<a href="groupes">Consulter les groupes</a>
<a href="all">Liste des étudiants</a>
<a href="consultationModules">Liste des modules</a>
  -->

