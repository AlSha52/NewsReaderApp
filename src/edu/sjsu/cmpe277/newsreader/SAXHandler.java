package edu.sjsu.cmpe277.newsreader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

	private List<NewsItem> newsItemList;
	private Set<NewsItem> newsSet = new TreeSet<NewsItem>(new NewsItemComparator());
	private NewsItem currentNewsItem;
	private StringBuilder builder;

	public List<NewsItem> getNews()
	{
		if (newsItemList == null)
			newsItemList = new ArrayList<NewsItem>(newsSet);
		return this.newsItemList;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		super.endElement(uri, localName, name);
		if (this.currentNewsItem != null){
			if (localName.equalsIgnoreCase(FeedParser.TITLE)){
				currentNewsItem.setTitle(builder.toString());
			} else if (localName.equalsIgnoreCase(FeedParser.LINK)){
				currentNewsItem.setNewsDetailsLink(builder.toString());
			} else if (localName.equalsIgnoreCase(FeedParser.DESCRIPTION)){
				currentNewsItem.setDescription(builder.toString());
			} else if (localName.equalsIgnoreCase(FeedParser.PUB_DATE)){
				currentNewsItem.setPubDate(builder.toString());
			} else if (localName.equalsIgnoreCase(FeedParser.ITEM)){
	//			newsItemList.add(currentNewsItem);
				newsSet.add(currentNewsItem);
			}
			builder.setLength(0);    
		}
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		//newsItemList = new ArrayList<NewsItem>();
		builder = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		if (localName.equalsIgnoreCase(FeedParser.ITEM)){
			this.currentNewsItem = new NewsItem();
			builder.setLength(0);
		}
	}
}
