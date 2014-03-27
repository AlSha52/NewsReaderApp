package edu.sjsu.cmpe277.newsreader;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SAXFeedParser extends FeedParser{

	public SAXFeedParser(String feedURL) {
		super(feedURL);	
	}

	public List<NewsItem> parse()
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();	
		try {
			SAXParser parser = factory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse(this.getInputStream(), handler);
			return handler.getNews();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
