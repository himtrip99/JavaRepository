package com.google.search;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.Gson;


public class GoogleSearch {

	public static void main(String[] args) throws Exception {
	    String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	    String search = "Edinburgh";
	    String charset = "UTF-8";

	    URL url = new URL(google + URLEncoder.encode(search, charset));
	    Reader reader = new InputStreamReader(url.openStream(), charset);
	    GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

	    // Show title and URL of 1st result.
	    System.out.println(results.getResponseData().getResults().get(0).getTitle());
	    System.out.println(results.getResponseData().getResults().get(0).getUrl());
	}
	}

