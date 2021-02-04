package mdad.proj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class backendService extends AppCompatActivity {

    private Button btnService, btnHelp, btnLogout;
    private TextView txtUpdate;
    private static final String TAGBTN ="BUTTON PRESSED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend_service);

        txtUpdate = (TextView)findViewById(R.id.txtUpdate);

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
                        txtUpdate.setText("Service has started successfully");
                    }
                }
        );
        btnHelp = (Button) findViewById(R.id.btnHelp);

        btnHelp.setOnClickListener(new View.OnClickListener()
                                      {

                                          @Override
                                          public void onClick(View v) {
                                              Log.i(TAGBTN,"Help has been called");
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
    
}