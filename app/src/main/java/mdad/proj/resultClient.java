package mdad.proj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;

public class resultClient extends AppCompatActivity {

    private ListView lvUpdate;

    //String[] client;

    ArrayList<HashMap<String, String>> updateList;

    // url to get all products list
    private static String url_all_products = MainActivity.ipBaseAddress1+"/get_update_detailsJson.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "updates";
    private static final String TAG_PID = "updates_id";
    private static final String TAG_NAME = "user_id";
    private static final String TAG_DATE = "last_updated";
    private static final String TAG_STATUS = "status";

    // products JSONArray
    JSONArray products = null;

    String nameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_client);

        // my_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        //RECEIVE DATA VIA INTENT
        Intent i = getIntent();
        String name = i.getStringExtra("NAME_KEY");
        //SET DATA TO TEXTVIEWS
        nameTxt = name;
        Toast.makeText(getApplicationContext(), "Check: "+ nameTxt, Toast.LENGTH_SHORT).show();

        updateList = new ArrayList<HashMap<String, String>>();
        // Loading products in Background Thread
        //postData(url_all_products,null );
        lvUpdate = (ListView) findViewById(R.id.updateList);

        // Getting complete product details in background thread

        JSONObject dataJson = new JSONObject();
        try {
            dataJson.put("user_id", nameTxt);
            //     dataJson.put("password", "def");

        } catch (JSONException e) {

        }
        postData(url_all_products, dataJson, 1);
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

            // products found
            // Getting Array of Products
            products = response.getJSONArray(TAG_PRODUCTS);

            // looping through All Products
            for (int i = 0; i < products.length(); i++) {
                JSONObject c = products.getJSONObject(i);

                // Storing each json item in variable
                String id = c.getString(TAG_PID);
                String name = c.getString(TAG_NAME);
                String date = c.getString(TAG_DATE);
                String status = c.getString(TAG_STATUS);

                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                map.put(TAG_PID, id);
                map.put(TAG_NAME, name);
                map.put(TAG_DATE, date);
                map.put(TAG_STATUS, status);

                // adding HashList to ArrayList
                updateList.add(map);
            }

            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    resultClient.this, updateList,
                    R.layout.list_search, new String[] { TAG_PID,
                    TAG_DATE, TAG_STATUS},
                    new int[] { R.id.pid, R.id.name, R.id.status });
            // updating listview
            lvUpdate.setAdapter(adapter);

            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();


        } catch (JSONException e) {
            e.printStackTrace();

        }


    }
}