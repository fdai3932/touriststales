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
        <title>Tourists Tales online</title>
    </head>
    <body>
        <%
            DB test = DB.getInstance();
            //Author testauthor = Author.construct_guest();
            String user_name = "<br>-------------------------------------------<br>";
            Review [] reviews = {new Review(), new Review(2, 6, 5, "db_title", "db_location", "db_text", "db_category", "db_time", "db_date")};
            try{
//                Review [] reviews = test.get_reviews_by_tale_id("11");
//                Comment [] reviews = test.get_comments_by_review_id("15");
                int i=0;
                while(i<2)
                    user_name += reviews[i++].toString();
            }catch(Exception e){
                test.err += "<br>index error: " + e.getMessage();
                test.debbug += "<br>index debbug: " + e.getMessage();
            }
            user_name += "<br>-------------------------------------------<br>";
        %>
        
        <%= user_name %>
        <%= test.debbug %>
        <%= test.err %>
        <%= reviews[0].toString() + "<br><br><br>" %>
        <%= reviews[1].toString() %>
        <h1>Hello World!</h1>
    </body>
</html>
