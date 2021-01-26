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
        lvUpdate = (ListView) findViewById(R.id.productsList);
    }


}