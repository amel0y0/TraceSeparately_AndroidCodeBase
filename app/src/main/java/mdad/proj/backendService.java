package mdad.proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class backendService extends AppCompatActivity {

    private Button btnService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend_service);

        btnService = (Button) findViewById(R.id.btnService);

        btnService.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v) {
                        startService(new Intent(backendService.this, accelerometerService.class));
                    }
                }
        );


    }
    
}