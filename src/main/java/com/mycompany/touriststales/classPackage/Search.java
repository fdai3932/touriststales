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
public class Search {
	
    private Review [] reviews;
    DB myDB;
    
    public Search (){
        this.myDB = DB.getInstance();
    }
    public Search (Review [] reviewsFromQuery) {
        this.reviews  = reviewsFromQuery;
        this.myDB = DB.getInstance();
    }
    
    public Review [] get_reviews ()
    {
        return this.reviews;
    }
    /*
    public function search_split_terms(String terms){

        terms = preg_replace("/\"(.*?)\"/e", "search_transform_term('\$1')", terms);
        String [] listOfTerms = preg_split("/\s+|,/", terms);

        $out = array();

		int i = 0;
		while (i < listOfTerms.length ){
			term = listOfTerms[i]
                term = term.replaceAll("/\{WHITESPACE-([0-9]+)\}/e", "chr(\$1)");
                term = term.replaceAll("/\{COMMA\}/", ",");

                $out[] = $term;
			
			i++;
		}

        return $out;
    }
	*/
	
	/*
    public function search_transform_term($term){
        $term = preg_replace("/(\s)/e", "'{WHITESPACE-'.ord('\$1').'}'", $term);
        $term = preg_replace("/,/", "{COMMA}", $term);
        return $term;
    }
    
     public function search_escape_rlike($string){
        return preg_replace("/([.\[\]*^\$])/", '\\\$1', $string);
    }
    
    public function search_db_escape_terms($terms){
        $out = array();
        foreach($terms as $term){
                $out[] = '[[:<:]]'.AddSlashes(Search::search_escape_rlike($term)).'[[:>:]]';
        }
        return $out;
    }
	*/
	
    /**
     * Return a search object containing an array of Review objects 
     * depending on the specified amount
     * 
     * @author Charles
     * @param amount
     * 
     * @return <Search>
     * @throws Exception
     */
    public  Search get_recent_reviews( int amount) throws Exception
    {
         if(amount <= 0) {
            throw new Exception ("Amount of reviews specified is <= 0");
         }
        
        return new Search(this.myDB.get_n_reviews( amount ));
    }
    
    /*
     * Return a Search object containing an array of found review objects
     * 
     * @author Charles
     * @return <Search>
     */
    public Search get_review_by_search_term (int amount, String term) throws Exception
    {
        if(amount <= 0) {
            throw new Exception ("Amount of reviews specified is 0 or < 0");
        }
        return new Search(this.myDB.get_n_reviews_by_term(amount, term));
    }
    
    /**
     * Return an array of Review objects depending on the specified
     * category and amount
     * 
     * @author Charles
     * 
     * @return \Array<Review>
     * @throws Exception
     */
    public Search get_reviews_by_category( int amount, String category) throws Exception
    {
        if(amount <= 0 ) {
            throw new Exception ("Amount of reviews specified is 0 or < 0");
        }
        
        return new Search (this.myDB.get_n_reviews_by_cathegory(amount, category));
    }
    
    /**
     * Return an array of Tale objects depending on the specified amount
     * 
     * @author Charles
     * 
     * @return \Array<Tales>
     * @throws Exception
     */
    /*
    public function get_recent_tales(amount $amount)
    {
        $i = 0;
        if($amount <= 0) {
            throw new Exception ("Amount of tales specified is 0 or < 0");
        }
        
        $tales = array();
        $db = TravelTaleDB::getInstance();
        // Read from db and order tales descending (DESC)
        $result = $db.query("SELECT * "
                . "FROM tales "
                . "ORDER BY date DESC");
        if($result == NULL) {
            throw new Exception("No Reviews found");            
        }
        
        // Read the specified amount of rows, create a tale object 
        // and write it to the array
        while($tale_row = $result.fetch_array()) {
            if($i < $amount) {
                 array_push($tales,new Tale($tale_row));
            }
            else {
                break;
            }
            $i++;
        }
        return new Search ($tales);
     }
    */
}

