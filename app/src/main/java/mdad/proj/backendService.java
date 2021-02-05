package mdad.proj;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class backendService extends AppCompatActivity {

    String client_userName;
    private Button btnService, btnHelp, btnLogout;
    private TextView txtUpdate;
    private static final String url_getNumber= MainActivity.ipBaseAddress+"get_mobileJson.php";
    private static final String TAGBTN ="BUTTON PRESSED";
    private static final String TAG_SUCCESS = "success";
    private static final String PHONE_NO= "phone_no";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend_service);


        txtUpdate = (TextView)findViewById(R.id.txtUpdate);
        btnService = (Button) findViewById(R.id.btnService);
        btnHelp    = (Button) findViewById(R.id.btnHelp);

        btnService.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        JSONObject dataJson = new JSONObject();
                        SharedPreferences myPrefs = getSharedPreferences("USER_DETAILS",0);
                        client_userName= myPrefs.getString("USERNAME","No name set");
                        try{
                            dataJson.put("username",client_userName);
                        }catch(JSONException e){

                        }

                        postData(url_getNumber,dataJson,1 );
                        //CALL DATA TO DO JSON posting for Heroku Fire
//                        Log.i(TAGBTN,"SERVICE HAS BEEN STARTEDDDD");
//                        int x=0;
//                        SharedPreferences myPrefs = getSharedPreferences("SERVICE_START",0);
//                        SharedPreferences.Editor myEditor = myPrefs.edit();
//                        myEditor.putInt("SERVICE_START",x);
//                        myEditor.commit();
//                        startService(new Intent(backendService.this, accelerometerService.class));
//                        txtUpdate.setText("Service has started successfully");


                    }
                }
        );

        btnHelp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i(TAGBTN,"Stopping Service");
               txtUpdate.setText("Help has been called");
             }
            }
        );
        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener()
        {
         @Override
         public void onClick(View v) {
             SharedPreferences sharedPreferences = getSharedPreferences(LogIn.Pref, Context.MODE_PRIVATE);
             SharedPreferences.Editor editor = sharedPreferences.edit();
             editor.clear();
             editor.apply();

             finish();
             Intent i = new Intent(backendService.this, LogIn.class);
             startActivity(i);
         }
        }
        );

    }
    @Override
    public void onBackPressed(){
            //super.onBackPressed();
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
    }

    public void postData(String url, final JSONObject json, final int option){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest json_obj_req = new JsonObjectRequest(
                Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                switch (option){
                    case 1:checkResponseNumber(response); break;

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


    public void checkResponseNumber(JSONObject response)
    {
        Log.i("----Response", response+" "+url_getNumber);
        try {
            if(response.getInt(TAG_SUCCESS)==1){

                //get CLIENT OR ADMIN STRING
                String admin_phone= response.getString(PHONE_NO);
                if(admin_phone!=null){
                       int x=0;
                        SharedPreferences myPrefs = getSharedPreferences("USER_DETAILS",0);
                        SharedPreferences.Editor myEditor = myPrefs.edit();
                        myEditor.putInt("SERVICE_START",x);
                        myEditor.putString("PHONE_NO",admin_phone);
                        myEditor.commit();
                        startService(new Intent(backendService.this, accelerometerService.class));
                        txtUpdate.setText("Service has started successfully");
                        Log.i("PHONE NUMBER", ""+admin_phone);
                }

            }else{
                Toast.makeText(this, "Unable to get Number", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }
}