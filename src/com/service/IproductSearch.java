package com.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public interface IproductSearch {
	
	
	public JSONArray searchProducts(String searchString);
	public JSONArray returnRecommendations(JSONArray productsArray);
	public ArrayList getProductReviews(JSONArray recommendations) ;

}
