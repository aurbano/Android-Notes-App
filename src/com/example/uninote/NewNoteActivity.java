package com.example.uninote;

import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteActivity extends Activity {
	
	private EditText title;
	private EditText content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_note);
		
		title=(EditText)findViewById(R.id.newnote_title);
		content=(EditText)findViewById(R.id.newnote_content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.newnote, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
			case R.id.action_save:
				// Save the note
				if(save()){
					finish();
				}
				break;
			case R.id.action_cancel:
				// Back to main
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean save() {
		try {
			OutputStreamWriter out = new OutputStreamWriter(openFileOutput(title.getText().toString()+".txt", 0));
			out.write(content.getText().toString());
			out.close();

			Toast.makeText(this, "Nota guardada!", Toast.LENGTH_LONG) .show();
			return true;
		}catch (Throwable t) {
			Toast.makeText(this, "Excepcion: " + t.toString(), Toast.LENGTH_LONG).show();
			return false;
		}
	}
}
