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

        <title>travel tales</title>
    </head>
    <body>
        <!-- Navigation Bar -->
		<%= Library.generate_nav_bar(active_author)%>
        <!-- End Navigation Bar -->
        
        <!--Get reviews -->
        <%
        String echo = "";
        String errorMsg = "";
        
        if (request.getMethod() == "GET") {
            String term = "";
            String category = "";
            Review[] reviews;
            
            int amount = 25; /* amount of reviews to display */
            Search search = new Search();
            errorMsg +=  "request.getMethod() == 'GET'<br>";
            try {
                if ( request.getParameter("category") == null && request.getParameter("term") == null ) {
                    errorMsg +=  "category and terms (location) are not set<br>";
                    throw new Exception();
                }else if (request.getParameter("term") != null){
                    term = request.getParameter("term");
                    Search search_results = search.get_review_by_search_term(amount, term);
                    reviews = search_results.get_reviews();
                    
                }else if (request.getParameter("category") != null){
                    category = request.getParameter("category");
                    Search search_results = search.get_reviews_by_category(amount, category);
                    reviews = search_results.get_reviews();
                }else{
                    amount = 1000;
                    category = "all";
                    Search search_results = search.get_recent_reviews(amount);
                    reviews = search_results.get_reviews();
                }
                
                errorMsg += "locationas cat: " + category + " != all<br>";



                if (reviews.length == 0) {
                        echo += "<h3>No Reviews Yet</h3>";
                        errorMsg += "if reviews.length == 0<br>";
                } else {
                    errorMsg += "else reviews.length == 0<br>";
                    echo += "<div class='container'>";

                    int i = 0;
                    while(i < reviews.length){
                        Review review = reviews[i];
                        String review_title = review.getTitle();
                        String review_text = review.getText();
                        String review_id = Integer.toString( review.getId() );
                        String review_category = review.getCategory();

                        echo += "<div class='jumbotron'>";
                        echo += "	<h2><span class='label label-default'>" + Integer.toString(i) +"</span>" + review_title + "</h2>";
                        echo += "	<p>" + review_category + "</p>";
                        echo += "	<form action='viewReview.jsp'>";
                        echo += "		<input type='hidden' name='id' value='" + review_id + "'/>";
                        echo += "		<button type='submit' class='btn btn-primary btn-lg'>Go To Review</button>";
                        echo += "	</form>";
                        echo += "</div>";
                        echo += "<br>";

                        i++;
                    }

                    echo += "</div>";
                }
            } catch (Exception ex) {
                response.sendRedirect("error.jsp?errorMsg=" + ex.getMessage() + "<br>----------------------------<br>" + errorMsg + "|" + echo);
               // die();
            }
        } else {
            errorMsg +=  "request.getMethod() != 'GET'";
            response.sendRedirect("error.jsp?errorMsg=" + errorMsg + "|" + echo);
            //die();
        }
        
        %>
       
        <%= echo %>
    </body>
</html>
