package com.supinfo.suplink_124543;

import org.json.JSONObject;

import com.supinfo.suplink_124543.rest.Request;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity implements OnClickListener{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		
	  try {  
		final String userId = getIntent().getStringExtra("userid");

		EditText name = (EditText) findViewById(R.id.editText_name);
		EditText url = (EditText) findViewById(R.id.editText_url);
	
		JSONObject obj = new JSONObject();
		obj.put("name", name.getText());
		obj.put("url", url.getText());
	
		JSONObject result = Request.sendPostRequest("http://10.0.2.2:8080/SupLink/api/users/" + userId + "/links", obj);
		
		if (result != null){
			Toast.makeText(this, "New link added !", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(AddActivity.this, LinksListActivity.class);
			intent.putExtra("userid", userId);  
			AddActivity.this.startActivity(intent);
		}
		
	  } catch (Exception e) {
	    Log.e("erreur", e.getMessage(), e);
	  }

	}

}
