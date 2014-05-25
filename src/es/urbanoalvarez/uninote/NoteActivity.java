package es.urbanoalvarez.uninote;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.uninote.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends Activity {

	private String name;
	private TextView content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);

		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		setTitle(name);

		content = (TextView) findViewById(R.id.nota);

		// Get content from text file
		read(name);
	}

	private void read(String name) {
		try {

			InputStream in = openFileInput(name+".txt");

			if (in != null) {

				InputStreamReader tmp = new InputStreamReader(in);

				BufferedReader reader = new BufferedReader(tmp);

				String str;

				StringBuilder buf = new StringBuilder();

				while ((str = reader.readLine()) != null) {

					buf.append(str);

				}

				in.close();

				content.setText(buf.toString());

			}

		}catch (java.io.FileNotFoundException e) {

			Toast.makeText(this, "La nota "+name+" no existe!", Toast.LENGTH_LONG).show();

		}catch (Throwable t) {

			Toast.makeText(this, "Excepcion: " + t.toString(), Toast.LENGTH_LONG).show();

		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
			case R.id.action_back:
				// Back to main
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
