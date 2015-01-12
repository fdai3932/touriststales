/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.touriststales.classPackage;

import java.sql.SQLException;

/**
 *
 * @author Achille
 */
public class Author {
    private Integer id = -1;
    private Boolean is_admin = false;
    private Boolean is_suspended = false;
    private String user_name;
    private String first_name;
    private String last_name;
    private String email;
    private String description;
    private String img = "";
    private String password;
    private Boolean is_guest = true;
    private DB myDB;

    
    public Author(Integer db_id
        ,String db_user_name 	
        ,String db_first_name 	
        ,String db_last_name 	
        ,String db_email 		
        ,Integer db_is_admin 	
        ,Integer db_is_suspended
        ,Integer db_is_guest 	
        ,String db_description	
        ,String db_img 			
        ,String db_password){

        this.id = db_id;
        this.user_name = db_user_name;
        this.first_name = db_first_name;
        this.last_name = db_last_name;
        this.email = db_email;
        this.is_admin = ( db_is_admin != 0 );
        this.is_suspended = ( db_is_suspended != 0 );
        this.is_guest = ( db_is_guest == 0 ) ? false : true; //leave this as it is.
        this.description = db_description;
        this.img = db_img;
        this.is_guest = false;
        
        this.myDB = DB.getInstance();
    }
    
    public static Author construct_guest(){  
        return new Author(-1
                ,"guest" 	
                ,"guest" 	
                ,"guest" 	
                ,"guest@guest.guest" 		
                ,0
                ,1
                ,1 	
                ,"Guest users have no account"	
                ,""			
                ,"");
    }
    
    public String getUser_Name(){
        return this.user_name;
    }

    public Integer getId() {
        return id;
    }

    public Boolean isIs_admin() {
        return is_admin;
    }

    public Boolean isIs_suspended() {
        return is_suspended;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isIs_guest() {
        return is_guest;
    }
    
    /*ADD METHODS*/
    public void add_bookmark(Review review) throws Exception, SQLException{
        if (this.is_guest) {
            throw new Exception("Author is a guest. Guest author cannot add bookmark");
        }
       // this.add_bookmark_by_review_id(review->get_id());
        this.myDB.add_bookmark_by_review_id(Integer.toString(this.id)
                , Integer.toString(review.getId()) );
    }
    
    /*CREATE METHODS*/
    public Tale create_tale(String title, String text) throws Exception{
        return this.myDB.create_tale(Integer.toString(this.id), title, text);
    }
    
    @Override
    public String toString(){
        String info = "";
        info += "ID: " + this.id + "<br>";
        info += "UserName: " + this.user_name + "<br>";
        info += "First Name: " + this.first_name + "<br>";
        info += "Last Name: " + this.last_name + "<br>";
        
        return info;
    }
}
