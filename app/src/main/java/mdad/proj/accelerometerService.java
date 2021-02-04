package mdad.proj;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.math.BigDecimal;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class accelerometerService extends Service implements SensorEventListener {

    //private final static int INTERVAL = 1000 * 60 * 180 //3 hours

    private static final String url_Updates= MainActivity.ipBaseAddress+"sendUpdate.php";
    private static DecimalFormat df = new DecimalFormat("0.00");

    int reportER;
    Handler handler = new Handler();
    Runnable runnable;
    int delay =15000;
    //int delay2=1000;
    private static final String TAG = "ACCELEROMETER SERVICE";
    private static final String  TIME_TAG= "TIME OF CURRENT FORMAT";
    private SensorManager sensorManager;
    Sensor accelerometer;

    private static final String  url_SEND= MainActivity.ipBaseAddress+"SEND.php";

    // JSON Node names
    private static final String TAG_USER_ID= "success";
    private static final String TAG_LAST_UPDATE= "last_update";
    private static final String TAG_STATUS= "status";

    public float x,y,z;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x=round(event.values[0]);
        y=round(event.values[1]);
        z=round(event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(accelerometerService.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        handler.postDelayed(runnable = new Runnable() {

            public void run() {
                int x_service, x_service_write;
                SharedPreferences myPrefs = getSharedPreferences("SERVICE_START",0);
                SharedPreferences.Editor myEditor = myPrefs.edit();
                x_service= myPrefs.getInt("SERVICE_START",0);
                handler.postDelayed(runnable, delay); // DELAY


                if(x_service>0)
                {
                    float check1,check2;
                    float x2,y2,z2;
                    Log.i(TAG,"onSensorChanged X:" + x + "   Y:" + y + "   Z:" +z);
                    /**
                     *  POST DATA HERE
                     */

                    x2=myPrefs.getFloat("X_VALUE",0);
                    y2=myPrefs.getFloat("Y_VALUE",0);
                    z2=myPrefs.getFloat("Z_VALUE",0);

                    check1=x+y+z;
                    check2=x2+y2+z2;

                    if(check1!=check2)
                    {
                        myEditor.putFloat("X_VALUE",x);
                        myEditor.putFloat("Y_VALUE",y);
                        myEditor.putFloat("Z_VALUE",z);
                        myEditor.commit();

                        if(reportER>0)
                            reportER--;

                        sendData(1);

                        Log.i("CHECK_CHECK","CHECK 1 and 2 ARE NOT THE SAME ");
                    }
                    else {
                        sendToReport(reportER++);
                        Log.i("CHECK CHECK SAME", "CURRENT DATA:"+reportER);
                    }

                }
                else if(x_service == 0)
                {
                     x_service_write=x_service+1;
                    Log.i("ADDED DATA", "SESSION SERVICE POSITION:" +x_service_write);

                    myEditor.putInt("SERVICE_START",x_service_write);
                    myEditor.putFloat("X_VALUE",x);
                    myEditor.putFloat("Y_VALUE",y);
                    myEditor.putFloat("Z_VALUE",z);
                    myEditor.commit();
                }
                /**
                 *     CALL FOR PHP SESSION and CHECK WITH IF ELSE STATEMENT IF WHETHER OR NOT SHOULD SEND TO DATABASE OR NOT.
                 *     IF(x>x+1);
                 *     then send Data
                 *
                 *     Compare with last session whether or not same, if not same then ACtIVE=1;
                 *     else If ACTIVE =0 FROM SESSION; then Report Via twillio
                 *
                 *     WE WILL BE USING 2 PHP FILES FOR THE SESSION AND CHANGING ALL.
                 */
            }
        }, delay);
        return START_STICKY;

    }

    public void sendData(int status){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentTime = sdf.format(Calendar.getInstance().getTime());
        Log.i(TIME_TAG,"Current Time of Sending:" +currentTime);


        SharedPreferences myPrefs = getSharedPreferences("USER_ID",0);
        int user_id= myPrefs.getInt("USER_ID",0);
        JSONObject dataJson = new JSONObject();
        try{
            dataJson.put("user_id", user_id);
            dataJson.put("status",status);
            dataJson.put("last_update", currentTime);

        }catch(JSONException e){

        }

        postData(url_Updates,dataJson,1 );
        //CALL DATA TO DO JSON posting for Heroku Fire
    }

    public void sendToReport(int reportER){

        if(reportER>5 )
        {

            Log.i("REPORTER_STATUS","TIME TO MESSAGE THE ADMIN");
            /**
             * Call for the Admin
              */
        }
    }

    public void postData(String url, final JSONObject json, final int option){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest json_obj_req = new JsonObjectRequest(
                Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                switch (option){
                    case 1:Log.i("SENDING","DATA HAS BEEN SENT"); break;

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
    public static int round(float floatValue) {
        String finalValue=String.format("%.0f", floatValue);
         return parseInt(finalValue);
    }

}