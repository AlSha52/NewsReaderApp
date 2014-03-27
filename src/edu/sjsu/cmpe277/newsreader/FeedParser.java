package edu.sjsu.cmpe277.newsreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class FeedParser {

	//xml tag names
	public static final String PUB_DATE = "pubDate";
	static final  String DESCRIPTION = "description";
	static final  String LINK = "link";
	static final  String TITLE = "title";
	static final  String ITEM = "item";
	
	URL feedURL;
	
	public FeedParser(String feedURL) {
		
		try {
			this.feedURL = new URL(feedURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e); 		
		}
	}
	
	
	public InputStream getInputStream()
	{
		try {
			return feedURL.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
