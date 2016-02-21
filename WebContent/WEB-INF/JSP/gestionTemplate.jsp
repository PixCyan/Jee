<!--  Récupération du content -->
<jsp:useBean id="content" class="java.lang.String" scope="request"/>
	
	<body>
		<jsp:include page="<%= getServletContext().getInitParameter(\"entetedepage\")%>"/>
		 <div id="page-wrapper">
		 <div class="row">
                <div class="col-lg-12">
                	<h1><%= getServletContext().getInitParameter("title")%></h1>  
                </div>
                <!-- /.col-lg-12 -->
          </div>
            <!-- /.row -->
			<jsp:include page="<%=content%>" />
		</div>
		<jsp:include page="<%= getServletContext().getInitParameter(\"pieddepage\")%>"/>
	</body>
</html>