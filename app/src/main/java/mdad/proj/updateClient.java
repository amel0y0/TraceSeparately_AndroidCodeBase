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

    EditText txtName;
    EditText txtPrice;
    EditText txtDesc;
    EditText txtCreatedAt;
    Button btnUpdateClient;
    // Response
    String responseServer;

    JSONObject json=null;

    String pid;
    static InputStream is = null;
    // Progress Dialog
    private ProgressDialog pDialog;



    // single product url
    private static final String url_product_details = MainActivity.ipBaseAddress+"/get_product_detailsJson.php";

    // url to update product
    private static final String url_update_product = MainActivity.ipBaseAddress+"/update_productJson.php";

    // url to delete product
    private static final String url_delete_product = MainActivity.ipBaseAddress+"/delete_productJson.php";
    // 152.226.144.250
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCT = "product";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";
    private static final String TAG_PRICE = "price";
    private static final String TAG_DESCRIPTION = "description";

    private static String prodName="";
    private static String prodPrice="";
    private static String prodDesc="";

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
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        // getting product details from intent
        Intent i = getIntent();

        // getting product id (pid) from intent
        pid = i.getStringExtra(TAG_PID);

        //  Log.i("----------Extra:::",pid);



        // Getting complete product details in background thread

        JSONObject dataJson = new JSONObject();
        try{
            dataJson.put("pid", pid);
            //     dataJson.put("password", "def");

        }catch(JSONException e){

        }

        postData(url_product_details,dataJson,1 );

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // getting updated data from EditTexts
                String name = txtName.getText().toString();
                String price = txtPrice.getText().toString();
                String description = txtDesc.getText().toString();


                pDialog.setMessage("Saving product ...");
                pDialog.show();

                // starting background task to update product
                JSONObject dataJson = new JSONObject();
                try{
                    dataJson.put("pid", pid);
                    dataJson.put(TAG_NAME, name);
                    dataJson.put(TAG_PRICE, price);
                    dataJson.put(TAG_DESCRIPTION, description);


                }catch(JSONException e){

                }

                postData(url_update_product,dataJson,1 );

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
                    case 1:checkResponseEditProduct(response); break;
                    case 2:checkResponseSave_delete_Product(response); break;

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




    public void checkResponseSave_delete_Product(JSONObject response)
    {

        try {

            // dismiss the dialog once product updated
            pDialog.dismiss();
            if(response.getInt("success")==1){
                // successfully updated
                Intent i = getIntent();
                // send result code 100 to notify about product update
                setResult(100, i);
                finish();

            }else{
                //Error Response from server
                Toast.makeText(getApplicationContext(),"Error in deleting...", Toast.LENGTH_LONG).show();

            }

        } catch (JSONException e) {

            e.printStackTrace();

        }


    }


    public void checkResponseEditProduct(JSONObject response)
    {
        try {
            pDialog.dismiss();

            if(response.getInt("success")==1){
                // successfully received product details
                JSONArray productObj = response.getJSONArray(TAG_PRODUCT); // JSON Array
                // get first product object from JSON Array
                JSONObject product = productObj.getJSONObject(0);
                prodName=product.getString(TAG_NAME);
                prodPrice=product.getString(TAG_PRICE);
                prodDesc=product.getString(TAG_DESCRIPTION);

//                Log.i("---Prod details",prodName+"  "+prodPrice+"  "+prodDesc);
                txtName = (EditText) findViewById(R.id.inputName);
                txtPrice = (EditText) findViewById(R.id.inputPrice);
                txtDesc = (EditText) findViewById(R.id.inputDesc);

                // display product data in EditText
                txtName.setText(prodName);
                txtPrice.setText(prodPrice);
                txtDesc.setText(prodDesc);



            }else{
                //Error Response from server
                Toast.makeText(getApplicationContext(),"Error in Editing...", Toast.LENGTH_LONG).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }


    }
}