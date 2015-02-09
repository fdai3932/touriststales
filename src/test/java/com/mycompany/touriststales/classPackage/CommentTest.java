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
public class CommentTest {
    
//    public CommentTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }

    /**
     * Test of getId method, of class Comment.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Integer result = -1;
        Integer expResult = 2;
        try{
            Comment instance = DB.getInstance().get_comment_by_id("2");
            result = instance.getId();
        }catch(Exception ex){
            result = -1;
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAuthor method, of class Comment.
     */
//    @Test
//    public void testGetAuthor() throws Exception {
//        System.out.println("getAuthor");
//        Comment instance = null;
//        Author expResult = null;
//        Author result = instance.getAuthor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getReview_id method, of class Comment.
//     */
//    @Test
//    public void testGetReview_id() {
//        System.out.println("getReview_id");
//        Comment instance = null;
//        Integer expResult = null;
//        Integer result = instance.getReview_id();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getText method, of class Comment.
//     */
//    @Test
//    public void testGetText() {
//        System.out.println("getText");
//        Comment instance = null;
//        String expResult = "";
//        String result = instance.getText();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTime method, of class Comment.
//     */
//    @Test
//    public void testGetTime() {
//        System.out.println("getTime");
//        Comment instance = null;
//        String expResult = "";
//        String result = instance.getTime();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDate method, of class Comment.
//     */
//    @Test
//    public void testGetDate() {
//        System.out.println("getDate");
//        Comment instance = null;
//        String expResult = "";
//        String result = instance.getDate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteCommentFromBD method, of class Comment.
//     */
//    @Test
//    public void testDeleteCommentFromBD() throws Exception {
//        System.out.println("deleteCommentFromBD");
//        Comment instance = null;
//        instance.deleteCommentFromBD();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Comment.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Comment instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
