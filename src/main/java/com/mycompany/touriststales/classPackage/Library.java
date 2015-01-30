/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.touriststales.classPackage;

/**
 *
 * @author Achille
 */
public class Library {
    public static String generate_nav_bar(Author active_author) {

        String navbar = "";
        navbar += "<!-- NAVIGATION BAR -------------------------------------------------->";
        navbar += "    <nav class='navbar navbar-default' role='navigation'>";
        navbar += "        <!-- Brand and toggle get grouped for better mobile display -->";
        navbar += "        <div class='navbar-header'>";
        navbar += "            <button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#bs-example-navbar-collapse-1'>";
        navbar += "                <span class='sr-only'>Toggle navigation</span>";
        navbar += "				<span class='icon-bar'></span>";
        navbar += "                <span class='icon-bar'></span>";
        navbar += "                <span class='icon-bar'></span>";
        navbar += "            </button>";
        navbar += "            <a class='navbar-brand' href='index.jsp'>travel tales</a>";
        navbar += "        </div>";

        navbar += "        <!-- Collect the nav links, forms, and other content for toggling -->";
        navbar += "       <div class='collapse navbar-collapse' id='bs-example-navbar-collapse-1'>"; 
        navbar += "           <ul class='nav navbar-nav'>";
        navbar += "               <li><a href='searchResults.jsp?category=Fun'>Fun</a></li>";
        navbar += "                <li><a href='searchResults.jsp?category=Food'>Food</a></li>";
        navbar += "               <li><a href='searchResults.jsp?category=Sleep'>Sleep</a></li>";
        navbar += "            </ul>";
        navbar += "            <form class='navbar-form navbar-left' action='searchResults.jsp'>";
        navbar += "                <input type='text' name='term' placeholder='Enter location'/>";
        navbar += "                <button class='btn btn-default' type='submit'>Search</button>";
        navbar += "           </form>";

        if (!active_author.is_guest()) {
            String active_author_id = Integer.toString(active_author.getId());
            String active_author_user_name = active_author.getUser_name();
            
            navbar += "<form class='navbar-form navbar-right' action='logout.jsp'>";
            navbar += "	<button class='btn btn-default' type='submit'>Sign Out</button>";
            navbar += "</form>";
            navbar += "<form class='navbar-form navbar-right' action='viewAuthor.jsp'>";
            navbar += "    <input type='hidden' name='author_id' value='"+active_author_id+"' />";
            navbar += "    <button class='btn btn-default' type='submit'>"+active_author_user_name+"</button>";
            navbar += "</form>";
        } else {
            navbar += "<form class='navbar-form navbar-right' action='sign_in.jsp'>";
            navbar += "    <button class='btn btn-default' type='submit'>Sign In</button>";
            navbar += "</form>";
        }

        navbar += "    </div>";
        navbar += "</nav>";
        navbar += "<!-- END NAVIGATION BAR ----------------------------------------------->";

        return navbar;
    }
}
