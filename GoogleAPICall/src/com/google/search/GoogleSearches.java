package com.google.search;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GoogleSearches {
	final static String apiKey = "key";
	final static String customSearchEngineKey = "customSearchKey";
	final static String searchURL = "https://www.googleapis.com/customsearch/v1?";

	public static String search(String pUrl) {
		try {
	        URL url = new URL(pUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        int statusCode = connection.getResponseCode();
	        InputStream is;
	        if (statusCode != HttpURLConnection.HTTP_OK) {
	            is = connection.getErrorStream();
	        } else {
	            is = connection.getInputStream();
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        String line;
	        StringBuilder buffer = new StringBuilder();
	        while ((line = br.readLine()) != null) {
	            buffer.append(line);
	        }
	        return buffer.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
	private static String buildSearchString(String searchString, int start, int numOfResults) {
		String toSearch = searchURL + "key=" + apiKey + "&cx=" + customSearchEngineKey + "&q=";

		// replace spaces in the search query with +
		String newSearchString = searchString.replace(" ", "%20");

		toSearch += newSearchString;

		// specify response format as json
		toSearch += "&alt=json";

		// specify starting result number
		toSearch += "&start=" + start;

		// specify the number of results you need from the starting position
		toSearch += "&num=" + numOfResults;

		System.out.println("Seacrh URL: " + toSearch);
		return toSearch;
	}


	public static void main(String[] args) throws Exception {

		String url = buildSearchString("Himanshu", 1, 10);
		String result = search(url);
		System.out.println(result);

	}
}