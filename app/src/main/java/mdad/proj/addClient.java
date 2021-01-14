package mdad.proj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class addClient extends AppCompatActivity {

    private EditText txtAddUsername, txtAddPassword, txtClientName, txtNRIC, txtMobileNo, txtClientAddress, txtDOB, txtClientAdmin, txtInCharge;
    private Button btnAdd;
    String  username, password, clientName, nric, mobileNo, clientAddress, clientAdmin, dob, in_charge;

    // url to create new product

    private static String url_create_product =MainActivity.ipBaseAddress+"register_clientJson.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    private static final String TAG_USERNAME = "username";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_CLIENTNAME = "name";
    private static final String TAG_NRIC = "nric_number";
    private static final String TAG_MOBILENUMBER = "phone_number";
    private static final String TAG_CLIENTADDRESS = "address";
    private static final String TAG_DOB = "date_of_birth";
    private static final String TAG_CLIENTADMIN = "client_admin";
    private static final String TAG_INCHARGE = "in_charge";


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

        txtAddUsername=(EditText) findViewById(R.id.txtAddUsername);
        txtAddPassword=(EditText) findViewById(R.id.txtAddPassword);
        txtClientName=(EditText) findViewById(R.id.txtClientName);
        txtNRIC=(EditText) findViewById(R.id.txtNRIC);
        txtMobileNo= (EditText) findViewById(R.id.txtMobileNo);
        txtClientAddress = (EditText) findViewById(R.id.txtClientAddress);
        txtDOB=(EditText) findViewById(R.id.txtDOB);
        txtClientAdmin=(EditText) findViewById(R.id.txtClientAdmin);
        txtInCharge=(EditText) findViewById(R.id.txtInCharge);

        btnAdd=(Button) findViewById(R.id.btnAddClient);

        btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    username = txtAddUsername.getText().toString();
                    password = txtAddPassword.getText().toString();
                    clientName = txtClientName.getText().toString();
                    nric = txtNRIC.getText().toString();
                    mobileNo = txtMobileNo.getText().toString();
                    clientAddress = txtClientAddress.getText().toString();
                    dob = txtDOB.getText().toString();
                    clientAdmin = txtClientAdmin.getText().toString();
                    in_charge = txtInCharge.getText().toString();


                    /**
                     *  HERE IS WHERE U PUT ALL THE ADDING OF THE CODE LMAO FOR THE DATABASE
                     */
                    JSONObject dataJson = new JSONObject();
                    try{
                        dataJson.put(TAG_USERNAME, username);
                        dataJson.put(TAG_PASSWORD, password);
                        dataJson.put(TAG_CLIENTNAME, clientName);
                        dataJson.put(TAG_NRIC, nric);
                        dataJson.put(TAG_MOBILENUMBER, mobileNo);
                        dataJson.put(TAG_CLIENTADDRESS, clientAddress);
                        dataJson.put(TAG_DOB, dob);
                        dataJson.put(TAG_CLIENTADMIN, clientAdmin);
                        dataJson.put(TAG_INCHARGE, in_charge);



                    }catch(JSONException e){

                    }

                    postData(url_create_product,dataJson,1 );
                }
            });
    }
    public void postData(String url, final JSONObject json, final int option){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest json_obj_req = new JsonObjectRequest(
                Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                switch (option){
                    case 1:checkResponseCreate_Product(response); break;

                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
//                String alert_message;
//                alert_message = error.toString();
//                showAlertDialogue("Error", alert_message);
            }

        });
        requestQueue.add(json_obj_req);
    }


    public void checkResponseCreate_Product(JSONObject response)
    {
        Log.i("----Response", response+" ");
        try {
            if(response.getInt(TAG_SUCCESS)==1){
                finish();
                Intent i = new Intent(this, addClient.class);
                startActivity(i);


            }else{
                // product with pid not found
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }
}