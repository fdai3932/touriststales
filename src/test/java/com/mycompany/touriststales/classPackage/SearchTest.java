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
public class SearchTest {
    
//    public SearchTest() {
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
//     * Test of get_reviews method, of class Search.
//     */
//    @Test
//    public void testGet_reviews() {
//        System.out.println("get_reviews");
//        Search instance = new Search();
//        Review[] expResult = null;
//        Review[] result = instance.get_reviews();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_recent_reviews method, of class Search.
//     */
//    @Test
//    public void testGet_recent_reviews() throws Exception {
//        System.out.println("get_recent_reviews");
//        int amount = 0;
//        Search instance = new Search();
//        Search expResult = null;
//        Search result = instance.get_recent_reviews(amount);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of get_review_by_search_term method, of class Search.
//     */
//    @Test
//    public void testGet_review_by_search_term() throws Exception {
//        System.out.println("get_review_by_search_term");
//        int amount = 0;
//        String term = "";
//        Search instance = new Search();
//        Search expResult = null;
//        Search result = instance.get_review_by_search_term(amount, term);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of get_reviews_by_category method, of class Search.
     */
    @Test
    public void testGet_reviews_by_category() throws Exception {
        System.out.println("get_reviews_by_category");
        
        boolean isCategoryFun = true;
        int amount = 5;
        String category = "Fun";
        Search instance = new Search();
        Search result = instance.get_reviews_by_category(amount, category);
        Review [] reviews = result.get_reviews();
        int i = 0;
        while(i < reviews.length)
            if(!reviews[i++].getCategory().contentEquals(category))
                isCategoryFun = false;
        
        assertTrue(reviews.length <= amount && isCategoryFun);
    }
    
}
