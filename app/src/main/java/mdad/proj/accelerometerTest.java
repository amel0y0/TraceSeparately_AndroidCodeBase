package mdad.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class accelerometerTest extends AppCompatActivity implements SensorEventListener {

    private static final String TAG="TESTING";

    private SensorManager sensorManager;
    Sensor accelerometer;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_test);

        Log.d(TAG,"onCreate:Initialzing Sensor Services");

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(accelerometerTest.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG,"onCreate: Registered acceleromter listener");

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG,"onSensorChanged :X" +event.values[0] + "Y:" + event.values[1] + "Z:" +event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}