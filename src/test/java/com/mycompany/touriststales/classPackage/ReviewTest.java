/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.touriststales.classPackage;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Achille
 */
public class ReviewTest {
    private final DB myDB = DB.getInstance();
//    public ReviewTest() {
//    }
//    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getId method, of class Review.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Integer result = -1;
        Integer expResult = 1;
        try{
            Review instance = this.myDB.get_review_by_id("1");
            result = instance.getId();
        }catch(Exception ex){
            result = -1;
            System.err.print("Achille: " + ex.getMessage() + "\n");
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAuthor method, of class Review.
     */
//    @Test
//    public void testGetAuthor() throws Exception {
//        System.out.println("getAuthor");
//        Review instance = new Review();
//        Author expResult = null;
//        Author result = instance.getAuthor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTale_id method, of class Review.
//     */
//    @Test
//    public void testGetTale_id() {
//        System.out.println("getTale_id");
//        Review instance = new Review();
//        Integer expResult = null;
//        Integer result = instance.getTale_id();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getText method, of class Review.
//     */
//    @Test
//    public void testGetText() {
//        System.out.println("getText");
//        Review instance = new Review();
//        String expResult = "";
//        String result = instance.getText();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCategory method, of class Review.
//     */
//    @Test
//    public void testGetCategory() {
//        System.out.println("getCategory");
//        Review instance = new Review();
//        String expResult = "";
//        String result = instance.getCategory();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTitle method, of class Review.
//     */
//    @Test
//    public void testGetTitle() {
//        System.out.println("getTitle");
//        Review instance = new Review();
//        String expResult = "";
//        String result = instance.getTitle();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTime method, of class Review.
//     */
//    @Test
//    public void testGetTime() {
//        System.out.println("getTime");
//        Review instance = new Review();
//        String expResult = "";
//        String result = instance.getTime();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDate method, of class Review.
//     */
//    @Test
//    public void testGetDate() {
//        System.out.println("getDate");
//        Review instance = new Review();
//        String expResult = "";
//        String result = instance.getDate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAuthorID method, of class Review.
//     */
//    @Test
//    public void testGetAuthorID() {
//        System.out.println("getAuthorID");
//        Review instance = new Review();
//        Integer expResult = null;
//        Integer result = instance.getAuthorID();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRating method, of class Review.
//     */
//    @Test
//    public void testGetRating() throws Exception {
//        System.out.println("getRating");
//        Review instance = new Review();
//        Integer expResult = null;
//        Integer result = instance.getRating();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_comments method, of class Review.
//     */
//    @Test
//    public void testGet_comments() throws Exception {
//        System.out.println("get_comments");
//        Review instance = new Review();
//        Comment[] expResult = null;
//        Comment[] result = instance.get_comments();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add_author_rating_by_id method, of class Review.
//     */
//    @Test
//    public void testAdd_author_rating_by_id() throws Exception {
//        System.out.println("add_author_rating_by_id");
//        String author_id = "";
//        String rating = "";
//        Review instance = new Review();
//        instance.add_author_rating_by_id(author_id, rating);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add_image method, of class Review.
//     */
//    @Test
//    public void testAdd_image() {
//        System.out.println("add_image");
//        String img_str = "";
//        Review instance = new Review();
//        instance.add_image(img_str);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add_comment method, of class Review.
//     */
//    @Test
//    public void testAdd_comment() throws Exception {
//        System.out.println("add_comment");
//        String author_id = "";
//        String review_id = "";
//        String text = "";
//        Review instance = new Review();
//        Comment expResult = null;
//        Comment result = instance.add_comment(author_id, review_id, text);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of set_text method, of class Review.
//     */
//    @Test
//    public void testSet_text() throws Exception {
//        System.out.println("set_text");
//        String description = "";
//        Review instance = new Review();
//        instance.set_text(description);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteReviewFromBD method, of class Review.
//     */
//    @Test
//    public void testDeleteReviewFromBD() throws Exception {
//        System.out.println("deleteReviewFromBD");
//        Review instance = new Review();
//        instance.deleteReviewFromBD();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Review.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Review instance = new Review();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
