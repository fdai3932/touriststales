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
public class TaleTest {
    
//    public TaleTest() {
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
     * Test of getId method, of class Tale.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Integer result = -1;
        Integer expResult = 1;
        try{
            Tale instance = DB.getInstance().get_tale_by_id("1");
            result = instance.getId();
        }catch(Exception ex){
            result = -1;
        }
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAuthor_id method, of class Tale.
     */
//    @Test
//    public void testGetAuthor_id() {
//        System.out.println("getAuthor_id");
//        Tale instance = null;
//        Integer expResult = null;
//        Integer result = instance.getAuthor_id();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTitle method, of class Tale.
//     */
//    @Test
//    public void testGetTitle() {
//        System.out.println("getTitle");
//        Tale instance = null;
//        String expResult = "";
//        String result = instance.getTitle();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getText method, of class Tale.
//     */
//    @Test
//    public void testGetText() {
//        System.out.println("getText");
//        Tale instance = null;
//        String expResult = "";
//        String result = instance.getText();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTime method, of class Tale.
//     */
//    @Test
//    public void testGetTime() {
//        System.out.println("getTime");
//        Tale instance = null;
//        String expResult = "";
//        String result = instance.getTime();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDate method, of class Tale.
//     */
//    @Test
//    public void testGetDate() {
//        System.out.println("getDate");
//        Tale instance = null;
//        String expResult = "";
//        String result = instance.getDate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAuthor method, of class Tale.
//     */
//    @Test
//    public void testGetAuthor() throws Exception {
//        System.out.println("getAuthor");
//        Tale instance = null;
//        Author expResult = null;
//        Author result = instance.getAuthor();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_reviews method, of class Tale.
//     */
//    @Test
//    public void testGet_reviews() throws Exception {
//        System.out.println("get_reviews");
//        Tale instance = null;
//        Review[] expResult = null;
//        Review[] result = instance.get_reviews();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add_review method, of class Tale.
//     */
//    @Test
//    public void testAdd_review() throws Exception {
//        System.out.println("add_review");
//        String title = "";
//        String category = "";
//        String location = "";
//        String description = "";
//        Tale instance = null;
//        Review expResult = null;
//        Review result = instance.add_review(title, category, location, description);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of delete_from_database method, of class Tale.
//     */
//    @Test
//    public void testDelete_from_database() throws Exception {
//        System.out.println("delete_from_database");
//        Tale instance = null;
//        instance.delete_from_database();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Tale.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Tale instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
