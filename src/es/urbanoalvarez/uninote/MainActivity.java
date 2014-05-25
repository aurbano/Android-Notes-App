package es.urbanoalvarez.uninote;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.uninote.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getNotes();
	}
	
	@Override   
    protected void onResume() {
		super.onResume();
		getNotes();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			// Open the new note activity
			Intent newNoteIntent = new Intent(this, NewNoteActivity.class);
			startActivity(newNoteIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void getNotes(){
		final ListView listview = (ListView) findViewById(R.id.listview);

		String[] values = getApplicationContext().fileList();
		
		for (int i = 0; i < values.length; ++i){
			values[i] = values[i].substring(0, values[i].indexOf("."));
		}
		
		// Invierte el orden de los archivos, queda mejor que el ultimo creado este arriba
		List<String> list = Arrays.asList(values);
		Collections.reverse( Arrays.asList(values));
		values = (String[]) list.toArray();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		listview.setAdapter(adapter); 

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				String nota = listview.getItemAtPosition(position).toString();
				
				Intent noteActivity = new Intent(getApplicationContext(), NoteActivity.class);
				noteActivity.putExtra("name", nota);
				startActivity(noteActivity);
			}

		});
	}

}
