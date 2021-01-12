package mdad.proj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormatSymbols;

public class updateClientList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lvClientUpdate;
    String[] client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client_list);

        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        lvClientUpdate = findViewById(R.id.lvClientUpdate);
        client = new DateFormatSymbols().getMonths();
        ArrayAdapter<String> clientAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, client);
        lvClientUpdate.setAdapter(clientAdapter);
        lvClientUpdate.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String client = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "Clicked: "+ client, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(), updateClient.class);
        startActivity(i);
    }
}