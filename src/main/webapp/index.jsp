<%-- 
    Document   : index
    Created on : 30.12.2014, 20:43:35
    Author     : Achille
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.touriststales.classPackage.*;"%>

<!DOCTYPE html>
<%
    Author active_author = (Author)session.getAttribute("active_author");
    
    if (active_author == null) {
        session.setAttribute("active_author", Author.construct_guest());// = Author::construct_guest();
    }
    
    active_author = (Author)session.getAttribute("active_author");
%>

<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>

        <title>travel tales</title>
    </head>
    <body>
        <%= Library.generate_nav_bar(active_author)%>
        <%
            String echo = "";
            if (!active_author.is_guest()) {
                //String active_author_id = Integer.toString(active_author.getId());
                //String active_author_user_name = active_author.getUser_name();

                echo += "        <div class='jumbotron' style='background-image:url(https://docs.google.com/drawings/d/1cxEkUOIjaCkUYtgYfcmeruIqTva884itD0MzeY5OvIM/pub?w=1389&amp;h=719);'>";
                echo += "            <h1>Tell your tale</h1><br>";
                echo += "            <p>Create a tale and share it with others!</p>";
                echo += "            <form action='tellTale.php'>";
                echo += "            <button type='submit' class='btn btn-primary btn-lg'>Create Tale</button>";
                echo += "            </form>";
                echo += "        </div>";
            } else {
                echo += "        <div class='jumbotron' style='background-image:url(https://docs.google.com/drawings/d/1cxEkUOIjaCkUYtgYfcmeruIqTva884itD0MzeY5OvIM/pub?w=1389&amp;h=719);'>";
                echo += "            <h1>Tell your tale</h1><br>";
                echo += "            <p>Sign up and tell your tales!</p>";
                echo += "            <p><a href='createAuthor.php' class='btn btn-primary btn-lg' role='button'>Sign Up</a></p>";
                echo += "        </div>";
            }
        %>
        <%= echo %>
    </body>
</html>
