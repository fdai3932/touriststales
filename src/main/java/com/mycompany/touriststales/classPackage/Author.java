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
public class Author {
    private Integer id = -1;
    private Boolean is_admin = false;
    private Boolean is_suspended = false;
    private String user_name = "Guest";
    private String first_name = "Jane";
    private String last_name = "Doe";
    private String email = "";
    private String description = "";
    private String img = null;
    private String password = "";
    private Boolean is_guest = true;

    
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
