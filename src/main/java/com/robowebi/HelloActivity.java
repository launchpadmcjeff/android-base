package com.robowebi;

import org.joda.time.LocalTime;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.robowebi.R;

public class HelloActivity extends Activity {

	private static final String tag = "HelloActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello_layout);
		Log.d(tag, "onCreate");
	}

	@Override
	public void onStart() {
		super.onStart();
		LocalTime currentTime = new LocalTime();
		TextView textView = (TextView) findViewById(R.id.text_view);
		textView.setText("The current local time is: " + currentTime);

		// The connection URL
		String url = "https://ajax.googleapis.com/ajax/"
				+ "services/search/web?v=1.0&q={query}";

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		// Add the String message converter
		restTemplate.getMessageConverters().add(
				new StringHttpMessageConverter());

		// Make the HTTP GET request, marshaling the response to a String
		String result = restTemplate.getForObject(url, String.class, "Android");

		Log.d(tag, result);
	}

}
