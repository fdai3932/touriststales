/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.touriststales.classPackage;
import com.mycompany.touriststales.classPackage.*;
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
    //private final String password = "root";
    private final String dbName = "touriststales";
    //private final String dbHost = "54.148.37.252";
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
    public DB(){
        this.queryStmt = "";
        this.err += "In DB()<br>";
        this.debbug =  this.debbug + "DB Contructor...<br/>";
        
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
    }
    
    //This method must be static, and must return an instance of the object if the object
    //does not already exist.
    public static DB getInstance() {
        if (! (DB.instance instanceof DB) ) {
            DB.instance = new DB();
        }
        return DB.instance;
    }
    
    private String getLastInsertedID(){
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
    
    private int getResultRowsNumber(String query){
        int numberOfRows = 0;
        try{
            if(this.queryResult.last()) {
                this.err += "in getResultRowsNumberquery(" + query + "): this.queryResult.last() is TRUE.<br/>";
                numberOfRows = this.queryResult.getRow();
                this.queryResult.beforeFirst();
            }
            else this.err += "in getResultRowsNumber(" + query + "): this.queryResult.last() is FALSE.<br/>";
        }catch(SQLException e){
            this.err += "executeQuery(getNumbersOfRows(" + query + ")): " + this.err + e.getMessage() + ".<br/>";
        }
        
        this.err += "this.getResultRowsNumber(" + query + ") = " + numberOfRows + "<br>";
        
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
        try{
            this.queryResult = this.stmt.executeQuery(this.queryStmt);
        }catch(SQLException SQLe){
            this.err += "executeQuery(Select): " + this.err + SQLe.getMessage() + ".<br/>";
        }
        if (this.queryResult.next()) {
            throw new Exception("User name already taken");
        }
        
        // Check if email is unique
        this.queryStmt = "SELECT * FROM authors WHERE email='" + email + "'";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        Integer numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        
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
        Author author = Author.construct_guest();
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            String db_user_name = this.queryResult.getString("user_name");
            String db_first_name = this.queryResult.getString("first_name");
            String db_last_name = this.queryResult.getString("last_name");
            String db_email = this.queryResult.getString("email");
            Integer db_is_admin = this.queryResult.getInt("is_admin");
            Integer db_is_suspended = this.queryResult.getInt("is_suspended");
            Integer db_is_guest = this.queryResult.getInt("is_guest");
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
    
    /*ADD METHODS*/
    //add_bookmark_by_review_id
    
    /*GET METHODS*/
    public Author get_author_by_user_name(String user_name)throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * FROM authors WHERE user_name='"+user_name+"'";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        Integer numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        // Construct author object
        Author author = null;//Author.construct_guest();
		
        if (numberOfReturnedRows == 0) {
            throw new Exception("No author with user name '" + user_name + "'.");
        }
        if (numberOfReturnedRows > 1) {
            throw new Exception("More than one author with user name '" + user_name + "'.");
        }
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            String db_user_name = this.queryResult.getString("user_name");
            String db_first_name = this.queryResult.getString("first_name");
            String db_last_name = this.queryResult.getString("last_name");
            String db_email = this.queryResult.getString("email");
            Integer db_is_admin = this.queryResult.getInt("is_admin");
            Integer db_is_suspended = this.queryResult.getInt("is_suspended");
            Integer db_is_guest = this.queryResult.getInt("is_guest");
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
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        Integer numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        // Construct author object
        Author author = null;//Author.construct_guest();
		
        if (numberOfReturnedRows == 0) {
            throw new Exception("No author with id '" + id + "'.");
        }
        if (numberOfReturnedRows > 1) {
            throw new Exception("More than one author with id '" + id + "'.");
        }
        if(this.queryResult.next()){
            Integer db_id = this.queryResult.getInt("id");
            String db_user_name = this.queryResult.getString("user_name");
            String db_first_name = this.queryResult.getString("first_name");
            String db_last_name = this.queryResult.getString("last_name");
            String db_email = this.queryResult.getString("email");
            Integer db_is_admin = this.queryResult.getInt("is_admin");
            Integer db_is_suspended = this.queryResult.getInt("is_suspended");
            Integer db_is_guest = this.queryResult.getInt("is_guest");
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
    
    public Comment get_comment_by_id(String comment_id)throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * FROM comments WHERE id="+comment_id+"";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        Integer numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
       
        Comment comment = null;
		
        if (numberOfReturnedRows == 0) {
            throw new Exception("No comment with id '" + comment_id + "'.");
        }
        
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
        this.queryStmt = "SELECT * FROM comments WHERE id="+id+"";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        Integer numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        
        // Construct author object
        Review review = null;//Author.construct_guest();
		
        if (numberOfReturnedRows == 0) {
            throw new Exception("No author with id '" + id + "'.");
        }
        if (numberOfReturnedRows > 1) {
            throw new Exception("More than one author with id '" + id + "'.");
        }
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
               
        Integer numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);

        if (numberOfReturnedRows == 0) {
            throw new Exception("No rating associated with review id: '" + review_id + "'");
        }
        
        if(this.queryResult.next())
            return this.queryResult.getFloat(1);
        
        return 0.0F;
    }
    
    public Comment[] get_comments_by_review_id(String review_id) throws Exception{
        this.queryStmt = "SELECT * FROM comments WHERE review_id=" + review_id;
        this.queryResult = this.stmt.executeQuery(this.queryStmt);

        Integer numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
         // comments array
        Comment [] coments_by_review = new Comment[numberOfReturnedRows];//Author.construct_guest();
		
        if (numberOfReturnedRows  == 0) {
            throw new Exception("No comment for the review with id '" + review_id + "'.");
        }
        
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
        this.queryStmt = "SELECT * FROM reviews";
        this.err += "query String: " + this.queryStmt + "<br>";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);

        Integer numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        
        this.err += "after this.stmt.executeQuery(this.queryStmt)<br>";
         // comments array
        Review [] review_by_tale = new Review[numberOfReturnedRows];
		
        if (numberOfReturnedRows == 0) {
            throw new Exception("Oupss! something went wrong with tale id '" + tale_id + "'.");
        }
        
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
            i++;
        }

        //return array review object 
        return review_by_tale;
    }    
    
    /*UPDATE METHODS*/
    public void update_review_by_review_id(String review_id, String description) throws SQLException{
        this.queryStmt = "UPDATE reviews SET text = '" + description +
                " WHERE id = " + review_id;
        this.stmt.executeUpdate(this.queryStmt);
    }
    
    
    /*ADD METHODS*/
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
        Integer numberOfReturnedRows = this.getResultRowsNumber(this.queryStmt);
        
        if (numberOfReturnedRows == 0) {
            throw new Exception("No review for the tale with id '" + tale_id + "'.");
        }
        
        int i = 0;
        while(this.queryResult.next()){ //this could be done with WHERE IN List of ids
            this.delete_review_by_id(Integer.toString(this.queryResult.getInt("id")));
        }
        
        this.queryStmt = "DELETE FROM tales WHERE id="+tale_id+"";
        this.stmt.executeUpdate(this.queryStmt);
    }
    
    
}
