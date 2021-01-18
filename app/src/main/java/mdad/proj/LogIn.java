package mdad.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {
    private EditText mPassword,mUserName;
    private Button btnSignIn;
    SharedPreferences sharedPreferences;

    private String client_admin;
    public String  X = "john";
    public String Y = "password";
    public static final String Pref="JOHNNY";
    public static final String Name = "nameKey";

    private static final String url_login = MainActivity.ipBaseAddress+"LoginJ.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String CLIENT_ADMIN = "client_admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        mPassword = (EditText) findViewById(R.id.password);
        mUserName = (EditText) findViewById(R.id.username);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        sharedPreferences = getSharedPreferences(Pref, Context.MODE_PRIVATE);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String pw= mPassword.getText().toString();
                String uName= mUserName.getText().toString();
                if(pw.isEmpty())
                {
                    mPassword.setError(getString(R.string.error_field_required));
                }

                if(uName.isEmpty())
                {
                    mUserName.setError(getString(R.string.error_field_required));

                }else
                {
                    JSONObject dataJson = new JSONObject();
                    try{
                        dataJson.put("username", uName);
                        dataJson.put("password", pw);
                    }catch(JSONException e){

                    }

                    postData(url_login,dataJson,1 );
                    //CALL DATA TO DO JSON posting for Heroku Fire

                }
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
                    case 1:checkResponseLogin(response); break;

                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
             String alert_message;
             alert_message = error.toString();
             Log.d("Error", alert_message);
            }

        });
        requestQueue.add(json_obj_req);
    }


    public void checkResponseLogin(JSONObject response)
    {
        Log.i("----Response", response+" "+url_login);
        try {
            if(response.getInt(TAG_SUCCESS)==1){

                //get CLIENT OR ADMIN STRING
                client_admin=response.getString(CLIENT_ADMIN);

                if(client_admin.equals("A")){
                    //Go Admin Side
                    finish();
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                }
                if(client_admin.equals("C")){
                    //go Client Side
                    finish();
                    Intent i = new Intent(this, backendService.class);
                    startActivity(i);
                }

            }else{
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }


}