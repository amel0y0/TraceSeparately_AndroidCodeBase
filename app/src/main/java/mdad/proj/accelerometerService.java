package mdad.proj;

import android.app.Service;
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
    int delay = 10000;
    private static final String TAG = "MyActivity";

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                Log.i(TAG,"LOG CAT 10 SECONDS");
                Toast.makeText(accelerometerService.this, "This method is run every 2 seconds",
                        Toast.LENGTH_SHORT).show();
            }
        }, delay);
        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}