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
    //private final String dbHost = "localhost";
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
        /*
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
           this.err = "com.mysql.jdbc.Driver: new Instance " + e.getMessage() + ".<br/>";
        }
        
        try{    
            this.conn = DriverManager.getConnection(
                    "jdbc:mysqld://" + this.dbHost + ":3306/" + this.dbName
                    , this.user
                    , this.password);
            this.stmt = conn.createStatement();
            this.debbug = this.debbug + "DB Contructor.getConnection n createStmt...<br/>";
       }catch(SQLException e){
           this.err = "GetConnection and CreateStmt: " + e.getMessage() + ".<br/>";
       }*/
    }
    
    //This method must be static, and must return an instance of the object if the object
    //does not already exist.
    
   
}
