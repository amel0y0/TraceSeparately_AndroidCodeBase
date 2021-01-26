package mdad.proj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class updateClient extends AppCompatActivity {

    TextView txtUsername;
    EditText txtName, txtPassword, txtNric, txtPhone, txtAddress, txtDate, txtClientAdminUpdate, txtInChargeUpdate;
    Button btnUpdateClient;
    // Response
    String responseServer;

    private static String client_username = "";
    private static String client_password = "";
    private static String client_name = "";
    private static String client_nric = "";
    private static String client_phone = "";
    private static String client_address = "";
    private static String client_date = "";
    private static String client_admin = "";
    private static String client_incharge = "";

    JSONObject json = null;

    String pid;
    static InputStream is = null;

    // single product url
    private static final String url_product_details = MainActivity.ipBaseAddress1 + "/get_client_detailsJson.php";
    // url to update product
    private static final String url_update_product = MainActivity.ipBaseAddress1 + "/update_productJson.php";

    // 152.226.144.250
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCT = "users";
    private static final String TAG_PID = "user_id";

    private static final String TAG_USERNAME = "username";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_NAME = "name";
    private static final String TAG_NRICNUMBER = "nric_number";
    private static final String TAG_PHONENUMBER = "phone_number";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_DATE = "date_of_birth";
    private static final String TAG_CLIENTADMIN = "client_admin";
    private static final String TAG_INCHARGE = "in_charge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client);

        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        // save button
        btnUpdateClient = (Button) findViewById(R.id.btnUpdateClient);

        // getting product details from intent
        Intent i = getIntent();
        // getting product id (pid) from intent
        pid = i.getStringExtra(TAG_PID);

        //  Log.i("----------Extra:::",pid);


        // Getting complete product details in background thread

        JSONObject dataJson = new JSONObject();
        try {
            dataJson.put("user_id", pid);
            //     dataJson.put("password", "def");

        } catch (JSONException e) {

        }

        postData(url_product_details, dataJson, 1);

        //Toast.makeText(getApplicationContext(), "This is the pid: " + pid, Toast.LENGTH_SHORT).show();

        btnUpdateClient.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // getting updated data from EditTexts
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                String name = txtName.getText().toString();
                String nric = txtNric.getText().toString();
                String phone = txtPhone.getText().toString();
                String address = txtAddress.getText().toString();
                String dob = txtDate.getText().toString();
                String client_admin = txtClientAdminUpdate.getText().toString();
                String in_charge = txtInChargeUpdate.getText().toString();


                // starting background task to update product
                JSONObject dataJson = new JSONObject();
                try {
                    dataJson.put("user_id", pid);
                    dataJson.put(TAG_USERNAME, username);
                    dataJson.put(TAG_PASSWORD, password);
                    dataJson.put(TAG_NAME, name);
                    dataJson.put(TAG_NRICNUMBER, nric);
                    dataJson.put(TAG_PHONENUMBER, phone);
                    dataJson.put(TAG_ADDRESS, address);
                    dataJson.put(TAG_DATE, dob);
                    dataJson.put(TAG_CLIENTADMIN, client_admin);
                    dataJson.put(TAG_INCHARGE, in_charge);


                } catch (JSONException e) {

                }

                postData(url_update_product, dataJson, 1);

            }
        });

    }


    public void postData(String url, final JSONObject json, final int option){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest json_obj_req = new JsonObjectRequest(
                Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "This works", Toast.LENGTH_SHORT).show();

                switch (option){
                    case 1:checkResponseEditProduct(response);

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


    public void checkResponseEditProduct(JSONObject response) {
        try {

                // successfully received product details
                JSONArray productObj = response.getJSONArray(TAG_PRODUCT); // JSON Array
                // get first product object from JSON Array
                JSONObject users = productObj.getJSONObject(0);
                client_username = users.getString(TAG_USERNAME);
                client_password = users.getString(TAG_PASSWORD);
                client_name = users.getString(TAG_NAME);
                client_nric = users.getString(TAG_NRICNUMBER);
                client_phone = users.getString(TAG_PHONENUMBER);
                client_address = users.getString(TAG_ADDRESS);
                client_date = users.getString(TAG_DATE);
                client_admin = users.getString(TAG_CLIENTADMIN);
                client_incharge = users.getString(TAG_INCHARGE);

//                Log.i("---Prod details",prodName+"  "+prodPrice+"  "+prodDesc);
                txtUsername = (TextView) findViewById(R.id.txtUsername);
                txtPassword = (EditText) findViewById(R.id.txtPassword);
                txtName = (EditText) findViewById(R.id.txtName);
                txtNric = (EditText) findViewById(R.id.txtNric);
                txtPhone = (EditText) findViewById(R.id.txtPhone);
                txtAddress = (EditText) findViewById(R.id.txtAddress);
                txtDate = (EditText) findViewById(R.id.txtDate);
                txtClientAdminUpdate = (EditText) findViewById(R.id.txtClientAdminUpdate);
                txtInChargeUpdate = (EditText) findViewById(R.id.txtInChargeUpdate);

                // display product data in EditText
                txtUsername.setText(client_username);
                txtPassword.setText(client_password);
                txtName.setText(client_name);
                txtNric.setText(client_nric);
                txtPhone.setText(client_phone);
                txtAddress.setText(client_address);
                txtDate.setText(client_date);
                txtClientAdminUpdate.setText(client_admin);
                txtInChargeUpdate.setText(client_incharge);

                Toast.makeText(getApplicationContext(), "Value is "+client_username, Toast.LENGTH_LONG).show();


        } catch (JSONException e) {
            e.printStackTrace();

        }


    }
}