package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public interface IOrderProductsByReviewSentiments {

	public Map<String,Map<Integer,Double>> groupProductsByReviews(ArrayList<String> reviews);
	public List<Entry<String,Entry<Integer,Double>>> orderProductsByReviews(Map<String,Map<Integer,Double>> reviewSentiments);
}
