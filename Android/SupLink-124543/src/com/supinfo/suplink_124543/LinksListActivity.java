package com.supinfo.suplink_124543;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.supinfo.suplink_124543.rest.Request;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LinksListActivity extends Activity implements OnClickListener {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linkslist);
				
		try {
			final String userId = getIntent().getStringExtra("userid");

			Button button = (Button) findViewById(R.id.button);
			button.setOnClickListener(new OnClickListener() {                      
	            @Override
	            public void onClick(View arg0) {
	    			Intent intent = new Intent(LinksListActivity.this, AddActivity.class);
	    			intent.putExtra("userid", userId);  
	    			LinksListActivity.this.startActivity(intent);
	            }
	        });

			JSONObject result = Request.sendGetRequest("http://10.0.2.2:8080/SupLink/api/users/" + userId + "/links");
			TableLayout tl = (TableLayout)findViewById(R.id.Table);

            JSONArray shortlinks = new JSONArray();

			try{
	            shortlinks = result.getJSONArray("shortlink");
            } catch (JSONException e) {
                JSONObject res = result.getJSONObject("shortlink");
                shortlinks.put(res);
            } finally {
            }
			
            for (int i = 0; i < shortlinks.length(); i++) {
                TableRow tr = new TableRow(this);
                
                TextView name = new TextView(this);
                TextView originalurl = new TextView(this);
                TextView date = new TextView(this);
                
	            name.setText(shortlinks.getJSONObject(i).getString("name").toString());
	            originalurl.setText(shortlinks.getJSONObject(i).getString("url").toString());
	            date.setText(shortlinks.getJSONObject(i).getString("creationdate").toString());
	            final String shorturl = shortlinks.getJSONObject(i).getString("shorturl").toString();
	            	            	    		
	    		name.setPadding(10, 2, 10, 2);
	    		originalurl.setPadding(10, 2, 10, 2);
	    		date.setPadding(10, 2, 10, 2);
	            
	            tr.addView(name);
	            tr.addView(originalurl);
	            tr.addView(date);

	            tr.setOnClickListener(new OnClickListener() {                      
	                @Override
	                public void onClick(View arg0) {
	        			Intent intent = new Intent(LinksListActivity.this, StatisticsActivity.class);
	        			intent.putExtra("shorturl", shorturl);  
	        			intent.putExtra("userid", userId);  
	        			LinksListActivity.this.startActivity(intent);
	                }
	            });

	            tl.addView(tr);
            }
            
            Button logout = (Button) findViewById(R.id.button_logout);
            logout.setOnClickListener(new OnClickListener() {                      
	            @Override
	            public void onClick(View arg0) {
	    			Intent intent = new Intent(LinksListActivity.this, LoginActivity.class);
	    			LinksListActivity.this.startActivity(intent);
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
