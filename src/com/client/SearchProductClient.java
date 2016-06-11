package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.service.IOrderProductsByReviewSentiments;
import com.service.IproductSearch;
import com.service.OrderProductsByReviewSentimentsImpl;
import com.service.ProductSearchImpl;

public class SearchProductClient {

	public static void main(String[] args) throws IOException  {
		
		
		System.out.println("Enter the product you want to search");
		BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
		String searchText= br1.readLine();
		
		//replacing empty spaces in search string with %20 so that url doesn't error out when there are spaces
		String formattedText=searchText.replaceAll("\\s+","%20");
		
		//Searching for products related to search string inputed by user
		IproductSearch search= new ProductSearchImpl();
		IOrderProductsByReviewSentiments sen= new OrderProductsByReviewSentimentsImpl();
		JSONArray products= search.searchProducts(formattedText);
		//System.out.println("products is:" +products);
		
	    //Using the first returned product (item id) for getting product recommendations
		JSONArray recommendations= search.returnRecommendations(products);
	//	System.out.println("Recommendations are:" +recommendations);
		
	
		
		if(recommendations.length()==0){
			System.out.println("product list is:" +products);
		}
		
		
		else {
			//Find the list of reviews
			ArrayList reviews= search.getProductReviews(recommendations);
		//	System.out.println("Reviews are :" +reviews);
			
			//Group the products according to reviewSentiments
			Map<String,Map<Integer,Double>> reviewSentiMentMap= sen.groupProductsByReviews(reviews);
		//	System.out.println("Review Sentiment map is:" +reviewSentiMentMap);
			
			//Get the list of products for end user sorted by review sentiments
			List sortedProductList=sen.orderProductsByReviews(reviewSentiMentMap);
			System.out.println("Sorted product list is:" +sortedProductList);
			
		}
		
		System.out.println("done..");
		

	}

}
