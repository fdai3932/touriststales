/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.touriststales.classPackage;
//import org.apache.commons.lang.StringEscapeUtils;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

/**
 *
 * @author Achille
 */
public class DB{
    // single instance of self shared among all instances
    private static DB instance = null;
    
    // DB connection config vars
    private final String user = "root";
//    private final String password = "root";
    private final String dbName = "touriststales";
//    private final String dbHost = "54.148.37.252";
    private final String password = "";
    private final String dbHost = "localhost";
    
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet queryResult;
    private String queryStmt;
    
    // debbug variable 
    public String err = "";
    public String debbug = "";
    //   
    
    private Integer numberOfReturnedRows;
    
    public DB(){
        this.queryStmt = "";
        this.err += "Err In DB()<br>";
        this.debbug =  this.debbug + "Debbug DB Contructor...<br/>";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
           this.err += "com.mysql.jdbc.Driver: new Instance classnotfoundexception " + e.getMessage() + ".<br/>";
        }catch(Exception e){
           this.err += "com.mysql.jdbc.Driver: new Instance exception " + e.getMessage() + ".<br/>";
        }
        
        try{    
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://" + this.dbHost + ":3306/" + this.dbName
                    , this.user
                    , this.password);
            this.stmt = conn.createStatement();
            this.debbug = this.debbug + "DB Contructor.getConnection n createStmt...<br/>";
        }catch(SQLException e){
            this.err += "GetConnection and CreateStmt: " + e.getMessage() + ".<br/>";
        }
        
        this.numberOfReturnedRows = -1;
    }
    
    //This method must be static, and must return an instance of the object if the object
    //does not already exist.
//     synchronized insures that per the same instance will only be use in ONE thread at a time
    public static synchronized DB getInstance() {
//        if ( (DB.instance == null) ) {
        if (! (DB.instance instanceof DB) ) {
            DB.instance = new DB();
        }
        return DB.instance;
    }
    
    private String getLastInsertedID() throws Exception{
        String insertedID = "-1";
        try{
            PreparedStatement getLastInsertId = this.conn.prepareStatement("SELECT LAST_INSERT_ID()");  
            ResultSet rs = getLastInsertId.executeQuery();  
            if (rs.next())  
            {  
                insertedID = Long.toString( rs.getLong("last_insert_id()") );              
            }     
        }catch (SQLException e){
            this.err += "executeQuery(LAST_INSERT_ID): " + this.err + e.getMessage() + ".<br/>";
        }
    
        return insertedID;
    }
    
    private int getResultRowsNumber(String queryStmt){
        int numberOfRows = 0;
        try{
            if(this.queryResult.last()) {
                this.err += "in getResultRowsNumberquery(" + queryStmt + "): this.queryResult.last() is TRUE.<br/>";
                numberOfRows = this.queryResult.getRow();
                this.queryResult.beforeFirst();
            }
            else this.err += "in getResultRowsNumber(" + queryStmt + "): this.queryResult.last() is FALSE.<br/>";
        }catch(SQLException e){
            this.err += "executeQuery(getNumbersOfRows(" + queryStmt + ")): " + this.err + e.getMessage() + ".<br/>";
        }
        
        this.err += "this.getResultRowsNumber(" + queryStmt + ") = " + numberOfRows + "<br>";
        
        return numberOfRows;
    }
    
    /*CREATE METHODS*/
    public Author create_author(String user_name,
                                  String first_name,
                                  String last_name,
                                  String email,
                                  Integer is_admin,
                                  Boolean is_suspended,
                                  String description,
                                  String img,
                                  String password) throws Exception{
        // Check if user name is unique
        this.queryStmt = "SELECT * FROM authors WHERE user_name='" + user_name + "'";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
            
        if (this.queryResult.next()) {
            throw new Exception("User name already taken");
        }
        
        // Check if email is unique
        this.queryStmt = "SELECT * FROM authors WHERE email='" + email + "'";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (numberOfReturnedRows < 0) {
            throw new Exception("Email already taken");
        }
        /*
        if (img == ""){
            img = '';
        }*/
        this.queryStmt = "INSERT INTO authors "
                + "(is_admin,is_suspended,user_name,first_name,last_name,"
                + "email,password,description,img)"
                + " VALUES "
                + "("+is_admin+","+is_suspended+",'"+user_name+"','"
                + first_name+"','"+last_name+"','"+email+"','"+password+"','"
                + description+"','+img+')";
        
        // Insert author into database
        this.stmt.executeUpdate(this.queryStmt);
        // Get the auto increment id
        String author_id = this.getLastInsertedID();
        
        // Get the author row
        this.queryStmt = "SELECT * FROM authors WHERE id="+author_id+"";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        // Construct author object
        Author author = null;
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            String db_user_name = this.queryResult.getString("user_name");
            String db_first_name = this.queryResult.getString("first_name");
            String db_last_name = this.queryResult.getString("last_name");
            String db_email = this.queryResult.getString("email");
            Integer db_is_admin = this.queryResult.getInt("is_admin");
            Integer db_is_suspended = this.queryResult.getInt("is_suspended");
            Integer db_is_guest = 0;//this.queryResult.getInt("is_guest");
            String db_description = this.queryResult.getString("description");
            String db_img = this.queryResult.getString("img");
            String db_password = this.queryResult.getString("password");
            
            author = new Author(db_id
                            ,db_user_name 	
                            ,db_first_name 	
                            ,db_last_name 	
                            ,db_email 		
                            ,db_is_admin 	
                            ,db_is_suspended
                            ,db_is_guest 	
                            ,db_description	
                            ,db_img 			
                            ,db_password);
        }
        
        return author;
    }
    
    public Author create_guest() {
        return Author.construct_guest();
    }
    
    public Review create_review(String author_id, String title, String category, String location, String text, String tale_id) throws Exception{        
        this.queryStmt = "INSERT INTO reviews (author_id, title, category, location, text, time, date, tale_id)" +
                " VALUES (" + author_id + ", '" + title + "', '" + category + "', '" 
                + location  + "', '" + text + "', CURTIME(), CURDATE(), " + tale_id + ")";
        
        // Insert author into database
        this.stmt.executeUpdate(this.queryStmt);

        //create Review Object from the last inserted Id.
        return this.get_review_by_id(this.getLastInsertedID());
    }
    
    public Tale create_tale(String author_id, String title, String text) throws Exception{
        DateFormat timeFormat = new SimpleDateFormat("'H:i:s'");
        DateFormat dateFormat = new SimpleDateFormat("Y-m-d");
        Date currentDate = new Date();
        String time = timeFormat.format(currentDate);
        String date = dateFormat.format(currentDate);
        
        this.queryStmt = "INSERT INTO tales (author_id, title, text, time, date)" +
                " VALUES (" + author_id + ",'" + title + "','" + text + "',CURTIME(),CURDATE())";
        // Insert author into database
        this.stmt.executeUpdate(this.queryStmt);
        
        // Get the auto incremented id
        String tale_id = this.getLastInsertedID();

        // Get the tale row: the partiular tale which has just been added
        this.queryStmt = "SELECT * FROM tales WHERE id=" + tale_id;
        // Insert tale into database
        this.stmt.executeQuery(this.queryStmt);
        
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " tale with id '" + tale_id + "'");
        }
        
        Tale tale = null;
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            String db_title = this.queryResult.getString("title");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
            
            tale = new Tale(db_id,
                db_author_id,
                db_title,
                db_text,
                db_time,
                db_date);
        }
        
        // return tale object 
        return tale;
        
    }
    
    /*SET METHODS*/
    public void  set_author_description(String author_id, String text) throws Exception{
        this.queryStmt = "UPDATE  authors SET  description = '" + text + "' WHERE id = " + author_id;
        this.stmt.executeUpdate(this.queryStmt);
    }
    public void  set_author_user_name(String author_id, String user_name) throws Exception{
        this.queryStmt = "UPDATE  authors SET  user_name = '" + user_name + "' WHERE id = " + author_id;
        this.stmt.executeUpdate(this.queryStmt);
    }
    public void  set_author_last_name(String author_id, String last_name) throws Exception{
        this.queryStmt = "UPDATE  authors SET  last_name = '" + last_name + "' WHERE id = " + author_id;
        this.stmt.executeUpdate(this.queryStmt);
    }
    public void  set_author_first_name(String author_id, String first_name) throws Exception{
        this.queryStmt = "UPDATE  authors SET  first_name = '" + first_name + "' WHERE id = " + author_id;
        this.stmt.executeUpdate(this.queryStmt);
    }
    public void  set_author_email(String author_id, String email) throws Exception{
        this.queryStmt = "UPDATE  authors SET  email = '" + email + "' WHERE id = " + author_id;
        this.stmt.executeUpdate(this.queryStmt);
    }
    public void  set_author_password(String author_id, String password) throws Exception{
        this.queryStmt = "UPDATE  authors SET  password = '" + password + "' WHERE id = " + author_id;
        this.stmt.executeUpdate(this.queryStmt);
    }
    public void  set_author_is_suspended(String author_id, String is_suspended) throws Exception{
        this.queryStmt = "UPDATE  authors SET  password = '" + is_suspended + "' WHERE id = " + author_id;
        this.stmt.executeUpdate(this.queryStmt);
    }
     
    /*GET METHODS*/
    public Author get_author_by_user_name(String user_name)throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * FROM authors WHERE user_name='"+user_name+"'";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
		
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " author with user name '" + user_name + "'.");
        }
        if (numberOfReturnedRows > 1) {
            throw new Exception("More than one author with user name '" + user_name + "'.");
        }
        
        // Construct author object
        Author author = null;//Author.construct_guest();
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            String db_user_name = this.queryResult.getString("user_name");
            String db_first_name = this.queryResult.getString("first_name");
            String db_last_name = this.queryResult.getString("last_name");
            String db_email = this.queryResult.getString("email");
            Integer db_is_admin = this.queryResult.getInt("is_admin");
            Integer db_is_suspended = this.queryResult.getInt("is_suspended");
            Integer db_is_guest = 0;//this.queryResult.getInt("is_guest");
            String db_description = this.queryResult.getString("description");
            String db_img = this.queryResult.getString("img");
            String db_password = this.queryResult.getString("password");
            
            author = new Author(db_id
                            ,db_user_name 	
                            ,db_first_name 	
                            ,db_last_name 	
                            ,db_email 		
                            ,db_is_admin 	
                            ,db_is_suspended
                            ,db_is_guest 	
                            ,db_description	
                            ,db_img 			
                            ,db_password);
        }
        
        return author;
    }
    
    public Author get_author_by_id(String id)throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * FROM authors WHERE id="+id+"";
	//if(true) throw new Exception(this.queryStmt);
        
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception( Integer.toString (this.numberOfReturnedRows) +" author with id '" + id + "'.");
        }
        if (numberOfReturnedRows > 1) {
            throw new Exception("More than one author with id '" + id + "'.");
        }
        
        // Construct author object
        Author author = null;//Author.construct_guest();
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            String db_user_name = this.queryResult.getString("user_name");
            String db_first_name = this.queryResult.getString("first_name");
            String db_last_name = this.queryResult.getString("last_name");
            String db_email = this.queryResult.getString("email");
            Integer db_is_admin = this.queryResult.getInt("is_admin");
            Integer db_is_suspended = this.queryResult.getInt("is_suspended");
            Integer db_is_guest = 0;//this.queryResult.getInt("is_guest");
            String db_description = this.queryResult.getString("description");
            String db_img = this.queryResult.getString("img");
            String db_password = this.queryResult.getString("password");
            
            author = new Author(db_id
                            ,db_user_name 	
                            ,db_first_name 	
                            ,db_last_name 	
                            ,db_email 		
                            ,db_is_admin 	
                            ,db_is_suspended
                            ,db_is_guest 	
                            ,db_description	
                            ,db_img 			
                            ,db_password);
        }
        
        return author;
    }
    
    public Author log_in(String user_name, String password) throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * FROM authors WHERE (user_name='" + user_name + "' OR email ='" + user_name + "') "
                + "AND password='" + password + "'";
	//if(true) throw new Exception(this.queryStmt);
        
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception( Integer.toString (this.numberOfReturnedRows) +" author with user_name '" + user_name + "' match the password");
        }
        if (numberOfReturnedRows > 1) {
            throw new Exception("More than one author with user_name '" + user_name + "' match the password");
        }
        
         // Construct author object
        Author author = null;//Author.construct_guest();
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            String db_user_name = this.queryResult.getString("user_name");
            String db_first_name = this.queryResult.getString("first_name");
            String db_last_name = this.queryResult.getString("last_name");
            String db_email = this.queryResult.getString("email");
            Integer db_is_admin = this.queryResult.getInt("is_admin");
            Integer db_is_suspended = this.queryResult.getInt("is_suspended");
            Integer db_is_guest = 0;//this.queryResult.getInt("is_guest");
            String db_description = this.queryResult.getString("description");
            String db_img = this.queryResult.getString("img");
            String db_password = this.queryResult.getString("password");
            
            author = new Author(db_id
                            ,db_user_name 	
                            ,db_first_name 	
                            ,db_last_name 	
                            ,db_email 		
                            ,db_is_admin 	
                            ,db_is_suspended
                            ,db_is_guest 	
                            ,db_description	
                            ,db_img 			
                            ,db_password);
        }
        
        return author;
    }
    
    public Author [] get_followers_by_author(String author_id) throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * "
                + "FROM authors "
                + "WHERE id IN ("
                    + "SELECT following_author_id"
                    + "FROM followers"
                    + "WHERE followed_author_id="
                    + "'"+ author_id+"')";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
		
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows < 1) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + "  follower found for author with id '" + author_id + "'.");
        }
        
        // Construct author object. the list of the followers
        Author [] followers = new Author[numberOfReturnedRows];
        int i = 0;
        while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            String db_user_name = this.queryResult.getString("user_name");
            String db_first_name = this.queryResult.getString("first_name");
            String db_last_name = this.queryResult.getString("last_name");
            String db_email = this.queryResult.getString("email");
            Integer db_is_admin = this.queryResult.getInt("is_admin");
            Integer db_is_suspended = this.queryResult.getInt("is_suspended");
            Integer db_is_guest = 0;//this.queryResult.getInt("is_guest");
            String db_description = this.queryResult.getString("description");
            String db_img = this.queryResult.getString("img");
            String db_password = this.queryResult.getString("password");
            
            followers[i++] = new Author(db_id
                            ,db_user_name 	
                            ,db_first_name 	
                            ,db_last_name 	
                            ,db_email 		
                            ,db_is_admin 	
                            ,db_is_suspended
                            ,db_is_guest 	
                            ,db_description	
                            ,db_img 			
                            ,db_password);
        }
        
        return followers;
    }
    
    public Author [] get_following_by_author(String author_id) throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * "
                + "FROM authors "
                + "WHERE id IN ("
                    + "SELECT followed_author_id"
                    + "FROM followers"
                    + "WHERE following_author_id="
                    + "'"+ author_id+"')";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
		
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows < 1) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + "  follower found for author with id '" + author_id + "'.");
        }
        
        // Construct author object. the list of the followers
        Author [] following = new Author[numberOfReturnedRows];
        int i = 0;
        while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            String db_user_name = this.queryResult.getString("user_name");
            String db_first_name = this.queryResult.getString("first_name");
            String db_last_name = this.queryResult.getString("last_name");
            String db_email = this.queryResult.getString("email");
            Integer db_is_admin = this.queryResult.getInt("is_admin");
            Integer db_is_suspended = this.queryResult.getInt("is_suspended");
            Integer db_is_guest = 0;//this.queryResult.getInt("is_guest");
            String db_description = this.queryResult.getString("description");
            String db_img = this.queryResult.getString("img");
            String db_password = this.queryResult.getString("password");
            
            following[i++] = new Author(db_id
                            ,db_user_name 	
                            ,db_first_name 	
                            ,db_last_name 	
                            ,db_email 		
                            ,db_is_admin 	
                            ,db_is_suspended
                            ,db_is_guest 	
                            ,db_description	
                            ,db_img 			
                            ,db_password);
        }
        
        return following;
    }
    
    public Comment get_comment_by_id(String comment_id)throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * FROM comments WHERE id="+comment_id+"";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
		
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception( Integer.toString (this.numberOfReturnedRows) + " comment with id '" + comment_id + "'.");
        }
        
        Comment comment = null;
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            Integer db_review_id = this.queryResult.getInt("review_id");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
        
            comment = new Comment(db_id, db_author_id, db_review_id, db_text, db_time, db_date);
        }
        
        return comment;
    }
    
    public Review get_review_by_id(String id)throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * FROM reviews WHERE id="+id+"";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);	
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception( Integer.toString(this.numberOfReturnedRows) + " review with id '" + id + "'.");
        }
        if (numberOfReturnedRows > 1) {
            throw new Exception("More than one review with id '" + id + "'.");
        }
        
        // Construct author object
        Review review = null;//Author.construct_guest();
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            Integer db_tale_id = this.queryResult.getInt("tale_id");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
            String db_category = this.queryResult.getString("category");
            String db_location = this.queryResult.getString("location");
            String db_title = this.queryResult.getString("title");
        
            review = new Review(db_id, db_author_id, db_tale_id, db_title, db_location, db_text, db_category, db_time, db_date);
        }
        
        return review;
    }
    
    public Float get_rating_by_review_id(String review_id) throws Exception{
        this.queryStmt = "SELECT AVG(rating) FROM images WHERE review_id=" + review_id;
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
               
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);

        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " rating associated with review id: '" + review_id + "'");
        }
        
        if(this.queryResult.next())
            return this.queryResult.getFloat(1);
        
        return 0.0F;
    }
    
    public Comment[] get_comments_by_review_id(String review_id) throws Exception{
        this.queryStmt = "SELECT * FROM comments WHERE review_id=" + review_id;
        this.queryResult = this.stmt.executeQuery(this.queryStmt);

		
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (numberOfReturnedRows  == 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " comment for the review with id '" + review_id + "'.");
        }
        
         // comments array
        Comment [] coments_by_review = new Comment[numberOfReturnedRows];//Author.construct_guest();
        int i = 0;
        while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            Integer db_review_id = this.queryResult.getInt("review_id");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
        
            coments_by_review[i] = new Comment(db_id, db_author_id, db_review_id, db_text, db_time, db_date);
            i++;
        }

        // return array comemnts object 
        return coments_by_review;
    }
    
    public Review[] get_reviews_by_tale_id(String tale_id) throws Exception{
        this.queryStmt = "SELECT * FROM reviews WHERE tale_id=" + tale_id;
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        // comments array
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " Oupss! something went wrong with tale id '" + tale_id + "'.");
        }
        
        Review [] review_by_tale = new Review[numberOfReturnedRows];
        int i = 0;
        while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            Integer db_tale_id = this.queryResult.getInt("tale_id");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
            String db_category = this.queryResult.getString("category");
            String db_location = this.queryResult.getString("location");
            String db_title = this.queryResult.getString("title");
            
            review_by_tale[i] = new Review(db_id, db_author_id, db_tale_id, db_title, db_location, db_text, db_category, db_time, db_date);
//            review_by_tale[i] = new Review(2, 6, 5, "db_title", "db_location", "db_text", "db_category", "db_time", "db_date");
//            review_by_tale[i] = new Review();
            i++;
        }

        //return array review object 
        return review_by_tale;
    }
    
    public Review[] get_n_reviews_by_cathegory(int n, String category) throws Exception{
        this.queryStmt = "SELECT * FROM reviews WHERE category='" 
                + category + "' ORDER BY date DESC LIMIT " + Integer.toString(n);
        
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        // comments array
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " Oupss! something went wrong while trying to get all reviews. I think no review matched your search category: " + category);
        }
        
        Review [] allReviews = new Review[numberOfReturnedRows];
        int i = 0;
        while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            Integer db_tale_id = this.queryResult.getInt("tale_id");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
            String db_category = this.queryResult.getString("category");
            String db_location = this.queryResult.getString("location");
            String db_title = this.queryResult.getString("title");
            
            allReviews[i] = new Review(db_id, db_author_id, db_tale_id, db_title, db_location, db_text, db_category, db_time, db_date);
            i++;
        }

        //return array review object 
        return allReviews;
    }
    
    public Review[] get_n_reviews_by_term(int n, String term) throws Exception{
        this.queryStmt = "SELECT * FROM reviews WHERE category LIKE '%" 
                + term + "%' OR text LIKE '%" 
                + term + "%' OR location LIKE '%" 
                + term + "%' OR title LIKE '%"
                + term + "%' ORDER BY date DESC LIMIT " + Integer.toString(n);
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        // comments array
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " Oupss! something went wrong while trying to get all reviews. I think no review matched your criteria: " + term);
        }
        
        Review [] allReviews = new Review[numberOfReturnedRows];
        int i = 0;
        while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            Integer db_tale_id = this.queryResult.getInt("tale_id");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
            String db_category = this.queryResult.getString("category");
            String db_location = this.queryResult.getString("location");
            String db_title = this.queryResult.getString("title");
            
            allReviews[i] = new Review(db_id, db_author_id, db_tale_id, db_title, db_location, db_text, db_category, db_time, db_date);
            i++;
        }

        //return array review object 
        return allReviews;
    }
    
    public Review[] get_n_reviews(int n) throws Exception{
        this.queryStmt = "SELECT * FROM reviews ORDER BY date DESC LIMIT " + Integer.toString(n);
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        // comments array
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " Oupss! something went wrong while trying to get all reviews. That is weird");
        }
        
        Review [] allReviews = new Review[numberOfReturnedRows];
        int i = 0;
        while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            Integer db_tale_id = this.queryResult.getInt("tale_id");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
            String db_category = this.queryResult.getString("category");
            String db_location = this.queryResult.getString("location");
            String db_title = this.queryResult.getString("title");
            
            allReviews[i] = new Review(db_id, db_author_id, db_tale_id, db_title, db_location, db_text, db_category, db_time, db_date);
            i++;
        }

        //return array review object 
        return allReviews;
    }
    
    public Tale[] get_tales_by_author(String author_id) throws Exception{
        this.queryStmt = "SELECT * FROM tales WHERE author_id=" + author_id;
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        // tales array
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows < 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " tale found for author with id '" + author_id + "'.");
        }
        
        Tale [] tales_by_author = new Tale[numberOfReturnedRows];
        int i = 0;
       while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            String db_title = this.queryResult.getString("title");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
            
            tales_by_author[i++] = new Tale(db_id,
                db_author_id,
                db_title,
                db_text,
                db_time,
                db_date);
        }

        //return array of Tales object 
        return tales_by_author;
    }    
    
     public Tale get_tale_by_id(String id) throws Exception{
        this.queryStmt = "SELECT * FROM tales WHERE id=" + id;
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        // tales array
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " tale found with id '" + id + "'.");
        }
        
        if (this.numberOfReturnedRows > 1) {
            throw new Exception("Something went worng. More than 1 tale found with id '" + id + "'.");
        }
        
        Tale tale = null;
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            String db_title = this.queryResult.getString("title");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
            
            tale = new Tale(db_id,
                db_author_id,
                db_title,
                db_text,
                db_time,
                db_date);
        }

        //return array of Tales object 
        return tale;
    }
    
    public Review [] get_bookmarks_by_author_id(String author_id) throws Exception{
          this.queryStmt = "SELECT * "
                + "FROM reviews "
                + "WHERE review_id IN("
                    + "SELECT review_id "
                    + "FROM bookmarks "
                    + "WHERE author_id='" + author_id + "')";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        // comments array
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " Oupss! something went wrong with tale reviewd bookmarks from author with id '" + author_id + "'.");
        }
        
        Review [] bookmarked_reviews = new Review[numberOfReturnedRows];
        int i = 0;
        while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            Integer db_tale_id = this.queryResult.getInt("tale_id");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
            String db_category = this.queryResult.getString("category");
            String db_location = this.queryResult.getString("location");
            String db_title = this.queryResult.getString("title");
            
            bookmarked_reviews[i] = new Review(db_id, db_author_id, db_tale_id
                    , db_title, db_location, db_text, db_category, db_time, db_date);
            i++;
        }

        //return array review object 
        return bookmarked_reviews;
    }
    
    public Comment [] get_comments_by_author(String author_id) throws Exception{
        this.queryStmt = "SELECT * FROM comments WHERE author_id='" + author_id + "'";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);

         // comments array
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " comment found from the author with id '" + author_id + "'.");
        }
        
        Comment [] coments_by_author = new Comment[numberOfReturnedRows];//Author.construct_guest();
        int i = 0;
        while(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            Integer db_author_id = this.queryResult.getInt("author_id");
            Integer db_review_id = this.queryResult.getInt("review_id");
            String db_text = this.queryResult.getString("text");
            String db_time = this.queryResult.getTime("time").toString();
            String db_date = this.queryResult.getDate("date").toString();
        
            coments_by_author[i] = new Comment(db_id, db_author_id, db_review_id, db_text, db_time, db_date);
            i++;
        }
        
        return coments_by_author;
    } 
            
            
    /*UPDATE METHODS*/
    public void update_review_by_review_id(String review_id, String description) throws SQLException{
        this.queryStmt = "UPDATE reviews SET text = '" + description +
                " WHERE id = " + review_id;
        this.stmt.executeUpdate(this.queryStmt);
    }
    
    
    /*ADD METHODS*/
    public void add_bookmark_by_review_id(String author_id, String review_id) throws SQLException{
        //$review_id = $taledb->real_escape_string($review_id);
        // Check if bookmark already exists
        this.queryStmt = "SELECT * FROM bookmarks WHERE review_id='" + 
                review_id + "' AND author_id='" + author_id + "'";
        this.stmt.executeQuery(this.queryStmt);

        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        if (numberOfReturnedRows > 0){
            // Bookmark already exists
        } else {
            // Insert into bookmarks.
            this.queryStmt = "INSERT INTO bookmarks"
                    + "(review_id,author_id)"
                    + "VALUES"
                    + "('" + review_id + "','" + author_id + "')";
            this.stmt.executeUpdate(this.queryStmt);
        }
    }
       
    public Comment add_comment(String author_id, String review_id, String text) throws  Exception{
        DateFormat timeFormat = new SimpleDateFormat("'H:i:s'");
        DateFormat dateFormat = new SimpleDateFormat("Y-m-d");
        Date currentDate = new Date();
        String time = timeFormat.format(currentDate);
        String date = dateFormat.format(currentDate);
        
        this.queryStmt = "INSERT INTO comments (author_id, review_id, text, time, date)" 
                + " VALUES ('" + author_id + "', '" + review_id + "', '" + text + "', '" +
                time + "', '" + date +"')";
        this.stmt.executeUpdate(this.queryStmt);

        //return coment object 
        return this.get_comment_by_id(this.getLastInsertedID());
    }
    
    public void add_rating(String author_id, String review_id, String rating) throws SQLException{
        this.queryStmt = "INSERT INTO rating (author_id, review_id, rating)" 
                + " VALUES ('" + author_id + "', '" + review_id + "', '" + rating + "')";
        this.stmt.executeUpdate(this.queryStmt);
    }
    
    public void add_image(String img_str, String review_id) throws Exception{
        
        this.queryStmt = "INSERT INTO images (review_id, img)"
                + " VALUES (" + review_id + ", '" + img_str + "')";
        this.stmt.executeUpdate(this.queryStmt);
        
        // Error check
        String [] img_strs = Image.get_image_by_review_id(review_id);
        if( !Arrays.asList(img_strs).contains(img_str))throw new Exception("Could not add image!");
        
    }
    
    
    /*DELETE METHODS*/
    public void delete_comment_by_id(String comment_id) throws SQLException{
        this.queryStmt = "DELETE FROM comments WHERE id="+comment_id+"";
        this.stmt.executeUpdate(this.queryStmt);
    } 

    public void delete_review_by_id(String review_id) throws SQLException{
        this.queryStmt = "DELETE FROM reviews WHERE id="+review_id+"";
        this.stmt.executeUpdate(this.queryStmt);
        this.queryStmt = "DELETE FROM comments WHERE review_id="+review_id+"";
        this.stmt.executeUpdate(this.queryStmt);
        this.queryStmt = "DELETE FROM rating WHERE review_id="+review_id+"";
        this.stmt.executeUpdate(this.queryStmt);
        this.queryStmt = "DELETE FROM bookmarks WHERE review_id="+review_id+"";
        this.stmt.executeUpdate(this.queryStmt);
        this.queryStmt = "DELETE FROM images WHERE review_id="+review_id+"";
        this.stmt.executeUpdate(this.queryStmt);
    } 
    
    public void delete_tale_by_id(String tale_id) throws Exception{
        //get the id of all review of this tale.
        this.queryStmt = "SELECT id FROM reviews WHERE tale_id=" + tale_id;
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        this.numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " review for the tale with id '" + tale_id + "'.");
        }
        
        int i = 0;
        while(this.queryResult.next()){ //this could be done with WHERE IN List of ids
            this.delete_review_by_id(Integer.toString(this.queryResult.getInt("id")));
        }
        
        this.queryStmt = "DELETE FROM tales WHERE id="+tale_id+"";
        this.stmt.executeUpdate(this.queryStmt);
    }
    
    public void delete_author_by_id(String author_id) throws Exception{
        //get the id of all tales of this author.
        this.queryStmt = "SELECT id FROM tales WHERE author_id ='" + author_id + "'";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        if (this.numberOfReturnedRows <= 0) {
            throw new Exception(Integer.toString (this.numberOfReturnedRows) + " Tale found for author with id '" + author_id + "'");
        }
        
        while(this.queryResult.next()){ //this could be done with WHERE IN List of ids
            String tale_id = Integer.toString(this.queryResult.getInt("id"));
            this.delete_tale_by_id(tale_id);
        }
        
         // Delete all comments by author
        this.queryStmt = "DELETE FROM comments WHERE author_id ='" + author_id + "'";
        this.stmt.executeUpdate(this.queryStmt);
        
        // Delete all rows from followers
        this.queryStmt = "DELETE FROM followers WHERE "
                + "followed_author_id='" + author_id + "' OR"
                + "following_author_id='" + author_id + "'";
        this.stmt.executeUpdate(this.queryStmt);
        
        // Delete all rows from bookmarks
        this.queryStmt = "DELETE FROM bookmarks WHERE author_id ='" + author_id + "'";
        this.stmt.executeUpdate(this.queryStmt);
        
        // Remove all ratings
        this.queryStmt = "DELETE FROM ratings WHERE author_id ='" + author_id + "'";
        this.stmt.executeUpdate(this.queryStmt);

        // Goodbye author
        this.queryStmt = "DELETE FROM authors WHERE author_id ='" + author_id + "'";
        this.stmt.executeUpdate(this.queryStmt);
    }
    
    public void remove_bookmark_by_review_id(String author_id, String review_id) throws Exception{
        this.queryStmt = "DELETE FROM bookmarks "
                + "WHERE author_id='" + author_id + "' AND"
                + "review_id='" + review_id + "'";
        this.stmt.executeUpdate(this.queryStmt);
    }
    
    
}
