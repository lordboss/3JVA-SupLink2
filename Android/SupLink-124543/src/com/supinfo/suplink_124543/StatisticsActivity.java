package com.supinfo.suplink_124543;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.suplink_124543.rest.Request;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class StatisticsActivity extends Activity implements OnClickListener{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		final String shorturl = getIntent().getStringExtra("shorturl");
		final String userId = getIntent().getStringExtra("userid");
		
		try {
			final JSONObject result = Request.sendGetRequest("http://10.0.2.2:8080/SupLink/api/users/" + userId + "/links/" + shorturl);
			
			TableRow trName = (TableRow)findViewById(R.id.RowName);
	        TextView name = new TextView(this);
	        name.setText(result.getString("name"));
	        trName.addView(name);
	        
			TableRow trOriginalURL = (TableRow)findViewById(R.id.RowOriginalURL);
	        TextView originalURL = new TextView(this);
	        originalURL.setText(result.getString("url"));
	        trOriginalURL.addView(originalURL);

			TableRow trShortenedURL = (TableRow)findViewById(R.id.RowShortenedURL);
	        TextView shortenedURL = new TextView(this);
	        shortenedURL.setText("http://suplink.com/" + result.getString("shorturl"));
	        trShortenedURL.addView(shortenedURL);
	        
			TableRow trNbClicks = (TableRow)findViewById(R.id.RowNbClicks);
	        TextView nbClicks = new TextView(this);
	        nbClicks.setText(result.getString("nbclicks"));
	        trNbClicks.addView(nbClicks);


			TextView webLink = (TextView)findViewById(R.id.webLink);
			webLink.setOnClickListener(new OnClickListener() {                      
                @Override
                public void onClick(View arg0) {
                	Intent browserIntent;
					browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10.0.2.2:8080/SupLink/link/" + shorturl));
					startActivity(browserIntent);
                }
            });
			
			final Button GoToButton = (Button) findViewById(R.id.goTo);
			GoToButton.setText("Go to " + name.getText());
			GoToButton.setOnClickListener(new OnClickListener() {                      
                @Override
                public void onClick(View arg0) {					
                	Intent browserIntent;
					browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10.0.2.2:8080/SupLink/" + shorturl));
					startActivity(browserIntent);
                }
            });

			final ToggleButton DisableButton = (ToggleButton) findViewById(R.id.disable);
			
			String currentStatus = result.getString("enabled");
			if(currentStatus.equals("1")){
				DisableButton.toggle();
			}
			
			DisableButton.setOnClickListener(new OnClickListener() {
				
				public void onClick(View arg0) {
					
					try {
	                	JSONObject obj = new JSONObject();
	                	
	                	if(!DisableButton.isChecked()){	
							obj.put("enabled", 1);
						}
						else{
							obj.put("enabled", 0);
						}
	       
						Request.sendPutRequest("http://10.0.2.2:8080/SupLink/api/users/" + userId + "/links/" + result.getString("id"), obj);	
					
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
                
			});			
			
			final Button RemoveButton = (Button) findViewById(R.id.remove);
			RemoveButton.setOnClickListener(new OnClickListener() {                      
                @Override
                public void onClick(View arg0) {
					Toast.makeText(StatisticsActivity.this, "Link removed !", Toast.LENGTH_LONG).show();

					try {
						Request.sendDeleteRequest("http://10.0.2.2:8080/SupLink/api/users/" + userId + "/links/" + result.getString("id"));
						Intent intent = new Intent(StatisticsActivity.this, LinksListActivity.class);
						intent.putExtra("userid", userId);  
						StatisticsActivity.this.startActivity(intent);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            });

            			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

}
