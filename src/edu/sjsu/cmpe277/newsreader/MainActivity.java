package edu.sjsu.cmpe277.newsreader;

import java.util.ArrayList;
import java.util.List;

import android.R.layout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsreaderapp.R;

public class MainActivity extends Activity {

	private List<NewsItem> newsList = new ArrayList<NewsItem>();
//	private List<String> newsTitles = new ArrayList<String>();

	NewsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new URLParser().execute("http://rss.cnn.com/rss/cnn_us.rss");
		adapter = new NewsAdapter(this, R.layout.item, newsList);
		
		ListView listView = (ListView) findViewById(R.id.newsList);
		listView.setBackgroundColor(Color.BLACK);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String title = newsList.get(arg2).getTitle();
				Toast.makeText(getBaseContext(), "Loading news:"+title, Toast.LENGTH_LONG).show();
				Intent intent = new Intent(Intent.ACTION_VIEW, 
						Uri.parse(newsList.get(arg2).getNewsDetailsLink()));
				startActivity(intent); 		
			}
		});
		listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class URLParser extends AsyncTask<String, Integer, Void>
	{
		@Override
		protected Void doInBackground(String... params) {
			String url = params[0];
			SAXFeedParser feedParser = new SAXFeedParser(url);
			newsList = feedParser.parse();
			//adapter.notifyDataSetChanged();
			return null;
		}

		@SuppressLint("NewApi")
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter.clear();
			for(NewsItem item:newsList)	{
				adapter.add(item);
			}			
			adapter.notifyDataSetChanged();
		}
	}

	private class NewsAdapter extends ArrayAdapter<NewsItem>{

		private Context ctx;
        private List<NewsItem> newsItems;
		
		public NewsAdapter(Context context, int resource, List<NewsItem> objects) {
			super(context, resource, objects);
            this.ctx = context;
            this.newsItems = objects;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater linf = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = linf.inflate(R.layout.item, parent, false);
			TextView tt = (TextView) row.findViewById(R.id.toptext);
			TextView bt = (TextView) row.findViewById(R.id.bottomtext);

			NewsItem item = newsItems.get(position);
			if (item != null){
				tt.setText(item.getTitle());
				bt.setText(item.getPubDate());
			}
			return row;
		}
	}

}
