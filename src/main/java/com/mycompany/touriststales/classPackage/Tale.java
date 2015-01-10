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
public class Tale {
    private Integer id = -1;
    private Integer author_id = -1;
    private String title;
    private String text;
    private String time;
    private String date;
    private DB myDB;
    
    public Tale(Integer id,
            Integer author_id,
            String title,
            String text,
            String time,
            String date){
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.text = text;
        this.time = time;
        this.date = date; 
        this.myDB = DB.getInstance();
    }

    public Integer getId() {
        return id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public String getTitle() {
        return title;
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
    
    public Author getAuthor() throws Exception{
        return this.myDB.get_author_by_id(Integer.toString(this.author_id));
    }
    
    public Review[] get_reviews() throws Exception{
        return this.myDB.get_reviews_by_tale_id(Integer.toString(this.id));
    }
    
    public Review add_review(String title, String category, String location, String description) throws Exception{
        return this.myDB.create_review(Integer.toString(this.author_id), title, category, location, description, Integer.toString(this.id));
    }
    
    public void delete_from_database() throws Exception{
        this.myDB.delete_tale_by_id(Integer.toString(this.id));
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
