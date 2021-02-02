package mdad.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class backendService extends AppCompatActivity {

    private Button btnService;
    private static final String TAGBTN ="BUTTON PRESSED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend_service);

        btnService = (Button) findViewById(R.id.btnService);

        btnService.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v) {
                        Log.i(TAGBTN,"SERVICE HAS BEEN STARTEDDDD");

                        /**
                         * Shows that it Service just started so unable to send data first. Only when X is more than previous session then can say that send data lorh
                         */

                        int x=0;

                        SharedPreferences myPrefs = getSharedPreferences("SERVICE_START",0);
                        SharedPreferences.Editor myEditor = myPrefs.edit();
                        myEditor.putInt("SERVICE_START",x);
                        myEditor.commit();
                        startService(new Intent(backendService.this, accelerometerService.class));
                    }
                }
        );


    }
    
}