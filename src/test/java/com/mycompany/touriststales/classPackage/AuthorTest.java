/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.touriststales.classPackage;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Achille
 */
public class AuthorTest {
    
//    public AuthorTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    /**
//     * Test of construct_guest method, of class Author.
//     */
//    @Test
//    public void testConstruct_guest() {
//        System.out.println("construct_guest");
//        Author expResult = null;
//        Author result = Author.construct_guest();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getId method, of class Author.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Integer result = -1;
        Integer expResult = 1;
        try{
            Author instance = DB.getInstance().get_author_by_id("1");
            result = instance.getId();
        }catch(Exception ex){
            result = -1;
        }
        
        assertEquals(expResult, result);
    }

//    /**
//     * Test of is_admin method, of class Author.
//     */
//    @Test
//    public void testIs_admin() {
//        System.out.println("is_admin");
//        Author instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.is_admin();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of is_suspended method, of class Author.
//     */
//    @Test
//    public void testIs_suspended() {
//        System.out.println("is_suspended");
//        Author instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.is_suspended();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUser_name method, of class Author.
//     */
//    @Test
//    public void testGetUser_name() {
//        System.out.println("getUser_name");
//        Author instance = null;
//        String expResult = "";
//        String result = instance.getUser_name();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFirst_name method, of class Author.
//     */
//    @Test
//    public void testGetFirst_name() {
//        System.out.println("getFirst_name");
//        Author instance = null;
//        String expResult = "";
//        String result = instance.getFirst_name();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLast_name method, of class Author.
//     */
//    @Test
//    public void testGetLast_name() {
//        System.out.println("getLast_name");
//        Author instance = null;
//        String expResult = "";
//        String result = instance.getLast_name();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEmail method, of class Author.
//     */
//    @Test
//    public void testGetEmail() {
//        System.out.println("getEmail");
//        Author instance = null;
//        String expResult = "";
//        String result = instance.getEmail();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDescription method, of class Author.
//     */
//    @Test
//    public void testGetDescription() {
//        System.out.println("getDescription");
//        Author instance = null;
//        String expResult = "";
//        String result = instance.getDescription();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getImg method, of class Author.
//     */
//    @Test
//    public void testGetImg() {
//        System.out.println("getImg");
//        Author instance = null;
//        String expResult = "";
//        String result = instance.getImg();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPassword method, of class Author.
//     */
//    @Test
//    public void testGetPassword() {
//        System.out.println("getPassword");
//        Author instance = null;
//        String expResult = "";
//        String result = instance.getPassword();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of is_guest method, of class Author.
//     */
//    @Test
//    public void testIs_guest() {
//        System.out.println("is_guest");
//        Author instance = null;
//        Boolean expResult = null;
//        Boolean result = instance.is_guest();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_bookmarks method, of class Author.
//     */
//    @Test
//    public void testGet_bookmarks() throws Exception {
//        System.out.println("get_bookmarks");
//        Author instance = null;
//        Review[] expResult = null;
//        Review[] result = instance.get_bookmarks();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_comments method, of class Author.
//     */
//    @Test
//    public void testGet_comments() throws Exception {
//        System.out.println("get_comments");
//        Author instance = null;
//        Comment[] expResult = null;
//        Comment[] result = instance.get_comments();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_followers method, of class Author.
//     */
//    @Test
//    public void testGet_followers() throws Exception {
//        System.out.println("get_followers");
//        Author instance = null;
//        Author[] expResult = null;
//        Author[] result = instance.get_followers();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_following method, of class Author.
//     */
//    @Test
//    public void testGet_following() throws Exception {
//        System.out.println("get_following");
//        Author instance = null;
//        Author[] expResult = null;
//        Author[] result = instance.get_following();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_tables method, of class Author.
//     */
//    @Test
//    public void testGet_tables() throws Exception {
//        System.out.println("get_tables");
//        Author instance = null;
//        Tale[] expResult = null;
//        Tale[] result = instance.get_tables();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setIs_suspended method, of class Author.
//     */
//    @Test
//    public void testSetIs_suspended() throws Exception {
//        System.out.println("setIs_suspended");
//        Boolean is_suspended = null;
//        Author instance = null;
//        instance.setIs_suspended(is_suspended);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setUser_name method, of class Author.
//     */
//    @Test
//    public void testSetUser_name() throws Exception {
//        System.out.println("setUser_name");
//        String user_name = "";
//        Author instance = null;
//        instance.setUser_name(user_name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFirst_name method, of class Author.
//     */
//    @Test
//    public void testSetFirst_name() throws Exception {
//        System.out.println("setFirst_name");
//        String first_name = "";
//        Author instance = null;
//        instance.setFirst_name(first_name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setLast_name method, of class Author.
//     */
//    @Test
//    public void testSetLast_name() throws Exception {
//        System.out.println("setLast_name");
//        String last_name = "";
//        Author instance = null;
//        instance.setLast_name(last_name);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEmail method, of class Author.
//     */
//    @Test
//    public void testSetEmail() throws Exception {
//        System.out.println("setEmail");
//        String email = "";
//        Author instance = null;
//        instance.setEmail(email);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDescription method, of class Author.
//     */
//    @Test
//    public void testSetDescription() throws Exception {
//        System.out.println("setDescription");
//        String description = "";
//        Author instance = null;
//        instance.setDescription(description);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setPassword method, of class Author.
//     */
//    @Test
//    public void testSetPassword() throws Exception {
//        System.out.println("setPassword");
//        String password = "";
//        Author instance = null;
//        instance.setPassword(password);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add_bookmark method, of class Author.
//     */
//    @Test
//    public void testAdd_bookmark() throws Exception {
//        System.out.println("add_bookmark");
//        Review review = null;
//        Author instance = null;
//        instance.add_bookmark(review);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of create_tale method, of class Author.
//     */
//    @Test
//    public void testCreate_tale() throws Exception {
//        System.out.println("create_tale");
//        String title = "";
//        String text = "";
//        Author instance = null;
//        Tale expResult = null;
//        Tale result = instance.create_tale(title, text);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete_author_from_database method, of class Author.
//     */
//    @Test
//    public void testDelete_author_from_database() throws Exception {
//        System.out.println("delete_author_from_database");
//        Author instance = null;
//        instance.delete_author_from_database();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of remove_bookmark_by_review_id method, of class Author.
//     */
//    @Test
//    public void testRemove_bookmark_by_review_id() throws Exception {
//        System.out.println("remove_bookmark_by_review_id");
//        String review_id = "";
//        Author instance = null;
//        instance.remove_bookmark_by_review_id(review_id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Author.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Author instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
