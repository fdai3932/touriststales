<%-- 
    Document   : index
    Created on : 30.12.2014, 20:43:35
    Author     : Achille
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.touriststales.classPackage.*;"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tourists Tales</title>
    </head>
    <body>
        <%
            DB test = new DB();
            Author testauthor = Author.construct_guest();
            String user_name = testauthor.getUser_Name();
        %>
        
        <%= user_name %>
        <%= test.debbug %>
        <%= test.err %>
        <h1>Hello World!</h1>
    </body>
</html>
