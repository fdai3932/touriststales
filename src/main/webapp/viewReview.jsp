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
        session.setAttribute("active_author", Author.construct_guest());
    }
    
    active_author = (Author)session.getAttribute("active_author");
%>

<!--Get Specified Tale-->
<%
    String echo = "";
    String errorMsg = "";
    String id = "";
    String review_id = "";
    String review_title = "";
    String review_text = "";
    String review_author_user_name = "";
    String review_category = "";
    String tale_title = "";
    String tale_text = "";
    String tale_id = "";

    Author review_author = null;
    Review review = null;
    Comment [] comments = null;
    Tale tale = null;

    if (request.getMethod() == "GET") {
        try {
            if (request.getParameter("id") == null ) {
                throw new Exception();
            }
            errorMsg += "request.getParameter('id') != null<br>";
            id = request.getParameter("id");
            review = DB.getInstance().get_review_by_id(id);
            review_id = id;
            review_author = review.getAuthor();
            review_title = review.getTitle();
            review_text = review.getText();
            review_author_user_name = review_author.getUser_name();
            review_category = review.getCategory();
            comments = review.get_comments();
            tale = DB.getInstance().get_tale_by_id( Integer.toString( (review.getTale_id()) ) );
            tale_title = tale.getTitle();
            tale_text = tale.getText();
            tale_id = Integer.toString(tale.getId());
        } catch (Exception ex) {
            response.sendRedirect("error.jsp?errorMsg=" 
                + ex.getMessage() 
                + "<br>----------------------------<br>" 
                + errorMsg + "|" + echo);
            return;
        }
    } else {
        errorMsg = "GET not found";
        response.sendRedirect("error.jsp?errorMsg="
                            + "<br>----------------------------<br>" 
                            + errorMsg + "|" + echo);
        return;
    }
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
        <!-- End Navigation Bar -->

        <div class='jumbotron' style='background-image:url(https://docs.google.com/drawings/d/1cxEkUOIjaCkUYtgYfcmeruIqTva884itD0MzeY5OvIM/pub?w=1389&amp;h=719);
             padding-top: 0px;
             padding-bottom: 5px;'>
            <br><br><br>
            <form action="viewTale.php">
                <input type="hidden" name="id" value="<%= tale_id %>"/>
                <button type="submit" style="background:none; padding: none; border: none;">
                    <h1><%= tale_title %></h1>
                </button>
            </form>
            <form action="viewAuthor.php">
                <input type="hidden" name="user_name" value="<%= review_author_user_name %>"/>
                <p style="color: white;">Author: <button type="submit" class="btn" style="color: navy;">
                        <%= review_author_user_name %> (Details not implemented!)
                    </button></p>
            </form>
            <br><br><br>
        </div>
        <div class="container">
            <div class="jumbotron">
                <h2>Review: <%= review_title %></h2>
                <p><%= review_category %></p>
                <div class="container">
                    Description:<br>
                    <p style="color: darkorange;"><%= review_text %></p>
                </div>
            </div>
        </div> 
        <br> 

        <%
        try{
            String active_author_id = Integer.toString( active_author.getId() );
            String review_author_id = Integer.toString( review.getAuthorID() );
            String active_author_first_name = active_author.getFirst_name();
            if (review_author_id == active_author_id) {		
                echo += "<div class='container'>";
                echo += "    <form action='editReview.jsp' method='POST' enctype='multipart/form-data'>";
                echo += "        <h3>Hello, " + active_author_first_name + "! You could share more images";
                echo += "            but the functionality is not implemented in this demo version :(</h3>";
                echo += "        <input type='hidden' name='review_id' value='"+review_id+"' /><br>";
                echo += "        <input type='file' name='userfile' id='file'/>";
                echo += "        <button type='submit' name='submit' class='btn btn-default'>Don't Upload new image! the functionality is not implemented</button>";
                echo += "    </form>";
                echo += "</div>";
                echo += "<br>";
            }
        %>

        <div class="container">
            <form action="createComment.php" method="POST">
                <input type="hidden" name="review_id" value="<%= review_id %>"/>
                 <div class="form-group">
                    <input style="visibility: none; 
                                    width: 25px; 
                                    height: 25px;
                                    cursor: pointer; 
                                    border: none; 
                                    outline: none; 
                                    margin: 0px;
                                    background: transparent;
                                    font-size: 25px;
                                    color: transparent" id="puffer" type="text" name="puffer" value="" x-webkit-speech>
                    <textarea  id="text_comment" name="text" class="form-control" rows="3"></textarea>
                    <script>
                        var puffer = document.getElementById('puffer');
                        puffer.onwebkitspeechchange = function(e) {
                                document.getElementById('text_comment').value += " " + puffer.value;
                                document.getElementById('puffer').value = "";
                        };
                    </script>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default">Comment</button>
                </div>
            </form>
        </div>
        <div>
            <%
            if (comments.length == 0) {
                echo += "<div class='container'>";
                echo += "<h3>No Comments Yet</h3>";
                echo += "</div><br><br>";
            } else {
                echo += "<div class='container'>";
                int i = 0;
                while(i <comments.length){			
                    Comment comment = comments[i];
                    String comment_id = Integer.toString( comment.getId() );
                    String comment_text = comment.getText();
                    Author comment_author = comment.getAuthor();
                    String comment_author_user_name = comment_author.getUser_name();
                    
                    echo += "<div class='jumbotron'>";
                    echo += "    <p>" + comment_text + "</p>";
                    echo += "    <form action='viewAuthor.jsp'>";
                    echo += "        <input type='hidden' name='user_name' value='" + comment_author_user_name + "'/>";
                    //echo += "        <button type='submit' class='btn'>" + comment_author_user_name + "</button>";
                    echo += "    </form>";
                    echo += "    <form action='viewComment.jsp'>";
                    echo += "        <input type='hidden' name='comment_id' value='" + comment_id + "'/>";
                    //echo += "        <button type='submit' class='btn'>Details (NOT Implemented!)</button>";
                    echo += "    </form>";
                    echo += "</div>";
                    echo += "<br>";
                    i++;
                }
                echo += "</div>";
            }
        }catch (Exception ex){
            errorMsg += "An Error Occured: DB or ID";
            response.sendRedirect("error.jsp?errorMsg=" 
                            + ex.getMessage() 
                            + "<br>----------------------------<br>" 
                            + errorMsg + "|" + echo);
            return;
        }
            %>
	
        <%=echo%>
        </div>
    </body>
</html>

