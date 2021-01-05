package mdad.proj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addClient extends AppCompatActivity {

    private EditText txtMobileNo, txtClientName, txtClientAddress;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        txtMobileNo= (EditText) findViewById(R.id.txtMobileNo);
        txtClientAddress = (EditText) findViewById(R.id.txtClientAddress);
        txtClientName=(EditText) findViewById(R.id.txtClientName);

        btnAdd=(Button) findViewById(R.id.btnAddClient);

        btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mobileNo = txtMobileNo.getText().toString();
                    String clientAddress = txtClientAddress.getText().toString();
                    String clientName = txtClientName.getText().toString();

                    /**
                     *  HERE IS WHERE U PUT ALL THE ADDING OF THE CODE LMAO FOR THE DATABASE
                     */
                }
            });
    }
}