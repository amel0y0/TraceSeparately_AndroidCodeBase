package mdad.proj;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class accelerometerService extends Service implements SensorEventListener {

    //private final static int INTERVAL = 1000 * 60 * 180 //3 hours

    Handler handler = new Handler();
    Runnable runnable;
    int delay =10000;
    //int delay2=1000;
    private static final String TAG = "ACCELEROMETER SERVICE";
    private static final String  TIME_TAG= "TIME OF CURRENT FORMAT";
    private SensorManager sensorManager;
    Sensor accelerometer;

    private static final String  url_CHECK= MainActivity.ipBaseAddress+"CHECK.php";
    private static final String  url_SEND= MainActivity.ipBaseAddress+"SEND.php";
    public float x,y,z;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x=event.values[0];
        y=event.values[1];
        z=event.values[2];
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
                handler.postDelayed(runnable, delay);
                Log.i(TAG,"onSensorChanged X:" + x + "   Y:" + y + "   Z:" +z);
                Date currentTime = Calendar.getInstance().getTime();
                Log.i(TIME_TAG,"Current Time of Sending:" +currentTime);
                Toast.makeText(accelerometerService.this, "X:" + x + "Y:" + y + "Z:" +z, Toast.LENGTH_SHORT).show();
            }
        }, delay);
        return START_STICKY;

    }


}