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

        <title>Sign In</title>
    </head>
    <body>
        <!-- Navigation Bar -->
        <%= Library.generate_nav_bar(active_author)%>
        
        <%
		String user_name = "";
		String password = "";
		Author author = null;
        if (request.getMethod() == "POST") {
            try {
                if (request.getParameter("user_name") == null) {
                    throw new Exception();
                }
                if (request.getParameter("password") == null) {
                    throw new Exception();
                }
                user_name = request.getParameter("user_name");
                password = request.getParameter("password");
                author = DB.getInstance().log_in(user_name, password);
				session.setAttribute("active_author", author);
                response.sendRedirect("index.jsp");
                return;
            } catch (Exception ex) {
                response.sendRedirect("error.jsp?errorMsg=" 
                    + ex.getMessage());
                return;
            }
        }
        %>
        <div class="container">
            <form class="form-horizontal" action="sign_in.jsp" method="POST">
                <div class="control-group">
                    <label class="control-label" for="inputUsername">Username</label>
                    <div class="controls">
                        <input type="text" name="user_name" placeholder="Email or User Name">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputPassword">Password</label>
                    <div class="controls">
                        <input type="password" name="password" placeholder="Password">
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn">Sign in</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
