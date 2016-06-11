package com.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

// Connect to walmart apis to get the result.
	
	
    private static Connection instance = new Connection();
    
    private Connection(){};
    
    public static Connection getInstance(){
    	return instance;
    }
	
	public String queryWalmartApis(URL url){
		
		String output="";
		HttpURLConnection conn=null;
		
		try{
			
			conn= (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br= new BufferedReader(new InputStreamReader(conn.getInputStream()));
			//Output from server
			 output=br.readLine();
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			conn.disconnect();
		}
		
		return output;
	}
	
}
