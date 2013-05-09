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
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		
	  try {
		EditText email = (EditText) findViewById(R.id.editText_email);
		EditText password = (EditText) findViewById(R.id.editText_password);
	
		JSONObject obj = new JSONObject();
		obj.put("email", email.getText());
		obj.put("password", password.getText());
		
		JSONObject result = Request.sendPostRequest("http://10.0.2.2:8080/SupLink/api/users", obj);
		
		if (result != null){
			Intent intent = new Intent(LoginActivity.this, LinksListActivity.class);
			intent.putExtra("userid", result.getString("id").toString());  
			LoginActivity.this.startActivity(intent);
		}
		else{
			TextView wrongAuth = (TextView) findViewById(R.id.textView_wrongauth);
			wrongAuth.setVisibility(0);
		}
		
	  } catch (Exception e) {
	    Log.e("erreur", e.getMessage(), e);
	  }

	}
	
	public void onBackPressed() {
	}

}
