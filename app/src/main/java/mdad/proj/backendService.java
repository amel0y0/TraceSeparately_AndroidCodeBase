package mdad.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                        startService(new Intent(backendService.this, accelerometerService.class));
                    }
                }
        );


    }
    
}