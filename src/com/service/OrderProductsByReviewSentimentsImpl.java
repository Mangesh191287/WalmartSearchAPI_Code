package com.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class OrderProductsByReviewSentimentsImpl implements IOrderProductsByReviewSentiments {
	
	
	public Map<String,Map<Integer,Double>> groupProductsByReviews(ArrayList<String> reviews){
		
	
		Map<String,Map<Integer,Double>> finalResultMap= new HashMap<String,Map<Integer,Double>>();
		
		JSONObject obj= new JSONObject();
		JSONArray  arr=null;
		String name="";
		String itemId="";
		
	  try {
		
		for(String review: reviews){
			
		    obj= new JSONObject(review);
		    arr= new JSONArray(obj.get("reviews").toString());
		    itemId=obj.getString("itemId");
		  //  System.out.println("name is:" +obj.getString("name"));
		    Map<Integer,Double> resultMap= new HashMap<Integer,Double>();
		    
		    //treemap and navigable map for sorting the keys (reviews) in descending order...
		    TreeMap<Integer,Double> map= new TreeMap<Integer,Double>();
		    
		    for(int i=0;i<arr.length();i++){
		    	   
		    	JSONObject obj1= new JSONObject(arr.get(i).toString());
		    	JSONObject overallRating=  new JSONObject(obj1.get("overallRating").toString());
		    	
		    	Integer rating=Integer.parseInt(overallRating.getString("rating"));
		    	Double upvote=  Double.parseDouble(obj1.getString("upVotes"));
		    	Double downvote= Double.parseDouble(obj1.getString("downVotes"));
		    	
		    	//calculate the difference between upvote and downvote
		    	name=obj1.getString("name");
			    Double diff=  upvote-downvote;
			    
			    //if map already contains the rating add the current rating's difference between upvote and downvote and add it to
			    //previous rating difference to get overall stats of upvotes and downvotes for that rating
			    if(map.containsKey(rating) ){
			    	map.put(rating, diff+map.get(rating));
			    }
			    
			    //if map doesn't contain a rating then add that rating to the map
			    else if(!map.containsKey(rating)){
			    	map.put(rating, diff);
			    }
			    
			   
			    
		    }
		    
		    //sort the keys in descending order..
		    NavigableMap<Integer, Double> nav= map.descendingMap();
		    
		    //take the record with highest review and where sentiment is not 0 or negative
		    for(Map.Entry<Integer, Double> map2 : nav.entrySet()){
				   
			       if(map2.getValue()>0.0){
			    	   resultMap.put(map2.getKey(), map2.getValue());
			    	   break;
			       }
			   }
		    		    
		    
		    if(resultMap.size()>0){
		        finalResultMap.put(name, resultMap) ;
		    }
		}
		
	  }
	  
	  catch(Exception ex){
		  ex.printStackTrace();
	  }
		
		
		
		return finalResultMap;
		
	}
	
	
	
	
	public List<Entry<String,Entry<Integer,Double>>> orderProductsByReviews(Map<String,Map<Integer,Double>> reviewSentimentsMap){
		
		Set set= reviewSentimentsMap.entrySet();
		List<Entry<String,Entry<Integer,Double>>> list = new LinkedList<Entry<String,Entry<Integer,Double>>>(set);
		
		//Sort the reviewSentimentMap on keys/values by using a comparator
		
		Collections.sort(list,new Comparator<Map.Entry<String, Entry<Integer,Double>>>(){
			
			public int compare(Map.Entry<String, Entry<Integer,Double>> o1,Map.Entry<String, Entry<Integer,Double>>o2){
				
				Map map1 =(Map)o1.getValue();
                Map map2=(Map)o2.getValue();
                
                Integer key1= (Integer)map1.keySet().iterator().next();
                Integer key2= (Integer)map2.keySet().iterator().next();
                
                Double value1=(Double)map1.get(key1);
                Double value2=(Double)map2.get(key2);
                
                // sort the list so that higher keys comes first
                if(key2 >key1){
                	return 1;
                }
                
                else if(key2<key1){
                	return -1;
                }
                
                //sort the list such that if two keys are equal then higher value comes ahead of lower one
                else if(key1==key2){
                	 
                	if(value2>value1){
                		return 1;
                	}
                	
                	else{
                		return -1;
                	}
                }
                
                else{
                	return 0;
                }
			}
			
		});
		
		
		
		
		return list;

		
	}

}
