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

/**
 *
 * @author Achille
 */
public class DB{
    // single instance of self shared among all instances
    private static DB instance = null;
    
    // DB connection config vars
    private final String user = "root";
    private final String password = "root";
    private final String dbName = "touriststales";
    private final String dbHost = "54.148.37.252";
    
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
        this.err = "";
        this.debbug =  this.debbug + "DB Contructor...<br/>";
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception e){
           this.err = "com.mysql.jdbc.Driver: new Instance " + e.getMessage() + ".<br/>";
        }
        
        try{    
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://" + this.dbHost + ":3306/" + this.dbName
                    , this.user
                    , this.password);
            this.stmt = conn.createStatement();
            this.debbug = this.debbug + "DB Contructor.getConnection n createStmt...<br/>";
       }catch(SQLException e){
           this.err = "GetConnection and CreateStmt: " + e.getMessage() + ".<br/>";
       }
    }
    
    //This method must be static, and must return an instance of the object if the object
    //does not already exist.
    
    
    private String getLastInsertedID(){
        String insertID = "";
        try{
            PreparedStatement getLastInsertId = this.conn.prepareStatement("SELECT LAST_INSERT_ID()");  
            ResultSet rs = getLastInsertId.executeQuery();  
            if (rs.next())  
            {  
                insertID = Long.toString( rs.getLong("last_insert_id()") );              
            }     
        }catch (Exception e){
            this.err = "executeQuery(LAST_INSERT_ID): " + this.err + e.getMessage() + ".<br/>";
        }
    
        return insertID;
    }
    
    private int getResultRowsNumber(){
        int numberOfRows = 0;
        try{
            while( this.queryResult.next() ) numberOfRows++;
        }catch(Exception e){
            this.err = "executeQuery(getNumbersOfRows): " + this.err + e.getMessage() + ".<br/>";
        }
        return numberOfRows;
    }
    
    public Author create_author(String user_name,
                                  String first_name,
                                  String last_name,
                                  String email,
                                  Integer is_admin,
                                  Boolean is_suspended,
                                  String description,
                                  String img,
                                  String password) throws Exception{
        /*if (is_admin) {
            is_admin = 1;
        } else {
            is_admin = 0;
        }
        if (is_suspended) {
            is_suspended = 1;
        } else {
            is_suspended = 0;
        }*/

        // Check if user name is unique
        this.queryStmt = "SELECT * FROM authors WHERE user_name='" + user_name + "'";
        try{
            this.queryResult = this.stmt.executeQuery(this.queryStmt);
        }catch(SQLException SQLe){
            this.err = "executeQuery(Select): " + this.err + SQLe.getMessage() + ".<br/>";
        }
        if (this.queryResult.next()) {
            throw new Exception("User name already taken");
        }
        
        // Check if email is unique
        this.queryStmt = "SELECT * FROM authors WHERE email='" + email + "'";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        if (this.getResultRowsNumber() < 0) {
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
        this.stmt.executeQuery(this.queryStmt);
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
    
    public Author get_author_by_user_name(String user_name)throws Exception{
        // Get the author row
	//String user_name_temp = StringEscapeUtils.escapeJava(user_name);
        this.queryStmt = "SELECT * FROM authors WHERE user_name='"+user_name+"'";
        this.queryResult = this.stmt.executeQuery(this.queryStmt);
        
        // Construct author object
        Author author = Author.construct_guest();
		if (this.getResultRowsNumber() == 0) {
            throw new Exception("No author with user name '" + "user_name" + "'.");
        }
        if (this.getResultRowsNumber() > 1) {
            throw new Exception("More than one author with user name '" + "user_name" + "'.");
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
}
