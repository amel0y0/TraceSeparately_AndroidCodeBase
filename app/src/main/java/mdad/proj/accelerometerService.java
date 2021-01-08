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

public class accelerometerService extends Service implements SensorEventListener {

    //private final static int INTERVAL = 1000 * 60 * 180 //3 hours

    Handler handler = new Handler();
    Runnable runnable;
    int delay =10000;
    //int delay2=1000;
    private static final String TAG = "ACCELEROMETER SERVICE";

    private SensorManager sensorManager;
    Sensor accelerometer;

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
                handler.postDelayed(runnable, delay);
                Log.i(TAG,"onSensorChanged X:" + x + "   Y:" + y + "   Z:" +z);
                Toast.makeText(accelerometerService.this, "X:" + x + "Y:" + y + "Z:" +z, Toast.LENGTH_SHORT).show();
            }
        }, delay);
        return START_STICKY;

    }


}