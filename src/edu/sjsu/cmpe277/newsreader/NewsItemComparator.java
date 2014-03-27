package edu.sjsu.cmpe277.newsreader;

import java.util.Comparator;

public class NewsItemComparator implements Comparator<NewsItem> {

	public int compare(NewsItem lhs, NewsItem rhs) {
		return rhs.getDate().compareTo(lhs.getDate());
	}

}
