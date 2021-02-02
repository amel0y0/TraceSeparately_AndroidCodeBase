package mdad.proj;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link clientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class clientFragment extends Fragment {

    private ListView lvUpdate;

    //String[] client;

    ArrayList<HashMap<String, String>> mainList;

    // For Exel: modify the php file name to the new one created
    private static String url_all_products = MainActivity.ipBaseAddress+"/get_update_detailsJson.php";
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public clientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExampleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static clientFragment newInstance(String param1, String param2) {
        clientFragment fragment = new clientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_client, container, false);

        //RECEIVE DATA VIA INTENT
//        Intent i = getIntent();
//        String name = i.getStringExtra("NAME_KEY");
//        //SET DATA TO TEXTVIEWS
//        nameTxt = name;
//        Toast.makeText(getApplicationContext(), "Check: "+ nameTxt, Toast.LENGTH_SHORT).show();
//
//        mainList = new ArrayList<HashMap<String, String>>();
//        // Loading products in Background Thread
//        //postData(url_all_products,null );
//        lvUpdate = (ListView) findViewById(R.id.list_main);
//
//        JSONObject dataJson = new JSONObject();
//        try {
//            dataJson.put("user_id", nameTxt);
//            //     dataJson.put("password", "def");
//
//        } catch (JSONException e) {
//
//        }
//        postData(url_all_products, dataJson, 1);
//
        return view;
   }
//    public void postData(String url, final JSONObject json, final int option){
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest json_obj_req = new JsonObjectRequest(
//                Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(getApplicationContext(), "This works", Toast.LENGTH_SHORT).show();
//
//                switch (option){
//                    case 1:checkResponseEditProduct(response);
//
//                }
//
//            }
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
////                String alert_message;
////                alert_message = error.toString();
////                showAlertDialogue("Error", alert_message);
//            }
//
//        });
//        requestQueue.add(json_obj_req);
//    }
//
//
//    public void checkResponseEditProduct(JSONObject response) {
//        try {
//
//            // products found
//            // Getting Array of Products
//            products = response.getJSONArray(TAG_PRODUCTS);
//
//            // looping through All Products
//            for (int i = 0; i < products.length(); i++) {
//                JSONObject c = products.getJSONObject(i);
//
//                // Storing each json item in variable
//                String id = c.getString(TAG_PID);
//                String name = c.getString(TAG_NAME);
//                String date = c.getString(TAG_DATE);
//                String status = c.getString(TAG_STATUS);
//
//                // creating new HashMap
//                HashMap<String, String> map = new HashMap<String, String>();
//
//                // adding each child node to HashMap key => value
//                map.put(TAG_PID, id);
//                map.put(TAG_NAME, name);
//                map.put(TAG_DATE, date);
//                map.put(TAG_STATUS, status);
//
//                // adding HashList to ArrayList
//                mainList.add(map);
//            }
//
//            /**
//             * Updating parsed JSON data into ListView
//             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    clientFragment.this, mainList,
//                    R.layout.list_main, new String[] { TAG_PID,
//                    TAG_DATE, TAG_STATUS},
//                    new int[] { R.id.pid, R.id.name, R.id.status });
//            // updating listview
//            lvUpdate.setAdapter(adapter);
//
//            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//
//        }
//
//
//    }
}