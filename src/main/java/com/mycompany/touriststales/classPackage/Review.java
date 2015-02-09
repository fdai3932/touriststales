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
public class Review {
    private Integer id = -1;
    private Integer author_id = -1;
    private String title;
    private String category;
    private String text;
    private Integer tale_id = -1;
    private String time;
    private String date;
    private String location;
    private DB myDB;

    public Review(Integer id,
            Integer author_id,
            Integer tale_id,
            String title,
            String location,
            String text,
            String category,
            String time,
            String date){
        this.id = id;
        this.author_id = author_id;
        this.tale_id = tale_id;
        this.title = title;
        this.location = location;
        this.text = text;
        this.time = time;
        this.date = date; 
        this.category = category; 
        this.myDB = DB.getInstance();
        
    }
    
    public Review(){
        this.id = 1;
        this.author_id = 2;
        this.tale_id = 3;
        this.title = "title";
        this.location = "location";
        this.text = "text";
        this.time = "time";
        this.date = "date"; 
        this.category = "category"; 
        this.myDB = DB.getInstance();
    }

    public Integer getId() {
        return id;
    }

    public Author getAuthor() throws Exception{
        return this.myDB.get_author_by_id(Integer.toString(this.author_id));
    }
    
    public Integer getTale_id() {
        return tale_id;
    }

    public String getText() {
        return text;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getTitle() {
        return title;
    }
    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
    
    public Integer getAuthorID() {
        return author_id;
    }
    
    public Integer getRating() throws Exception{
        return (Integer)Math.round(this.myDB.get_rating_by_review_id(Integer.toString(this.id)));
    }
    
    public Comment[] get_comments() throws Exception{
        return this.myDB.get_comments_by_review_id(Integer.toString(this.id));
    }
    
    public void add_author_rating_by_id(String author_id, String rating) throws SQLException{
        this.myDB.add_rating(author_id, Integer.toString(this.id), rating);
    }
    
    public void add_image(String img_str) {
        
    }
    
    public Comment add_comment(String author_id, String review_id, String text) throws  Exception{
        return this.myDB.add_comment(author_id, review_id, text);
    }
    
    public void set_text(String description) throws SQLException{
        this.myDB.update_review_by_review_id(Integer.toString(this.id), description);
    }
    
    public void deleteReviewFromBD() throws SQLException{
        this.myDB.delete_review_by_id(Integer.toString(this.id));
    }
    
    @Override
    public String toString(){
        String info = "";
        info += "ID: " + this.id + "<br>";
        info += "title: " + this.title + "<br>";
        info += "text: " + this.text + "<br>";
        info += "time: " + this.time + "<br>";
        info += "date: " + this.date + "<br>";
        
        return info;
    }
    
    
}
