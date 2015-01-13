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
//            Review [] reviews = {new Review(), new Review(2, 6, 5, "db_title", "db_location", "db_text", "db_category", "db_time", "db_date")};
//            Comment [] comments;// = {new Review(), new Review(2, 6, 5, "db_title", "db_location", "db_text", "db_category", "db_time", "db_date")};
            try{
                Review []reviews = test.get_reviews_by_tale_id("11");
                Comment [] comments = test.get_comments_by_review_id("15");
                int i=0;
                while(i<reviews.length)
                    user_name += reviews[i++].toString();
                    
                user_name += "<br>------------------comments--------<br>";
                i=0;
                while(i<comments.length)
                    user_name += comments[i++].toString();
            }catch(Exception e){
                test.err += "<br>index error: " + e.getMessage();
                test.debbug += "<br>index debbug: " + e.getMessage();
            }
            user_name += "<br>-------------------------------------------<br>";
        %>
        
        <%= user_name %>
        <%= test.debbug %>
        <%= test.err %>
        <h1>Hello World!</h1>
    </body>
</html>
