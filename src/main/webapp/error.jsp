<%-- 
    Document   : searchResults
    Created on : 29.01.2015, 20:46:30
    Author     : Achille
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.touriststales.classPackage.*;"%>

<!DOCTYPE html>
<%
    Author active_author = (Author)session.getAttribute("active_author");
    
    if (active_author == null) {
        session.setAttribute("active_author", Author.construct_guest());// = Author.construct_guest();
    }
    
    active_author = (Author)session.getAttribute("active_author");
%>

<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>

        <title>Error</title>
    </head>
    <body>
        <!-- Navigation Bar -->
		<%= Library.generate_nav_bar(active_author)%>
        <!-- End Navigation Bar -->
        
        <!--Get reviews -->
        <%

        String echo = "";       
        echo += "<div class='jumbotron' style='background-image:url(https://docs.google.com/drawings/d/1cxEkUOIjaCkUYtgYfcmeruIqTva884itD0MzeY5OvIM/pub?w=1389&amp;h=719);'>";
        echo += "I bet you something went wrong<br>";
        echo += request.getParameter("errorMsg");
        echo += "</div>";
        
        %>
       <%= echo %>
       
    </body>
</html>
