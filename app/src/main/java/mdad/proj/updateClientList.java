package mdad.proj;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;

public class updateClientList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //ListView lvClientUpdate;
    //String[] client;

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String url_all_products = MainActivity.ipBaseAddress+"/get_all_clientsJson.php";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "client_id";
    private static final String TAG_NAME = "client_username";

    // products JSONArray
    JSONArray products = null;

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

        //lvClientUpdate = findViewById(R.id.lvClientUpdate);
        //client = new DateFormatSymbols().getMonths();
        //ArrayAdapter<String> clientAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, client);
        //lvClientUpdate.setAdapter(clientAdapter);
        //lvClientUpdate.setOnItemClickListener(this);

        //   Log.i("------url_all_products",url_all_products);
        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();
        // Loading products in Background Thread
        postData(url_all_products,null );
        // Get listview from list_items.xml
        //ListView lv = getListView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String client = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "Clicked: "+ client, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(), updateClient.class);
        startActivity(i);
    }
    public void postData(String url, final JSONObject json){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest json_obj_req = new JsonObjectRequest(
                Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                checkResponse(response, json);

//                String alert_message;
//                alert_message = response.toString();

//                showAlertDialogue("Response", alert_message);

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


    private void checkResponse(JSONObject response, JSONObject creds){
        try {
            if(response.getInt(TAG_SUCCESS)==1){

                // products found
                // Getting Array of Products
                products = response.getJSONArray(TAG_PRODUCTS);

                // looping through All Products
                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(TAG_PID);
                    String name = c.getString(TAG_NAME);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_PID, id);
                    map.put(TAG_NAME, name);

                    // adding HashList to ArrayList
                    productsList.add(map);
                }

                /**
                 * Updating parsed JSON data into ListView
                 * */
                ListAdapter adapter = new SimpleAdapter(
                        updateClientList.this, productsList,
                        R.layout.list_item, new String[] { TAG_PID,
                        TAG_NAME},
                        new int[] { R.id.pid, R.id.name });
                // updating listview
                //setListAdapter(adapter);

            }
            else{

            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }
}