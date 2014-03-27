package edu.sjsu.cmpe277.newsreader;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsItem {

	private String title;
	private URL newsDetailsLink;
	private String description;
	private Date pubDate;
	SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNewsDetailsLink() {
		return newsDetailsLink.toString();
	}
	public void setNewsDetailsLink(String newsDetailsLink) {
		try {
			this.newsDetailsLink = new URL(newsDetailsLink);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return formatter.format(this.pubDate);
	}
	public void setPubDate(String pubDate) {
		try {
			this.pubDate = formatter.parse(pubDate);
		} catch (ParseException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public Date getDate(){
		return pubDate;
	}
}
