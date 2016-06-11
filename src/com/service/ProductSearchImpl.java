package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.connection.Connection;

public class ProductSearchImpl implements IproductSearch{
	
	//get the product list
	public JSONArray searchProducts(String searchString) {
		
		JSONArray arr=new JSONArray();
		//Connect to the walmart Search API
		HttpURLConnection conn=null;
		try {
			URL url= new URL("http://api.walmartlabs.com/v1/search?query="+searchString+"&format=json&apiKey=tbbdx2p2s6yhefwxmyd66neg");			
			String output=Connection.getInstance().queryWalmartApis(url);
			//Converting String to JSON Object
			JSONObject obj= new JSONObject(output);
		    arr= obj.has("items")?obj.getJSONArray("items"):arr;			
		} 
		
		catch ( IOException|JSONException  ex) {		
			ex.printStackTrace();
		} 
				
	 return arr;
	
	}
		
	
	//return recommendations.. Assumption(If first element in product array returns error then search for second element in product array)
	
	
	public JSONArray returnRecommendations(JSONArray productsArray) {
		
		JSONArray recommArray=new JSONArray();
		String result=null;
		String itemId=null;
		String recomm=null;
		
		for(int i=0;i<productsArray.length();i++){
			
			try {
			    result=(productsArray.get(i)!=null)?productsArray.getString(i):"";
				JSONObject obj1=new JSONObject(result);
			    itemId=(obj1.has("itemId") && (obj1.get("itemId")!=null))?obj1.get("itemId").toString():"";
			    URL url= new URL("http://api.walmartlabs.com/v1/nbp?apiKey=tbbdx2p2s6yhefwxmyd66neg&itemId="+itemId);
		
			    recomm=Connection.getInstance().queryWalmartApis(url);	
			  //  System.out.println("recomm is:" +recomm);
			    
			    
			    //if recommendations doesnt contain error return the recommendation else continue to loop
			    if(! recomm.contains("errors")){
			    	recommArray=new JSONArray(recomm);
				    break;
				}
			
			    
				
			} catch (IOException|JSONException  ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
			finally{
				//conn1.disconnect();
			}
			
		}
		
		
		return recommArray;
		
	}
	
	
	
	
	
	//get product reviews
	public ArrayList getProductReviews(JSONArray recommendations) {
		
		ArrayList<String> reviews= new ArrayList<String>();
		JSONObject obj=new JSONObject();
		String itemIdForReviewId="";
		
	try{
		
		for(int i=0;i<recommendations.length();i++){
			
			//	obj=new JSONObject(recommendations.get(i).toString());
			    obj=recommendations.getJSONObject(i);
				itemIdForReviewId= obj.get("itemId").toString();
				reviews.add(getReviewsForItemIds(itemIdForReviewId));
							
		}
		
	}
	
	catch(JSONException ex){
		ex.printStackTrace();
	}
		
				
		//System.out.println("Reviews is:" +reviews);
		
		return reviews;
	}
	
  
	//get Reviews for Item Ids..
	public String getReviewsForItemIds(String itemId){
		
		String result="";
		HttpURLConnection conn=null;
		
		try {
		 URL url= new URL("http://api.walmartlabs.com/v1/reviews/"+itemId+"?format=json&apiKey=tbbdx2p2s6yhefwxmyd66neg");	
		 result=Connection.getInstance().queryWalmartApis(url);
		    			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return result;
	}

}
