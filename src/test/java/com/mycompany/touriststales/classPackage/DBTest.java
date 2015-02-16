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
public class DBTest {
    
    public DBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getInstance method, of class DB.
     */
    @Test
    public void testGetInstance() {
        System.out.println("BDTEst: getInstance - demo");
        DB expResult = new DB();
        DB result = DB.getInstance();
        
        System.err.println("If the instantiation of the object work fine, expresult and result should be the same object");
        
        assertSame(expResult, result); //both expResult and Result should refer to the same object
        
        System.err.println("result.tostring(): " + result.toString());
        System.err.println("expResult.tostring(): " + expResult.toString());
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of create_author method, of class DB.
     */
//    @Test
//    public void testCreate_author() throws Exception {
//        System.out.println("create_author");
//        String user_name = "";
//        String first_name = "";
//        String last_name = "";
//        String email = "";
//        Integer is_admin = null;
//        Boolean is_suspended = null;
//        String description = "";
//        String img = "";
//        String password = "";
//        DB instance = null;
//        Author expResult = null;
//        Author result = instance.create_author(user_name, first_name, last_name, email, is_admin, is_suspended, description, img, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of create_guest method, of class DB.
//     */
//    @Test
//    public void testCreate_guest() {
//        System.out.println("create_guest");
//        DB instance = null;
//        Author expResult = null;
//        Author result = instance.create_guest();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of create_review method, of class DB.
//     */
//    @Test
//    public void testCreate_review() throws Exception {
//        System.out.println("create_review");
//        String author_id = "";
//        String title = "";
//        String category = "";
//        String location = "";
//        String text = "";
//        String tale_id = "";
//        DB instance = null;
//        Review expResult = null;
//        Review result = instance.create_review(author_id, title, category, location, text, tale_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of create_tale method, of class DB.
//     */
//    @Test
//    public void testCreate_tale() throws Exception {
//        System.out.println("create_tale");
//        String author_id = "";
//        String title = "";
//        String text = "";
//        DB instance = null;
//        Tale expResult = null;
//        Tale result = instance.create_tale(author_id, title, text);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of set_author_description method, of class DB.
//     */
//    @Test
//    public void testSet_author_description() throws Exception {
//        System.out.println("set_author_description");
//        String author_id = "";
//        String text = "";
//        DB instance = null;
//        instance.set_author_description(author_id, text);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of set_author_user_name method, of class DB.
//     */
//    @Test
//    public void testSet_author_user_name() throws Exception {
//        System.out.println("set_author_user_name");
//        String author_id = "";
//        String user_name = "";
//        DB instance = null;
//        instance.set_author_user_name(author_id, user_name);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of set_author_last_name method, of class DB.
//     */
//    @Test
//    public void testSet_author_last_name() throws Exception {
//        System.out.println("set_author_last_name");
//        String author_id = "";
//        String last_name = "";
//        DB instance = null;
//        instance.set_author_last_name(author_id, last_name);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of set_author_first_name method, of class DB.
//     */
//    @Test
//    public void testSet_author_first_name() throws Exception {
//        System.out.println("set_author_first_name");
//        String author_id = "";
//        String first_name = "";
//        DB instance = null;
//        instance.set_author_first_name(author_id, first_name);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of set_author_email method, of class DB.
//     */
//    @Test
//    public void testSet_author_email() throws Exception {
//        System.out.println("set_author_email");
//        String author_id = "";
//        String email = "";
//        DB instance = null;
//        instance.set_author_email(author_id, email);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of set_author_password method, of class DB.
//     */
//    @Test
//    public void testSet_author_password() throws Exception {
//        System.out.println("set_author_password");
//        String author_id = "";
//        String password = "";
//        DB instance = null;
//        instance.set_author_password(author_id, password);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of set_author_is_suspended method, of class DB.
//     */
//    @Test
//    public void testSet_author_is_suspended() throws Exception {
//        System.out.println("set_author_is_suspended");
//        String author_id = "";
//        String is_suspended = "";
//        DB instance = null;
//        instance.set_author_is_suspended(author_id, is_suspended);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of get_author_by_user_name method, of class DB.
     */
//    @Test
//    public void testGet_author_by_user_name() throws Exception {
//        System.out.println("get_author_by_user_name");
//        String user_name = "";
//        DB instance = null;
//        Author expResult = null;
//        Author result = instance.get_author_by_user_name(user_name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_author_by_id method, of class DB.
//     */
//    @Test
//    public void testGet_author_by_id() throws Exception {
//        System.out.println("get_author_by_id");
//        String id = "";
//        DB instance = null;
//        Author expResult = null;
//        Author result = instance.get_author_by_id(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_followers_by_author method, of class DB.
//     */
//    @Test
//    public void testGet_followers_by_author() throws Exception {
//        System.out.println("get_followers_by_author");
//        String author_id = "";
//        DB instance = null;
//        Author[] expResult = null;
//        Author[] result = instance.get_followers_by_author(author_id);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_following_by_author method, of class DB.
//     */
//    @Test
//    public void testGet_following_by_author() throws Exception {
//        System.out.println("get_following_by_author");
//        String author_id = "";
//        DB instance = null;
//        Author[] expResult = null;
//        Author[] result = instance.get_following_by_author(author_id);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_comment_by_id method, of class DB.
//     */
//    @Test
//    public void testGet_comment_by_id() throws Exception {
//        System.out.println("get_comment_by_id");
//        String comment_id = "";
//        DB instance = null;
//        Comment expResult = null;
//        Comment result = instance.get_comment_by_id(comment_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_review_by_id method, of class DB.
//     */
//    @Test
//    public void testGet_review_by_id() throws Exception {
//        System.out.println("get_review_by_id");
//        String id = "";
//        DB instance = null;
//        Review expResult = null;
//        Review result = instance.get_review_by_id(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_rating_by_review_id method, of class DB.
//     */
//    @Test
//    public void testGet_rating_by_review_id() throws Exception {
//        System.out.println("get_rating_by_review_id");
//        String review_id = "";
//        DB instance = null;
//        Float expResult = null;
//        Float result = instance.get_rating_by_review_id(review_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_comments_by_review_id method, of class DB.
//     */
//    @Test
//    public void testGet_comments_by_review_id() throws Exception {
//        System.out.println("get_comments_by_review_id");
//        String review_id = "";
//        DB instance = null;
//        Comment[] expResult = null;
//        Comment[] result = instance.get_comments_by_review_id(review_id);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_reviews_by_tale_id method, of class DB.
//     */
//    @Test
//    public void testGet_reviews_by_tale_id() throws Exception {
//        System.out.println("get_reviews_by_tale_id");
//        String tale_id = "";
//        DB instance = null;
//        Review[] expResult = null;
//        Review[] result = instance.get_reviews_by_tale_id(tale_id);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_tales_by_author method, of class DB.
//     */
//    @Test
//    public void testGet_tales_by_author() throws Exception {
//        System.out.println("get_tales_by_author");
//        String author_id = "";
//        DB instance = null;
//        Tale[] expResult = null;
//        Tale[] result = instance.get_tales_by_author(author_id);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_bookmarks_by_author_id method, of class DB.
//     */
//    @Test
//    public void testGet_bookmarks_by_author_id() throws Exception {
//        System.out.println("get_bookmarks_by_author_id");
//        String author_id = "";
//        DB instance = null;
//        Review[] expResult = null;
//        Review[] result = instance.get_bookmarks_by_author_id(author_id);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_comments_by_author method, of class DB.
//     */
//    @Test
//    public void testGet_comments_by_author() throws Exception {
//        System.out.println("get_comments_by_author");
//        String author_id = "";
//        DB instance = null;
//        Comment[] expResult = null;
//        Comment[] result = instance.get_comments_by_author(author_id);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of update_review_by_review_id method, of class DB.
//     */
//    @Test
//    public void testUpdate_review_by_review_id() throws Exception {
//        System.out.println("update_review_by_review_id");
//        String review_id = "";
//        String description = "";
//        DB instance = null;
//        instance.update_review_by_review_id(review_id, description);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add_bookmark_by_review_id method, of class DB.
//     */
//    @Test
//    public void testAdd_bookmark_by_review_id() throws Exception {
//        System.out.println("add_bookmark_by_review_id");
//        String author_id = "";
//        String review_id = "";
//        DB instance = null;
//        instance.add_bookmark_by_review_id(author_id, review_id);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add_comment method, of class DB.
//     */
//    @Test
//    public void testAdd_comment() throws Exception {
//        System.out.println("add_comment");
//        String author_id = "";
//        String review_id = "";
//        String text = "";
//        DB instance = null;
//        Comment expResult = null;
//        Comment result = instance.add_comment(author_id, review_id, text);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add_rating method, of class DB.
//     */
//    @Test
//    public void testAdd_rating() throws Exception {
//        System.out.println("add_rating");
//        String author_id = "";
//        String review_id = "";
//        String rating = "";
//        DB instance = null;
//        instance.add_rating(author_id, review_id, rating);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add_image method, of class DB.
//     */
//    @Test
//    public void testAdd_image() throws Exception {
//        System.out.println("add_image");
//        String img_str = "";
//        String review_id = "";
//        DB instance = null;
//        instance.add_image(img_str, review_id);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete_comment_by_id method, of class DB.
//     */
//    @Test
//    public void testDelete_comment_by_id() throws Exception {
//        System.out.println("delete_comment_by_id");
//        String comment_id = "";
//        DB instance = null;
//        instance.delete_comment_by_id(comment_id);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete_review_by_id method, of class DB.
//     */
//    @Test
//    public void testDelete_review_by_id() throws Exception {
//        System.out.println("delete_review_by_id");
//        String review_id = "";
//        DB instance = null;
//        instance.delete_review_by_id(review_id);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete_tale_by_id method, of class DB.
//     */
//    @Test
//    public void testDelete_tale_by_id() throws Exception {
//        System.out.println("delete_tale_by_id");
//        String tale_id = "";
//        DB instance = null;
//        instance.delete_tale_by_id(tale_id);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete_author_by_id method, of class DB.
//     */
//    @Test
//    public void testDelete_author_by_id() throws Exception {
//        System.out.println("delete_author_by_id");
//        String author_id = "";
//        DB instance = null;
//        instance.delete_author_by_id(author_id);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of remove_bookmark_by_review_id method, of class DB.
//     */
//    @Test
//    public void testRemove_bookmark_by_review_id() throws Exception {
//        System.out.println("remove_bookmark_by_review_id");
//        String author_id = "";
//        String review_id = "";
//        DB instance = null;
//        instance.remove_bookmark_by_review_id(author_id, review_id);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
    
}
