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
public class Comment {
    private Integer id = -1;
    private Integer author_id = -1;
    private Integer review_id = -1;
    private String text;
    private String time;
    private String date;
    private DB myDB;

    public Comment(Integer id,
            Integer author_id,
            Integer review_id,
            String text,
            String time,
            String date){
        this.id = id;
        this.author_id = author_id;
        this.review_id = review_id;
        this.text = text;
        this.time = time;
        this.date = date; 
        this.myDB = DB.getInstance();
    }

    public Integer getId() {
        return id;
    }

    public Author getAuthor() throws Exception{
        return this.myDB.get_author_by_id(Integer.toString(this.author_id));
    }

    public Integer getReview_id() {
        return review_id;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
    
    public void deleteCommentFromBD() throws SQLException{
        this.myDB.delete_comment_by_id(Integer.toString(this.id));
    }
    
    @Override
    public String toString(){
        String info = "";
        info += "ID: " + this.id + "<br>";
        info += "text: " + this.text + "<br>";
        info += "time: " + this.time + "<br>";
        info += "date: " + this.date + "<br>";
        
        return info;
    }
}
