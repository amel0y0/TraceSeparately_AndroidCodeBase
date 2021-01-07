package mdad.proj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormatSymbols;

public class deleteClient extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lvClient;
    String[] client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_client);

        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        lvClient = findViewById(R.id.lvClient);
        client = new DateFormatSymbols().getMonths();
        ArrayAdapter<String> clientAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, client);
        lvClient.setAdapter(clientAdapter);
        lvClient.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String client = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "Clicked: "+ client, Toast.LENGTH_SHORT).show();
    }
}