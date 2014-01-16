package com.example.simpleui;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.PushService;

public class MainActivity extends Activity {

	private Button submitButton;
	private EditText inputEdit;
	private CheckBox isEncrypt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Parse.initialize(this, "hCJ3YM593qsoFGt3CNVa0XRECus3Vbrz56HdyUvD",
				"WRUIsQkcyj0fgv8inoF6hSeo0rftbr2WTKPWLE09");
		PushService.setDefaultPushCallback(this, MainActivity.class);

		setContentView(R.layout.activity_main);

		submitButton = (Button) findViewById(R.id.submitButton);
		inputEdit = (EditText) findViewById(R.id.input);
		isEncrypt = (CheckBox) findViewById(R.id.encrypt);

		submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("debug", "click");
				sendMessage();
			}
		});

		inputEdit.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						Log.d("debug", "enter");
						sendMessage();
						return true;
					}
				}
				return false;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void sendMessage() {
		Editable editable = inputEdit.getText();
		String text = editable.toString();
		if (isEncrypt.isChecked()) {
			text = "************";
		}
		editable.clear();
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
}
